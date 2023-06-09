<template>
    <div class="case-section">
        <template v-if="homeList.length">
            <div class="custom-page-container">
                <el-carousel type="card" height="430px" :autoplay="false" @change="setCarouselIndex">
                    <el-carousel-item v-for="(item, i) in homeList" :key="item.homeId" :class="getCarouselClass(i)">
                        <div class="case-card">
                            <el-image :src="getFileURL(item.imageVOs)" class="case-card__left" />
                            <div class="case-card__right">
                                <div class="case-logo">
                                    {{ item.homeTitle }}
                                </div>
                                <div class="case-content">
                                    {{ item.homeInfo }}
                                </div>
                            </div>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </template>
    </div>
</template>

<script lang="ts">
import * as api from '@/api/home'
import { Options } from "vue-class-component";
import BasePage from "../BasePage";
import { BASE_URL } from "@/utils/request";

const queryData = {
    page: { current: 1, size: 4 },
    homeVO: {}
}

@Options({
    name: 'HomeSection'
})
export default class HomeSection extends BasePage {
    carouselIndex = 0
    homeList = []

    async findHomeInfo() {
        const resp = await api.findHomeInfo(queryData)
        if (resp) {
            const records = resp.data || []
            this.homeList = JSON.parse(JSON.stringify(records))
        }
    }

    setCarouselIndex(index) {
        this.carouselIndex = index
    }

    getCarouselClass(index) {
        const count = this.homeList.length
        const val = this.carouselIndex - index + count
        if (val % count === 1) {
            return 'active-left'
        } else if (val % count === count - 1) {
            return 'active-right'
        }
        return ''
    }

    created() {
        this.findHomeInfo()
    }

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
.case-section {
    width: 100%;
    overflow: hidden;
}

.el-carousel {
    width: 1960px;
    margin-left: -340px;
    --el-carousel-arrow-background: #fff;
    --el-carousel-arrow-hover-background: #eee;

    :deep(.el-carousel__item) {
        background-color: rgba(255, 255, 255, 0.3);
        opacity: 0;
        height: 414px;
        border-radius: 8px;
        transform: translateX(490px) scale(0) !important;

        &.is-active {
            background-color: #fff;
            opacity: 1;
            transform: translateX(490px) scale(1) !important;
        }

        &.active-left {
            opacity: 0.58;
            transform: translateX(135px) scale(0.58) !important;
        }

        &.active-right {
            opacity: 0.58;
            transform: translateX(845px) scale(0.58) !important;
        }
    }

    :deep(.el-carousel__arrow--left) {
        left: 360px;
        color: #666;
    }

    :deep(.el-carousel__arrow--right) {
        right: 360px;
        color: #666;
    }

    .case-card {
        display: flex;
        border-radius: 8px;

        .case-card__left {
            width: 624px;
            height: 414px;
        }

        .case-card__right {
            width: 360px;
            height: 414px;
            padding: 30px 50px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
        }

        .case-logo {
            font-size: 18px;
            height: 40px;
            width: 200px;
            border-radius: var(--custom-size-radius);
            margin-bottom: var(--custom-size-gap-lg);
        }

        .case-content {
            color: var(--el-text-color-regular);
            flex: 1;
            word-break: break-all;
            overflow: auto;
        }
    }
}
</style>