<template>
    <full-panel v-loading="loading" class="case-editor">
        <el-form ref="form" :model="formData" label-position="top" class="custom-row">
            <el-form-item v-bind="$plus.attrFormItem('homeTitle', '案例标题', !readonly)" class="custom-col-1">
                <div v-if="readonly" class="custom-readonly" style="min-height: 100px;">{{ formData.homeTitle }}</div>
                <el-input v-else v-model="formData.homeTitle" :disabled="readonly" :row="6" maxlength="200" show-word-limit
                    type="textarea" />
            </el-form-item>
            <el-form-item v-bind="$plus.attrFormItem('homeInfo', '案例描述', !readonly)" class="custom-col-1">
                <div v-if="readonly" class="custom-readonly" style="min-height: 100px;">{{ formData.homeInfo }}</div>
                <el-input v-else v-model="formData.homeInfo" :disabled="readonly" :row="6" maxlength="2000" show-word-limit
                    type="textarea" />
            </el-form-item>
            <el-form-item v-bind="$plus.attrFormItem('imageVOs', '案例图', !readonly)" class="custom-col-1">
                <file-uploader v-if="!readonly" v-model="formData.imageVOs" :show-file-list="true" :limit="20 * 1024 * 1024"
                    accept="png|jpg|gif|jpeg|bmp" style="width: 15%;">
                    <template #default="scope">
                        <div class="custom-around-center">
                            <svg-icon icon="upload" :size="50" style="margin: 0 auto;" />
                            {{ scope.remind }}
                        </div>
                    </template>
                </file-uploader>
                <image-items :list="formData.imageVOs" :size="[200, 150]" :deletable="!readonly" style="margin-top: 20px;">
                    <template #default="scope">
                        <base64-image :src="scope.data" />
                    </template>
                </image-items>
            </el-form-item>
            <!-- <el-form-item v-bind="$plus.attrFormItem('imageVOs', '案例图', !readonly)" class="custom-col-1">
                <image-uploader v-model="formData.imageVOs" :disabled="readonly"
                    style="--custom-width-standard: 30vw;--custom-ratio: 0.66666667;" />
            </el-form-item> -->

        </el-form>
        <template v-if="!readonly" #footer>
            <toolbar>
                <template #actions>
                    <el-button :loading="saveLoading" type="primary" @click="doSave">保存</el-button>
                </template>
            </toolbar>
        </template>

    </full-panel>
</template>

<script lang="ts">
import { Options } from "vue-class-component";
import BasePage from "@/pages/BasePage";
import * as api from '@/api/home'
import { ElMessage } from 'element-plus';
import { BASE_URL } from "@/utils/request";

@Options({
    name: 'HomeEditor',
})
export default class HomeEditor extends BasePage {

    saveLoading = false

    formData: any = {}

    get homeId() {
        return this.$route.query.id || this.$route.query.detailId
    }

    get readonly() {
        return !!this.$route.query.detailId
    }

    created() {
        if (this.homeId) {
            this.doQuery()
        }
    }

    async doQuery() {
        this.loading = true
        const resp = await api.findHomeDetail({
            homeId: this.homeId
        })
        if (resp && resp.data) {
            const vo = resp.data
            this.formData = vo
        }
        this.loading = false
    }

    async doSave() {
        const valid = await (this.$refs.form as any).validate()
        if (!valid) {
            return
        }
        const vo = this.formData
        const homeVO = {
            ...vo,
            homeType: '00',
        }
        this.saveLoading = true
        await api.saveHomeInfo({ homeVO })
        this.saveLoading = false
        ElMessage.success('保存成功！')
        this.$router.go(-1)
    }

           /**
     * 获取附件的访问路径
     */
     getFileURL(vo): String {
        if (!vo) {
            return null
        }
        const imageId = vo.imageId
        if (imageId) {
            return `${BASE_URL}/image/downloadFile?imageId=${imageId}`
        }
        return null
    }

}

</script>
<style lang='scss' scoped></style>