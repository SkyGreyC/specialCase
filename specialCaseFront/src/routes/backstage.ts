import BackstageCenter from '@/pages/backstage/index.vue'
import UserManage from '@/pages/backstage/user/index.vue'
import UserEditor from '@/pages/backstage/user/UserEditor.vue'

export default {
    path: '/backstage',
    component: BackstageCenter,
    redirect: '/backstage/user',
    children:[
        {
            path: 'user',
            name: 'UserManage',
            component: UserManage
        },
        {
            path: 'user/editor',
            name: 'UserEditor',
            component: UserEditor
        }
    ]
}