import request from '../utils/request'

export function getMessagePage(data: { pageIndex: number; pageSize: number }) {
  return request.post('/admin/message/page', data)
}

export function sendMessage(data: { title: string; content: string; receiveUserId: string }) {
  return request.post('/admin/message/send', data)
}
