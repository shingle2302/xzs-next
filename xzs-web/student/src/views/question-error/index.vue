<template>
  <div>
    <a-table :data-source="list" :columns="columns" :pagination="pagination" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="$router.push(`/exam/read/${record.id}`)">查看</a-button>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getWrongAnswerPage } from '../../api/questionAnswer'
import { formatDate } from '../../utils'

const list = ref<any[]>([])
const pagination = reactive({ current: 1, pageSize: 10, total: 0 })

const columns = [
  { title: '试卷名称', dataIndex: 'paperName', key: 'paperName' },
  { title: '得分', dataIndex: 'score', key: 'score', width: 80 },
  { title: '提交时间', dataIndex: 'createTime', key: 'createTime', width: 160 },
  { title: '操作', key: 'action', width: 80 },
]

onMounted(loadData)

async function loadData() {
  try {
    const res: any = await getWrongAnswerPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
    list.value = (res?.list || []).map((r: any) => ({ ...r, createTime: formatDate(r.createTime) }))
    pagination.total = res?.total || 0
  } catch { /* */ }
}

function onTableChange(p: any) {
  pagination.current = p.current
  pagination.pageSize = p.pageSize
  loadData()
}
</script>
