import { createApp } from 'vue'
import routers from '@/routers'
import store, {key} from '@/store'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import ElementPlus from 'element-plus'
import Components from '@/components'
import Utils from '@/utils'
import App from '@/App.vue'

import './assets/main.css'

const app = createApp(App)
app.use(routers)
app.use(store, key)
app.use(ElementPlus, {locale})
app.use(Components)
app.use(Utils)
app.mount('#app')
