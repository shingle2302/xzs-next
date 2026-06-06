import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login as loginApi, getUserInfo } from '../api/login'
import type { LoginRequest, LoginResponse } from '../api/login'

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref<any>(JSON.parse(localStorage.getItem('userInfo') || 'null'))

  async function login(data: LoginRequest) {
    const res = await loginApi(data)
    const loginRes = res.data as LoginResponse
    token.value = loginRes.token
    userInfo.value = loginRes.user
    localStorage.setItem('token', loginRes.token)
    localStorage.setItem('userInfo', JSON.stringify(loginRes.user))
  }

  async function fetchUserInfo() {
    const res = await getUserInfo()
    userInfo.value = res.data
    localStorage.setItem('userInfo', JSON.stringify(res.data))
  }

  function logout() {
    token.value = ''
    userInfo.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
  }

  return { token, userInfo, login, fetchUserInfo, logout }
})
