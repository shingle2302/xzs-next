<template>
  <a-row :gutter="16">
    <a-col :span="8">
      <a-card title="个人信息" :bordered="false">
        <a-descriptions :column="1">
          <a-descriptions-item label="用户名">{{ userStore.userInfo?.userName }}</a-descriptions-item>
          <a-descriptions-item label="姓名">{{ userStore.userInfo?.realName }}</a-descriptions-item>
          <a-descriptions-item label="角色">{{ userStore.userInfo?.role }}</a-descriptions-item>
          <a-descriptions-item label="手机号">{{ userStore.userInfo?.phone }}</a-descriptions-item>
        </a-descriptions>
      </a-card>
    </a-col>
    <a-col :span="16">
      <a-card title="修改信息" :bordered="false">
        <a-form :model="editForm" layout="vertical" style="max-width: 400px;">
          <a-form-item label="姓名">
            <a-input v-model:value="editForm.realName" />
          </a-form-item>
          <a-form-item label="手机号">
            <a-input v-model:value="editForm.phone" />
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleUpdate" :loading="updating">保存修改</a-button>
          </a-form-item>
        </a-form>
      </a-card>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useUserStore } from '../../stores/user'
import { message } from 'ant-design-vue'

const userStore = useUserStore()
const updating = ref(false)

const editForm = reactive({
  realName: userStore.userInfo?.realName || '',
  phone: userStore.userInfo?.phone || '',
})

async function handleUpdate() {
  updating.value = true
  try {
    await (await import('../../api/user')).updateUser({
      id: userStore.userInfo?.id,
      realName: editForm.realName,
      phone: editForm.phone,
    })
    message.success('更新成功')
    await userStore.fetchUserInfo()
  } finally {
    updating.value = false
  }
}
</script>
