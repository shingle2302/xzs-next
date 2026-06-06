<template>
  <a-card :bordered="false">
    <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <a-tag :color="record.status === 2 ? 'green' : record.status === 1 ? 'blue' : 'orange'">
            {{ statusMap[record.status] || '未知' }}
          </a-tag>
        </template>
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="$router.push(`/exam/paper/answer/${record.id}`)">查看</a-button>
        </template>
      </template>
    </a-table>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { getAnswerPage } from '../../api/examPaperAnswer'
import { formatDate } from '../../utils'

const list = ref<any[]>([])
const loading = ref(false)

const statusMap: Record<number, string> = {
  1: '待批改',
  2: '已批改',
  3: '未完成',
}

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '试卷', dataIndex: 'paperName', key: 'paperName', ellipsis: true },
  { title: '用户', dataIndex: 'userName', key: 'userName', width: 120 },
  { title: '得分', dataIndex: 'userScore', key: 'userScore', width: 80 },
  { title: '状态', dataIndex: 'status', key: 'status', width: 100 },
  { title: '提交时间', dataIndex: 'createTime', key: 'createTime', width: 180, customRender: ({ text }: any) => formatDate(text) },
  { title: '操作', key: 'action', width: 80 },
]

onMounted(() => loadData())

async function loadData() {
  loading.value = true
  try {
    const res = await getAnswerPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
    list.value = res.data?.list || []
    pagination.total = res.data?.total || 0
  } finally {
    loading.value = false
  }
}

function onTableChange(pag: any) {
  pagination.current = pag.current
  pagination.pageSize = pag.pageSize
  loadData()
}
</script>
