import request from '../utils/request'

export interface LoginRequest {
  userName: string
  password: string
}

export function login(data: LoginRequest) {
  return request.post('/login', data)
}

export function register(data: LoginRequest) {
  return request.post('/login/register', data)
}

export function getUserInfo() {
  return request.get('/login/user-info')
}

export function logout() {
  return request.post('/logout')
}
