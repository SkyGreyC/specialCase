import { RootStateType } from '@/store'
import { Module } from 'vuex'
import { setToken } from '@/utils/token'
import * as api from '@/api/login'

export interface UserStateType {
    userInfo: { [key: string]: any }
}
const User: Module<UserStateType, RootStateType> = {
    namespaced: true,
    state: {
        userInfo: null,
    },
    getters: {
        userInfo: (state) => state.userInfo
    },
    mutations: {
        SET_LOGIN_INFO(state: UserStateType, userInfo: any): void {
            state.userInfo = userInfo
        }
    },
    actions: {
        async login({ commit }, obj: object): Promise<Boolean> {
            const resp = await api.doLogin(obj)
            if (resp && resp.data) {
                const { scLogin,userInfo } = resp.data
                setToken(scLogin)
                commit('SET_LOGIN_INFO', userInfo)
                return true
            } else {
                return false
            }
        }
    }
}

export default User