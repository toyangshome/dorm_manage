import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = []

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
