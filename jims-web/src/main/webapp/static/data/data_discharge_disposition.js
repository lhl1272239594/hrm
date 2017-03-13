/**
 * Created by pq on 2016/7/4 0004.
 * 出院方式字典
 */
var discharge = [];
var preData={};
preData.dictType="PRE_DISCHARGE_DICT";
var dis = [];
var predischarge =[];


$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=DISCHARGE_DISPOSITION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        discharge=data;
    }
});


/**
 * 出院方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function dischargeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < discharge.length; i++) {
        if (discharge[i].value == value) {
            return discharge[i].label;
        }
    }
}

//出院通知单
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(preData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        predischarge = data;
    }
});


/**
 * 出院通知单翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function perFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < predischarge.length; i++) {
        if (predischarge[i].discharge_code == value) {
            return predischarge[i].discharge_name;
        }
    }
}


function getDis(id){
    var drugNameData={};
    drugNameData.dictType="PRE_DISCHARGE_DICT"
    var inputParamVos=new Array();
    if(id!='' && id!=null){
        var InputParamVo={};
        InputParamVo.colName='DISCHARGE_CODE';
        InputParamVo.colValue=id;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
    }
    drugNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(drugNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            dis = data;

        }
    });
}