import Cookies from 'js-cookie'

export const TokenKey = 'Sc-Login'

export function getToken() {
    const token = {
        [TokenKey]: Cookies.get(TokenKey)
    }
    return token
}

export function setToken(token: any) {
    Cookies.set(TokenKey, token)
}

export function removeToken() {
    Cookies.remove(TokenKey)
}