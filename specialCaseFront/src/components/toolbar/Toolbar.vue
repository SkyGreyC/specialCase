<template>
    <div class='toolbar'>
        <slot>
            <div v-if="label" class="toolbar-label">{{ label }}</div>
            <template v-else>&nbsp;</template>
        </slot>
        <div class="toolbar-actions">
            <slot name="actions" />
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Prop } from 'vue-property-decorator';
import BaseComponent from '../BaseComponent';

@Options({
    name: 'Toolbar'
})
export default class Toolbar extends BaseComponent {
    @Prop()
    label
}
</script>
<style lang='scss' scoped>
.toolbar {
    min-height: var(--toolbar-height, 40px);
    position: relative;

    .toolbar-label {
        font-size: 16px;
        font-weight: 600;
        line-height: 40px;
    }

    .toolbar-actions {
        position: absolute;
        top: 0;
        right: 0;
        height: var(--toolbar-height, 40px);
        padding: 4px 0;
        box-sizing: border-box;
    }
}
</style>