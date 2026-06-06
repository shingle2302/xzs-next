import request from '../utils/request'

export function getAnswerPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/exam/paper/answer/page', data)
}

export function getAnswerDetail(id: number) {
  return request.get(`/admin/exam/paper/answer/${id}`)
}
