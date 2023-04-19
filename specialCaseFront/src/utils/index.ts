import plus from './ElementPlus'
import utils from './Utils'
import { post, get } from './request'
import fs from './Image'

const install = (Vue) => {
    Vue.config.globalProperties.$utils = utils
    Vue.config.globalProperties.$fs = fs
    Vue.config.globalProperties.$plus = plus
    Vue.config.globalProperties.$post = post
    Vue.config.globalProperties.$get = get
}

export default {
    install
}