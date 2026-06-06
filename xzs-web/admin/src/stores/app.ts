import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  const sidebarCollapsed = ref(false)
  const visitedViews = ref<{ path: string; title: string }[]>([])

  function toggleSidebar() {
    sidebarCollapsed.value = !sidebarCollapsed.value
  }

  function addVisitedView(view: { path: string; title: string }) {
    if (!visitedViews.value.find(v => v.path === view.path)) {
      visitedViews.value.push(view)
    }
  }

  function removeVisitedView(path: string) {
    visitedViews.value = visitedViews.value.filter(v => v.path !== path)
  }

  return { sidebarCollapsed, visitedViews, toggleSidebar, addVisitedView, removeVisitedView }
})
