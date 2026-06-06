<template>
  <div>
    <a-row :gutter="16">
      <a-col :span="6" v-for="stat in stats" :key="stat.label">
      <a-card hoverable @click="$router.push(stat.path)">
        <a-statistic :title="stat.label" :value="stat.value" />
      </a-card>
    </a-col>
    </a-row>
    <a-divider />
    <h3>我的任务</h3>
    <a-list v-if="tasks.length" :data-source="tasks" :grid="{ gutter: 16, column: 3 }">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-card :title="item.title">
            <p>{{ item.gradeLevel }}级 · {{ item.subjectName }}</p>
            <template #actions>
              <a-button type="link" @click="startTask(item.id)">开始</a-button>
            </template>
          </a-card>
        </a-list-item>
      </template>
    </a-list>
    <a-empty v-else description="暂无任务" />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getDashboard } from '../../api/dashboard'
import { message } from 'ant-design-vue'

const router = useRouter()
const stats = ref([
  { label: '进行中试卷', value: 0, path: '/record' },
  { label: '已完成试卷', value: 0, path: '/record' },
  { label: '错题数', value: 0, path: '/question-error' },
  { label: '未读消息', value: 0, path: '/message' },
])
const tasks = ref<any[]>([])

onMounted(async () => {
  try {
    const res: any = await getDashboard()
    if (res) {
      stats.value = [
        { label: '进行中试卷', value: res.inProgress || 0, path: '/record' },
        { label: '已完成试卷', value: res.completed || 0, path: '/record' },
        { label: '错题数', value: res.wrongBookCount || 0, path: '/question-error' },
        { label: '未读消息', value: res.unreadMessage || 0, path: '/message' },
      ]
      tasks.value = res.tasks || []
    }
  } catch { /* */ }
})

function startTask(id: number) {
  router.push(`/exam/do/${id}`)
}
</script>
