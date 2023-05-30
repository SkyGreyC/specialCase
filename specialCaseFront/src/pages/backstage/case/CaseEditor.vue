<template>
    <full-panel v-loading="loading" class="case-editor">
        <el-form ref="form" :model="formData" label-position="top" class="custom-row">
            <div class="custom-row">
                <el-form-item v-bind="$plus.attrFormItem('caseTitle', '病例编号', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.caseTitle }}</div>
                    <el-input v-else v-model="formData.caseTitle" maxlength="32" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('clinical', '病例', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.clinical }}</div>
                    <el-input v-else v-model="formData.clinical" maxlength="200" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('clinicalHistory', '病史', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.clinicalHistory }}</div>
                    <el-input v-else v-model="formData.clinicalHistory" maxlength="50" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('opinion', '病例描述', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.opinion }}</div>
                    <el-input v-else v-model="formData.opinion" maxlength="2000" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('machine', '机器', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.machine }}</div>
                    <el-input v-else v-model="formData.machine" maxlength="20" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('tracer', '示踪剂', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.tracer }}</div>
                    <el-input v-else v-model="formData.tracer" maxlength="20" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('diagnosis', '诊断', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">
                        <el-tag v-if="formData.diagnosis === '00'" effect="light" type="success">良性</el-tag>
                        <el-tag v-else effect="light" type="error">恶性</el-tag>
                    </div>
                    <el-select v-else v-model="formData.diagnosis">
                        <el-option label="良性" value="00"></el-option>
                        <el-option label="恶性" value="01"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('patientAge', '患者年纪', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.patientAge }}</div>
                    <el-input v-else v-model="formData.patientAge" maxlength="10" show-word-limit />
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('patientGender', '患者性别', !readonly)" class="custom-col-2">
                    <div v-if="readonly" class="custom-readonly">{{ formData.patientGender }}</div>
                    <el-input v-else v-model="formData.patientGender" maxlength="2" show-word-limit />
                </el-form-item>
            </div>
            <el-form-item class="custom-col-2">
                <!-- <div v-if="readonly" class="custom-readonly">{{ formData.patientGender }}</div> -->
                <div v-for="item in lesionVOs" :key="item.lesionInfo">
                    <el-input v-model="item.lesionLocation" class="el-input" placeholder="请填写病灶位置"></el-input>
                    <el-input v-model="item.lesionInfo" class="el-input" placeholder="请填写病灶信息"></el-input>
                    <el-button @click="addInput">添加</el-button>
                </div>
                <!-- <el-input v-else v-model="formData.patientGender" maxlength="2" show-word-limit /> -->
            </el-form-item>
            <div class="custom-row">
                <el-form-item v-bind="$plus.attrFormItem('caseImageVO', '病灶图', !readonly)" class="custom-col-1">
                    <file-uploader v-if="!readonly" v-model="formData.caseImageVO" :show-file-list="true" :multiple="false"
                        :limit="20 * 1024 * 1024" accept="png|jpg|gif|jpeg|bmp" style="width: 15%;">
                        <template #default="scope">
                            <div class="custom-around-center">
                                <svg-icon icon="upload" :size="50" style="margin: 0 auto;" />
                                {{ scope.remind }}
                            </div>
                        </template>
                    </file-uploader>
                    <image-items :list="formData.caseImageVO" :size="[200, 400]" :deletable="!readonly"
                        style="margin-top: 20px;">
                        <template #default="scope">
                            <base64-image :src="scope.data" />
                        </template>
                    </image-items>
                </el-form-item>
                <el-form-item v-bind="$plus.attrFormItem('labelImageVO', '原图', !readonly)" class="custom-col-1">
                    <file-uploader v-if="!readonly" v-model="formData.labelImageVO" :show-file-list="true" :multiple="false"
                        :limit="20 * 1024 * 1024" accept="png|jpg|gif|jpeg|bmp" style="width: 15%;">
                        <template #default="scope">
                            <div class="custom-around-center">
                                <svg-icon icon="upload" :size="50" style="margin: 0 auto;" />
                                {{ scope.remind }}
                            </div>
                        </template>
                    </file-uploader>
                    <image-items :list="formData.labelImageVO" :size="[200, 400]" :deletable="!readonly"
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
import * as api from '@/api/case'
import { ElMessage } from 'element-plus';

@Options({
    name: 'CaseEditor',
})
export default class CaseEditor extends BasePage {

    saveLoading = false

    formData: any = {}

    user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}

    lesionVOs = [
        { lesionLocation: '', lesionInfo: '' }
    ]

    FILE_TYPE = {
        LABEL: '01',
        CASE: '02'
    }

    get caseId() {
        return this.$route.query.id || this.$route.query.detailId
    }

    get readonly() {
        return !!this.$route.query.detailId
    }

    created() {
        if (this.caseId) {
            this.doQuery()
        }
    }

    async doQuery() {
        this.loading = true
        const resp = await api.findCaseDetail({
            caseId: this.caseId,
            userId: this.user.userId
        })
        if (resp && resp.data) {
            const vo = resp.data
            // vo.imageVOs01 = []
            // vo.imageVOs.forEach(async fsVO => {
            //     const { fileType } = fsVO.fileInfoVO
            //     if (fileType === this.FILE_TYPE.MASTER) {
            //         vo.imageVOs00 = [fsVO]
            //     } else if (fileType === this.FILE_TYPE.THUMBNAIL) {
            //         vo.imageVOs01.push(fsVO)
            //     }
            // })
            this.formData = vo
        }
        this.loading = false
    }

    // 动态添加
    addInput() {
        this.lesionVOs.push({ lesionLocation: '', lesionInfo: '' })
    }

    async doSave() {
        const valid = await (this.$refs.form as any).validate()
        if (!valid) {
            return
        }
        let vo = this.formData
        const lesionVOs = this.lesionVOs
        const userId = this.user.userId
        vo.caseImageVO.forEach((fsVO, i) => {
            fsVO.resourceType = this.FILE_TYPE.CASE
        })
        vo.labelImageVO.forEach((fsVO, i) => {
            fsVO.resourceType = this.FILE_TYPE.LABEL
        })
        console.log(vo)
        const caseVO = {
            ...vo,
            // imageVOs00: null,
            // imageVOs01: null,
            // imageVOs: [imageVOs00, ...imageVOs01],
            lesionVOs
        }
        this.saveLoading = true
        await api.saveCase({ caseVO, userId })
        this.saveLoading = false
        ElMessage.success('保存成功！')
        this.$router.go(-1)
    }

}

</script>
<style lang='scss' scoped></style>