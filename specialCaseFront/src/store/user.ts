// 3. 定义一个Store
// 3.1 引入定义Store的函数
import { defineStore } from 'pinia';
// 4.按约定俗成 返回useXxx
export const useUser = defineStore({
    id:'user',
    state(){
        return {
            userInfo:JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')):{}
        }
    },
    // 修改方式
    actions:{
        setUserInfo(user){
            this.userInfo = user;
        },
        setUserName(user){
            this.userName = user;
        },
        setNickName(user){
            this.nickName = user;
        },
        // { userMenu:值  }
        setToken(token) {
            this.token = token;
        }
    }
})