function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}
var config = {};
config.org_Id = '1';
config.operator = 'thinkgem';
config.currentStorage = '1001';

$(function () {
    //var orgId = parent.config.org_Id;
    var orgId = '1';
    var personId = '9259A0BC4CAD44CDA89A19D4D1CC6D0E';      //小玺   登陆:0000XX
    var staffId = '';   //员工Id
    var serviceId = []; //员工对应的多个角色下的所有服务(去掉重复的服务)

    //查询员工信息
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId,function(data){
        staffId = data.id;

        //查询员工的服务
        $.get(basePath + '/orgStaff/find-serviceId-by-staffId?staffId=' + staffId, function (data) {
            for (var i = 0; i < data.length; i++) {
                serviceId.push(data[i].serviceId);
            }
            for(var i = 0; i < serviceId.length; i++){
                serviceId = serviceId.unique2();    //去掉相同的serviceId
                $.get(basePath + '/sys-service/get?id=' + serviceId[i], function (data) {
                    $("#menu").append("<li><a id='" + data.id + "'>" + data.serviceName + "</a></li>");
                    $("#" + data.id).on('click',function(){
                        $.get(basePath + '/orgStaff/find-list-by-serviceId?serviceId=' + data.id, function (data) {
                            console.log(data);
                        });
                    });
                });
            }

           /* $.get(basePath + '/menuDict/list', function (data) {
                for (var i = 0; i < data.length; i++) {
                    //console.log("data[i].menuName:" + data[i].menuName + "-----" + data[i].id);
                    if (data[i].menuName == '系统管理') {
                        $("#menu").append("<li><a id='" + data[i].id + "'>" + data[i].menuName + "</a></li>");
                    }
                }
            });*/
        });
    });

    //数组元素去重
    Array.prototype.unique2 = function () {
        this.sort(); //先排序
        var res = [this[0]];
        for (var i = 1; i < this.length; i++) {
            if (this[i] !== res[res.length - 1]) {
                res.push(this[i]);
            }
        }
        return res;
    }
});