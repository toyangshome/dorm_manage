import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
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
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/welcome',
    children: [
      {
        path: 'welcome',
        name: 'Welcome',
        component: Welcome,
        meta: {
          role: 'normal',
          title: '首页'
        }
      },
      {
        path: 'dorm_manager',
        name: 'DormManager',
        component: DormManager,
        meta: {
          role: 'admin',
          title: '宿舍管理员管理'
        }
      },
      {
        path: 'dorm_build',
        name: 'DormBuild',
        component: DormBuild,
        meta: {
          role: 'admin',
          title: '宿舍楼管理'
        }
      },
      {
        path: 'student',
        name: 'Student',
        component: Student,
        meta: {
          role: 'student',
          title: '学生管理'
        }
      },
      {
        path: 'record',
        name: 'Record',
        component: Record,
        meta: {
          role: 'student',
          title: '缺勤记录'
        }
      },
      {
        path: 'change_pwd',
        name: 'ChangePassword',
        component: ChangePassword,
        meta: {
          role: 'student',
          title: '修改密码'
        }
      },
      {
        path: '/404',
        name: 'NotFound',
        component: NotFound,
        meta: {
          visible: true
        }
      },
      {
        path: '/403',
        name: 'AuthError',
        component: AuthError,
        meta: {
          visible: true
        }
      },
      {
        path: '/500',
        name: 'ServerError',
        component: ServerError,
        meta: {
          visible: true
        }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      role: 'any',
      title: '登录'
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  if (to.meta.visible === true) {
    console.log('visible')
  }
  if (to.matched.length === 0) {
    from.path ? next({ path: from.path }) : next('/404') // 如果每匹配成功，那么就前往 from-path，如果不存在， 最后才去 404 页面
  } else {
    next()
  }
})

export default router
