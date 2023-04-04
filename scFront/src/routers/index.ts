import 'nprogress/css/nprogress.css'
import NProgress from 'nprogress'
import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import store from '@/store'
import Layout from '@/components/HelloWorld.vue'

NProgress.configure({ showSpinner: true })

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        component: Layout
    }
]

const router = createRouter({ history: createWebHashHistory(), routes})
router.beforeEach(async (to, from, next) => {
    if (location.search) {

    }
    NProgress.start()
    const getters = store.getters
    if (!getters['user/userInfo']) {
        
    } else {
        next()
    }
})
router.afterEach(() => NProgress.done())

export default router