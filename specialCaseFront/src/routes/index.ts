import { createRouter, createWebHistory } from "vue-router";
import Layout from '@/pages/index.vue'
import backstageRoute from './backstage'
import homeRoute from './home'
 
let routes= [
    {
        path: '/',
        name: 'home',
        //使用import可以路由懒加载，如果不使用，太多组件一起加载会造成白屏
        // component: () => import('../components/HelloWorld.vue')
        component: Layout,
    },
    backstageRoute,
    homeRoute
    //{
        //配置404页面
        //path: '/:catchAll(.*)',
        //name: '404',
        //component: () => import(''),
    //}
]
// 路由
const router = createRouter({
    history: createWebHistory(),
    routes
})
// 导出
export default router 