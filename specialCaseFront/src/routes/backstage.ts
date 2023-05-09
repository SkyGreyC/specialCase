import BackstageCenter from '@/pages/backstage/index.vue'
import UserManage from '@/pages/backstage/user/index.vue'
import UserEditor from '@/pages/backstage/user/UserEditor.vue'
import CaseManage from '@/pages/backstage/case/index.vue'
import CaseEditor from '@/pages/backstage/case/CaseEditor.vue'
import HomeManage from '@/pages/backstage/home/index.vue'
import HomeEditor from '@/pages/backstage/home/HomeEditor.vue'

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
        },
        {
            path: 'case',
            name: 'CaseManage',
            component: CaseManage
        },
        {
            path: 'case/editor',
            name: 'CaseEditor',
            component: CaseEditor
        },
        {
            path: 'home',
            name: 'HomeManage',
            component: HomeManage
        },
        {
            path: 'home/editor',
            name: 'HomeEditor',
            component: HomeEditor
        }
    ]
}