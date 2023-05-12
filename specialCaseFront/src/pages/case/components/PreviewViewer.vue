<!-- <template>
<div v-show="visible" class="preview-viewer">
    <template v-if="visible">
        <span v-show="!viewerTarger" class="preview-viewer__btn preview-viewer__prev" @click="doPrev">
            <svg-icon icon="arrow-left-circle" :size="44" />
        </span>
        <span v-show="!viewerTarger" class="preview-viewer__btn preview-viewer__next" @click="doNext">
            <svg-icon icon="arrow-right-circle" :size="44" />
        </span>
        <span class="preview-viewer__mask" @click="doClose" />
        <div class="preview-viewer__content">
            <el-scrollbar class="preview-viewer__image">
                <div class="custom-panel-lg custom-around-center" style="min-height: 70vh;">
                    <base64-image v-if="forImage" :src="refVO" @click="openViewer(item)" />
                    <template v-else>
                        <el-image v-for="(item, i) in thumbnailVOs" :key="i" :src="$fs.getFileURL(item)" @click="openViewer(item)" />
                    </template>
                </div>
            </el-scrollbar>
            <full-panel class="preview-viewer__detail custom-panel-lg">
                <div class="custom-title" style="word-break: break-all;">{{ currentItem.resoureName }}</div>
                <div class="custom-subtext" style="padding: 8px 0;word-break: break-all;">编号：{{ currentItem.resoureNum }}</div>
                <hr class="custom-hr">
                <el-form label-width="85px">
                    <el-form-item>
                        <template #label>
                            <span>大</span>
                            <span>小：</span>
                        </template>
                        {{ $fs.getFileSizeUnit(refVO.fileInfoVO.attSize) }}
                    </el-form-item>
                    <el-form-item>
                        <template #label>
                            <span>格</span>
                            <span>式：</span>
                        </template>
                        {{ refVO.fileInfoVO.suffix.substr(1) }}
                    </el-form-item>
                    <el-form-item>
                        <template #label>
                            <span>下</span>
                            <span>载</span>
                            <span>次</span>
                            <span>数：</span>
                        </template>
                        {{ refVO.fileInfoVO.downloadCount }}
                    </el-form-item>
                </el-form>
                <template #footer>
                    <div style="margin: 10px 0;text-align: center;">
                        <el-button type="primary" style="min-width: 150px;" @click="download">
                            下载{{ categoryLabel }}
                        </el-button>
                    </div>
                </template>
            </full-panel>
        </div>
    </template>
    <el-image-viewer v-if="viewTarget" :url-list="[viewerTarget.base64]" class="image-viewer" @close="viewTarget = null" />
</div>
</template>

<script lang="ts">
import { Options, Prop, Model } from "vue-property-decorator";
import BasePage from "../../BasePage";

@Options({
    name: 'PreviewViewer'
})
export default class PreviewViewer extends BasePage {
    @Model('modelValue', {default: -1})
    value

    @Prop({ default: () => [] })
    previewList

    viewerTarget = null

    get currentItem() {
        return this.previewList[this.value] || this.previewList[0]
    }

    get forImage() {
        const category = this.currentItem.resourceCategory
        const { IMAGE } = this.$res
        return IMAGE.value === category
    }

    get categoryLabel() {
        const category = this.currentItem.resourceCategory
        const { IMAGE, DOC, PPT } = this.$res
        if (DOC.value === category) {
            return DOC.label
        } else if (PPT.value === category) {
            return PPT.label
        } else {
            return IMAGE.label
        }
    }

    get thumbnailVOs() {
        const {fileAndSliceVOs} = this.currentItem
        const { THUMBNAIL } = this.$res.FILE_TYPE
        //缩略图
        const vos = this.$fs.filterByType(fileAndSliceVOs, THUMBNAIL)
        if (vos.length) {
            return vos
        }
        return []
    }

    get refVO() {
        const { MASTER } = this.$res.FILE_TYPE
        const { fileAndSliceVOs } = this.currentItem
        return this.$refs.findByType(fileAndSliceVOs, MASTER, MASTER)
    }

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

    download() {
        const vo = this.refVO
        if (vo) {
            vo.filename = this.currentItem.resourceName
            this.$fs.downloadFile(vo)
        }
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
    left: 20vw;
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
    right: 20vw;
    text-indent: 2px;
    width: 44px;
    height: 44px;
    font-size: 24px;
    color: #fff;
    background-color: var(--el-text-color-regular);
    border-color: #fff;
}

.preview-viewer__content {
    margin: 15vh 25vw;
    width: 50vw;
    height: 70vh;
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
    width: 60%;
    height: 100%;
    border-radius: 8px 0 0 8px;
    background-color: #eee;
    .el-image {
        width: 100%;
        height: 100%;
    }
}

.preview-viewer__detail {
    width: 40%;
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
</style> -->