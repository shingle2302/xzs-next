<template>
  <a-card :bordered="false">
    <h3>{{ isEdit ? '编辑题目' : '新增题目' }}</h3>
    <a-form :model="form" layout="vertical" style="max-width: 800px;">
      <a-form-item label="题型">
        <a-select v-model:value="form.questionType" :options="questionTypeOptions" @change="onTypeChange" />
      </a-form-item>
      <a-form-item label="学科">
        <a-select v-model:value="form.subjectId" placeholder="选择学科">
          <a-select-option v-for="s in subjects" :key="s.id" :value="s.id">{{ s.name }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="标题">
        <a-input v-model:value="form.title" placeholder="题目标题" />
      </a-form-item>
      <a-form-item label="题目内容">
        <a-textarea v-model:value="form.content" :rows="4" placeholder="题目内容" />
      </a-form-item>
      <a-form-item label="分数">
        <a-input-number v-model:value="form.score" :min="1" />
      </a-form-item>
      <a-form-item label="难度">
        <a-rate v-model:value="form.difficulty" :count="5" />
      </a-form-item>
      <a-form-item label="选项" v-if="form.questionType <= 2">
        <div v-for="(item, index) in form.items" :key="index" style="display: flex; gap: 8px; margin-bottom: 8px;">
          <a-input v-model:value="item.prefix" placeholder="前缀" style="width: 60px;" />
          <a-input v-model:value="item.content" placeholder="选项内容" style="flex: 1;" />
          <a-checkbox v-model:checked="item.correct">正确</a-checkbox>
          <a-button @click="removeItem(index)" danger type="link" v-if="form.items.length > 2">删除</a-button>
        </div>
        <a-button type="dashed" @click="addItem">+ 添加选项</a-button>
      </a-form-item>
      <a-form-item label="正确答案" v-if="form.questionType === 3">
        <a-radio-group v-model:value="form.correctAnswer">
          <a-radio :value="true">正确</a-radio>
          <a-radio :value="false">错误</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="参考答案" v-if="form.questionType >= 4">
        <a-textarea v-model:value="form.correctAnswer" :rows="4" placeholder="参考答案" />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="handleSave" :loading="saving">保存</a-button>
        <a-button style="margin-left: 8px;" @click="$router.back()">取消</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getQuestionDetail, saveQuestion } from '../../../../api/question'
import { getSubjectList } from '../../../../api/subject'
import { questionTypeOptions } from '../../../../utils'
import { message } from 'ant-design-vue'

const route = useRoute()
const router = useRouter()
const subjects = ref<any[]>([])
const saving = ref(false)

const isEdit = computed(() => !!route.params.id)

const form = reactive({
  id: undefined as number | undefined,
  questionType: 1,
  subjectId: undefined as number | undefined,
  title: '',
  content: '',
  score: 5,
  difficulty: 3,
  correctAnswer: undefined as any,
  items: [{ prefix: 'A', content: '', correct: false }, { prefix: 'B', content: '', correct: false }],
})

onMounted(async () => {
  const res = await getSubjectList()
  subjects.value = res.data || []

  if (isEdit.value) {
    const res = await getQuestionDetail(Number(route.params.id))
    if (res.data) {
      Object.assign(form, res.data)
    }
  }
})

function onTypeChange(val: number) {
  form.correctAnswer = undefined
  if (val <= 2) {
    form.items = [{ prefix: 'A', content: '', correct: false }, { prefix: 'B', content: '', correct: false }]
  } else {
    form.items = []
  }
}

function addItem() {
  const prefix = String.fromCharCode(65 + form.items.length)
  form.items.push({ prefix, content: '', correct: false })
}

function removeItem(index: number) {
  form.items.splice(index, 1)
  form.items.forEach((item, i) => { item.prefix = String.fromCharCode(65 + i) })
}

async function handleSave() {
  saving.value = true
  try {
    await saveQuestion(form)
    message.success('保存成功')
    router.push('/exam/question/list')
  } finally {
    saving.value = false
  }
}
</script>
