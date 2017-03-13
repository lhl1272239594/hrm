var bloodTypeName = [];//血液要求
var userBloodData = {};
userBloodData.isOrgId=false;
userBloodData.dictType = "BLOOD_COMPONENT";
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
userBloodData.inputParamVos = inputParamVos;

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(userBloodData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        bloodTypeName = data;
    }
})
/**
 * 血液要求翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {Document.deptName|.queryParams.deptName|*|deptName|obj.deptName|deptDictVo.deptName}
 */
function bloodTypeNameFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < bloodTypeName.length; i++) {
        if (bloodTypeName[i].blood_type == value) {
           return distinction = bloodTypeName[i].blood_type_name;
        }
    }
}