<template>
  <div class="login-container">
    <div class="login-card">
      <h2 style="text-align: center; margin-bottom: 24px;">注册账号</h2>
      <a-form :model="form" :rules="rules" ref="formRef" @finish="handleRegister">
        <a-form-item name="userName">
          <a-input v-model:value="form.userName" placeholder="用户名" size="large">
            <template #prefix><UserOutlined /></template>
          </a-input>
        </a-form-item>
        <a-form-item name="password">
          <a-input-password v-model:value="form.password" placeholder="密码" size="large">
            <template #prefix><LockOutlined /></template>
          </a-input-password>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit" block size="large" :loading="loading">注 册</a-button>
        </a-form-item>
        <div style="text-align: center;">
          已有账号？<router-link to="/login">去登录</router-link>
        </div>
      </a-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { register } from '../../api/login'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'

const router = useRouter()
const formRef = ref()
const loading = ref(false)

const form = reactive({
  userName: '',
  password: '',
})

const rules = {
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
}

async function handleRegister() {
  loading.value = true
  try {
    await register(form)
    message.success('注册成功，请登录')
    router.push('/login')
  } catch { /* handled by interceptor */ }
  finally { loading.value = false }
}
</script>

<style scoped>
.login-container {
  display: flex; align-items: center; justify-content: center;
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
.login-card {
  background: #fff; padding: 40px; border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.15); width: 400px;
}
</style>
