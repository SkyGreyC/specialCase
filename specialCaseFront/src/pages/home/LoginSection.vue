<template>
    <div class='login-section'>
        <div class="custom-page-container">
            <div class="content">
                <el-card class="card-item">
                    <template #header>
                        <div class="animation" style="height: 100px;">
                            <div>
                                <svg-icon :size="40" icon="personal-message" />
                                <div v-if="user.nickName">
                                    <h1>{{ user.nickName }}您好，欢迎使用</h1>
                                    <div class="actions">
                                        <svg-icon :size="20" icon="loginout" @click="loginOut()" />
                                    </div>
                                </div>
                                <div v-else>
                                    <ul class="menu-tab">
                                        <li :class="{ current: this.current_menu === item.type }"
                                            v-for="item in formData.tab_menu" :key="item.type"
                                            @click="toggleMenu(item.type)">
                                            {{ item.label }}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </template>
                    <div>
                        <div v-if="user.nickName" class="list-item">
                            <div>
                                <h3>具体工作</h3>
                                <el-button type="primary" style="height: 40px;" @click="goPage('/case')">病例查看</el-button>
                            </div>
                            <div>
                                <h3>个人信息</h3>
                                <el-button type="primary" plain style="height: 40px;" @click="doLook">我的信息</el-button>
                                <el-button type="primary" plain style="height: 40px;"
                                    @click="goPage('/case')">我的收藏</el-button>
                            </div>
                            <div v-if="user.userType === '02'">
                                <h3>后台管理</h3>
                                <div class="personal-menu-item" @click="goPage('/backstage')">
                                    <span>
                                        <el-button type="primary" style="height: 40px;">管理界面</el-button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div v-else class="form-wrap">
                            <el-form label-width="80px" ref="formRef" :model="formData.form" :rules="rules">
                                <el-form-item label="用户名" prop="userName">
                                    <el-input v-model="formData.form.userName"></el-input>
                                </el-form-item>
                                <el-form-item label="密码" prop="password">
                                    <el-input type="password" v-model="formData.form.password">
                                    </el-input>
                                </el-form-item>
                                <el-form-item v-show="this.current_menu === 'register'" label="确认密码" prop="password">
                                    <el-input type="password" v-model="confirmPwd"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button @click="submit(formData.form)">{{ this.current_menu === "login" ? "登录" : "注册"
                                    }}</el-button>
                                    <!-- <el-button v-show="current_menu === 'login'" @click="goPage('/resetPwd')">忘记密码</el-button> -->
                                    <!-- <el-button @click="reset(formRef)">重置</el-button> -->
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                </el-card>
                <div class="step-bg-container">
                    <div class="step-bg" />
                </div>
            </div>
        </div>
    </div>
    <personal-card v-model="previewIndex" :preview="preview" />
</template>

<script lang="ts">
import { ref, inject, reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { loginAPI, registerAPI } from '@/request/api'
import { Options } from "vue-class-component";
import BasePage from "../BasePage";
import PersonalCard from "@/pages/home/PersonalCard.vue";
import { ElMessageBox, ElMessage } from 'element-plus';

@Options({
    name: 'LoginSection',
    components: {
        PersonalCard
    }
})
export default class LoginSection extends BasePage {
    router = useRouter()
    route = useRoute()
    //登录相关
    // user = useUser(); // 相当于setup方法
    user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}
    // 单向数据输出,双向数据绑定,还需要API方法的调用
    formSize = ref('default');  // formSize.value

    previewIndex = -1

    preview = {}

    confirmPwd = ''

    formData = reactive({
        form: {
            userName: '',
            password: ''
        },
        tab_menu:
            [
                { type: "login", label: "登录" },
                { type: "register", label: "注册" },
            ]
    })

    toggleMenu(type) {
        this.current_menu = type;
    };

    current_menu = this.formData.tab_menu[0].type;

    // 获取页面组件对象
    formRef = ref();
    rules = {
        username: [
            { required: true, message: '必须输入用户名', trigger: 'blur' },
        ],
        password: [
            { required: true, message: '必须输入密码', trigger: 'blur' },
        ]
    }

    // 传递参数的方式
    async submit(form) {
        try {
            if (this.current_menu === 'register') {
                if (form.password === this.confirmPwd) {
                    let res = await registerAPI(form);
                    if (res.code === '000') {
                        ElMessage.error(res.message)
                        this.goPage('/')
                    } else {
                        ElMessage.error(res.errMessage)
                    }
                } else {
                    ElMessage.error('密码不一致')
                }
            } else {
                let res = await loginAPI(form);
                if (res.code === '000') {
                    var userInfo = res.data.userInfo;
                    this.user = userInfo;
                    window.sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
                    this.goPage('/')
                } else {
                    ElMessage.error(res.errMessage)
                }
            }
        } catch (err) {
            console.log(err)
        }
    }

    //注销登录信息
    loginOut = () => {
        window.sessionStorage.clear();
        this.user = {}
    };

    //首页前端栏

    goPage(path: any) {
        this.$router.push({ path })
    }

    // goMark(path: any) {
    //     this.$router.push({ path: path, query: { type: 'mark' } })
    // }

    doLook() {
        this.preview = this.user
        this.previewIndex = 1
    }

}

</script>
<style lang='scss' scoped>
.animation {
    position: relative;

    .animation-item {
        position: absolute;
        top: 0;
        left: 0;
        width: 520px;
        display: none;
        animation-fill-mode: forwards !important;

        &.item-show {
            display: block;
            animation: itemShow 3s;
        }

        &.item-hide {
            display: block;
            animation: itemHide 3s;
        }
    }
}

.content {
    width: 1240px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;
    z-index: 2;

    .card-item {
        width: 520px;
        height: 440px;
        border-color: white;
        background-color: #fde0ff1a;
        position: relative;

        .bottom-line {
            margin: 20px 0 0;
        }

        .list-item {
            margin: 0 0 20px 0;
        }

        .actions {
            top: 13px;
            right: 20px;
            height: 32px;
            position: absolute;

            .svg-icon {
                cursor: pointer;

                &+.svg-icon {
                    margin-top: 10px;
                }
            }
        }
    }
}

.step-bg-container {
    position: relative;
    width: 666px;
    height: 440px
}

.step-bg {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    // display: none;
    background-size: 100%;
    background-repeat: no-repeat;
    background-image: url('@/assets/images/login-bg.png');
}

.form-wrap {
    width: 320px;
    margin: auto;

    .el-form {
        padding: 30px 0;
    }
}

.menu-tab {
    text-align: center;

    li {
        display: inline-block;
        padding: 10px 24px;
        margin: 0 10px;
        color: #db6c6c;
        font-size: 14px;
        border-radius: 5px;
        cursor: pointer;

        &.current {
            background-color: rgba(0, 0, 0, 0.1);
        }
    }
}
</style>