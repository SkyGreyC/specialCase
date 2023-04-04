import utils from './Utils'

export const emptyValidator = (rule, value, callback) => {
    if (utils.isEmpty(value)) {
        callback(rule.message)
    } else {
        callback()
    }
}

/**
 * element-plus相关组件处理工具
 */
export class ElementPlus {

    /**
     * el-form-item添加基本属性
     */
    attrFormItem(prop, label, required = true) {
        const attr: any = {
            prop,
            label
        }
        if (required) {
            attr.rules = [{
                required: true,
                message: `${label}不能为空`,
                validator: emptyValidator
            }]
        }
        return attr
    }
}

export default new ElementPlus()