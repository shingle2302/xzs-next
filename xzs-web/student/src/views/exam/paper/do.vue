<template>
  <div v-if="paper" style="max-width: 900px; margin: 0 auto; padding: 20px;">
    <a-card>
      <h2>{{ paper.name }}</h2>
      <p v-for="t in paper.titleItems" :key="t.name">
        <strong>{{ t.name }}</strong>
        <div v-for="q in t.questionItems" :key="q.id" style="margin: 12px 0; padding: 12px; background: #fafafa; border-radius: 4px;">
          <div style="margin-bottom: 8px;">
            <span v-html="q.title"></span>
            <span v-if="q.questionType === 3">（判断题）</span>
            <span v-else-if="q.questionType === 4">（填空题）</span>
            <span v-else-if="q.questionType === 5">（简答题）</span>
          </div>
          <div v-if="q.questionType === 1">
            <a-radio-group v-model:value="answers[q.id]">
              <a-radio v-for="item in q.items" :key="item.prefix" :value="item.prefix">{{ item.prefix }}. {{ item.content }}</a-radio>
            </a-radio-group>
          </div>
          <div v-else-if="q.questionType === 2">
            <a-checkbox-group v-model:value="answers[q.id]">
              <a-checkbox v-for="item in q.items" :key="item.prefix" :value="item.prefix">{{ item.prefix }}. {{ item.content }}</a-checkbox>
            </a-checkbox-group>
          </div>
          <div v-else-if="q.questionType === 3">
            <a-radio-group v-model:value="answers[q.id]">
              <a-radio :value="true">正确</a-radio>
              <a-radio :value="false">错误</a-radio>
            </a-radio-group>
          </div>
          <div v-else-if="q.questionType === 4">
            <a-input v-model:value="answers[q.id]" placeholder="请输入答案" />
          </div>
          <div v-else-if="q.questionType === 5">
            <a-textarea v-model:value="answers[q.id]" :rows="3" placeholder="请输入答案" />
          </div>
        </div>
      </p>
      <div style="text-align: center;">
        <a-button type="primary" size="large" @click="handleSubmit" :loading="submitting">提交答卷</a-button>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getExamPaperDetail } from '../../../api/examPaper'
import { submitAnswer } from '../../../api/examPaperAnswer'
import { message } from 'ant-design-vue'

const route = useRoute()
const router = useRouter()
const paper = ref<any>(null)
const answers = reactive<Record<number, any>>({})
const submitting = ref(false)

onMounted(async () => {
  try {
    const res: any = await getExamPaperDetail(Number(route.params.id))
    paper.value = res
  } catch { router.push('/404') }
})

function handleSubmit() {
  message.warning('提交功能待API联调')
}
</script>
