<template>
    <div v-loading="(progressList.length > 0 && !showFileList && multiple) || loading" class='file-uploader'>
        <el-upload v-if="!disabled" ref="uploader" :headers="headers" :show-file-list="false" :before-upload="beforeUpload"
            :drag="drag" :multiple="multiple" :action="uploadUrl" class="custom-full">
            <slot :remind="remind">
                <div class="custom-around-center">
                    <el-icon icon="UploadFilled" :size="50" style="margin: 0 auto;" />
                    <template v-if="placeholder">{{ placeholder }}<br /></template>
                    {{ remind }}
                </div>
            </slot>
        </el-upload>
        <!-- 用于触发elForm的校验事件 -->
        <!-- <el-input v-show="1 === 0" v-model="mdValue" /> -->
        <template v-if="showFileList">
            <div v-for="item in imageVOs" :key="item.imageId" class="file-item">
                <el-link :underline="false" @click="downloadFile(item)">{{ item.imageName }}</el-link>
                <el-link v-if="!disabled" :underline="false" type="danger" style="margin-left: 10px;"
                    @click="removeFile(item)">删除</el-link>
            </div>
            <template v-if="multiple">
                <div v-for="progress in progressList" :key="progress.uid" class="progress-item">
                    {{ progress.name }}&nbsp;&nbsp;
                    <el-progress :percentage="getPercentage(progress)" :stroke-width="16" :text-inside="true"
                        color="#009983" style="width: 100px;" />
                </div>
            </template>
            <template v-if="!multiple">
                <div v-if="progressList[0]" class="custom-around-center upload-progress">
                    <el-progress :percentage="getPercentage(progressList[0])" :width="50" type="circle" color="#009983" />
                </div>
            </template>
        </template>
    </div>
</template>

<script  lang="ts">
import _ from 'lodash'
import { Options, Emit, Model, Prop } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';
import request from '@/utils/request'


@Options({
    name: 'FileUploader'
})
export default class FileUploader extends BaseComponent {
    @Model('modelValue', { default: () => [] })
    value: any

    @Prop({ default: 'http://localhost:8080/image/uploadFile' })
    uploadUrl: string

    @Prop()
    disabled: Boolean

    @Prop({ default: true })
    drag: Boolean // 是否可拖拽上传

    @Prop({ default: true })
    multiple: Boolean // 是否可一次性上传多个文件

    @Prop({ default: true })
    showFileList: Boolean // 是否显示上传列表

    @Prop({ default: 100 * 1024 * 1024 })
    limit: number // 附件最大限制

    @Prop()
    accept: any // 接受的附件类型

    @Prop()
    acceptErrorMessage: String // 不接受的附件类型时提示信息

    @Prop()
    placeholder: String

    @Prop()
    autodeco // 是否自动解压压缩包

    headers = {}

    progressList = []

    get imageVOs() {
        console.log(this.value)
        return _.isArray(this.value) ? this.value : []
    }

    get mdValue() { // 用于判断附件是否改变，可触发表单校验
        return this.imageVOs.map(val => val.imageVO.imageId).join(',')
    }

    get remind() { //附件上传框内的提示信息
        let remind = `文件不得大于20M`
        if (this.accept && this.accept instanceof Array) {
            remind = `上传格式为${this.accept.join('、')},` + remind
        }
        if (this.drag) {
            remind = `拖拽文件到此次或点击上传，` + remind
        }
        return remind + `${this.autodeco ? '(zip)文件自动解压后上传' : ''}`
    }

    /**
     * 上传成功回调
     */
    @Emit('change')
    onFileUpload(resp, file, progress) {
        const result = resp
        // 从正在上传的附件列表中移除
        const index = this.progressList.indexOf(progress)
        if (index >= 0) {
            this.progressList.splice(index, 1)
        }
        // 添加到value中
        let value
        if (this.multiple && _.isArray(this.value)) {
            value = [...this.value, result]
        } else {
            value = [result]
        }
        this.value = value
        return value
    }

    getPercentage(progress) {
        return Math.round(Math.min(1, progress.loaded / progress.size) * 10000) / 100
    }

    /**
     * 上传
     */
    async upload(file, progress) {
        const formData = new FormData()
        formData.append('file', file)
        // 文件上传
        const resp = await request({
            method: 'POST',
            url: this.uploadUrl,
            data: formData,
            timeout: 300000,
            onUploadProgress(e) {
                progress.loaded = (progress._loaded || 0) + e.loaded
            }
        })
        return resp.data
    }

    validateFile(file) {
        if (file.size > this.limit) {
            return `文件不能超过20M`
        }
    }

    /**
     * 上传前处理，用于校验文件是否合理
     */
    beforeUpload(file) {
        const filename = file.name.toLowerCase()
        //附件校验
        const errorMessage = this.validateFile(file)
        if (errorMessage) {
            return false
        }
        let progress = {
            uid: file.uid,
            name: file.name,
            _loaded: 0,
            loaded: 0,
            size: file.size
        }
        //加入正在上传中列表
        if (this.multiple) {
            this.progressList.push(progress)
        } else {
            this.progressList = [progress]
        }
        // 获取代理对象
        progress = this.progressList[this.progressList.length - 1]
        this.upload(file, progress).then(resp => {
            this.onFileUpload(resp.data, file, progress)
        })
        return false
    }

    removeFile(item) {
        this.value.splice(this.value.indexOf(item), 1)
    }

    async downloadFile(imageVO) {

    }
}
</script>
<style lang='scss' scoped>
.file-uploader {
    line-height: 22px;
    position: relative;
}

.upload-progress {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: var(--el-mask-color);
}

.progress-item {
    display: flex;
}
</style>