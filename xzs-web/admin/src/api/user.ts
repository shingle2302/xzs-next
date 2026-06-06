import request from '../utils/request'

export function getStudentPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/user/student/page', data)
}

export function getAdminPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/user/admin/page', data)
}

export function createUser(data: any) {
  return request.post('/admin/user/create', data)
}

export function updateUser(data: any) {
  return request.post('/admin/user/update', data)
}

export function getUserById(id: number) {
  return request.get(`/admin/user/${id}`)
}

export function deleteUser(id: number) {
  return request.delete(`/admin/user/${id}`)
}
