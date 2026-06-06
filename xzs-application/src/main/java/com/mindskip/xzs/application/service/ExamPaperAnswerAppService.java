package com.mindskip.xzs.application.service;

import com.mindskip.xzs.domain.aggregate.exam.*;
import com.mindskip.xzs.domain.aggregate.message.TextContent;
import com.mindskip.xzs.domain.aggregate.question.Question;
import com.mindskip.xzs.domain.aggregate.task.TaskExamCustomerAnswer;
import com.mindskip.xzs.domain.aggregate.task.TaskItemAnswerObject;
import com.mindskip.xzs.domain.event.ExamSubmittedEvent;
import com.mindskip.xzs.domain.gateway.*;
import com.mindskip.xzs.enums.AnswerStatusEnum;
import com.mindskip.xzs.enums.ExamPaperTypeEnum;
import com.mindskip.xzs.enums.QuestionTypeEnum;
import com.mindskip.xzs.util.JsonUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamPaperAnswerAppService {
    private final ExamPaperAnswerGateway answerGateway;
    private final ExamPaperGateway examPaperGateway;
    private final ExamPaperQuestionCustomerAnswerGateway customerAnswerGateway;
    private final QuestionGateway questionGateway;
    private final TextContentGateway textContentGateway;
    private final TaskExamCustomerAnswerGateway taskExamCustomerAnswerGateway;
    private final ApplicationEventPublisher eventPublisher;

    public ExamPaperAnswerAppService(ExamPaperAnswerGateway answerGateway,
                                     ExamPaperGateway examPaperGateway,
                                     ExamPaperQuestionCustomerAnswerGateway customerAnswerGateway,
                                     QuestionGateway questionGateway,
                                     TextContentGateway textContentGateway,
                                     TaskExamCustomerAnswerGateway taskExamCustomerAnswerGateway,
                                     ApplicationEventPublisher eventPublisher) {
        this.answerGateway = answerGateway;
        this.examPaperGateway = examPaperGateway;
        this.customerAnswerGateway = customerAnswerGateway;
        this.questionGateway = questionGateway;
        this.textContentGateway = textContentGateway;
        this.taskExamCustomerAnswerGateway = taskExamCustomerAnswerGateway;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ExamPaperAnswer submit(Integer examPaperId, Integer userId, Integer doTime,
                                  List<ExamAnswerSubmitItem> answerItems) {
        ExamPaper examPaper = examPaperGateway.findById(examPaperId);
        if (examPaper == null) throw new RuntimeException("试卷不存在");

        if (ExamPaperTypeEnum.fromCode(examPaper.getPaperType()) == ExamPaperTypeEnum.Task) {
            ExamPaperAnswer existing = answerGateway.findByPaperAndUser(examPaperId, userId);
            if (existing != null) throw new RuntimeException("任务试卷只能做一次");
        }

        List<Question> questions = loadQuestions(examPaper);

        List<ExamPaperQuestionCustomerAnswer> customerAnswers = buildCustomerAnswers(
                examPaper, questions, answerItems, userId);

        int systemScore = customerAnswers.stream()
                .filter(a -> a.getDoRight() != null && a.getDoRight())
                .mapToInt(a -> a.getQuestionScore() != null ? a.getQuestionScore() : 0)
                .sum();
        long questionCorrect = customerAnswers.stream()
                .filter(a -> a.getDoRight() != null && a.getDoRight())
                .count();
        boolean needJudge = customerAnswers.stream()
                .anyMatch(a -> QuestionTypeEnum.needSaveTextContent(a.getQuestionType()));

        ExamPaperAnswer answer = new ExamPaperAnswer();
        answer.setExamPaperId(examPaperId);
        answer.setUserId(userId);
        answer.setPaperName(examPaper.getName());
        answer.setPaperType(examPaper.getPaperType());
        answer.setSubjectId(examPaper.getSubjectId());
        answer.setPaperScore(examPaper.getScore());
        answer.setQuestionCount(examPaper.getQuestionCount());
        answer.setSystemScore(systemScore);
        answer.setUserScore(systemScore);
        answer.setQuestionCorrect((int) questionCorrect);
        answer.setDoTime(doTime);
        answer.setTaskExamId(examPaper.getTaskExamId());
        answer.setStatus(needJudge ? AnswerStatusEnum.WAIT_JUDGE.getCode() : AnswerStatusEnum.COMPLETE.getCode());
        answer.setCreateTime(LocalDateTime.now());

        answerGateway.save(answer);

        for (ExamPaperQuestionCustomerAnswer ca : customerAnswers) {
            ca.setExamPaperAnswerId(answer.getId());
        }
        customerAnswerGateway.saveBatch(customerAnswers);

        eventPublisher.publishEvent(new ExamSubmittedEvent(answer.getId(), examPaperId, userId));

        return answer;
    }

    @Transactional
    public String judge(Integer answerId, List<ExamAnswerJudgeItem> judgeItems) {
        ExamPaperAnswer examPaperAnswer = answerGateway.findById(answerId);
        if (examPaperAnswer == null) throw new RuntimeException("答卷不存在");

        List<ExamPaperQuestionCustomerAnswer> customerAnswers = customerAnswerGateway.findByAnswerId(answerId);
        Map<Integer, ExamPaperQuestionCustomerAnswer> answerMap = customerAnswers.stream()
                .collect(Collectors.toMap(ExamPaperQuestionCustomerAnswer::getId, a -> a));

        int userScore = examPaperAnswer.getUserScore() != null ? examPaperAnswer.getUserScore() : 0;
        int questionCorrect = examPaperAnswer.getQuestionCorrect() != null ? examPaperAnswer.getQuestionCorrect() : 0;

        for (ExamAnswerJudgeItem item : judgeItems) {
            ExamPaperQuestionCustomerAnswer ca = answerMap.get(item.getId());
            if (ca == null) continue;
            ca.judge(item.getCustomerScore());
            customerAnswerGateway.update(ca);
            userScore += ca.getScore();
            if (ca.getDoRight()) {
                questionCorrect++;
            }
        }

        examPaperAnswer.setUserScore(userScore);
        examPaperAnswer.setQuestionCorrect(questionCorrect);
        examPaperAnswer.setStatus(AnswerStatusEnum.COMPLETE.getCode());
        answerGateway.update(examPaperAnswer);

        if (ExamPaperTypeEnum.fromCode(examPaperAnswer.getPaperType()) == ExamPaperTypeEnum.Task) {
            updateTaskExamAnswer(examPaperAnswer);
        }

        return com.mindskip.xzs.util.ExamUtil.scoreToVM(userScore);
    }

    private void updateTaskExamAnswer(ExamPaperAnswer answer) {
        ExamPaper examPaper = examPaperGateway.findById(answer.getExamPaperId());
        if (examPaper == null || examPaper.getTaskExamId() == null) return;

        TaskExamCustomerAnswer taskAnswer = taskExamCustomerAnswerGateway.findByTaskAndUser(
                examPaper.getTaskExamId(), answer.getUserId());

        if (taskAnswer == null || taskAnswer.getTextContentId() == null) return;

        TextContent textContent = textContentGateway.findById(taskAnswer.getTextContentId());
        if (textContent == null || textContent.getContent() == null) return;

        List<TaskItemAnswerObject> items = JsonUtil.toJsonListObject(textContent.getContent(), TaskItemAnswerObject.class);
        for (TaskItemAnswerObject item : items) {
            if (item.getExamPaperAnswerId() != null && item.getExamPaperAnswerId().equals(answer.getId())) {
                item.setStatus(answer.getStatus());
                break;
            }
        }
        textContent.setContent(JsonUtil.toJsonStr(items));
        textContentGateway.update(textContent);
    }

    public ExamPaperAnswer findById(Integer id) {
        return answerGateway.findById(id);
    }

    public List<ExamPaperAnswer> findByUserId(Integer userId) {
        return answerGateway.findByUserId(userId);
    }

    public List<ExamPaperAnswer> findPage(Integer pageIndex, Integer pageSize, Integer userId, Integer subjectId) {
        return answerGateway.findPage(pageIndex, pageSize, userId, subjectId);
    }

    public long count(Integer userId, Integer subjectId) {
        return answerGateway.count(userId, subjectId);
    }

    public long countAll() {
        return answerGateway.count(null, null);
    }

    private List<Question> loadQuestions(ExamPaper examPaper) {
        if (examPaper.getFrameTextContentId() == null) return List.of();
        TextContent textContent = textContentGateway.findById(examPaper.getFrameTextContentId());
        if (textContent == null || textContent.getContent() == null) return List.of();

        List<ExamPaperTitleItemObject> titleItems = JsonUtil.toJsonListObject(
                textContent.getContent(), ExamPaperTitleItemObject.class);
        List<Integer> questionIds = titleItems.stream()
                .flatMap(t -> t.getQuestionItems().stream())
                .map(ExamPaperQuestionItemObject::getId)
                .collect(Collectors.toList());
        return questionGateway.findByIds(questionIds);
    }

    private List<ExamPaperQuestionCustomerAnswer> buildCustomerAnswers(
            ExamPaper examPaper, List<Question> questions,
            List<ExamAnswerSubmitItem> answerItems, Integer userId) {
        if (examPaper.getFrameTextContentId() == null) return List.of();

        TextContent textContent = textContentGateway.findById(examPaper.getFrameTextContentId());
        if (textContent == null || textContent.getContent() == null) return List.of();

        List<ExamPaperTitleItemObject> titleItems = JsonUtil.toJsonListObject(
                textContent.getContent(), ExamPaperTitleItemObject.class);
        Map<Integer, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));
        Map<Integer, ExamAnswerSubmitItem> answerItemMap = answerItems != null ?
                answerItems.stream().collect(Collectors.toMap(ExamAnswerSubmitItem::getQuestionId, a -> a)) :
                Map.of();

        List<ExamPaperQuestionCustomerAnswer> result = new ArrayList<>();
        for (ExamPaperTitleItemObject title : titleItems) {
            for (ExamPaperQuestionItemObject qItem : title.getQuestionItems()) {
                Question question = questionMap.get(qItem.getId());
                if (question == null) continue;

                ExamPaperQuestionCustomerAnswer ca = new ExamPaperQuestionCustomerAnswer();
                ca.setQuestionId(question.getId());
                ca.setExamPaperId(examPaper.getId());
                ca.setSubjectId(examPaper.getSubjectId());
                ca.setUserId(userId);
                ca.setQuestionType(question.getQuestionType());
                ca.setQuestionScore(question.getScore());
                ca.setQuestionTextContentId(String.valueOf(question.getInfoTextContentId()));
                ca.setItemOrder(qItem.getItemOrder());
                ca.setCreateTime(LocalDateTime.now());

                ExamAnswerSubmitItem submitItem = answerItemMap.get(question.getId());
                if (submitItem != null) {
                    scoreCustomerAnswer(ca, question, submitItem);
                } else {
                    setDefaultScore(ca);
                }

                result.add(ca);
            }
        }
        return result;
    }

    private void scoreCustomerAnswer(ExamPaperQuestionCustomerAnswer ca, Question question, ExamAnswerSubmitItem submitItem) {
        QuestionTypeEnum type = QuestionTypeEnum.fromCode(ca.getQuestionType());
        if (type == null) {
            setDefaultScore(ca);
            return;
        }

        switch (type) {
            case SingleChoice:
            case TrueFalse: {
                String studentAnswer = submitItem.getContent();
                ca.setAnswer(studentAnswer);
                boolean correct = question.getCorrectAnswer() != null && question.getCorrectAnswer().equals(studentAnswer);
                ca.setDoRight(correct);
                ca.setScore(correct ? question.getScore() : 0);
                break;
            }
            case MultipleChoice: {
                String studentAnswer = com.mindskip.xzs.util.ExamUtil.contentToString(submitItem.getContentArray());
                ca.setAnswer(studentAnswer);
                boolean correct = question.getCorrectAnswer() != null && question.getCorrectAnswer().equals(studentAnswer);
                ca.setDoRight(correct);
                ca.setScore(correct ? question.getScore() : 0);
                break;
            }
            case GapFilling: {
                String answerJson = JsonUtil.toJsonStr(submitItem.getContentArray());
                ca.setAnswer(answerJson);
                ca.setScore(0);
                ca.setDoRight(false);
                break;
            }
            case ShortAnswer: {
                ca.setAnswer(submitItem.getContent());
                ca.setScore(0);
                ca.setDoRight(false);
                break;
            }
        }
    }

    private void setDefaultScore(ExamPaperQuestionCustomerAnswer ca) {
        ca.setScore(0);
        ca.setDoRight(false);
    }

    public static class ExamAnswerSubmitItem {
        private final Integer questionId;
        private final String content;
        private final List<String> contentArray;

        public ExamAnswerSubmitItem(Integer questionId, String content, List<String> contentArray) {
            this.questionId = questionId;
            this.content = content;
            this.contentArray = contentArray;
        }

        public Integer getQuestionId() { return questionId; }
        public String getContent() { return content; }
        public List<String> getContentArray() { return contentArray; }
    }

    public static class ExamAnswerJudgeItem {
        private final Integer id;
        private final Integer customerScore;

        public ExamAnswerJudgeItem(Integer id, Integer customerScore) {
            this.id = id;
            this.customerScore = customerScore;
        }

        public Integer getId() { return id; }
        public Integer getCustomerScore() { return customerScore; }
    }
}
