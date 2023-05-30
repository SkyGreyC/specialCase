import { createPost } from "@/utils/request";

const SCOPE = '/mark'

export const saveMarkInfo = createPost(`${SCOPE}/saveMarkInfo`)