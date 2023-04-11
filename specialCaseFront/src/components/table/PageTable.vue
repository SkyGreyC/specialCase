<!-- <template>
    <div class=''>
        <el-table :data="data.records" height="100%" @selection-change="handleSelectionChange">
            <el-table-column v-if="multiple" type="selection" width="40px" />
        </el-table>
        <div style="width: 100%;margin-top: 10px;">
            <el-pagination :page-size="page.size" :current-page="page.current" :total="data.total"
                layout="total, sizes, perv, pager, next, jumper" small background style="float: right;"
                @size-change="setPageSize" @current-change="setPageCurrent" />
        </div>
        <div v-if="selection.length">
            <span>当前已选择{{ selection.length }}项</span>
        </div>
    </div>
</template>

<script setup lang="ts">
import BaseComponent from "@/components/BaseComponent"
import { Options, Prop } from 'vue-property-decorator'

@Options({
    name: 'PageTable',
    emits: ['page-change', 'selection-change']
})
    export default PageTable extends BaseComponent {
    @Prop({ default: () => ({ records: [], total: 0 }) })
    data

    @Prop({ default: () => ({ current: 1, size: 20 }) })
    page

    @Prop()
    multiple

    selection = []

}
</script>
<style lang='scss' scoped></style> -->