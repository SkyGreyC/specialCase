<template>
    <span class="svg-icon" v-html="svgHtml" />
</template>

<script lang="ts">
import { Options, Prop, Watch } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';

@Options({
    name: 'SvgIcon'
})
export default class SvgIcon extends BaseComponent {
    @Prop({ required: true })
    icon: String

    @Prop({ type: [Number, String], default: '12px' })
    size // 图标大小，若width/height存在，宽度以width/height为准

    @Prop({ type: String })
    width: String // 图标宽度

    @Prop({ type: String })
    height: String // 图标高度

    html = '' // 图标的svg格式源码

    get iconSize() {
        if (typeof this.size === 'number') {
            return `${this.size}px`
        } else if (typeof this.size === 'string') {
            return this.size.trim()
        }
        return null
    }

    get svgHtml() {
        const height = this.height || this.iconSize
        const width = this.width || this.iconSize
        return this.html.replace(/\<svg .*?>/, (str) => {
            if (height) {
                str = str.replace(/height=".*?"/, `height="${height}"`)
            }
            if (width) {
                str = str.replace(/width=".*?"/g, `width="${width}"`)
            }
            return str
        })
    }

    @Watch('icon')
    async loadSvg() {
        const icon = this.icon
        if (typeof icon === 'string') { // @/assets/icons中的图片名称
            const { height, width, iconSize } = this
            this.html = `<svg height="${height || iconSize}" width="${width || iconSize}"><use xlink:href="#icon-${icon}" /></svg>`
        } else if (icon) { // imageVO对象

        }
    }

    created() {
        this.loadSvg()
    }
}
</script>
<style lang='scss' scoped>
.svg-icon {
    display: inline-block;
    line-height: 1;
    vertical-align: middle;
}
</style>