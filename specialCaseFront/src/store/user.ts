// 3. 定义一个Store
// 3.1 引入定义Store的函数
import { defineStore } from 'pinia';
// 4.按约定俗成 返回useXxx
export const useUser = defineStore({
    id:'user',
    state(){
        return {
            userName:sessionStorage.getItem('userName') ? sessionStorage.getItem('userName'):'',
            nickName:sessionStorage.getItem('nickName') ? sessionStorage.getItem('nickName'):'',
            token:sessionStorage.getItem('token') ? sessionStorage.getItem('token'):''
        }
    },
    // 修改方式
    actions:{
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