<template>
    <div class='login-section'>
        <div class="custom-page-container">
            <el-card class="card-item">
                <template #header>
                    <div class="animation" style="height: 130px;">
                        <div>
                            <svg-icon :size="40" icon="logo" />
                        </div>
                        <h1>登录</h1>

                    </div>
                </template>
            </el-card>
        </div>
    </div>
</template>

<script setup lang="ts">

</script>
<style lang='scss' scoped>
.animation {
    position: relative;

    .animation-item {
        position: absolute;
        top: 0;
        left: 0;
        width: 520px;
        display: none;
        animation-fill-mode: forwards !important;

        &.item-show {
            display: block;
            animation: itemShow 3s;
        }

        &.item-hide {
            display: block;
            animation: itemHide 3s;
        }
    }
}

.content {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;
    z-index: 2;

    .card-item {
        width: 520px;
        height: 440px;
        border-color: white;
        background-color: #fde0ff1a;
        position: relative;

        .bottom-line {
            margin: 20px 0 0;
        }

        .list-item {
            margin: 0 0 20px 0;
        }

        .actions {
            top: 43px;
            right: 20px;
            height: 32px;
            position: absolute;

            .svg-icon {
                cursor: pointer;

                &+.svg-icon {
                    margin-top: 10px;
                }
            }
        }
    }
}</style>