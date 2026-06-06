import request from '../utils/request'

export function getUserInfo() {
  return request.get('/student/user/current')
}

export function updateUser(data: any) {
  return request.post('/student/user/update', data)
}

export function getMessagePage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/student/user/message/page', data)
}

export function readMessage(id: number) {
  return request.post(`/student/user/message/read/${id}`)
}

export function unreadCount() {
  return request.get('/student/user/message/unread-count')
}
