import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import router from "./routes/index"
import { createPinia } from 'pinia'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import ElementPlus from 'element-plus'
import Components from '@/components'
import Utils from '@/utils'

// import 'virtual:svg-icons-register'
import 'element-plus/dist/index.css'
import '@/styles/index.scss'

const pinia = createPinia()
const app= createApp(App)
 
//routes 
app.use(router)  
app.use(pinia)
app.use(ElementPlus, { locale }) 
app.use(Components)
app.use(Utils)
app.mount('#app')
