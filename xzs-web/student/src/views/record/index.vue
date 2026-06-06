<template>
  <div>
    <a-table :data-source="list" :columns="columns" :pagination="pagination" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'status'">
          <a-tag :color="record.status === 2 ? 'green' : 'orange'">
            {{ record.status === 2 ? '已完成' : '待批改' }}
          </a-tag>
        </template>
        <template v-if="column.key === 'action'">
          <a-button v-if="record.status === 2" type="link" @click="$router.push(`/exam/read/${record.id}`)">查看</a-button>
          <a-button v-else type="link" disabled>阅卷中</a-button>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getAnswerPage } from '../../api/examPaperAnswer'
import { formatDate } from '../../utils'

const list = ref<any[]>([])
const total = ref(0)
const pagination = reactive({ current: 1, pageSize: 10, total: 0 })

const columns = [
  { title: '试卷名称', dataIndex: 'paperName', key: 'paperName' },
  { title: '得分', dataIndex: 'score', key: 'score', width: 80 },
  { title: '总分', dataIndex: 'paperScore', key: 'paperScore', width: 80 },
  { title: '状态', dataIndex: 'status', key: 'status', width: 100 },
  { title: '提交时间', dataIndex: 'createTime', key: 'createTime', width: 160 },
  { title: '操作', key: 'action', width: 80 },
]

onMounted(loadData)

async function loadData() {
  try {
    const res: any = await getAnswerPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
    list.value = (res?.list || []).map((r: any) => ({ ...r, createTime: formatDate(r.createTime) }))
    total.value = res?.total || 0
    pagination.total = total.value
  } catch { /* */ }
}

function onTableChange(p: any) {
  pagination.current = p.current
  pagination.pageSize = p.pageSize
  loadData()
}
</script>
