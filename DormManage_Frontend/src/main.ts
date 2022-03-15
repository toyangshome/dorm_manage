import { createApp } from 'vue'
import App from './App.vue'
import 'ant-design-vue/dist/antd.css'
import loadComponent from './plugins/antDesign'
import router from './router'
const app = createApp(App)

app.use(router)
loadComponent(app)
app.mount('#app')
