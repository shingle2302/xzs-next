<template>
  <a-card :bordered="false">
    <h3>发送消息</h3>
    <a-form :model="form" layout="vertical" style="max-width: 600px;">
      <a-form-item label="标题">
        <a-input v-model:value="form.title" />
      </a-form-item>
      <a-form-item label="接收用户">
        <a-select v-model:value="form.receiveUserId" mode="multiple" placeholder="选择接收用户">
          <a-select-option v-for="u in users" :key="u.id" :value="u.id">{{ u.realName || u.userName }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="内容">
        <a-textarea v-model:value="form.content" :rows="6" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="handleSend" :loading="sending">发送</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { sendMessage } from '../../api/message'
import { getStudentPage } from '../../api/user'
import { message as antMessage } from 'ant-design-vue'

const sending = ref(false)
const users = ref<any[]>([])

const form = reactive({
  title: '',
  content: '',
  receiveUserId: [] as number[],
})

onMounted(async () => {
  const res = await getStudentPage({ pageIndex: 1, pageSize: 200 })
  users.value = res.data?.list || []
})

async function handleSend() {
  sending.value = true
  try {
    await sendMessage({
      title: form.title,
      content: form.content,
      receiveUserId: form.receiveUserId.join(','),
    })
    antMessage.success('发送成功')
    form.title = ''
    form.content = ''
    form.receiveUserId = []
  } finally {
    sending.value = false
  }
}
</script>
