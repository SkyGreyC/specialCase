import { createPost } from "@/utils/request";

const SCOPE = '/home'

export const findHomeInfo = createPost(`${SCOPE}/findHomeInfo`)