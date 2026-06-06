<template>
  <a-card :bordered="false">
    <a-form layout="inline" style="margin-bottom: 16px;">
      <a-form-item>
        <a-button type="primary" @click="showEditModal(null)">新增学生</a-button>
      </a-form-item>
    </a-form>
    <a-table :columns="columns" :data-source="list" :pagination="pagination" :loading="loading" row-key="id" @change="onTableChange">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a-button type="link" @click="showEditModal(record)">编辑</a-button>
          <a-popconfirm title="确定删除?" @confirm="handleDelete(record.id)">
            <a-button type="link" danger>删除</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>

    <a-modal v-model:open="modalVisible" :title="editingUser ? '编辑学生' : '新增学生'" @ok="handleSave" :confirm-loading="saving">
      <a-form :model="editForm" layout="vertical">
        <a-form-item label="用户名">
          <a-input v-model:value="editForm.userName" :disabled="!!editingUser" />
        </a-form-item>
        <a-form-item label="姓名">
          <a-input v-model:value="editForm.realName" />
        </a-form-item>
        <a-form-item label="密码" v-if="!editingUser">
          <a-input-password v-model:value="editForm.password" />
        </a-form-item>
        <a-form-item label="年级">
          <a-input-number v-model:value="editForm.userLevel" :min="1" :max="12" />
        </a-form-item>
        <a-form-item label="手机号">
          <a-input v-model:value="editForm.phone" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-card>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { getStudentPage, createUser, updateUser, deleteUser } from '../../../api/user'
import { message } from 'ant-design-vue'

const list = ref<any[]>([])
const loading = ref(false)
const modalVisible = ref(false)
const editingUser = ref<any>(null)
const saving = ref(false)

const editForm = reactive({
  userName: '',
  realName: '',
  password: '',
  userLevel: 1,
  phone: '',
  role: 'STUDENT',
})

const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
})

const columns = [
  { title: 'ID', dataIndex: 'id', key: 'id', width: 80 },
  { title: '用户名', dataIndex: 'userName', key: 'userName' },
  { title: '姓名', dataIndex: 'realName', key: 'realName' },
  { title: '年级', dataIndex: 'userLevel', key: 'userLevel', width: 80 },
  { title: '手机号', dataIndex: 'phone', key: 'phone' },
  { title: '操作', key: 'action', width: 120 },
]

onMounted(() => loadData())

async function loadData() {
  loading.value = true
  try {
    const res = await getStudentPage({ pageIndex: pagination.current, pageSize: pagination.pageSize })
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

function showEditModal(record: any) {
  editingUser.value = record
  if (record) {
    editForm.userName = record.userName
    editForm.realName = record.realName || ''
    editForm.userLevel = record.userLevel || 1
    editForm.phone = record.phone || ''
  } else {
    editForm.userName = ''
    editForm.realName = ''
    editForm.password = ''
    editForm.userLevel = 1
    editForm.phone = ''
  }
  modalVisible.value = true
}

async function handleSave() {
  saving.value = true
  try {
    if (editingUser.value) {
      await updateUser({ ...editForm, id: editingUser.value.id })
      message.success('更新成功')
    } else {
      await createUser(editForm)
      message.success('创建成功')
    }
    modalVisible.value = false
    loadData()
  } finally {
    saving.value = false
  }
}

async function handleDelete(id: number) {
  await deleteUser(id)
  message.success('删除成功')
  loadData()
}
</script>
