var operatingRoom = [];//手术室
var operatingDate = {};
var operationRoomNo = [];//手术间
var operationRoomNoData = {};
operationRoomNoData.isOrgId = false;
operationRoomNoData.dictType = "OPERATING_ROOM";

$.ajax({
    'type': 'POST',
    'url': basePath + '/dept-dict/getOperation',
    'data': JSON.stringify(operatingDate),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        operatingRoom = data;
    }
})

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(operationRoomNoData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        operationRoomNo=data;
    }
})
/**
 * 手术室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {Document.deptName|.queryParams.deptName|*|deptName|obj.deptName|deptDictVo.deptName}
 */
function operationFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operatingRoom.length; i++) {
        if (operatingRoom[i].id == value) {
            return operatingRoom[i].deptName;
        }
    }
}
/**
 * 手术间翻译
 * @param value
 * @param id
 * @param rowData
 * @param rowindex
 */
function operationRoomNoFormatter(value, rowData, rowindex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operationRoomNo.length; i++) {
        if (operationRoomNo[i].id == value) {
            return operationRoomNo[i].room_no;
        }
    }

}

/**
 * 手术室下拉框加载
 * @param q
 * @param id
 */
function comboboxLoad(q, id, operatingRoomNoId) {
    if (q != '' && q != null) {
        var operationRoomNoData = {};
        operationRoomNoData.isOrgId = false;
        operationRoomNoData.dictType = "OPERATING_ROOM";
        var InputParamVo = {};
        var InputParamVo2 = {};
        var inputParamVos = [];
        InputParamVo.colName = 'DEPT_ID';
        InputParamVo.colValue = q;
        InputParamVo.operateMethod = '=';
        InputParamVo2.colName = 'status';
        InputParamVo2.colValue = '0';
        InputParamVo2.operateMethod = '=';
        inputParamVos.push(InputParamVo);
        inputParamVos.push(InputParamVo2);
        operationRoomNoData.inputParamVos = inputParamVos;
        $.ajax({
            'type': 'POST',
            'url': basePath + '/input-setting/listParam',
            data: JSON.stringify(operationRoomNoData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function (data) {
                $("#" + id).combobox({
                    data: data,
                    valueField: 'id',
                    textField: 'room_no',
                    onSelect: function (data1) {
                        $("#" + operatingRoomNoId).val(data1.room_no);
                    }
                })
            }
        });
    } else {
        $("#" + id).combobox('loadData', '');
    }
}