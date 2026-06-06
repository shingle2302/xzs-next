import request from '../utils/request'

export interface LoginRequest {
  userName: string
  password: string
}

export interface LoginResponse {
  token: string
  user: {
    id: number
    userName: string
    realName: string
    role: string
    sex: number
    age: number
    userLevel: number
    phone: string
  }
}

export function login(data: LoginRequest) {
  return request.post<LoginResponse>('/login', data)
}

export function getUserInfo() {
  return request.get('/student/user/current')
}

export function logout() {
  return request.post('/login', { action: 'logout' })
}
