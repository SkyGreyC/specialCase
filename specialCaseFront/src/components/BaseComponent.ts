import { Vue } from 'vue-class-component'
import { ElementPlus } from '@/utils/ElementPlus'
import { Utils } from '@/utils/Utils'
import { getVForSourceType } from '@/__VLS_types'
// import { getStore } from '@/store'

export default class BaseComponent extends Vue {
  $router: any

  $route: any

  $plus: ElementPlus

  $utils: Utils

//   get store(): any {
//       return getStore() || {}
//   }

  loading = false
}