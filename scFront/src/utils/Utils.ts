import _ from 'lodash'

export class Utils {
    isEmpty(value) {
        if (typeof value === 'string') {
            value = value.trim()
        }
        return _.isEmpty(value)
    }

    treeToList(tree) {
        const handler = (perv, curr) =>{
            perv.push({ ...curr })
            if(curr.children) {
                curr.children.map(child =>({ ...child, parentId: curr.id })).reduce(handler, perv)
            }
            return perv
        }
        return tree.reduce(handler, [])
    }

    listToJson(list) {
        return list.reduce((perv, curr) =>{
            perv[curr.id] = curr
            return perv
        }, {})
    }
}

export default new Utils()