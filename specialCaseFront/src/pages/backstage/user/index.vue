<template>
    <page-table v-loading="loading" :data="tableData" :page="page" class="user-manage" @page-change="doQuery">
        <template #topbar>
            <toolbar :label="`用户列表(${tableData.total})`">
                <template #actions>
                    <el-button type="primary" @click="doEdit()">新增</el-button>
                </template>
            </toolbar>
        </template>
        <el-table-column prop="userName" label="用户名" />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column label="用户类型">
            <template #default="scope">
                <el-tag v-if="scope.row.userType === '00'" effect="light" type="success">一般用户</el-tag>
                <el-tag v-else-if="scope.row.userType === '01'" effect="light" type="success">中级用户</el-tag>
                <el-tag v-else effect="light" type="success">高级用户</el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-link :underline="false" type="primary" @click="doLook(scope.row)">查看</el-link>
                <el-link :underline="false" type="primary" @click="doEdit(scope.row)" style="margin-left: 10px;">编辑</el-link>
                <el-link :underline="false" type="danger" @click="doDelete(scope.row)" style="margin-left: 10px;">删除</el-link>
            </template>
        </el-table-column>
    </page-table>
</template>

<script lang="ts">
import { Options } from "vue-class-component";
import BasePage from "@/pages/BasePage";
import * as api from '@/api/user'
import { ElMessageBox, ElMessage } from 'element-plus';

@Options({
    name: 'UserManage',
})
export default class UserManage extends BasePage {

    tableData = { records: [], total: 0 }

    page = { current: 1, size: 20 }

    created() {
        this.doQuery()
    }

    async doQuery() {
        this.loading = true
        const resp = await api.findUserList({
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

    async doDelete(row) {
        const flag = await ElMessageBox.confirm('确定删除？')
        if (flag) {
            this.loading = true
            await api.deleteUser({ userId: row.userId })
            this.loading = false
            ElMessage.success('删除成功！')
            this.doQuery()
        }
    }

    doLook(row: any) {
        this.$router.push({
            path: this.$route.path + '/editor',
            query: {
                detailId: row.userId
            }
        })
    }

    doEdit(row: any = {}) {
        this.$router.push({
            path: this.$route.path + '/editor',
            query: {
                id: row.userId
            }
        })
    }

}

</script>
<style lang='scss' scoped></style>