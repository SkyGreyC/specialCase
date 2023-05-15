<template>
    <normal-table v-loading="loading" :data="tableData" :page="page" class="case-manage" >
        <template #topbar>
            <toolbar :label="`案例列表`">
                <template #actions>
                    <el-input v-model="search.tracer" prefix-icon="Search" claerable placeholder="搜索"
                        style="margin-right: 10px;width: 220px;" @keyup.enter="reload(false)" />
                    <el-button type="primary" @click="doEdit()">上传</el-button>
                </template>
            </toolbar>
        </template>
        <el-table-column prop="homeTitle" label="案例标题" />
        <el-table-column prop="homeInfo" label="案例描述" />
        <el-table-column label="操作">
            <template #default="scope">
                <el-link :underline="false" type="primary" @click="doLook(scope.row)">查看</el-link>
                <el-link :underline="false" type="primary" @click="doEdit(scope.row)" style="margin-left: 10px;">编辑</el-link>
                <el-link :underline="false" type="danger" @click="doDelete(scope.row)" style="margin-left: 10px;">删除</el-link>
            </template>
        </el-table-column>
    </normal-table>
</template>

<script lang="ts">
import { Options } from "vue-class-component";
import BaseResourceList from "@/pages/BaseResourceList";
import * as api from '@/api/home'
import { ElMessageBox, ElMessage } from 'element-plus';

@Options({
    name: 'CaseManage',
})
export default class CaseManage extends BaseResourceList {

    tableData = { records: [], total: 0 }

    page = { current: 1, size: 20 }

    created() {
        this.doQuery()
    }

    async doQuery() {
        this.loading = true
        const resp = await api.findHomeInfo({
            page: this.page, homeVO: {}
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

    async doDelete(row) {
        const flag = await ElMessageBox.confirm('确定删除？')
        if (flag) {
            this.loading = true
            await api.deleteHomeInfo({ homeId: row.homeId })
            this.loading = false
            ElMessage.success('删除成功！')
            this.doQuery()
        }
    }

    doLook(row: any) {
        this.$router.push({
            path: this.$route.path + '/editor',
            query: {
                detailId: row.homeId
            }
        })
    }

    doEdit(row: any = {}) {
        this.$router.push({
            path: this.$route.path + '/editor',
            query: {
                id: row.homeId
            }
        })
    }

}

</script>
<style lang='scss' scoped></style>