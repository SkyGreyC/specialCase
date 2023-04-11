<template>
    <el-table :data="tableData()" style="width: 100%">
        <template>
            <!-- <el-table-column type="index" width="50" />
            <el-table-column label="登录名" prop="userName" min-width="120px" />
            <el-table-column label="昵称" prop="nickName" min-width="120px" />
            <el-table-column label="用户类型" prop="userType" min-width="120px" />
            <el-table-column label="注册时间" prop="createTime" min-width="150px" />
            <el-table-column label="操作" min-width="150px">
                <template #default="scope">
                    <el-link :underline="false" type="primary" @click="doLook(scope.row)">查看</el-link>
                    <el-link :underline="false" type="primary" @click="doEdit(scope.row)">编辑</el-link>
                    <el-link :underline="false" type="danger" @click="doDelete(scope.row)">删除</el-link>
                </template>
            </el-table-column> -->
            <el-table-column type="index" width="50" />
            <el-table-column prop="date" label="日期" width="180" />
            <el-table-column prop="name" label="名字" width="180" />
            <el-table-column prop="address" label="地址" />
        </template>
    </el-table>
    <div class="example-pagination-block">
        <el-pagination background layout="prev, pager, next ,total,sizes" :total="total"
            @current-change="handleCurrentChange" @size-change="handleSizeChange" />
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import BaseResourceList from '@/pages/BaseResourceList';

export default defineComponent({
    name: "HomeView",
    components: {},
    setup() {
        //表格的全数据（这里是自定义的列表，要看分页效果自行往此数组内加数据）
        // const allTableData = [
        //     {
        //         createTime: "2016-05-03",
        //         userName: "Tom",
        //         nickName: "Tom",
        //         userType: "No. 189, Grove St, Los Angeles",
        //     },
        //     {
        //         createTime: "2016-05-02",
        //         userName: "Tom",
        //         nickName: "Tom",
        //         userType: "No. 189, Grove St, Los Angeles",
        //     },
        //     {
        //         createTime: "2016-05-04",
        //         userName: "Tom",
        //         nickName: "Tom",
        //         userType: "No. 189, Grove St, Los Angeles",
        //     },
        //     {
        //         createTime: "2016-05-01",
        //         userName: "Tom",
        //         nickName: "Tom",
        //         userType: "No. 189, Grove St, Los Angeles",
        //     },
        // ];
        const allTableData = [
            {
                date: "2016-05-03",
                name: "Tom",
                address: "No. 189, Grove St, Los Angeles",
            },
            {
                date: "2016-05-02",
                name: "Tom",
                address: "No. 189, Grove St, Los Angeles",
            },
            {
                date: "2016-05-04",
                name: "Tom",
                address: "No. 189, Grove St, Los Angeles",
            },
            {
                date: "2016-05-01",
                name: "Tom",
                address: "No. 189, Grove St, Los Angeles",
            },
        ];
        //表格用到的参数
        const state = reactive({
            page: 1,
            limit: 10,
            total: allTableData.length,
        });
        //前端限制分页（tableData为当前展示页表格）
        const tableData = () => {
            return allTableData.filter(
                (item, index) =>
                    index < state.page * state.limit &&
                    index >= state.limit * (state.page - 1)
            );
        };
        //改变页码
        const handleCurrentChange = (e) => {
            state.page = e;
        };
        //改变页数限制
        const handleSizeChange = (e) => {
            state.limit = e;
        };
        return {
            allTableData,
            tableData,
            handleCurrentChange,
            handleSizeChange,
            ...toRefs(state),
        };
    },
});

</script>
<style lang='scss' scoped></style>