import request from '../utils/request'

export function getSubjectList() {
  return request.get('/admin/education/subject/list')
}

export function getSubjectPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/education/subject/page', data)
}

export function saveSubject(data: { name: string; level: number }) {
  return request.post('/admin/education/subject/save', data)
}

export function deleteSubject(id: number) {
  return request.delete(`/admin/education/subject/${id}`)
}
