export function formatDate(date: string | Date | undefined): string {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
}

export function formatDateShort(date: string | Date | undefined): string {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
}

export const questionTypeMap: Record<number, string> = {
  1: '单选题',
  2: '多选题',
  3: '判断题',
  4: '填空题',
  5: '简答题',
}

export const questionTypeOptions = Object.entries(questionTypeMap).map(([value, label]) => ({
  value: Number(value),
  label,
}))

export const statusOptions = [
  { value: 1, label: '启用' },
  { value: 2, label: '禁用' },
]

export const roleOptions = [
  { value: 'STUDENT', label: '学生' },
  { value: 'ADMIN', label: '管理员' },
]
