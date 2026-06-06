<template>
  <a-card :bordered="false">
    <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange" />
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { formatDate } from '../../utils'
import request from '../../utils/request'

const list = ref<any[]>([])
const loading = ref(false)

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '用户', dataIndex: 'userName', key: 'userName', width: 120 },
  { title: '操作', dataIndex: 'content', key: 'content', ellipsis: true },
  { title: '时间', dataIndex: 'createTime', key: 'createTime', width: 180, customRender: ({ text }: any) => formatDate(text) },
]

onMounted(() => loadData())

async function loadData() {
  loading.value = true
  try {
    const res = await request.post('/admin/log/page', { pageIndex: pagination.current, pageSize: pagination.pageSize })
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
