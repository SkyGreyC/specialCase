<template>
    <div class='image-items'>
        <div class="custom-flex">
            <div v-for="(item, i) in list" :key="i" :style="itemStyle" :class="{ 'is-selected': value === i }"
                class="image-item" @click="value = i">
                <slot :data="item">
                    <base64-image :src="item.imageVOs[0]" />
                </slot>
                <el-link v-if="deletable && item.deletable !== false" class="image-item__delete" @click.stop="doDelete(i)">
                    <Delete style="width: 16px;height: 16px;" />
                </el-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Model, Options, Prop } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';
import { Delete } from '@element-plus/icons-vue'

@Options({
    name: 'ImageItems',
    components: {
        Delete
    }
})
export default class ImageItems extends BaseComponent {
    @Model('modelValue')
    value

    @Prop({ default: () => [] })
    list

    @Prop({ default: 80 })
    size

    @Prop({ default: true })
    deletable

    @Prop()
    maxHeight

    get itemStyle() {
        const size = this.size
        if (!size) {
            return {}
        }
        if (typeof size === 'number') {
            return { 'width': `${size}px`, 'height': `${size}px` }
        } else if (size[0] && size[1]) {
            return { 'width': `${size[0]}px`, 'height': `${size[1]}px` }
        }
        return {}
    }

    doDelete(i) {
        this.list.splice(i, 1)
        if (i === this.value) {
            this.value = 0
        }
    }
}
</script>
<style lang='scss' scoped>
.image-items {
    position: relative;
    overflow: auto;
}

.image-item {
    cursor: pointer;
    border-radius: var(--custom-size-radius);
    margin-right: var(--custom-size-gap-sm);
    margin-bottom: var(--custom-size-gap-sm);
    text-align: center;
    justify-content: center;
    display: flex;
    flex-direction: column;
    background-color: var(--custom-fill-color-text);
    border: 1px solid var(--custom-fill-color-text);
    position: relative;
    overflow: hidden;

    &.is-selected {
        border-color: var(--el-color-primary);
    }

    .base64-image {
        width: 100%;
        height: 100%;
    }
}

.image-item__delete {
    position: absolute;
    right: 2px;
    top: 2px;
}

.image-item__clear {
    position: absolute;
    right: 0;
    top: -35px;
}</style>