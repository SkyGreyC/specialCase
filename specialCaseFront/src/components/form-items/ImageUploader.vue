<template>
    <div class='image-uploader'>
        <base64-image v-if="disabled" :src="value" class="custom-standard-size" />
        <file-uploader v-else :model-value="value ? [value] : []" :limit="limit" :accept="accept.join('|')"
            :accept-error-message="`只支持${accept.join('、')}格式的图片`" :multiple="false" :show-file-list="false"
            class="custom-standard-size" @change="handleChange">
            <template #default="scope">
                <base64-image v-if="value" :src="value" class="custom-full" />
                <div v-else class="custom-around-center">
                    <el-icon icon="upload" :size="50" style="margin: 0 auto;" />
                </div>
            </template>
        </file-uploader>
    </div>
</template>

<script lang="ts">
import { Options } from 'vue-class-component';
import { Emit, Model, Prop } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';

declare interface ImageVO {
    imageVO: any
    blob?: Blob
    base64?: String
    text?: String
    filename?: String
}

@Options({
    name: 'imageUploader'
})
export default class imageUploader extends BaseComponent {
    @Model('modelValue', { default: null })
    value: Array<ImageVO>

    @Prop()
    disabled: Boolean

    @Prop({ default: 20 * 1024 * 1024 })
    limit: Number

    @Prop({ default: () => ['png', 'jpg', 'jpeg'] })
    accept

    async handleChange(arrayValue, resp, file) {
        const base64 = await new Promise(resolve => {
            const fr = new FileReader()
            fr.onload = () => {
                resolve(fr.result)
            }
            fr.readAsDataURL(file)
        })
        this.onFileUpload(resp, base64)
    }

    @Emit('change')
    onFileUpload(resp, base64) {
        const result = resp
        result.base64 = base64
        this.value = result
        return result
    }
}
</script>
<style lang='scss'>
.image-uploader {
    --custom-width-standard: 360px;
}
</style>