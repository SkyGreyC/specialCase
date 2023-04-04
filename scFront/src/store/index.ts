import { createStore, Store, useStore } from 'vuex'
import user from '@/store/user'

export interface RootStateType {
    _state?: any
}

export default createStore<RootStateType>({
    state: {},
    getters: {},
    mutations: {},
    actions: {},
    modules: {
        user
    }
})

export const key = Symbol(`sc-store`)
export function getStore<S = any>(): Store<S> {
    return useStore(key as any)
}