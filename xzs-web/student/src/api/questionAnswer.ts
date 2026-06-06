import request from '../utils/request'

export function getWrongAnswerPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/student/question/answer/page', data)
}

export function getWrongAnswerDetail(id: number) {
  return request.get(`/student/question/answer/select/${id}`)
}
