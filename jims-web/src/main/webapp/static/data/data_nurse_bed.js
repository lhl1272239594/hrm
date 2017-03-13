/**
 * Created by Administrator on 2016/6/29 0029.
 */
var bedClass = [];
var airconditionClass = [];
var bedApprovedType = [];
var orgId = config.org_Id;
//空调费
$.ajax({
    'type': 'GET',
    'url': basePath + '/bedRec/findBedPrice?itemClass=' + "N&orgId="+orgId,
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        airconditionClass = data;
    }
});

//空调费的翻译
function airconditionFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < airconditionClass.length; i++) {
        if (airconditionClass[i].item_code == value) {
            return airconditionClass[i].item_name;
        }
    }
}


//床位等级
$.ajax({
    'type': 'GET',
    'url': basePath + '/bedRec/findBedPrice?itemClass=' + "J&orgId="+orgId,
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        bedClass = data;
    }
});

//床位等级翻译
function bedClassFormatter(value, rowData, RowIndex) {

    if (value == 0) {
        return;
    }
    for (var i = 0; i < bedClass.length; i++) {
        if (bedClass[i].item_code == value) {
            return bedClass[i].item_name;
        }
    }
}


/**
 * 床位编制
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=BED_APPROVED_TYPE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        bedApprovedType=data;
    }
});




/**
 * 床位编制翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function bedFormatter(value, rowData, rowIndex) {
    if (value == null) {
        return;
    }
    for (var i = 0; i < bedApprovedType.length; i++) {
        if (bedApprovedType[i].value == value) {
            return bedApprovedType[i].label;
        }
    }
}
