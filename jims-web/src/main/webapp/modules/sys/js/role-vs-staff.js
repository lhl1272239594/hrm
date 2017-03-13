//create by zhu on 2016-8-16
//角色-员工 对照

$(function(){

    function getRootPath(){
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        return(localhostPaht);
    }
    var basePath=getRootPath()+"/service";
    var orgId=config.org_Id;

    var sexList = [];   //性别
    $.get(basePath + '/dict/findListByType?type=SEX_DICT',function(data){
        sexList = data;
    });

    var nationList = [];    //民族
    $.get(basePath + '/dict/find-list-by-type?type=NATION_DICT',function(data){
        nationList = data;
    });

    var titleList = [];     //职称
    $.get(basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT',function(data){
        titleList = data
    });

    var personLists = [];     //该机构所有人员
    $.get(basePath + '/orgStaff/findList?orgId=' + orgId,function(data){
        personLists = data;
        console.log(personLists);
    });


    var person=[];

    var roleListIndex;
    //角色表
    $("#roleList").datagrid({
        fit: true,
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "组织ID",
            field: "orgId",
            align: 'center',
            hidden: true
        }, {
            title: "职务名称",
            field: "roleName",
            align: 'center',
            width: '100%'
        }]],
        onClickRow: function (index, row) {
            roleListIndex=index;
            var roleId=row.id;
            var url=basePath+"/orgStaff/find-by-roleId?orgId="+orgId+"&roleId="+roleId;
            $.get(basePath+"/orgStaff/find-by-roleId?orgId="+orgId+"&roleId="+roleId,function(data){
                console.log(data);
                person=data;
            })
            $("#staffList").datagrid("reload",url);
        }
    });


    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#staffList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }
    //人员表
    $("#staffList").datagrid({
        fit: true,
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "staffId",
            field: "staffId",
            hidden: true
        }, {
            title: "姓名",
            field: "name",
            align: 'center',
            width: '15%',
            editor:{
            }
        }, {
            title: "性别",
            field: "sex",
            align: 'center',
            width: '10%',
            formatter: function(value,row,index){
                var sex = value;
                $.each(sexList, function (index, item) {
                    if (item.value == value) {
                        sex = item.label;
                    }
                });
                return sex;
            }
        }, {
            title: "昵称",
            field: "nickName",
            align: 'center',
            width: '10%'
        }, {
            title: "职称",
            field: "title",
            align: 'center',
            width: '10%',
            formatter: function(value,row,index){
                var title = value;
                $.each(titleList, function (index, item) {
                    if (item.value == value) {
                        title = item.label;
                    }
                });
                return title;
            }
        }, {
            title: "联系电话",
            field: "phoneNum",
            align: 'center',
            width: '15%'
        }, {
            title: "邮箱",
            field: "email",
            align: 'center',
            width: '20%'
        }, {
            title: "民族",
            field: "nation",
            align: 'center',
            width: '10%'
            ,formatter: function(value,row,index){
                var nation = value;
                $.each(nationList, function (index, item) {
                    if (item.value == value) {
                        nation = item.label;
                    }
                });
                return nation;
            }
        }]],
        onClickRow: function (index, row) {
            editIndex=index;
            $('#staffList').datagrid('selectRow',editIndex);
            if(row.id!=''){
                $('#staffList').datagrid('beginEdit',editIndex);
            }
        }
    });

    var ids='';
    //删除
    $('#delBtn').on('click',function(){
        if(editIndex!=undefined){
            var row=$('#staffList').datagrid('getSelected');
            ids=row.id;
            console.log(ids);
            $.postJSON(basePath + "/orgStaff/delete?",ids, function (data) {
                if (data.data == "success") {
                    $.messager.alert('系统提示', '人员已删除');
                    $('#staffList').datagrid('deleteRow',editIndex);
                    editIndex = undefined;
                }
            }, function (data) {
                $.messager.alert('系统提示', '删除失败', 'info');
                $("#staffForm").form('reset');
            });
        }
    })

    //检查类别模态框
    $("#addStaff").window({
        title: '新增员工',
        closed: true,
        modal: true
    });

    //新增
    $('#addBtn').on('click',function(){
        var row= $('#roleList').datagrid('getSelected');
        $('#roleId').textbox('setValue',row.id);
        $('#roleName').textbox('setValue',row.roleName);

        $("#addStaff").window('open');
    })


    //取消按钮
    $("#cancelBtn").on('click', function () {
        $("#staffForm").form('reset');
        $("#addStaff").window('close');
    });


    // 选取本机构员工
    $("#personList").combogrid({
        panelWidth: 300,
        mode: 'remote',
        idField: 'staffId',
        textField: 'name',
        method: 'GET',
        url: basePath + '/orgStaff/findList?orgId=' + orgId,
        columns: [[
            {field: 'staffId', title: '员工代码',hidden:true},
            {field: 'name', title: '姓名', width: 90},
            {
                field: 'sex',
                title: '性别',
                width: 50,
                formatter: function(value,row,index){
                    var sex = value;
                    $.each(sexList, function (index, item) {
                        if (item.value == value) {
                            sex = item.label;
                        }
                    });
                    return sex;
                }
            },
            {field: 'cardNo', title: '身份证号', width: 145}
        ]],
        onSelect: function (value,row) {

        }
    });

    $('#saveBtn').on('click',function(){
        var staffVsRole={};
        staffVsRole.roleId=$('#roleId').textbox('getValue');
        staffVsRole.staffId=$('#personList').combogrid('getValue');
        for(var i=0;i<person.length;i++){
            if(person[i].staffId==staffVsRole.staffId){
                $.messager.alert('提示', "该员工已存在", "error");
                return;
            }

        }
        var check=false;
        for(var i=0;i<personLists.length;i++){
            if(personLists[i].staffId==staffVsRole.staffId){
                check=true;
            }
        }
        if(check){
            $.postJSON(basePath + "/orgStaff/save-staff-role", JSON.stringify(staffVsRole), function (data) {
                if (data.data == "success") {
                    $("#addStaff").window('close');
                    $.messager.alert('系统提示', '保存成功');
                    $("#staffList").datagrid('reload');
                    $("#staffForm").form('reset');
                }
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'info');
                $("#staffForm").form('reset');
            });
        }else{
            $.messager.alert('系统提示', '请在下拉框中选择员工');
        }

    })

})
