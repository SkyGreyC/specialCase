import { createPost } from "@/utils/request";

const SCOPE = '/login'

export const doLogin = createPost(`${SCOPE}/login`)