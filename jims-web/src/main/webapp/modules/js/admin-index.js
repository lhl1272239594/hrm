function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}

$(function () {
    /**
     * 菜单管理
     */
    $("#menuMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/menuDict.html');
    });
    /**
     * 功能按钮管理
     */
    $("#buttonMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/buttonDict.html');
    });
    /**
     * 菜单序列维护
     */
    $("#initProcess").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/init-process-list.html');
    });
    /**
     * 基础服务维护
     */
    $("#serviceMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/sys-service.html');
    });
    /**
     * 机构审核
     */
    $("#companyCheckMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/sys-company.html');
    });
    /**
     * 字典管理
     */
    $("#dictMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/public-dict.html');
    });
    /**
     * 输入法字典维护
     */
    $("#dictInputMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/input-setting.html');
    });
    /**
     * 药品字典维护
     */
    $("#dictDrugMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/phstock/drug_class_dict.html');
    });
    /**
     * 药理信息维护
     */
    $("#drugTheoryMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/phstock/drug-theory-manager.html');
    });
    /**
     * 药品限制等级维护
     */
    $("#drugLimitMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/phstock/drug-limit-grade-manager.html');
    });
    /**
     * 药品类别维护
     */
    $("#drugClassMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/phstock/drug-class-dict.html');
    });
    /**
     * 药品目录维护
     */
    $("#drugCatalogMgr").on("click", function () {
        $('#centerIframe').attr('src','/modules/phstock/drug-catalog-manager.html');
    });

    /**
     * 医嘱执行缺省时间维护
     */
    $("#defaultSchedule").on("click", function () {
        $('#centerIframe').attr('src','/modules/sys/perform-default_schedule.html');
    });

    /**
     * 角色模板维护
     */
    $("#roleTemplate").on("click", function () {
        $('#centerIframe').attr('src','/modules/template/org_role_template.html');
    });

    /**
     * 价表模板维护
     */
    $('#priceListTemplate').on('click',function(){
        $('#centerIframe').attr('src','/modules/template/price_list_template.html');
    })
    /**
     * 退出
     */
    $("#exit").on("click", function () {
        $.getJSON("/service/login/exit",function(data){
            window.location.href = "/modules/sys/login.html";
        }) ;
    });
});