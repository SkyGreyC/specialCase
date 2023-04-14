import { createPost } from "@/utils/request";

const SCOPE = '/login'

export const login = createPost(`${SCOPE}/login`)
export const resetPassword = createPost(`${SCOPE}/resetPassword`)
export const registerUser = createPost(`${SCOPE}/registerUser`)
export const saveUser = createPost(`${SCOPE}/saveUser`)
export const findUserList = createPost(`${SCOPE}/findUserList`)
export const findUserByUserId = createPost(`${SCOPE}/findUserByUserId`)
export const updateUserType = createPost(`${SCOPE}/updateUserType`)
export const deleteUser = createPost(`${SCOPE}/deleteUser`)
export const updateNickName = createPost(`${SCOPE}/updateNickName`)