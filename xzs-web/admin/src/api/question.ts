import request from '../utils/request'

export function getQuestionPage(data: { pageIndex: number; pageSize: number; subjectId?: number; questionType?: number }) {
  return request.post('/admin/question/page', data)
}

export function getQuestionDetail(id: number) {
  return request.get(`/admin/question/${id}`)
}

export function saveQuestion(data: any) {
  return request.post('/admin/question/save', data)
}

export function deleteQuestion(id: number) {
  return request.delete(`/admin/question/${id}`)
}

export function getQuestionTypeCount() {
  return request.get('/admin/question/type-count')
}
