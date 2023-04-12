<template>
    <el-container>
        <el-header>
            <!-- logo返回首页 -->
            <div class="custom-space-between">
                <el-menu :default-active="defaultActive()" :router="true" mode="horizontal" active-text-color="#009983"
                    class="header-menu">
                    <el-menu-item index="/">
                        <span>首页</span>
                    </el-menu-item>
                    <el-menu-item index="/case">
                        <span>专病库</span>
                    </el-menu-item>
                </el-menu>
                <div class="custom-space-between">
                    <el-popover :show-arrow="false" placement="bottom" trigger="hover" width="300px"
                        popper-class="custom-popover">
                        <template #reference>
                            <el-link :underline="false" style="margin-left: 30px;line-height: 32px;">
                                <span style="margin-left:10px;">{{ userInfo.userName }} </span>
                            </el-link>
                        </template>
                        <div class="personal-card">
                            <div @click="goPage('/backstage')">
                                <span>管理后台</span>
                            </div>
                        </div>
                        <div>
                            <div @click="goPage('/')">
                                <span>退出登录</span>
                            </div>
                        </div>
                    </el-popover>
                </div>
            </div>
        </el-header>
        <el-main>
            <div class="section section-dark" style="margin-bottom: 80px;">
                <div class="section-head">
                    <p class="section-subtext">首页介绍</p>
                    <p class="section-title">多类型案例介绍</p>
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
import { useRoute, useRouter } from 'vue-router'
import { useUser } from '@/store/user';

const router = useRouter()
const route = useRoute()
//登录相关
const user = useUser(); // 相当于setup方法


//首页前端栏

const userInfo = user.$state.userInfo || {}
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
.el-container {
    height: 100%;
}

.el-header,
.el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-main {
    background-color: #e9eef3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

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
}

// .personal-center {
//     height: 100vh;
//     background-size: 100% 100%;
//     background-repeat: no-repeat;
//     background-image: url('@/assets/images/index/bg.png');

//     &.white-bg {
//         background: #fff;
//     }

//     :deep(.el-backtop) {
//         background-color: transparent;
//         box-shadow: none;
//         width: 36px;
//         height: 36px;
//     }
// }

// .app-logo {
//     cursor: pointer;
//     display: flex;
//     padding: 3px 20px;
//     box-sizing: border-box;
// }
</style>