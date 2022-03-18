import { createApp } from 'vue'
import App from './App.vue'
import 'ant-design-vue/dist/antd.css'
import loadComponent from './plugins/antDesign'
import router from './router'
import pinia from '@/store'
import useUserStore from '@/store/userStore'

const app = createApp(App)

function permission(app: ReturnType<typeof createApp>, ...options: any[]): any {
  const store = useUserStore()
  store.loadUser()
}
// 1.初始化 store
app.use(pinia)
// 2.初始化 router
loadComponent(app)
app.use(router)
app.mount('#app')
app.use(permission)
