<template>
  <a-card :bordered="false">
    <a-form layout="inline" style="margin-bottom: 16px;">
      <a-form-item>
        <a-button type="primary" @click="$router.push('/task/edit')">新增任务</a-button>
      </a-form-item>
    </a-form>
    <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="$router.push(`/task/edit/${record.id}`)">编辑</a-button>
          <a-popconfirm title="确定删除?" @confirm="handleDelete(record.id)">
            <a-button type="link" danger>删除</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { getTaskPage, deleteTask } from '../../api/task'
import { message } from 'ant-design-vue'

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
  { title: '标题', dataIndex: 'title', key: 'title' },
  { title: '年级', dataIndex: 'gradeLevel', key: 'gradeLevel', width: 80 },
  { title: '操作', key: 'action', width: 120 },
]

onMounted(() => loadData())

async function loadData() {
  loading.value = true
  try {
    const res = await getTaskPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
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

async function handleDelete(id: number) {
  await deleteTask(id)
  message.success('删除成功')
  loadData()
}
</script>
