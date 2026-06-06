<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="appStore.sidebarCollapsed" collapsible theme="light" width="200">
      <div class="logo">
        <h2 v-if="!appStore.sidebarCollapsed">XZS 学生端</h2>
        <h2 v-else>XZS</h2>
      </div>
      <a-menu mode="inline" :selectedKeys="[route.path]" :openKeys="openKeys" @click="onMenuClick">
        <a-menu-item key="/dashboard">
          <DashboardOutlined />
          <span>首页</span>
        </a-menu-item>
        <a-menu-item key="/paper/list">
          <FileTextOutlined />
          <span>试卷列表</span>
        </a-menu-item>
        <a-menu-item key="/record">
          <HistoryOutlined />
          <span>考试记录</span>
        </a-menu-item>
        <a-menu-item key="/question-error">
          <CloseCircleOutlined />
          <span>错题本</span>
        </a-menu-item>
        <a-menu-item key="/message">
          <MessageOutlined />
          <span>消息中心</span>
        </a-menu-item>
        <a-menu-item key="/user-info">
          <UserOutlined />
          <span>个人信息</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0 20px; display: flex; align-items: center; justify-content: space-between; border-bottom: 1px solid #f0f0f0; height: 48px; line-height: 48px;">
        <div style="display: flex; align-items: center; gap: 16px;">
          <MenuUnfoldOutlined v-if="appStore.sidebarCollapsed" @click="appStore.toggleSidebar" style="font-size: 18px; cursor: pointer;" />
          <MenuFoldOutlined v-else @click="appStore.toggleSidebar" style="font-size: 18px; cursor: pointer;" />
          <a-breadcrumb>
            <a-breadcrumb-item>{{ route.meta?.title || '首页' }}</a-breadcrumb-item>
          </a-breadcrumb>
        </div>
        <a-dropdown>
          <a class="ant-dropdown-link" @click.prevent>
            <UserOutlined /> {{ userStore.userInfo?.realName || userStore.userInfo?.userName }}
          </a>
          <template #overlay>
            <a-menu>
              <a-menu-item @click="$router.push('/user-info')">
                <UserOutlined /> 个人信息
              </a-menu-item>
              <a-menu-item @click="handleLogout">
                <LogoutOutlined /> 退出登录
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </a-layout-header>
      <a-layout-content style="margin: 16px; background: #fff; padding: 24px; min-height: calc(100vh - 80px);">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAppStore } from '../stores/app'
import { useUserStore } from '../stores/user'
import {
  MenuFoldOutlined, MenuUnfoldOutlined, UserOutlined, LogoutOutlined,
  DashboardOutlined, FileTextOutlined, HistoryOutlined, CloseCircleOutlined, MessageOutlined,
} from '@ant-design/icons-vue'

const router = useRouter()
const route = useRoute()
const appStore = useAppStore()
const userStore = useUserStore()
const openKeys = ref<string[]>([])

function onMenuClick({ key }: { key: string }) {
  router.push(key)
}

function handleLogout() {
  userStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.logo {
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #f0f0f0;
}
.logo h2 {
  margin: 0;
  font-size: 16px;
  color: #1890ff;
}
.ant-dropdown-link {
  display: flex;
  align-items: center;
  gap: 6px;
}
</style>
