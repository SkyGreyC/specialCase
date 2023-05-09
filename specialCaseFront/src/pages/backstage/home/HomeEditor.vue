<template>
    <full-panel v-loading="loading" class="case-editor">
        <el-form ref="form" :model="formData" label-position="top" class="custom-row">
            <div class="custom-row">
                <el-form-item v-bind="$plus.attrFormItem('homeTitle', '案例标题', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.homeTitle }}</div>
                    <el-input v-else v-model="formData.homeTitle" maxlength="200" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('homeInfo', '案例描述', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.homeInfo }}</div>
                    <el-input v-else v-model="formData.homeInfo" maxlength="2000" show-word-limit />
                </el-form-item>
            </div>
            <div class="custom-row">
                <el-form-item v-bind="$plus.attrFormItem('imageVOs', '案例图', !readonly)" class="custom-col-2">
                    <file-uploader v-if="!readonly" v-model="formData.imageVOs" :show-file-list="true"
                        :limit="20 * 1024 * 1024" accept="png|jpg|gif|jpeg|bmp" style="width: 100%;">
                        <template #default="scope">
                            <div class="custom-around-center">
                                <svg-icon icon="upload" :size="50" style="margin: 0 auto;" />
                                {{ scope.remind }}
                            </div>
                        </template>
                    </file-uploader>
                    <image-items :list="formData.imageVOs" :size="[200, 150]" :deletable="!readonly"
                        style="margin-top: 20px;">
                        <template #default="scope">
                            <base64-image :src="scope.data" />
                        </template>
                    </image-items>
                </el-form-item>
            </div>

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
import { callbackify } from "util";
import { trigger } from "@vue/reactivity";

@Options({
    name: 'CaseEditor',
})
export default class CaseEditor extends BasePage {

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
        if (resp && resp.data && resp.data[0]) {
            const vo = resp.data[0]
            this.formData = vo
        }
        this.loading = false
    }

    async doSave() {
        console.log(this.formData)
        const valid = await (this.$refs.form as any).validate()
        if (!valid) {
            return
        }
        const vo = this.formData
        // const imageVOs00 = vo.imageVOs00[0]
        // const imageVOs01 = vo.imageVOs01
        // imageVOs00.resourceType = this.FILE_TYPE.MASTER
        // imageVOs01.forEach((fsVO, i) => {
        //     fsVO.resourceType = this.FILE_TYPE.THUMBNAIL
        // })
        const homeVO = {
            ...vo,
            // imageVOs00: null,
            // imageVOs01: null,
            // imageVOs: [imageVOs00, ...imageVOs01],
        }
        this.saveLoading = true
        await api.saveHomeDetail({ homeVO })
        this.saveLoading = false
        ElMessage.success('保存成功！')
        this.$router.go(-1)
    }

}

</script>
<style lang='scss' scoped></style>