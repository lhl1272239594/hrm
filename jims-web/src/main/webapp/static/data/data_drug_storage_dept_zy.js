var drugStorage= [];
var drugStorageData={};
drugStorageData.storageType='2';
drugStorageData.storageType1='4'
drugStorageData.orgId=parent.config.org_Id;
/**
 * 住院药局
 */
//$.ajax({
//    'type': 'POST',
//    'url':basePath+'/drug-storage-dept/listByParams' ,
//    data: JSON.stringify(drugStorageData),
//    'contentType': 'application/json',
//    'dataType': 'json',
//    'async': false,
//    'success': function(data){
//        drugStorage=data;
//    }
//});

function  drugStorageDataMed(med){
    $.ajax({
        'type': 'POST',
        'url':basePath+'/drug-storage-dept/listByParams' ,
        data: JSON.stringify(drugStorageData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            drugStorage=data;
            med();
        }
    });
}
/**
 * 住院药局
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function drugStorageFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < drugStorage.length; i++) {
    if (drugStorage[i].id == value) {
        return drugStorage[i].storageName;

    }
    }
}