import { createApp } from 'vue'
import App from './App.vue'
import 'ant-design-vue/dist/antd.css'
import loadComponent from './plugins/antDesign'
import router from './router'
import { createPinia } from 'pinia'
const app = createApp(App)

app.use(router)
app.use(createPinia())
loadComponent(app)
app.mount('#app')
