<template>
    <div class='full-panel'>
        <slot name="header" />
        <el-scrollbar>
            <slot />
        </el-scrollbar>
        <slot name="footer" />
    </div>
</template>

<script lang="ts">
import BaseComponent from "@/components/BaseComponent"
import { Options, Prop } from 'vue-property-decorator'

@Options({
    name: 'FullPanel'
})
export default class FullPanel extends BaseComponent {
}
</script>
<style lang='scss' scoped>
.full-panel {
    height: 100%;
    display: flex;
    flex-direction: column;

    :deep(.el-scrollbar__view) {
        overflow-x: hidden;
    }
}
</style>