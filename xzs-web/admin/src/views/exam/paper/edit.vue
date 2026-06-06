<template>
  <a-card :bordered="false">
    <h3>{{ isEdit ? '编辑试卷' : '新增试卷' }}</h3>
    <a-form :model="form" layout="vertical" style="max-width: 1000px;">
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="试卷名称">
            <a-input v-model:value="form.name" placeholder="试卷名称" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="试卷类型">
            <a-select v-model:value="form.paperType" :options="paperTypeOptions" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="学科">
            <a-select v-model:value="form.subjectId" placeholder="选择学科">
              <a-select-option v-for="s in subjects" :key="s.id" :value="s.id">{{ s.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="建议时长(分钟)">
            <a-input-number v-model:value="form.suggestTime" :min="1" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-divider>题目选择</a-divider>
      <a-table :columns="questionColumns" :data-source="selectedQuestions" row-key="id" style="margin-bottom: 16px;">
        <template #bodyCell="{ column, record, index }">
          <template v-if="column.key === 'index'">{{ index + 1 }}</template>
          <template v-if="column.key === 'action'">
            <a-popconfirm title="移除该题目?" @confirm="removeQuestion(index)">
              <a-button type="link" danger>移除</a-button>
            </a-popconfirm>
          </template>
        </template>
      </a-table>
      <a-button type="dashed" @click="showQuestionSelector = true" block>+ 添加题目</a-button>
      <a-divider />
      <a-form-item>
        <a-button type="primary" @click="handleSave" :loading="saving">保存</a-button>
        <a-button style="margin-left: 8px;" @click="$router.back()">取消</a-button>
      </a-form-item>
    </a-form>

    <a-modal v-model:open="showQuestionSelector" title="选择题目" width="800px" @ok="confirmQuestions" :ok-text="'确认选择'">
      <a-table :columns="questionSelectorColumns" :data-source="availableQuestions" row-key="id" :row-selection="questionSelection" :pagination="{ pageSize: 5 }" size="small" />
    </a-modal>
  </a-card>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getPaperDetail, savePaper } from '../../../api/examPaper'
import { getQuestionPage } from '../../../api/question'
import { getSubjectList } from '../../../api/subject'
import { questionTypeMap } from '../../../utils'
import { message } from 'ant-design-vue'

const route = useRoute()
const router = useRouter()
const subjects = ref<any[]>([])
const saving = ref(false)
const showQuestionSelector = ref(false)
const availableQuestions = ref<any[]>([])

const isEdit = computed(() => !!route.params.id)

const paperTypeOptions = [
  { value: 1, label: '固定试卷' },
  { value: 2, label: '随机试卷' },
  { value: 3, label: '时段试卷' },
  { value: 4, label: '任务试卷' },
]

const form = reactive({
  id: undefined as number | undefined,
  name: '',
  paperType: 1,
  subjectId: undefined as number | undefined,
  suggestTime: 60,
  score: 0,
  questions: [] as any[],
})

const selectedQuestions = ref<any[]>([])

const questionColumns = [
  { title: '#', key: 'index', width: 50 },
  { title: '题目', dataIndex: 'title', ellipsis: true },
  { title: '题型', dataIndex: 'questionType', width: 80 },
  { title: '分数', dataIndex: 'score', width: 60 },
  { title: '操作', key: 'action', width: 80 },
]

const questionSelectorColumns = [
  { title: 'ID', dataIndex: 'id', width: 60 },
  { title: '题目', dataIndex: 'title', ellipsis: true },
  { title: '题型', dataIndex: 'questionType', width: 80 },
]

const questionSelection = {
  selectedRowKeys: ref<number[]>([]),
  onChange: (keys: any[]) => { questionSelection.selectedRowKeys.value = keys },
}

onMounted(async () => {
  const res = await getSubjectList()
  subjects.value = res.data || []

  const qRes = await getQuestionPage({ pageIndex: 1, pageSize: 200 })
  availableQuestions.value = qRes.data?.list || []

  if (isEdit.value) {
    const res = await getPaperDetail(Number(route.params.id))
    if (res.data) {
      Object.assign(form, res.data)
      selectedQuestions.value = res.data.questions || []
    }
  }
})

function removeQuestion(index: number) {
  selectedQuestions.value.splice(index, 1)
}

function confirmQuestions() {
  const keys = questionSelection.selectedRowKeys.value
  const selected = availableQuestions.value.filter(q => keys.includes(q.id))
  const existingIds = new Set(selectedQuestions.value.map(q => q.id))
  for (const q of selected) {
    if (!existingIds.has(q.id)) {
      selectedQuestions.value.push({ ...q })
      existingIds.add(q.id)
    }
  }
  questionSelection.selectedRowKeys.value = []
  showQuestionSelector.value = false
}

async function handleSave() {
  saving.value = true
  try {
    await savePaper({
      ...form,
      questions: selectedQuestions.value.map(q => ({ questionId: q.id, score: q.score || 5 })),
    })
    message.success('保存成功')
    router.push('/exam/paper/list')
  } finally {
    saving.value = false
  }
}
</script>
