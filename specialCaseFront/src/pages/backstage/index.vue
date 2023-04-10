<template>
    <el-container>
        <el-aside with="240px" height="100%">
            <el-scrollbar>
                <el-menu ref="menu" :default-active="getRoutePath('user')" :collapse-transition="false" router
                    style="border: 0;">
                    <el-sub-menu v-for="(item, i) in menuList" :key="i" :index="i + ''">
                        <template #title>
                            <span>{{ item.label }}</span>
                        </template>
                        <el-menu-item v-for="sub in item.children" :key="sub.name" :index="getRoutePath(sub.name)">
                            {{ sub.label }}
                        </el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </el-scrollbar>
        </el-aside>
        <el-container>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup lang="ts">
// import { Options } from 'vue-property-decorator'

// @Options({
//     name: 'BackstageLayout'
// })
class BakstageLayout {

}
const collapsed = false

const menuList = [
    {
        label: '后台管理',
        children: [
            {
                label: '用户管理',
                name: 'user'
            }
        ]
    }
]

const getRoutePath = (name: any) => {
    return `/backstage/${name}`
}
</script>
<style lang='scss' scoped></style>