<template>
  <div>
    <a-card :bordered="false">
      <a-form layout="inline" style="margin-bottom: 16px;">
        <a-form-item label="学科">
          <a-select v-model:value="filters.subjectId" placeholder="选择学科" style="width: 150px" allow-clear>
            <a-select-option v-for="s in subjects" :key="s.id" :value="s.id">{{ s.name }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="题型">
          <a-select v-model:value="filters.questionType" placeholder="选择题型" style="width: 150px" allow-clear>
            <a-select-option v-for="o in questionTypeOptions" :key="o.value" :value="o.value">{{ o.label }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="loadData">查询</a-button>
          <a-button style="margin-left: 8px;" @click="resetFilters">重置</a-button>
          <a-button type="primary" style="margin-left: 8px;" @click="$router.push('/exam/question/edit')">新增题目</a-button>
        </a-form-item>
      </a-form>
      <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange">
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'questionType'">
            {{ questionTypeMap[record.questionType] || '未知' }}
          </template>
          <template v-if="column.key === 'action'">
            <a-button type="link" @click="editQuestion(record.id)">编辑</a-button>
            <a-popconfirm title="确定删除?" @confirm="deleteQuestion(record.id)">
              <a-button type="link" danger>删除</a-button>
            </a-popconfirm>
          </template>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { getQuestionPage, deleteQuestion as deleteQuestionApi } from '../../../api/question'
import { getSubjectList } from '../../../api/subject'
import { questionTypeMap, questionTypeOptions } from '../../../utils'
import { message } from 'ant-design-vue'

const router = useRouter()
const list = ref<any[]>([])
const loading = ref(false)
const subjects = ref<any[]>([])

const filters = reactive({
  subjectId: undefined as number | undefined,
  questionType: undefined as number | undefined,
})

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '标题', dataIndex: 'title', key: 'title', ellipsis: true },
  { title: '题型', dataIndex: 'questionType', key: 'questionType', width: 100 },
  { title: '学科', dataIndex: 'subjectName', key: 'subjectName', width: 120 },
  { title: '难度', dataIndex: 'difficulty', key: 'difficulty', width: 80 },
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
    const res = await getQuestionPage({
      pageIndex: pagination.current,
      pageSize: pagination.pageSize,
      subjectId: filters.subjectId,
      questionType: filters.questionType,
    })
    list.value = res.data?.list || []
    pagination.total = res.data?.total || 0
  } finally {
    loading.value = false
  }
}

function resetFilters() {
  filters.subjectId = undefined
  filters.questionType = undefined
  loadData()
}

function onTableChange(pag: any) {
  pagination.current = pag.current
  pagination.pageSize = pag.pageSize
  loadData()
}

function editQuestion(id: number) {
  router.push(`/exam/question/edit/${id}`)
}

async function deleteQuestion(id: number) {
  await deleteQuestionApi(id)
  message.success('删除成功')
  loadData()
}
</script>
