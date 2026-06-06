<template>
  <a-card :bordered="false">
    <a-form layout="inline" style="margin-bottom: 16px;">
      <a-form-item>
        <a-button type="primary" @click="showAddModal">新增学科</a-button>
      </a-form-item>
    </a-form>
    <a-table :columns="columns" :data-source="list" :loading="loading" row-key="id" :pagination="false">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a-popconfirm title="确定删除?" @confirm="handleDelete(record.id)">
            <a-button type="link" danger>删除</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>

    <a-modal v-model:open="modalVisible" title="新增学科" @ok="handleSave" :confirm-loading="saving">
      <a-form :model="editForm" layout="vertical">
        <a-form-item label="学科名称">
          <a-input v-model:value="editForm.name" />
        </a-form-item>
        <a-form-item label="年级">
          <a-input-number v-model:value="editForm.level" :min="1" :max="12" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { getSubjectList, saveSubject, deleteSubject } from '../../../api/subject'
import { message } from 'ant-design-vue'

const list = ref<any[]>([])
const loading = ref(false)
const modalVisible = ref(false)
const saving = ref(false)

const editForm = reactive({
  name: '',
  level: 1,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '学科名称', dataIndex: 'name', key: 'name' },
  { title: '年级', dataIndex: 'level', key: 'level', width: 80 },
  { title: '操作', key: 'action', width: 120 },
]

onMounted(async () => {
  loading.value = true
  try {
    const res = await getSubjectList()
    list.value = res.data || []
  } finally {
    loading.value = false
  }
})

function showAddModal() {
  editForm.name = ''
  editForm.level = 1
  modalVisible.value = true
}

async function handleSave() {
  saving.value = true
  try {
    await saveSubject(editForm)
    message.success('创建成功')
    modalVisible.value = false
    const res = await getSubjectList()
    list.value = res.data || []
  } finally {
    saving.value = false
  }
}

async function handleDelete(id: number) {
  await deleteSubject(id)
  message.success('删除成功')
  const res = await getSubjectList()
  list.value = res.data || []
}
</script>
