<template>
    <!-- logo返回首页 -->
    <div style="display: flex;padding: 1px 0;">
        <div style="width: 220px" class="app-logo" @click="toHome">
            <svg-icon icon="logo" height="48px" width="48px" />
            <span
                style="font-family: FangSong,sans-serif;font-size: 18px;font-weight: bolder;margin-left: 0px;color: #009983;">
                川大专病库
            </span>
        </div>
    </div>
    <div class="custom-space-between" style="height: 32px;margin: 9px 50px;">
        <el-menu :default-active="defaultActive()" :router="true" :ellipsis="false" mode="horizontal"
            @select="handleMenuSelect" active-text-color="#009983" class="header-menu">
            <el-menu-item index="/">
                <span class="menu-label">首页</span>
            </el-menu-item>
            <el-menu-item index="/case">
                <span class="menu-label">专病库</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>

<script lang="ts">
import { Options } from "vue-class-component";
import BasePage from "../BasePage";
import { useRoute, useRouter } from 'vue-router'
import { useUser } from '@/store/user';
import { ElMessageBox, ElMessage } from 'element-plus';

@Options({
    name: 'UserHeader'
})
export default class UserHeader extends BasePage {

    router = useRouter()
    route = useRoute()
    //登录相关
    user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}

    defaultActive = () => '/' + (this.route.path.split('/')[1] || '')

    toHome = () => this.router.push('/')

    handleMenuSelect(index) {
        if ("/case" === index) {
            this.user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}
            if (this.user.nickName) {
                //nothing
            } else {
                ElMessage.error('请登录后查看')
                this.router.push('/')
            }
        }
    }
}
</script>
<style lang='scss' scoped>
.personal-center {
    height: 100%;
    width: 100%;
    margin: 0px;
    padding: 0px;
    // height: 100vh;
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-image: url('@/assets/images/index/bg.png');

    &.white-bg {
        background: #fff;
    }

    :deep(.el-backtop) {
        background-color: transparent;
        box-shadow: none;
        width: 36px;
        height: 36px;
    }
}

.app-logo {
    cursor: pointer;
    display: flex;
    padding: 0px 20px;
    box-sizing: border-box;
}

.el-menu.header-menu {
    --el-menu-bg-color: transparent;
    width: 150px;
    display: flex;
    justify-content: space-between;
    border-bottom: none !important;

    .el-menu-item {
        padding: 14px 0;
        border-bottom: none !important;
        background-color: transparent !important;
        line-height: 1;

        .menu-label {
            display: flex;
            justify-content: center;
            min-width: 40px;
            font-size: 14px;
        }

        &.is-active:after {
            content: '';
            display: block;
            height: 2px;
            background-color: currentColor;
            position: absolute;
            left: 0;
            bottom: -1px;
            right: 0;
        }
    }

    .personal-card {
        .personal-card__header {
            background-image: url('@/assets/images/personal-card.png');
            color: #fff;
            display: flex;
            padding: 15px 20px;
            height: 80px;
            box-sizing: border-box;
        }

        .personal-menu-item {
            cursor: pointer;
            padding: 12px 20px;
            box-sizing: border-box;
            display: inline-flex;
            justify-content: space-between;
            width: 100%;
            line-height: 16px;
            margin: 2px 0;
        }

        &:hover {
            color: var(--el-color-primary);
        }

        >span {
            display: inline-flex;
        }
    }
}
</style>