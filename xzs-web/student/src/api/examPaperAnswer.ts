import request from '../utils/request'

export function submitAnswer(data: any) {
  return request.post('/student/exam/paper/answer/do', data)
}

export function getAnswerRead(id: number) {
  return request.get(`/student/exam/paper/answer/read/${id}`)
}

export function getAnswerPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/student/exam/paper/answer/page', data)
}

export function getAnswerDetail(id: number) {
  return request.get(`/student/exam/paper/answer/select/${id}`)
}
