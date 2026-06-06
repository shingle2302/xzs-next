<template>
  <div v-if="answer" style="max-width: 900px; margin: 0 auto; padding: 20px;">
    <a-card>
      <h2>{{ answer.paperName }}</h2>
      <p>得分：<strong>{{ answer.score }}</strong> / {{ answer.paperScore }}</p>
      <p v-for="t in answer.titleItems" :key="t.name">
        <strong>{{ t.name }}</strong>
        <div v-for="q in t.questionItems" :key="q.id" style="margin: 12px 0; padding: 12px; background: #fafafa; border-radius: 4px;">
          <div v-html="q.title"></div>
          <p>你的答案：<span :style="{ color: q.correct ? 'green' : 'red' }">{{ q.answer }}</span></p>
          <p v-if="!q.correct">正确答案：<span style="color: green;">{{ q.correctAnswer }}</span></p>
          <p v-if="q.analyze">解析：{{ q.analyze }}</p>
        </div>
      </p>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getAnswerRead } from '../../../api/examPaperAnswer'

const route = useRoute()
const router = useRouter()
const answer = ref<any>(null)

onMounted(async () => {
  try {
    const res: any = await getAnswerRead(Number(route.params.id))
    answer.value = res
  } catch { router.push('/404') }
})
</script>
