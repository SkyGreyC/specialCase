import { createPost } from "@/utils/request";

const SCOPE = '/case'

export const findCaseDetail = createPost(`${SCOPE}/findCaseDetail`)