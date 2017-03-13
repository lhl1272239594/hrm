var rowNum=-1;
var labItemClass=[]//检验类别
var labItmeData={};
labItmeData.dictType="v_lab_class"


/**
 * 检验类别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string}
 */
function labItemClassFormatter(value,rowData,rowIndex){
    if(value==0){
        return;
    }
    for(var i=0;i<labItemClass.length;i++){
        if(labItemClass[i].class_code == value){
            return labItemClass[i].class_name;
        }
    }
}
///**
// * 检验标本翻译
// * @param value
// * @param rowData
// * @param rowIndex
// * @returns {string}
// */
//function specimenFormatter(value,rowData,rowIndex){
//    if(value==0){
//        return;
//    }
//    for(var i=0;i<specimen.length;i++){
//        if(specimen[i].value==value){
//            return specimen[i].text;
//        }
//    }
//}
/**
 * 门诊检验类别
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(labItmeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        labItemClass=data;
    }
});