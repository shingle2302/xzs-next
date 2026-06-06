<template>
  <div>
    <a-form layout="inline" style="margin-bottom: 16px;">
      <a-form-item>
        <a-input v-model:value="filters.keyword" placeholder="试卷名称" />
      </a-form-item>
      <a-form-item>
        <a-select v-model:value="filters.paperType" placeholder="试卷类型" style="width: 140px" allow-clear>
          <a-select-option v-for="[k, v] in Object.entries(paperTypeMap)" :key="k" :value="Number(k)">{{ v }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="loadData">查询</a-button>
      </a-form-item>
    </a-form>
    <a-table :data-source="list" :columns="columns" :pagination="pagination" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'paperType'">{{ paperTypeMap[record.paperType] || record.paperType }}</template>
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="startExam(record.id)">开始考试</a-button>
        </template>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getExamPaperPage } from '../../api/examPaper'
import { paperTypeMap } from '../../utils'

const router = useRouter()
const list = ref<any[]>([])
const total = ref(0)
const filters = reactive({ keyword: '', paperType: undefined as number | undefined })
const pagination = reactive({ current: 1, pageSize: 10, total: 0, showSizeChanger: true })

const columns = [
  { title: '名称', dataIndex: 'name', key: 'name' },
  { title: '类型', dataIndex: 'paperType', key: 'paperType', width: 100 },
  { title: '题目数', dataIndex: 'questionCount', key: 'questionCount', width: 80 },
  { title: '总分', dataIndex: 'score', key: 'score', width: 80 },
  { title: '时长(分钟)', dataIndex: 'suggestTime', key: 'suggestTime', width: 100 },
  { title: '操作', key: 'action', width: 120 },
]

onMounted(loadData)

async function loadData() {
  try {
    const res: any = await getExamPaperPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
    list.value = res?.list || []
    total.value = res?.total || 0
    pagination.total = total.value
  } catch { /* */ }
}

function onTableChange(p: any) {
  pagination.current = p.current
  pagination.pageSize = p.pageSize
  loadData()
}

function startExam(id: number) {
  router.push(`/exam/do/${id}`)
}
</script>
