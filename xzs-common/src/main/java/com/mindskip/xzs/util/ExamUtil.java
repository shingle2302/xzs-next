package com.mindskip.xzs.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExamUtil {
    private static final Integer SCORE_DIVIDE = 10;
    private static final String ANSWER_SPLIT = ",";

    public static Integer scoreFromVM(String score) {
        if (score == null) return 0;
        return new BigDecimal(score).multiply(new BigDecimal(SCORE_DIVIDE)).intValue();
    }

    public static String scoreToVM(Integer score) {
        if (score == null) return "0";
        return new BigDecimal(score).divide(new BigDecimal(SCORE_DIVIDE), 1, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String contentToString(List<String> contentArray) {
        if (contentArray == null) return "";
        return contentArray.stream().sorted().collect(Collectors.joining(ANSWER_SPLIT));
    }

    public static List<String> contentToArray(String contentArray) {
        if (contentArray == null || contentArray.isBlank()) return List.of();
        return Arrays.asList(contentArray.split(ANSWER_SPLIT));
    }
}
