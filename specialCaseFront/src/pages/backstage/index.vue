<template>
    <el-container class="backstage-center">
        <el-aside with="240px" height="100%">
            <el-scrollbar>
                <!-- logo返回首页 -->
                <div style="display: flex;padding: 1px 0;">
                    <div style="width: 220px" class="app-logo" @click="toHome">
                        <svg-icon icon="logo" height="48px" width="48px" />
                        <span
                            style="font-family: FangSong,sans-serif;font-size: 18px;font-weight: bolder;margin-left: 0px;margin-top: 15px;color: #009983;">
                            川大专病库
                        </span>
                    </div>
                </div>
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
                <el-button type="default" class="to-personal-center" @click="toHome">
                    <svg-icon icon="goback" :size="18" style="margin-right: 5px;" />
                    返回首页
                </el-button>
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
import { useRoute, useRouter } from 'vue-router'
// import { Options } from 'vue-property-decorator'

// @Options({
//     name: 'BackstageLayout'
// })

const router = useRouter()
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
            },
            {
                label: '病例管理',
                name: 'case'
            },
            {
                label: '案例管理',
                name: 'home'
            },
        ]
    }
]

const getRoutePath = (name: any) => {
    return `/backstage/${name}`
}

const toHome = () => router.push('/')
</script>
<style lang='scss' scoped>
.app-logo {
    cursor: pointer;
    display: flex;
    padding: 0px 20px;
    box-sizing: border-box;
}

.to-personal-center {
    position: absolute;
    bottom: 20px;
    left: 45px;
    height: 40px;
    width: 200px;
    color: var(--el-color-primary);

    &:hover {
        background-color: var(--el-color-primary-light-9);
    }
}
</style>