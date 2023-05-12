<template>
    <div :class="`common-footer--${type}`" class="common-footer">
        <div class="common-footer__container">
            <div class="custom-space-between">
                <div style="width: 50%;">
                    <el-card>
                        <div style="padding: 0 10px;">
                            <h4>华西官方微信公众号</h4>
                            <img src="@/assets/images/erweima1.jpg" />
                        </div>
                    </el-card>
                </div>
                <div style="width: 50%;">
                    <el-card>
                        <div style="padding: 0 10px;">
                            <h4>华西官方微博</h4>
                            <img src="@/assets/images/erweima2.jpg" />
                        </div>
                    </el-card>
                </div>
                <div style="width: 50%;">
                    <el-card>
                        <div style="padding: 0 10px;">
                            <h4>华西科研公众号</h4>
                            <img src="@/assets/images/erweima3.jpg" />
                        </div>
                    </el-card>
                </div>
                <div class="custom-space-between" style="max-width: 50%;width: 640px;height: 250px;">
                    <div style="width: 80px;">
                        <h4 style="margin: 0 0 30px;">更多</h4>
                        <p class="custom-clickable" @click="$router.push('/design/value')">联系我们</p>
                        <p class="custom-clickable" @click="$router.push('/design/value')">法律声明</p>
                        <p class="custom-clickable" @click="$router.push('/design/value')">网站地图</p>
                    </div>
                </div>
            </div>
            <div class="custom-space-between" style="margin-top: 30px;">
                <span class="custom-subtext-lg" style="line-height: 32px;">Copyright © 2023-2024 四川大学华西医院.</span>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Prop } from "vue-property-decorator";
import BasePage from "../BasePage";

@Options({
    name: 'CommonFooter'
})
export default class CommonFooter extends BasePage {
    @Prop({ default: 'default' })
    type
}
</script>
<style lang='scss' scoped>
.common-footer--default {
    background-color: #fff;
    padding: 50px 0;

    .common-footer__container {
        width: var(--custom-width-page-container);
        margin: 0 auto;
    }
}

.common-footer--full {
    background-color: var(--custom-fill-aliceblue);
    padding: 60px 40px;
    box-sizing: border-box;
}

.el-card {
    text-align: center;
    width: 280px;
}
</style>