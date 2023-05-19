<template>
    <normal-table v-loading="loading" :data="tableData" :page="page" class="x-wrap" @page-change="doQuery">
        <el-table-column prop="caseTitle" label="编号" />
        <el-table-column prop="clinicalHistory" label="关键字" />
        <el-table-column prop="machine" label="机器" />
        <el-table-column prop="tracer" label="示踪剂" />
        <el-table-column prop="updateTime" label="上传时间" />
        <el-table-column label="操作">
            <template #default="scope">
                <el-link :underline="false" type="primary" @click="doLook(scope.row)">查看</el-link>
            </template>
        </el-table-column>
    </normal-table>
    <preview-viewer v-model="previewIndex" :preview="preview" />
</template>

<script lang="ts">
import * as api from '@/api/case'
import { Options } from "vue-class-component";
import BasePage from "../BasePage";
import { ElMessageBox, ElMessage } from 'element-plus';
import PreviewViewer from "@/pages/case/components/PreviewViewer.vue";

const queryData = {
    page: { current: 1, size: 10 },
    homeVO: { homeType: '00' }
}
@Options({
    name: 'CaseSection',
    components: {
        PreviewViewer
    }
})
export default class CaseSection extends BasePage {
    tableData = { records: [], total: 0 }

    page = { current: 1, size: 20 }

    previewIndex = -1

    preview = {}

    user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}

    created() {
        this.doQuery()
    }

    async doQuery() {
        this.loading = true
        const resp = await api.findCaseList({
            page: this.page, userVO: {}
        })
        this.loading = false
        if (resp) {
            const records = resp.data || []
            this.tableData = {
                records,
                total: resp.page ? resp.page.total : 0
            }
        }
    }

    async doLook(row: any) {
        const resp = await api.findCaseDetail({
            caseId: row.caseId,
            userId: this.user.userId
        })
        if (resp && resp.data) {
            const vo = resp.data
            this.preview = vo
        }
        console.log(this.preview)
        this.previewIndex = 1
    }

}
</script>
<style lang='scss' scoped>
.x-wrap {
    position: relative;
    margin: auto;
    width: 1230px;
    height: 100%;
}
</style>