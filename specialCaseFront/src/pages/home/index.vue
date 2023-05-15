<template>
    <el-container :class="{ 'white-bg': isWhiteBg }" class="personal-center">
        <el-header class="custom-space-between" style="border-bottom: var(--custom-border);">
            <UserHeader />
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
                    <p class="section-subtext">系统介绍</p>
                    <p class="section-title">多类型案例介绍</p>
                </div>
                <HomeSection />
            </div>
            <div class="section section-aliceblue" style="margin-bottom: 80px;">
                <div class="section-head">
                    <p class="section-title">病例列表</p>
                    <p @click="showMore" class="section-subtext2">查看更多</p>
                </div>
                <CaseSection />
            </div>
            <CommonFooter type="default" />
        </el-main>
        <el-footer>版权所有</el-footer>
    </el-container>
</template>

<script setup lang="ts">
import { ref, computed, reactive } from 'vue'
import UserHeader from './UserHeader.vue';
import CaseSection from './CaseSection.vue';
import HomeSection from './HomeSection.vue';
import LoginSection from './LoginSection.vue';
import CommonFooter from './CommonFooter.vue';
import { useRoute, useRouter } from 'vue-router'
import { useUser } from '@/store/user';
import { ElMessageBox, ElMessage } from 'element-plus';

const router = useRouter()
const route = useRoute()
//登录相关
// const user = useUser(); // 相当于setup方法
const user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}


//首页前端栏

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

const showMore= () => {
              if(!user) {
              }else {
                ElMessage.error('请登录后查看')
              }
            }
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

    .section-subtext2 {
        cursor: pointer;
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