import PageTable from './table/PageTable.vue'
import NormalTable from './table/NormalTable.vue'
import Toolbar from './toolbar/Toolbar.vue'
import FullPanel from './full-panel/FullPanel.vue'
import ImageUploader from './form-items/ImageUploader.vue'
import FileUploader from './form-items/FileUploader.vue'
import Base64Image from './file-widgets/Base64Image.vue'
import ImageItems from './image-items/ImageItems.vue'
import SvgIcon from './file-widgets/SvgIcon.vue'

const components = [
    PageTable,
    NormalTable,
    Toolbar,
    FullPanel,
    ImageUploader,
    FileUploader,
    Base64Image,
    ImageItems,
    SvgIcon
]

const install = (Vue) => {
    components.forEach((item: any) => {
        Vue.component(item.name,item)
    })
}

export default {
    install
}