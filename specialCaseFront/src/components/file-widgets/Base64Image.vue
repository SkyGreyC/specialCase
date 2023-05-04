<template>
    <el-image v-loading="loading" :src="base64" :preview-src-list="preview ? [base64] : []" class="base64-iamge"
        @load="$emit('load')" @error="$emit('error')">
        <template #error>
            <div class="el-image__error">{{ base64 ? '加载失败' : '加载中...' }}</div>
        </template>
    </el-image>
</template>

<script lang="ts">
import { Options, Prop, Watch } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';

declare interface ImageVO {
    imageVO: any
    blob?: Blob
    base64?: String
    text?: String
    filename?: String
}

@Options({
    name: 'Base64Image',
    emits: ['load', 'error']
})
export default class Base64Image extends BaseComponent {
    @Prop()
    src: String | ImageVO | ImageVO[]

    @Prop()
    preview

    base64: String = ''

    @Watch('src')
    async getBase64() {
        let src: any = this.src
        if (!src) {
            this.base64 = '-'
            return
        }
        if (typeof src === 'string') {
            this.base64 = src
        } else {
            src = src[0] || src
            if (src.base64) {
                this.base64 = src.base64
            } else {
                this.loading = true
                // src.base64 = await this.$fs.toBase64(src, 'image')
                this.loading = false
                this.base64 = src.base64 || '-'
            }
        }
    }

    created(): void {
        this.getBase64()
    }
}
</script>
<style lang='scss' scoped>
.el-image__error {
    min-height: 50px;
}
</style>