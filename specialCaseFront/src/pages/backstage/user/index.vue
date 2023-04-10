<template>
    <page-table v-loading="BaseResourceList.loading" :data="BaseResourceList.tableData" :page="BaseResourceList.page"
        :multiple="true">
        <template>
            <el-table-column label="登录名" prop="userName" min-width="120px" />
            <el-table-column label="昵称" prop="nickName" min-width="120px" />
            <el-table-column label="用户类型" prop="userType" min-width="120px" />
            <el-table-column label="注册时间" prop="createTime" min-width="150px" />
            <el-table-column label="操作" min-width="150px" />
        </template>
    </page-table>
</template>

<script setup lang="ts">
import BaseResourceList from '@/pages/BaseResourceList';

data: {

}
</script>
<style lang='scss' scoped></style>