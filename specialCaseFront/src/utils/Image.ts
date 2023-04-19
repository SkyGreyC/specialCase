import { BASE_URL } from "./request";

export class Image {

    /**
     * 获取ImageVO对象的imageId
     */


    /**
     * 获取附件的访问路径
     */
    getFileURL(imageId: string): String {
        if (imageId) {
            return `${BASE_URL}/image/downloadFile?imageId=${imageId}`
        }
        return null
    }
}

export default new Image()