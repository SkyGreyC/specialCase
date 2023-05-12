<template>
    <div class=''></div>
    <preview-viewer v-model="previewIndex" :preview-list="previewList" />
</template>

<script lang="ts">
import * as api from '@/api/home'
import { Options } from "vue-class-component";
import BasePage from "../BasePage";
import PreviewViewer from "./components/PreviewViewer.vue";

const queryData = {
    page: { current: 1, size: 4 },
    homeVO: {}
}

@Options({
    name: 'CaseIndex',
    components: {
        PreviewViewer
    }
})
export default class CaseIndex extends BasePage {
    previewIndex = -1
    previewList = []
}
</script>
<style lang='scss' scoped></style>