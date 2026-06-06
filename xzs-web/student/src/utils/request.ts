import axios from 'axios'
import { message } from 'ant-design-vue'
import type { AxiosResponse } from 'axios'

const service = axios.create({
  baseURL: '/api',
  timeout: 30000,
})

service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error),
)

service.interceptors.response.use(
  (response: AxiosResponse) => {
    const res = response.data
    if (res.code !== undefined && res.code !== 0 && res.code !== 200) {
      message.error(res.message || '请求失败')
      if (res.code === 401) {
        localStorage.removeItem('token')
        localStorage.removeItem('userInfo')
        window.location.href = '#/login'
      }
      return Promise.reject(new Error(res.message || '请求失败'))
    }
    return res.response !== undefined ? res.response : res
  },
  (error) => {
    message.error(error.message || '网络错误')
    return Promise.reject(error)
  },
)

export default service
