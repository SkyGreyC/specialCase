<template>
    <div class='page-table'>
        <slot name="topbar" />
        <el-table :data="data.records" height="100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}" @selection-change="handleSelectionChange">
            <el-table-column v-if="multiple" type="selection" width="40px" />
            <slot />
        </el-table>
        <!-- <div style="width: 100%;margin-top: 10px;">
            <el-pagination :page-size="page.size" :current-page="page.current" :total="data.total"
                layout="total, sizes, perv, pager, next, jumper" small background style="float: right;"
                @size-change="setPageSize" @current-change="setPageCurrent" />
        </div>
        <div v-if="selection.length">
            <span class="custom-subtext">当前已选择{{ selection.length }}项</span>
            <div>
                <slot name="bottombar" />
            </div>
        </div> -->
    </div>
</template>

<script lang="ts">
import BaseComponent from "@/components/BaseComponent"
import { Options, Prop } from 'vue-property-decorator'

@Options({
    name: 'NormalTable'
})
export default class NormalTable extends BaseComponent {
    @Prop({ default: () => ({ records: [], total: 0 }) })
    data

    @Prop({ default: () => ({ current: 1, size: 20 }) })
    page

    @Prop()
    multiple

    selection = []

    setPageSize(size) {
        this.page.size = size
        this.page.current = 1
        this.$emit('page-change', this.page)
    }

    setPageCurrent(current) {
        this.page.current = current
        this.$emit('page-change', this.page)
    }

    handleSelectionChange(selection) {
        this.selection = selection
        this.$emit('selection-change', selection)
    }

}
</script>
<style lang='scss' scoped></style>