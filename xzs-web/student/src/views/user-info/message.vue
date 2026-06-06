<template>
  <div>
    <a-table :data-source="list" :columns="columns" :pagination="pagination" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'readed'">
          <a-tag :color="record.readed ? 'default' : 'blue'">{{ record.readed ? '已读' : '未读' }}</a-tag>
        </template>
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="showDetail(record)">查看</a-button>
        </template>
      </template>
    </a-table>
    <a-modal v-model:open="detailVisible" title="消息详情" footer>
      <h4>{{ currentMsg?.title }}</h4>
      <p>{{ currentMsg?.content }}</p>
      <p style="color: #999; font-size: 12px;">{{ currentMsg?.createTime }}</p>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getMessagePage, readMessage } from '../../api/user'
import { formatDate } from '../../utils'
import { message } from 'ant-design-vue'

const list = ref<any[]>([])
const pagination = reactive({ current: 1, pageSize: 10, total: 0 })
const detailVisible = ref(false)
const currentMsg = ref<any>(null)

const columns = [
  { title: '标题', dataIndex: 'title', key: 'title' },
  { title: '发送人', dataIndex: 'sendUserName', key: 'sendUserName', width: 120 },
  { title: '状态', dataIndex: 'readed', key: 'readed', width: 80 },
  { title: '时间', dataIndex: 'createTime', key: 'createTime', width: 160 },
  { title: '操作', key: 'action', width: 80 },
]

onMounted(loadData)

async function loadData() {
  try {
    const res: any = await getMessagePage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
    list.value = (res?.list || []).map((r: any) => ({ ...r, createTime: formatDate(r.createTime) }))
    pagination.total = res?.total || 0
  } catch { /* */ }
}

function onTableChange(p: any) {
  pagination.current = p.current
  pagination.pageSize = p.pageSize
  loadData()
}

async function showDetail(record: any) {
  currentMsg.value = record
  detailVisible.value = true
  if (!record.readed) {
    try {
      await readMessage(record.id)
      record.readed = true
    } catch { /* */ }
  }
}
</script>
