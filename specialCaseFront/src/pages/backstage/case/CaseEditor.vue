<template>
    <full-panel v-loading="loading" class="case-editor">
        <el-form ref="form" :model="formData" label-position="top" class="custom-row">
            <el-form-item v-bind="$plus.attrFormItem('caseTitle', '病例编号', !readonly)"
                :rules="[{ validator: accountInput, trigger: 'blur' }]" class="custom-col-2">
                <div v-if="readonly" class="custom-readonly">{{ formData.caseTitle }}</div>
                <el-input v-else v-model="formData.caseTitle" maxlength="32" show-word-limit />
            </el-form-item>
            <el-form-item v-bind="$plus.attrFormItem('clinical', '病例描述', !readonly)" class="custom-col-2">
                <div v-if="readonly" class="custom-readonly">{{ formData.clinical }}</div>
                <el-input v-else v-model="formData.clinical" maxlength="200" show-word-limit />
            </el-form-item>
            <el-form-item v-bind="$plus.attrFormItem('clinicalHistory', '病史', !readonly)" class="custom-col-2">
                <div v-if="readonly" class="custom-readonly">{{ formData.clinicalHistory }}</div>
                <el-input v-else v-model="formData.clinicalHistory" maxlength="50" show-word-limit />
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
            <el-form-item v-bind="$plus.attrFormItem('imageVO01', '原图', !readonly)" class="custom-col-2">
                <image-uploader v-model="formData.imageVO01" :disabled="readonly" style="--custom-width-standard: 30vw;" />
            </el-form-item>
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
import * as api from '@/api/user'
import { ElMessage } from 'element-plus';
import { callbackify } from "util";
import { trigger } from "@vue/reactivity";

@Options({
    name: 'UserEditor',
})
export default class UserEditor extends BasePage {

    saveLoading = false

    formData: any = {}

    get userId() {
        return this.$route.query.id || this.$route.query.detailId
    }

    get readonly() {
        return !!this.$route.query.detailId
    }

    created() {
        this.doQuery()
    }

    async doQuery() {
        if (!this.userId) {
            return
        }
        this.loading = true
        const resp = await api.findUserByUserId({
            userId: this.userId
        })
        this.loading = false
        this.formData = (resp && resp.data) || {}
    }

    async doSave() {
        const valid = await (this.$refs.form as any).validate()
        if (!valid) {
            return
        }
        this.saveLoading = true
        await api.saveUser({ userVO: this.formData })
        this.saveLoading = false
        ElMessage.success('发布成功！')
        this.$router.go(-1)
    }

    accountInput(rule, val, callback) {//账号的实时输入
        if (val) {
            const reg = /^(?![0-9]+$)(?!.*?_$)[a-z][a-z0-9_]{1,40}$/
            if (reg.test(val)) {
                callback()
            } else {
                callback(new Error('请输入字母或者以字母开头的字母数字组合，不支持大写字母'))
            }
        } else {
            callback(new Error('用户名不能为空'))
        }
    }

}

</script>
<style lang='scss' scoped></style>