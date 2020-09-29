
/**
 * 获取localStorage属性，支持级联属性
 */
export const getLocalStore = (key) => {
    let keys = key.split('.');
    let value ;
    // let value = JSON.parse(localStorage.getItem(keys[0]));
    // if(keys.length == 1 || !value){
    //     return value;
    // }else if(typeof value !== 'object'){
    //     return null;
    // }else{
        for (let index = 0, length = keys.length; index < length; index++) {
            if(index == 0){
                value = JSON.parse(localStorage.getItem(keys[0]));
            }else{
                if(index == length - 1 || !value){
                    return value;
                }else if(typeof value !== 'object'){    //数组或对象
                    return null;
                }else{
                    value = value[keys[index]];
                }
                
            }

        }
        return value;
    // }
}

/**
 * 设置localStorage属性，支持级联属性
 * @param created:当父级元素不存在时是否创建
 * @returns Boolean
 */
export const setLocalStore = (key, value, created = false) => {
    var keys = key.split('.');
    var l = keys.length;
    var rValue = curValue = pValue = JSON.parse(localStorage.getItem(keys[0]));
    if(l == 1){
        localStorage.setItem(keys[0],value);
    }else{
        if(!pValue || typeof pValue !== 'object'){
            pValue = {};
            localStorage.setItem(keys[0],pValue);
        }
    }

	var index = 1;
	for(;index < l;index++){
        //已经存在非object值
        if(typeof pValue !== 'object'){
            return false;
        }
        if(index != l - 1 ){
            if(!curValue){
                if(!created) return false;
                else{
                    //递归设值空值
                    for(;index < l - 1;index++){
                        curValue = {};
                        pValue[keys[index]] = curValue;
                        pValue = curValue;
                    }
                }
            }else{
                pValue[keys[index]] = curValue
                pValue = curValue;
                curValue = curValue[index+1];
            }

        }else {
            pValue[keys[index]] = value;
        }
	}
	
	return true;
}