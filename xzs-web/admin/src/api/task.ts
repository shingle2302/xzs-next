import request from '../utils/request'

export function getTaskPage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/task/page', data)
}

export function getTaskDetail(id: number) {
  return request.get(`/admin/task/${id}`)
}

export function saveTask(data: any) {
  return request.post('/admin/task/save', data)
}

export function deleteTask(id: number) {
  return request.delete(`/admin/task/${id}`)
}
