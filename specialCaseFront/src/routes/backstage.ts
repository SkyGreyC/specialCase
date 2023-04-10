import BackstageCenter from '@/pages/backstage/index.vue'
import UserManage from '@/pages/backstage/user/index.vue'

export default {
    path: '/backstage',
    component: BackstageCenter,
    redirect: '/backstage/user',
    children:[
        {
            path: 'user',
            name: 'UserManage',
            component: UserManage
        }
    ]
}