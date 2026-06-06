import { createRouter, createWebHashHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    component: () => import('../views/login/index.vue'),
    meta: { title: '登录', hidden: true },
  },
  {
    path: '/register',
    component: () => import('../views/register/index.vue'),
    meta: { title: '注册', hidden: true },
  },
  {
    path: '/',
    component: () => import('../layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/dashboard/index.vue'),
        meta: { title: '首页', icon: 'DashboardOutlined' },
      },
      {
        path: 'paper/list',
        name: 'PaperList',
        component: () => import('../views/paper/index.vue'),
        meta: { title: '试卷列表' },
      },
      {
        path: 'record',
        name: 'Record',
        component: () => import('../views/record/index.vue'),
        meta: { title: '考试记录' },
      },
      {
        path: 'question-error',
        name: 'QuestionError',
        component: () => import('../views/question-error/index.vue'),
        meta: { title: '错题本' },
      },
      {
        path: 'user-info',
        name: 'UserInfo',
        component: () => import('../views/user-info/index.vue'),
        meta: { title: '个人信息' },
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../views/user-info/message.vue'),
        meta: { title: '消息中心' },
      },
    ],
  },
  {
    path: '/exam/do/:id',
    component: () => import('../views/exam/paper/do.vue'),
    meta: { title: '考试', hidden: true },
  },
  {
    path: '/exam/read/:id',
    component: () => import('../views/exam/paper/read.vue'),
    meta: { title: '阅卷', hidden: true },
  },
  { path: '/401', component: () => import('../views/error-page/401.vue'), meta: { hidden: true } },
  { path: '/404', component: () => import('../views/error-page/404.vue'), meta: { hidden: true } },
  { path: '/:pathMatch(.*)', redirect: '/404', meta: { hidden: true } },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem('token')
  if (to.path === '/login' || to.path === '/register') {
    next()
  } else if (!token) {
    next('/login')
  } else {
    next()
  }
})

export default router
