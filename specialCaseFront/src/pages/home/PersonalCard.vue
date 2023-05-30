<template>
    <div v-show="visible" class="personal-card">
        <template v-if="visible">
            <span class="personal-card__mask" @click="doClose" />
            <div class="personal-card__content">
                <full-panel class="personal-card__detail custom-panel-lg">
                    <div class="custom-title" style="word-break: break-all;">{{ currentItem.nickName }}</div>
                    <div class="custom-subtext" style="padding: 8px 0;word-break: break-all;">编号：{{
                        currentItem.userId }}
                    </div>
                    <hr class="custom-hr">
                    <el-form label-width="85px">
                        <el-form-item>
                            <template #label>
                                <span>用</span>
                                <span>户</span>
                                <span>名：</span>
                            </template>
                            {{ currentItem.userName }}
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>类</span>
                                <span>型：</span>
                            </template>
                            <el-tag v-if="currentItem.userType === '00'" effect="light" type="success">一般用户</el-tag>
                            <el-tag v-else-if="currentItem.userType === '01'" effect="light" type="success">中级用户</el-tag>
                            <el-tag v-else effect="light" type="success">高级用户</el-tag>
                        </el-form-item>
                        <el-form-item>
                            <template #label>
                                <span>创</span>
                                <span>建</span>
                                <span>时</span>
                                <span>间：</span>
                            </template>
                            {{ currentItem.createTime }}
                        </el-form-item>
                        <el-form-item v-if="isUpdate === 1">
                            <template #label>
                                <span>新</span>
                                <span>昵</span>
                                <span>称：</span>
                            </template>
                            <el-input v-model="newNickName"></el-input>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                        <div style="margin: 10px 0;text-align: center;">
                            <el-button v-if="isUpdate === 0" type="primary" style="min-width: 150px;" @click="doUpdate">
                                修改昵称
                            </el-button>
                            <el-button v-else type="primary" style="min-width: 150px;" @click="updateNickName">
                                确认修改
                            </el-button>
                            <el-button type="primary" style="min-width: 150px;" @click="doClose">
                                返回
                            </el-button>
                        </div>
                    </template>
                </full-panel>
            </div>
        </template>
    </div>
</template>

<script lang="ts">
import { Options, Prop, Model } from "vue-property-decorator";
import BasePage from "../BasePage";
import * as api from '@/api/user'
import { ElMessage } from 'element-plus';

@Options({
    name: 'PersonalCard'
})
export default class PersonalCard extends BasePage {
    @Model('modelValue', { default: -1 })
    value

    @Prop({ default: {} })
    preview

    newNickName = ''

    isUpdate = 0

    viewerTarget = null

    get currentItem() {
        return this.preview
    }

    get visible() {
        return this.value >= 0
    }

    openViewer(item) {
        this.viewerTarget = item
    }

    doClose() {
        this.value = -1
    }

    doUpdate() {
        this.isUpdate = 1
    }

    async updateNickName() {
        this.preview.nickName = this.newNickName
        await api.updateNickName({ userVO: this.preview })
        ElMessage.success('修改成功！')
        this.isUpdate = 0
    }

}
</script>
<style lang='scss' scoped>
.personal-card {
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

.personal-card__mask {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    opacity: .5;
    background: #000;
}

.personal-card__btn {
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

.personal-card__content {
    margin: 10vh 35vw;
    width: 30vw;
    height: 50vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
    -webkit-user-select: none;
    user-select: none;
    position: relative;
    background-color: #fff;
    border-radius: 8px;
}

.personal-card__detail {
    width: 100%;
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