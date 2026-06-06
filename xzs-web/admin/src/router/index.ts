import { createRouter, createWebHashHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    component: () => import('../views/login/index.vue'),
    meta: { title: '登录', hidden: true },
  },
  {
    path: '/redirect',
    component: () => import('../layout/index.vue'),
    meta: { hidden: true },
    children: [
      {
        path: ':path(.*)',
        component: () => import('../views/redirect/index.vue'),
      },
    ],
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
        meta: { title: '仪表盘', icon: 'DashboardOutlined' },
      },
    ],
  },
  {
    path: '/exam',
    component: () => import('../layout/index.vue'),
    meta: { title: '考试管理', icon: 'FileTextOutlined' },
    children: [
      {
        path: 'question/list',
        name: 'QuestionList',
        component: () => import('../views/exam/question/list.vue'),
        meta: { title: '题目列表' },
      },
      {
        path: 'question/edit/:id?',
        name: 'QuestionEdit',
        component: () => import('../views/exam/question/edit/single-choice.vue'),
        meta: { title: '题目编辑', hidden: true },
      },
      {
        path: 'paper/list',
        name: 'PaperList',
        component: () => import('../views/exam/paper/list.vue'),
        meta: { title: '试卷列表' },
      },
      {
        path: 'paper/edit/:id?',
        name: 'PaperEdit',
        component: () => import('../views/exam/paper/edit.vue'),
        meta: { title: '试卷编辑', hidden: true },
      },
    ],
  },
  {
    path: '/user',
    component: () => import('../layout/index.vue'),
    meta: { title: '用户管理', icon: 'UserOutlined' },
    children: [
      {
        path: 'student/list',
        name: 'StudentList',
        component: () => import('../views/user/student/list.vue'),
        meta: { title: '学生列表' },
      },
      {
        path: 'admin/list',
        name: 'AdminList',
        component: () => import('../views/user/admin/list.vue'),
        meta: { title: '管理员列表' },
      },
    ],
  },
  {
    path: '/education',
    component: () => import('../layout/index.vue'),
    meta: { title: '教育管理', icon: 'BookOutlined' },
    children: [
      {
        path: 'subject/list',
        name: 'SubjectList',
        component: () => import('../views/education/subject/list.vue'),
        meta: { title: '学科列表' },
      },
    ],
  },
  {
    path: '/task',
    component: () => import('../layout/index.vue'),
    meta: { title: '任务管理', icon: 'SnippetsOutlined' },
    children: [
      {
        path: 'list',
        name: 'TaskList',
        component: () => import('../views/task/list.vue'),
        meta: { title: '任务列表' },
      },
      {
        path: 'edit/:id?',
        name: 'TaskEdit',
        component: () => import('../views/task/edit.vue'),
        meta: { title: '任务编辑', hidden: true },
      },
    ],
  },
  {
    path: '/message',
    component: () => import('../layout/index.vue'),
    meta: { title: '消息管理', icon: 'MessageOutlined' },
    children: [
      {
        path: 'list',
        name: 'MessageList',
        component: () => import('../views/message/list.vue'),
        meta: { title: '消息列表' },
      },
      {
        path: 'send',
        name: 'MessageSend',
        component: () => import('../views/message/send.vue'),
        meta: { title: '发送消息' },
      },
    ],
  },
  {
    path: '/answer',
    component: () => import('../layout/index.vue'),
    meta: { title: '成绩管理', icon: 'CheckCircleOutlined' },
    children: [
      {
        path: 'list',
        name: 'AnswerList',
        component: () => import('../views/answer/list.vue'),
        meta: { title: '答卷列表' },
      },
    ],
  },
  {
    path: '/log',
    component: () => import('../layout/index.vue'),
    meta: { title: '日志管理', icon: 'FileTextOutlined' },
    children: [
      {
        path: 'list',
        name: 'LogList',
        component: () => import('../views/log/list.vue'),
        meta: { title: '日志列表' },
      },
    ],
  },
  {
    path: '/profile',
    component: () => import('../layout/index.vue'),
    meta: { title: '个人中心', icon: 'UserOutlined' },
    children: [
      {
        path: '',
        name: 'Profile',
        component: () => import('../views/profile/index.vue'),
        meta: { title: '个人中心' },
      },
    ],
  },
  {
    path: '/401',
    component: () => import('../views/error-page/401.vue'),
    meta: { hidden: true },
  },
  {
    path: '/404',
    component: () => import('../views/error-page/404.vue'),
    meta: { hidden: true },
  },
  {
    path: '/:pathMatch(.*)',
    redirect: '/404',
    meta: { hidden: true },
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem('token')
  if (to.path === '/login') {
    next()
  } else if (!token) {
    next('/login')
  } else {
    next()
  }
})

export default router
