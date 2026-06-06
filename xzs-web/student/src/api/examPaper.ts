import request from '../utils/request'

export function getExamPaperPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/student/exam/paper/pageList', data)
}

export function getExamPaperDetail(id: number) {
  return request.get(`/student/exam/paper/select/${id}`)
}
