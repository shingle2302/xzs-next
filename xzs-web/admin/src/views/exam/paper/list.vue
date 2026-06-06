<template>
  <a-card :bordered="false">
    <a-form layout="inline" style="margin-bottom: 16px;">
      <a-form-item label="学科">
        <a-select v-model:value="filters.subjectId" placeholder="选择学科" style="width: 150px" allow-clear>
          <a-select-option v-for="s in subjects" :key="s.id" :value="s.id">{{ s.name }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="loadData">查询</a-button>
        <a-button type="primary" style="margin-left: 8px;" @click="$router.push('/exam/paper/edit')">新增试卷</a-button>
      </a-form-item>
    </a-form>
    <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'paperType'">
          {{ paperTypeMap[record.paperType] || '未知' }}
        </template>
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="$router.push(`/exam/paper/edit/${record.id}`)">编辑</a-button>
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
import { getPaperPage, deletePaper as deletePaperApi } from '../../../api/examPaper'
import { getSubjectList } from '../../../api/subject'
import { message } from 'ant-design-vue'

const list = ref<any[]>([])
const loading = ref(false)
const subjects = ref<any[]>([])

const paperTypeMap: Record<number, string> = {
  1: '固定试卷',
  2: '随机试卷',
  3: '时段试卷',
  4: '任务试卷',
}

const filters = reactive({
  subjectId: undefined as number | undefined,
})

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '名称', dataIndex: 'name', key: 'name', ellipsis: true },
  { title: '类型', dataIndex: 'paperType', key: 'paperType', width: 100 },
  { title: '学科', dataIndex: 'subjectName', key: 'subjectName', width: 120 },
  { title: '总分', dataIndex: 'score', key: 'score', width: 80 },
  { title: '题目数', dataIndex: 'questionCount', key: 'questionCount', width: 80 },
  { title: '操作', key: 'action', width: 120 },
]

onMounted(async () => {
  const res = await getSubjectList()
  subjects.value = res.data || []
  loadData()
})

async function loadData() {
  loading.value = true
  try {
    const res = await getPaperPage({
      pageIndex: pagination.current,
      pageSize: pagination.pageSize,
      subjectId: filters.subjectId,
    })
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
  await deletePaperApi(id)
  message.success('删除成功')
  loadData()
}
</script>
