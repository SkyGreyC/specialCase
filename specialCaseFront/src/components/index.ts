import PageTable from './table/PageTable.vue'
import Toolbar from './toolbar/Toolbar.vue'
import FullPanel from './full-panel/FullPanel.vue'

const components = [
    PageTable,
    Toolbar,
    FullPanel
]

const install = (Vue) => {
    components.forEach((item: any) => {
        Vue.component(item.name,item)
    })
}

export default {
    install
}