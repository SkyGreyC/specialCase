import { createPost } from "@/utils/request";

const SCOPE = '/case'

export const findCaseDetail = createPost(`${SCOPE}/findCaseDetail`)
export const saveCase = createPost(`${SCOPE}/saveCase`)
export const findCaseList = createPost(`${SCOPE}/findCaseList`)
export const deleteCase = createPost(`${SCOPE}/deleteCase`)