import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login as loginApi } from '../api/login'
import type { LoginRequest } from '../api/login'

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref<any>(JSON.parse(localStorage.getItem('userInfo') || 'null'))

  async function login(data: LoginRequest) {
    const res: any = await loginApi(data)
    token.value = res.token
    userInfo.value = res.userInfo
    localStorage.setItem('token', res.token)
    localStorage.setItem('userInfo', JSON.stringify(res.userInfo))
  }

  function logout() {
    token.value = ''
    userInfo.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
  }

  return { token, userInfo, login, logout }
})
