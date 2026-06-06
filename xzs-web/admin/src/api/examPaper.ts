import request from '../utils/request'

export function getPaperPage(data: { pageIndex: number; pageSize: number; subjectId?: number }) {
  return request.post('/admin/exam/paper/page', data)
}

export function getPaperDetail(id: number) {
  return request.get(`/admin/exam/paper/${id}`)
}

export function savePaper(data: any) {
  return request.post('/admin/exam/paper/save', data)
}

export function deletePaper(id: number) {
  return request.delete(`/admin/exam/paper/${id}`)
}
