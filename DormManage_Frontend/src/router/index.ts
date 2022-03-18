import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import { PieChartOutlined } from '@ant-design/icons-vue'
import { h } from 'vue'
import userStore from '@/store/userStore'
import pinia from '@/store'
import useUserStore from '@/store/userStore'
import { message } from 'ant-design-vue'

const Layout = () => import('@/views/Layout.vue')
const Welcome = () => import('@/views/welcome/Welcome.vue')
const DormBuild = () => import('@/views/dormbuild/DormBuild.vue')
const DormManager = () => import('@/views/dormmanager/DormManager.vue')
const Student = () => import('@/views/student/Student.vue')
const Login = () => import('@/views/login/Login.vue')
const Record = () => import('@/views/record/Record.vue')
const ChangePassword = () => import('@/components/password/ChangePassword.vue')
const NotFound = () => import('@/components/error/404.vue')
const AuthError = () => import('@/components/error/403.vue')
const ServerError = () => import('@/components/error/500.vue')
const mainRoutes: Array<RouteRecordRaw> = [
  {
    path: 'welcome',
    name: 'Welcome',
    component: Welcome,
    meta: {
      role: 0,
      title: '首页',
      icon: 'PieChartOutlined',
      show: true
    }
  },
  {
    path: 'dorm_manager',
    name: 'DormManager',
    component: DormManager,
    meta: {
      role: 2,
      title: '宿舍管理员管理',
      icon: 'UserOutlined',
      show: true
    }
  },
  {
    path: 'dorm_build',
    name: 'DormBuild',
    component: DormBuild,
    meta: {
      role: 1,
      title: '宿舍楼管理',
      icon: 'HomeOutlined',
      show: true

    }
  },
  {
    path: 'student',
    name: 'Student',
    component: Student,
    meta: {
      role: 1,
      title: '学生管理',
      icon: 'TeamOutlined',
      show: true
    }
  },
  {
    path: 'record',
    name: 'Record',
    component: Record,
    meta: {
      role: 0,
      title: '缺勤记录',
      icon: 'ScheduleOutlined',
      show: true
    }
  },
  {
    path: 'change_pwd',
    name: 'ChangePassword',
    component: ChangePassword,
    meta: {
      role: 0,
      title: '修改密码',
      show: false
    }
  }
]
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/welcome',
    children: [
      {
        path: '/404',
        name: 'NotFound',
        component: NotFound,
        meta: {
          role: 0
        }
      },
      {
        path: '/403',
        name: 'AuthError',
        component: AuthError,
        meta: {
          role: 0
        }
      },
      {
        path: '/500',
        name: 'ServerError',
        component: ServerError,
        meta: {
          role: 0
        }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      role: -1,
      title: '登录'
    }
  }
]
const store = useUserStore(pinia)

export const getMenuRoutes = () => {
  return mainRoutes.filter((item) => {
    return item.meta.role <= store.currentRole && item.meta.show
  })
}
export const loadRoutes = (): RouteRecordRaw[] => {
  routes[0].children.push(...mainRoutes)
  return routes
}
const router = createRouter({
  history: createWebHashHistory(),
  routes: loadRoutes()
})

router.beforeEach(async (to, from, next) => {
  if (to.path === '/login') {
    next()
    return
  }
  if (!store.auth) {
    await store.loadUser()
    if (store.auth) {
      next()
      return
    }

    message.warn('请先登录再尝试')
    next('/login')
    return
  }
  if (to.meta.role > store.currentRole) {
    next('/404')
    return
  }
  if (to.matched.length === 0) {
    from.path ? next({ path: from.path }) : next('/404') // 如果每匹配成功，那么就前往 from-path，如果不存在， 最后才去 404 页面
  } else {
    next()
  }
})

export default router
