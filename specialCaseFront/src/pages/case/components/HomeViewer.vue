<template>
    <div v-show="visible" class="preview-viewer">
        <template v-if="visible">
            <span v-show="!viewerTarget" class="preview-viewer__btn preview-viewer__prev" @click="doPrev">
                <svg-icon icon="arrow-left-circle" :size="44" />
            </span>
            <span v-show="!viewerTarget" class="preview-viewer__btn preview-viewer__next" @click="doNext">
                <svg-icon icon="arrow-right-circle" :size="44" />
            </span>
            <span class="preview-viewer__mask" @click="doClose" />
            <div class="preview-viewer__content">
                <el-scrollbar class="preview-viewer__image">
                    <div class="custom-panel-lg custom-around-center" style="min-height: 70vh;">
                        <base64-image :src="caseImageVO" />
                        <!-- <base64-image :src="caseImageVO" @click="openViewer(item)" /> -->
                        <!-- <template v-else>
                        <el-image v-for="(item, i) in thumbnailVOs" :key="i" :src="getFileURL(item)" @click="openViewer(item)" />
                    </template> -->
                    </div>
                </el-scrollbar>
                <full-panel class="preview-viewer__detail custom-panel-lg">
                    <div class="custom-title" style="word-break: break-all;">{{ currentItem.caseTitle }}</div>
                    <div class="custom-subtext" style="padding: 8px 0;word-break: break-all;">病史：{{
                        currentItem.clinicalHistory }}
                    </div>
                    <hr class="custom-hr">
                    <el-form label-width="85px">
                        <el-form-item>
                            <template #label>
                                <span>性</span>
                                <span>别：</span>
                            </template>
                            {{ currentItem.patientGender }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>年</span>
                                <span>龄：</span>
                            </template>
                            {{ currentItem.patientAge }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>病</span>
                                <span>例：</span>
                            </template>
                            {{ currentItem.clinical }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>机</span>
                                <span>器：</span>
                            </template>
                            {{ currentItem.machine }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>示</span>
                                <span>踪</span>
                                <span>剂：</span>
                            </template>
                            {{ currentItem.tracer }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>诊</span>
                                <span>断：</span>
                            </template>
                            <el-tag v-if="currentItem.diagnosis === '00'" effect="light" type="success">良性</el-tag>
                            <el-tag v-else effect="light" type="danger">恶性</el-tag>
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>病</span>
                                <span>例</span>
                                <span>描</span>
                                <span>述：</span>
                            </template>
                            <div v-html="currentItem.opinion"></div>
                        </el-form-item>
                        <el-form-item v-for="(item, i) in currentItem.lesionVOs" :key="item.lesionId" :index="i + ''">
                            <template #label>
                                <span>病</span>
                                <span>灶</span>
                                <span>位</span>
                                <span>置</span>
                                <span>{{ i + 1 }}：</span>
                            </template>
                            {{ item.lesionLocation }}，{{ item.lesionInfo }}
                        </el-form-item>
                    </el-form>
                    <template #footer>
                        <div style="margin: 10px 0;text-align: center;">
                            <el-button type="primary" style="min-width: 150px;" @click="doClose">
                                返回
                            </el-button>
                        </div>
                    </template>
                </full-panel>
            </div>
        </template>
        <el-image-viewer v-if="viewerTarget" :url-list="[viewerTarget.base64]" class="image-viewer"
            @close="viewerTarget = null" />
    </div>
</template>

<script lang="ts">
import { Options, Prop, Model } from "vue-property-decorator";
import BasePage from "../../BasePage";
import { BASE_URL } from "@/utils/request";
import * as api from '@/api/mark';
import { ElMessage } from 'element-plus';

@Options({
    name: 'PreviewViewer'
})
export default class PreviewViewer extends BasePage {
    @Model('modelValue', { default: -1 })
    value

    @Prop({ default: () => [] })
    previewList

    viewerTarget = null

    get currentItem() {
        return this.previewList[this.value]
    }

    get caseImageVO() {
        return this.previewList[this.value].caseImageVO[0]
    }

    get labelImageVO() {
        return this.previewList[this.value].labelImageVO[0]
    }

    // get forImage() {
    //     const category = this.currentItem.resourceCategory
    //     const { IMAGE } = this.$res
    //     return IMAGE.value === category
    // }

    // get categoryLabel() {
    //     const category = this.currentItem.resourceCategory
    //     const { IMAGE, DOC, PPT } = this.$res
    //     if (DOC.value === category) {
    //         return DOC.label
    //     } else if (PPT.value === category) {
    //         return PPT.label
    //     } else {
    //         return IMAGE.label
    //     }
    // }

    // get thumbnailVOs() {
    //     const {fileAndSliceVOs} = this.currentItem
    //     const { THUMBNAIL } = this.$res.FILE_TYPE
    //     //缩略图
    //     const vos = this.$fs.filterByType(fileAndSliceVOs, THUMBNAIL)
    //     if (vos.length) {
    //         return vos
    //     }
    //     return []
    // }

    // get refVO() {
    //     const { MASTER } = this.$res.FILE_TYPE
    //     const { fileAndSliceVOs } = this.currentItem
    //     return this.$refs.findByType(fileAndSliceVOs, MASTER, MASTER)
    // }

    get visible() {
        return this.value >= 0
    }

    openViewer(item) {
        this.viewerTarget = item
    }

    doPrev() {
        if (this.value > 0) {
            this.value = this.value - 1
        } else {
            this.value = this.previewList.length - 1
        }
    }

    doNext() {
        if (this.value < this.previewList.length - 1) {
            this.value = this.value + 1
        } else {
            this.value = 0
        }
    }

    doClose() {
        this.value = -1
    }

    async doMark(caseId) {
        let user = JSON.parse(sessionStorage.getItem('userInfo')) ? JSON.parse(sessionStorage.getItem('userInfo')) : {}
        const userId = user.userId
        let res = await api.saveMarkInfo({ caseId, userId })
        if (res.code === '000') {
            ElMessage.success('操作成功！')
            this.previewList[this.value].isMarked = this.previewList[this.value].isMarked === '01' ? '00' : '01'
        } else {
            ElMessage.error(res.errMessage)
        }
    }

    // download() {
    //     const vo = this.refVO
    //     if (vo) {
    //         vo.filename = this.currentItem.resourceName
    //         this.$fs.downloadFile(vo)
    //     }
    // }

    /**
     * 获取附件的访问路径
     */
    getFileURL(vo): String {
        if (!vo) {
            return null
        }
        const imageId = vo[0].imageId
        if (imageId) {
            return `${BASE_URL}/image/downloadFile?imageId=${imageId}`
        }
        return null
    }
}
</script>
<style lang='scss' scoped>
.preview-viewer {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 10;

    .base64-image {
        cursor: pointer;
    }

    //去除全屏/关闭全屏功能，默认都是全屏
    :deep(.el-image-viewer__img) {
        max-height: none !important;
        max-width: none !important;
    }

    // 去除全屏/关闭全屏按钮
    :deep(.el-image-viewer__actions_inner) {
        .el-image-viewer__actions__divider {
            display: none;
        }

        i:nth-child(4) {
            display: none;
        }
    }
}

.preview-viewer__mask {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    opacity: .5;
    background: #000;
}

.preview-viewer__btn {
    position: absolute;
    z-index: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
    opacity: .8;
    cursor: pointer;
    box-sizing: border-box;
    -webkit-user-select: none;
    user-select: none;
}

.preview-viewer__prev {
    top: 50%;
    transform: translateY(-50%);
    left: 12vw;
    width: 44px;
    height: 44px;
    font-size: 24px;
    color: #fff;
    background-color: var(--el-text-color-regular);
    border-color: #fff;
}

.preview-viewer__next {
    top: 50%;
    transform: translateY(-50%);
    right: 12vw;
    text-indent: 2px;
    width: 44px;
    height: 44px;
    font-size: 24px;
    color: #fff;
    background-color: var(--el-text-color-regular);
    border-color: #fff;
}

.preview-viewer__content {
    margin: 5vh 15vw;
    width: 70vw;
    height: 90vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
    -webkit-user-select: none;
    user-select: none;
    position: relative;
    background-color: #fff;
    border-radius: 8px;
}

.preview-viewer__image {
    width: 45%;
    height: 100%;
    border-radius: 8px 0 0 8px;
    background-color: #eee;

    .el-image {
        width: 80%;
        height: 80%;
    }
}

.preview-viewer__detail {
    width: 55%;
    height: 100%;
}

.el-form-item {
    margin-bottom: 0;

    :deep(.el-form-item__label) {
        display: flex;
        justify-content: space-between;
        font-size: 14px;
        color: var(--el-text-color-secondary);
    }
}
</style>