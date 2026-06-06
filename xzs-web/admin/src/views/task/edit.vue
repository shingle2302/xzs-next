<template>
  <a-card :bordered="false">
    <h3>{{ isEdit ? '编辑任务' : '新增任务' }}</h3>
    <a-form :model="form" layout="vertical" style="max-width: 600px;">
      <a-form-item label="标题">
        <a-input v-model:value="form.title" />
      </a-form-item>
      <a-form-item label="年级">
        <a-input-number v-model:value="form.gradeLevel" :min="1" :max="12" />
      </a-form-item>
      <a-form-item label="关联试卷">
        <a-select v-model:value="form.paperIds" mode="multiple" placeholder="选择试卷">
          <a-select-option v-for="p in papers" :key="p.id" :value="p.id">{{ p.name }}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="handleSave" :loading="saving">保存</a-button>
        <a-button style="margin-left: 8px;" @click="$router.back()">取消</a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getTaskDetail, saveTask } from '../../api/task'
import { getPaperPage } from '../../api/examPaper'
import { message } from 'ant-design-vue'

const route = useRoute()
const router = useRouter()
const saving = ref(false)
const papers = ref<any[]>([])

const isEdit = computed(() => !!route.params.id)

const form = reactive({
  id: undefined as number | undefined,
  title: '',
  gradeLevel: 1,
  paperIds: [] as number[],
})

onMounted(async () => {
  const res = await getPaperPage({ pageIndex: 1, pageSize: 200 })
  papers.value = res.data?.list || []

  if (isEdit.value) {
    const res = await getTaskDetail(Number(route.params.id))
    if (res.data) {
      form.id = res.data.id
      form.title = res.data.title
      form.gradeLevel = res.data.gradeLevel
      form.paperIds = (res.data.papers || []).map((p: any) => p.id)
    }
  }
})

async function handleSave() {
  saving.value = true
  try {
    await saveTask(form)
    message.success('保存成功')
    router.push('/task/list')
  } finally {
    saving.value = false
  }
}
</script>
