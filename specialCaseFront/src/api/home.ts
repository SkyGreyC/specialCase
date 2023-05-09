import { createPost } from "@/utils/request";

const SCOPE = '/home'

export const findHomeInfo = createPost(`${SCOPE}/findHomeInfo`)

export const findHomeDetail = createPost(`${SCOPE}/findHomeDetail`)

export const saveHomeDetail = createPost(`${SCOPE}/saveHomeDetail`)