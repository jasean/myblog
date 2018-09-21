
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
                value = value[keys[index]];
            }
            if(index == length - 1 || !value){
                return value;
            }else if(typeof value !== 'object'){    //数组或对象
                return null;
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
	var rValue = curValue = pValue = JSON.parse(localStorage.getItem(keys[0])) || {};
	var index = 1;
	for(;index < keys.length;index++){
		if(typeof curValue !== 'object'){
			if(created){
				curValue = {};
				curValue[keys[index]] = keys[index];
				pValue[keys[index-1]] = curValue;
			}else{
				return false;
			}
		}
		
		pValue = curValue;
		curValue = curValue[keys[index]];
	}
	
	pValue[keys[index-1]] = value;
	
	console.info(rValue);
	localStorage.setItem(keys[0], JSON.stringify(rValue));

	return true;
}