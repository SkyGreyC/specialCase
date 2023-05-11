import { createPost } from "@/utils/request";

const SCOPE = '/home'

export const findHomeInfo = createPost(`${SCOPE}/findHomeInfo`)

export const findHomeDetail = createPost(`${SCOPE}/findHomeDetail`)

export const saveHomeInfo = createPost(`${SCOPE}/saveHomeInfo`)

export const deleteHomeInfo = createPost(`${SCOPE}/deleteHomeInfo`)