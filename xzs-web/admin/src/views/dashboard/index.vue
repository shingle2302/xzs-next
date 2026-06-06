<template>
  <div>
    <a-row :gutter="16">
      <a-col :span="6" v-for="stat in stats" :key="stat.label">
        <a-card :bordered="false" style="margin-bottom: 16px;">
          <a-statistic :title="stat.label" :value="stat.value" :value-style="{ color: stat.color }">
            <template #prefix>
              <component :is="stat.icon" />
            </template>
          </a-statistic>
        </a-card>
      </a-col>
    </a-row>
    <a-row :gutter="16">
      <a-col :span="12">
        <a-card title="试卷趋势" :bordered="false">
          <div ref="paperChart" style="height: 300px;"></div>
        </a-card>
      </a-col>
      <a-col :span="12">
        <a-card title="用户活跃度" :bordered="false">
          <div ref="userChart" style="height: 300px;"></div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import * as echarts from 'echarts'
import { getDashboardData } from '../../api/dashboard'
import { UserOutlined, FileTextOutlined, CheckCircleOutlined, RiseOutlined } from '@ant-design/icons-vue'

const stats = ref([
  { label: '用户总数', value: 0, icon: UserOutlined, color: '#1890ff' },
  { label: '试卷总数', value: 0, icon: FileTextOutlined, color: '#52c41a' },
  { label: '答题总数', value: 0, icon: CheckCircleOutlined, color: '#faad14' },
  { label: '今日活跃', value: 0, icon: RiseOutlined, color: '#f5222d' },
])

const paperChart = ref<HTMLDivElement>()
const userChart = ref<HTMLDivElement>()
let paperChartInstance: echarts.ECharts
let userChartInstance: echarts.ECharts

onMounted(async () => {
  try {
    const res = await getDashboardData()
    if (res.data) {
      stats.value[0].value = res.data.userCount || 0
      stats.value[1].value = res.data.paperCount || 0
      stats.value[2].value = res.data.answerCount || 0
      stats.value[3].value = res.data.todayActive || 0
    }
  } catch {
    // use defaults
  }

  if (paperChart.value) {
    paperChartInstance = echarts.init(paperChart.value)
    paperChartInstance.setOption({
      xAxis: { type: 'category', data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'] },
      yAxis: { type: 'value' },
      series: [{ data: [82, 93, 89, 134, 120, 76, 55], type: 'line', smooth: true }],
      tooltip: { trigger: 'axis' },
      grid: { left: 40, right: 20, bottom: 30 },
    })
  }

  if (userChart.value) {
    userChartInstance = echarts.init(userChart.value)
    userChartInstance.setOption({
      xAxis: { type: 'category', data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'] },
      yAxis: { type: 'value' },
      series: [{ data: [150, 230, 180, 290, 260, 110, 80], type: 'bar' }],
      tooltip: { trigger: 'axis' },
      grid: { left: 40, right: 20, bottom: 30 },
    })
  }
})

onUnmounted(() => {
  paperChartInstance?.dispose()
  userChartInstance?.dispose()
})
</script>
