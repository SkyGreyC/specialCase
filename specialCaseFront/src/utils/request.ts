import axios from 'axios'
import { getToken } from './token'

// export const BASE_URL = import.meta.env.VITE_BASE_URL
export const BASE_URL = 'http://localhost:8080'

export function setHeaders(headers = {}) {
    Object.assign(headers, getToken())
    return headers
}

const service = axios.create({
    baseURL: BASE_URL,
    withCredentials: true,
    timeout: 20000
})

service.interceptors.request.use(
    config => {
        setHeaders(config.headers)
        return config
    },
    error => {
        Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        return response
    },
    error => {
        Promise.reject(error)
    }
)

export function request(config: any): Promise<any> {
    return new Promise((resolve, reject) => {
        if (typeof config === 'string') {
            config = { url: config, method: 'get' }
        }
        config.method = config.method || 'post'
        service(config).then(res => {
            const response = res.data || {}
            resolve(response)
        }).catch(reject)
    })
}

export function post(url: any, data = {}): Promise<any> {
    return new Promise<void>((resolve) => {
        service({
            url,
            method: 'post',
            data
        }).then(res => {
            resolve(res.data || {})
        }).catch(() => {
            resolve()
        })
    })
}

export function get(url: any, data = {}): Promise<any> {
    return new Promise<void>((resolve) => {
        service({
            url,
            method: 'get',
            data
        }).then(res => {
            resolve(res.data || {})
        }).catch(() => {
            resolve()
        })
    })
}

export function createPost(url: any) {
    return (data?: any) => post(url, data)
}

export function createGet(url: any) {
    return (data?: any) => get(url, data)
}

export default service