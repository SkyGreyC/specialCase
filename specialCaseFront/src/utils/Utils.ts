import _ from 'lodash'

export class Utils {
    isEmpty(value: any) {
        if (typeof value === 'string') {
            value = value.trim()
        }
        return _.isEmpty(value)
    }

    treeToList(tree: any) {
        const handler = (perv: any, curr: any) =>{
            perv.push({ ...curr })
            if(curr.children) {
                curr.children.map((child: any) =>({ ...child, parentId: curr.id })).reduce(handler, perv)
            }
            return perv
        }
        return tree.reduce(handler, [])
    }

    listToJson(list: any) {
        return list.reduce((perv: any, curr: any) =>{
            perv[curr.id] = curr
            return perv
        }, {})
    }
}

export default new Utils()