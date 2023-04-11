import BasePage from '@/pages/BasePage'
export default class BaseResourceList extends BasePage{

    tableData = { records: [], total: 0 }

    page = { current: 1, size: 20 }

    

}