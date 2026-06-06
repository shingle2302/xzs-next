<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="appStore.sidebarCollapsed" collapsible theme="light" width="220">
      <div class="logo">
        <h2 v-if="!appStore.sidebarCollapsed">XZS 考试系统</h2>
        <h2 v-else>XZS</h2>
      </div>
      <a-menu mode="inline" :selectedKeys="selectedKeys" :openKeys="openKeys" @openChange="onOpenChange" @click="onMenuClick">
        <template v-for="route in menuRoutes" :key="route.path">
          <a-sub-menu v-if="route.children && route.children.length" :key="route.path">
            <template #title>
              <component :is="route.meta?.icon" v-if="route.meta?.icon" />
              <span>{{ route.meta?.title }}</span>
            </template>
            <a-menu-item v-for="child in route.children" :key="child.path">
              <router-link :to="child.path">{{ child.meta?.title }}</router-link>
            </a-menu-item>
          </a-sub-menu>
        </template>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0 20px; display: flex; align-items: center; justify-content: space-between; border-bottom: 1px solid #f0f0f0; height: 48px; line-height: 48px;">
        <div style="display: flex; align-items: center; gap: 16px;">
          <MenuUnfoldOutlined v-if="appStore.sidebarCollapsed" @click="appStore.toggleSidebar" style="font-size: 18px; cursor: pointer;" />
          <MenuFoldOutlined v-else @click="appStore.toggleSidebar" style="font-size: 18px; cursor: pointer;" />
          <a-breadcrumb>
            <a-breadcrumb-item v-for="crumb in breadcrumbs" :key="crumb">{{ crumb }}</a-breadcrumb-item>
          </a-breadcrumb>
        </div>
        <a-dropdown>
          <a class="ant-dropdown-link" @click.prevent>
            <UserOutlined /> {{ userStore.userInfo?.realName || userStore.userInfo?.userName }}
            <DownOutlined />
          </a>
          <template #overlay>
            <a-menu>
              <a-menu-item @click="$router.push('/profile')">
                <UserOutlined /> 个人中心
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
import { computed, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAppStore } from '../stores/app'
import { useUserStore } from '../stores/user'
import { MenuFoldOutlined, MenuUnfoldOutlined, UserOutlined, DownOutlined, LogoutOutlined } from '@ant-design/icons-vue'

const router = useRouter()
const route = useRoute()
const appStore = useAppStore()
const userStore = useUserStore()

const openKeys = ref<string[]>([])

const menuRoutes = computed(() => {
  return router.getRoutes()
    .filter((r: any) => r.meta?.title && !r.meta?.hidden && r.path !== '/login' && r.path !== '/' && r.path !== '/redirect')
    .reduce<any[]>((acc: any[], r: any) => {
      if (r.path.split('/').length === 2) {
        acc.push(r)
      } else {
        const parent = acc.find((p: any) => r.path.startsWith(p.path))
        if (parent) {
          if (!parent.children) parent.children = []
          if (!parent.children.find((c: any) => c.path === r.path)) {
            parent.children.push(r)
          }
        }
      }
      return acc
    }, [])
})

const selectedKeys = computed(() => [route.path])

const breadcrumbs = computed(() => {
  const parts = route.path.split('/').filter(Boolean)
  const result: string[] = []
  for (const part of parts) {
    const routeMatch = router.getRoutes().find((r: any) => r.path === route.path && r.meta?.title)
    if (routeMatch?.meta?.title) {
      result.push(routeMatch.meta.title as string)
      break
    }
    result.push(part)
  }
  return result.length ? result : ['仪表盘']
})

function onOpenChange(keys: string[]) {
  openKeys.value = keys
}

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
