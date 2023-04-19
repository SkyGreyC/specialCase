import { createPost } from "@/utils/request";

const SCOPE = '/image'

export const uploadFile = createPost(`${SCOPE}/uploadFile`)