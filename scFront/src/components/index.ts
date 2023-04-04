import HelloWorld from './HelloWorld.vue'

const components = [
    HelloWorld
]

const install = (Vue) => {
    components.forEach((item: any) => {
        Vue.component(item.name, item)
    })
}

export default {
    install
}