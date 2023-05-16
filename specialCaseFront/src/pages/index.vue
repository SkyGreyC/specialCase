<template>
    <div class="form-wrap">
        <ul class="menu-tab">
            <li :class="{ current: current_menu === item.type }" v-for="item in data.tab_menu" :key="item.type"
                @click="toggleMenu(item.type)">
                {{ item.label }}
            </li>
        </ul>
        <el-form label-width="80px" ref="formRef" :model="data.form" :rules="rules">
            <el-form-item label="用户名" prop="userName">
                <el-input v-model="data.form.userName"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="data.form.password"></el-input>
            </el-form-item>
            <el-form-item v-show="current_menu === 'register'" label="确认密码" prop="password">
                <el-input v-model="data.form.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="submit(data.form)">{{ current_menu === "login" ? "登录" : "注册" }}</el-button>
                <el-button v-show="current_menu === 'login'" @click="goPage('/resetPwd')">忘记密码</el-button>
                <!-- <el-button @click="reset(formRef)">重置</el-button> -->
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUser } from '@/store/user';
import { loginAPI } from '@/request/api'


const router = useRouter()
const route = useRoute()
//登录相关
const user = useUser(); // 相当于setup方法
// 单向数据输出,双向数据绑定,还需要API方法的调用
const formSize = ref('default');  // formSize.value


const data = reactive({
    form: {
        userName: 'cyt',
        password: '123456'
    },
    tab_menu:
        [
            { type: "login", label: "登录" },
            { type: "register", label: "注册" },
        ]
})

const toggleMenu = (type) => {
    current_menu.value = type;
};
let current_menu = ref(data.tab_menu[0].type);

// 获取页面组件对象
const formRef = ref();
const rules = {
    username: [
        { required: true, message: '必须输入用户名', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '必须输入密码', trigger: 'blur' },
    ]
}

// 传递参数的方式
const submit = async (form) => {
    try {
        let res = await loginAPI(form);
        console.log(res, '测试数据');
        if (res.code === '000') {
            var userInfo = res.data.userInfo;
            // user.setUserInfo(userInfo);
            user.setToken(userInfo.userId);
            // token = userInfo.userId;
            window.localStorage.setItem("token", userInfo.userId);
            goPage('/home')
        }
    } catch (err) {
        console.log(err)
    }
}

//首页前端栏


// const userInfo = user.$state.userInfo || {}
// let token = user.$state.token || ''
const goPage = (path: any) => {
    router.push({ path })
}
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

.form-wrap {
    width: 320px;
    padding-top: 100px;
    margin: auto;
}

.form-label {
    display: block;
    color: #fff;
    font-size: 14px;
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