import request from '../utils/request'

export function getQuestionPage(data: { pageIndex: number; pageSize: number; subjectId?: number }) {
  return request.post('/student/question/page', data)
}

export function getQuestionDetail(id: number) {
  return request.get(`/student/question/select/${id}`)
}
