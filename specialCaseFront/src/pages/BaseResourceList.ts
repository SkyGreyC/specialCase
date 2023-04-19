import BasePage from '@/pages/BasePage'
export default class BaseResourceList extends BasePage{

    tableData = { records: [], total: 0 }

    page = { current: 1, size: 20 }

    search = { tracer: '' }

    selection = []
    
    reload(clear) {
        if (clear) {
            this.search.tracer = ''
            this.tableData = { records: [], total: 0 }
        }
        this.page.current = 1
        this.doQuery()
    }

    setSelection(selection) {
        this.selection = selection
    }

    async doQuery() {
        
    }

}