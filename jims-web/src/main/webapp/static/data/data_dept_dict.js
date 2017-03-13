var DeptCode=[];
/**
 * 全部科室
 * @type {{}}
 */
var DeptCodeData={};
DeptCodeData.dictType="v_outp_dept_dict"
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(DeptCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        DeptCode=data;
    }
});

/**
 * 科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function DeptCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < DeptCode.length; i++) {
        if (DeptCode[i].id == value) {
            return DeptCode[i].dept_name;
        }
    }
}