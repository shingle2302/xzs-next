<template>
  <div style="max-width: 600px;">
    <a-descriptions title="个人信息" bordered>
      <a-descriptions-item label="用户名">{{ info.userName }}</a-descriptions-item>
      <a-descriptions-item label="姓名">{{ info.realName }}</a-descriptions-item>
      <a-descriptions-item label="手机号">{{ info.phone }}</a-descriptions-item>
      <a-descriptions-item label="邮箱">{{ info.email }}</a-descriptions-item>
    </a-descriptions>
    <a-divider />
    <a-button type="primary" @click="editing = true">修改信息</a-button>
    <a-modal v-model:open="editing" title="修改信息" @ok="handleUpdate">
      <a-form :model="form">
        <a-form-item label="姓名">
          <a-input v-model:value="form.realName" />
        </a-form-item>
        <a-form-item label="手机号">
          <a-input v-model:value="form.phone" />
        </a-form-item>
        <a-form-item label="邮箱">
          <a-input v-model:value="form.email" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getUserInfo, updateUser } from '../../api/user'
import { message } from 'ant-design-vue'

const info = ref<any>({})
const editing = ref(false)
const form = reactive({ realName: '', phone: '', email: '' })

onMounted(async () => {
  try {
    const res: any = await getUserInfo()
    info.value = res || {}
    form.realName = info.value.realName || ''
    form.phone = info.value.phone || ''
    form.email = info.value.email || ''
  } catch { /* */ }
})

async function handleUpdate() {
  try {
    await updateUser(form)
    message.success('更新成功')
    editing.value = false
    const res: any = await getUserInfo()
    info.value = res || {}
  } catch { /* */ }
}
</script>
