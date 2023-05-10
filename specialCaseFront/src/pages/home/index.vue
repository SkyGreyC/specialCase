<template>
    <el-container :class="{ 'white-bg': isWhiteBg }" class="personal-center">
        <el-header class="custom-space-between" style="border-bottom: var(--custom-border);">
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
                    active-text-color="#009983" class="header-menu">
                    <el-menu-item index="/home">
                        <span class="menu-label">首页</span>
                    </el-menu-item>
                    <el-menu-item index="/case">
                        <span class="menu-label">专病库</span>
                    </el-menu-item>
                </el-menu>
                <!-- <div class="custom-space-between" style="height: 32px;margin: 9px 20px;">
                    <el-popover :show-arrow="false" placement="bottom" trigger="hover" width="300px"
                        popper-class="custom-popover">
                        <template #reference>
                            <el-link :underline="false" style="margin-left: 30px;line-height: 32px;">
                                <span style="margin-left:10px;">{{ nickName }} </span>
                            </el-link>
                        </template>
                        <div class="personal-card">
                            <div class="personal-card__header">
                                <div class="custom-title" style="line-height: 26px;">{{ nickName }}</div>
                            </div>
                            <div class="personal-card__body">
                                <div class="personal-menu-item" @click="goPage('/backstage')">
                                    <span>
                                        <svg-icon :size="16" icon="personal-message" style="margin-right: 12px;" />
                                        管理后台
                                    </span>
                                </div>
                            </div>
                            <div @click="goPage('/')">
                                <span>
                                    <svg-icon :size="16" icon="loginout" style="margin-right: 12px;" />
                                    退出登录
                                </span>
                            </div>
                        </div>
                    </el-popover>
                </div> -->
            </div>
        </el-header>
        <el-main>
            <div class="section" style="margin-bottom: 80px;">
                <div class="section-head">
                    <!-- <p class="section-subtext">登录</p> -->
                    <p class="section-title">个人中心</p>
                </div>
                <LoginSection />
            </div>
            <div class="section section-dark" style="margin-bottom: 80px;">
                <div class="section-head">
                    <p class="section-subtext">首页介绍</p>
                    <p class="section-title">多类型案例介绍</p>
                </div>
                <HomeSection />
            </div>
            <div class="section section-aliceblue" style="margin-bottom: 80px;">
                <div class="section-head">
                    <p class="section-title">案例介绍</p>
                </div>
                <CaseSection />
            </div>
        </el-main>
        <el-footer>版权所有</el-footer>
    </el-container>
</template>

<script setup lang="ts">
import { ref, computed, reactive } from 'vue'
import CaseSection from './CaseSection.vue';
import HomeSection from './HomeSection.vue';
import LoginSection from './LoginSection.vue';
import { useRoute, useRouter } from 'vue-router'
import { useUser } from '@/store/user';

const router = useRouter()
const route = useRoute()
//登录相关
const user = useUser(); // 相当于setup方法


//首页前端栏

const nickName = user.$state.nickName || {}
let token = user.$state.token || ''
const defaultActive = () => '/' + (route.path.split('/')[1] || '')
const toHome = () => router.push('/')
const goPage = (path: any) => {
    router.push({ path })
}
/**
 * 判断是否为白底背景
 */
const isWhiteBg = computed(() => {
    for (const m of route.matched) {
        if (m.meta && m.meta.whiteBg) {
            return true
        }
    }
    return false
})
</script>
<style lang='scss' scoped>
.section {
    .section-head {
        margin: var(--custom-size-gap-lg) 0;
        text-align: center;
    }

    .section-subtext {
        margin: var(--custom-size-gap-sm) 0;
        opacity: 0.6;
    }

    .section-title {
        margin: var(--custom-size-gap-sm) 0;
        font-size: 32px;
        font-weight: 500;
    }

    &.section-dark {
        background-color: var(--custom-fill-dark);
        padding: 30px 0;

        .section-head {
            color: #fff;
        }
    }
    &.section-aliceblue {
        background-color: var(--custom-fill-aliceblue);
        padding: 30px 0;
    }
}

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

.el-scrollbar {
    > :deep(.el-scrollbar__bar) {
        z-index: 10;
    }
}

.el-main {
    padding: 0;
    height: calc(100vh - 50px);
}

.el-header {
    --el-header-padding: 0;
    height: 50px;
    line-height: 50px;
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