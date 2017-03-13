/*
 *排班管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var contractVo = {};
var orgId = parent.config.org_Id;
var staffId = parent.config.staffId;
var id = '999';
var contractType = '';
var contractName = '';
var contractCode = '';
var value = '';
var userName = '';
var label = '';
var url = '';
var obj1 = new Object();
var obj2 = new Object();
var deptName;
var search = false;
var depts = [];
var treeDepts = [];
var myDate = new Date();
var statusType='0';
var endStartDate='';
var endEndDate ='';
var probationPeriodId='';
var userId='';
$(function () {

    contractTypeDict();

    //查询合同类型
    $("#contractType").combobox({
        panelWidth: '140px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '',
        loadMsg: '数据正在加载',
        url: '/service/contractType/contract-type-all-list?orgId=' + orgId,
        mode: 'remote',
        method: 'GET'
    });

    $("#statusType").combobox({
        panelWidth: '140px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value:'0',
        data: [
            {
            label: '全部',
            value: '0'
        },{
            label: '未到期',
            value: '3'
        },{
            label: '即将到期',
            value: '2'
        },{
            label: '已到期',
            value: '1'
        }
        ]
    });
    if(parent.check){
        statusType='2';
        $("#statusType").combobox('setValue','2');
    }
    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        pageSize: 30,
        method: 'get',
        toolbar: '#tb',
        fit: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        url: basePath + '/contract/contract-list?' +
        'contractType=' + contractType +
        '&contractName=' + contractName +
        '&contractCode=' + contractCode +
        '&endStartDate=' + endStartDate +
        '&endEndDate=' + endEndDate +
        '&orgId=' + orgId+
        '&statusType='+statusType,
        /*rowStyler:function(index,row){
            if (row.statusType=='未到期'){
                return 'background-color:white;';
            }
            if (row.statusType == '即将到期') {
                return 'background-color:yellow;';
            }
            if (row.statusType == '已到期') {
                return 'background-color:red;';
            }
        },*/
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'contractId', title: '', hidden: true},
            {field: 'contractCode', title: '合同编号', width: '10%', align: 'center',},
            {field: 'contractName', title: '合同名称', width: '15%', align: 'center',},
            {field: 'contractTypeDes', title: '合同类型', width: '5%', align: 'center',},
            {field: 'startDate', title: '开始时间', width: '5%', align: 'center',},
            {field: 'endDate', title: '结束时间', width: '5%', align: 'center', },
            {field: 'statusType', title: '合同状态', width: '5%', align: 'center',
                formatter: function(statusType,row,index) {
                    if(statusType=='1'){
                        return  '已到期';
                    }
                    if(statusType=='2'){
                        return  '即将到期';
                    }
                    if(statusType=='3'){
                        return  '未到期';
                    }
                }
            },
            {field: 'probationPeriodTimes', title: '试用期(天)', width: '5%', align: 'center',},

            {field: 'remainingTime', title: '试用期预警', width: '5%', align: 'center',
                formatter: function(remainingTime,row,index) {
                    if(parseInt(remainingTime)<0){
                        return  '已到期';
                    }
                    if(0<parseInt(remainingTime)<parseInt(row.remindTime)){
                        return  '即将到期';
                    }
                    if(parseInt(remainingTime)>parseInt(row.remindTime)){
                        return  '未到期';
                    }
                }

            },
/*
            {field: 'contractRemindTime', title: '合同预警时间(天)', width: '5%', align: 'center',},
*/

            {
                field: 'userId', title: '签订人员', width: '5%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },
            {
                field: 'deptId', title: '人员部门', width: '15%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId=' + deptId, function (data) {
                        deptName = data[0].deptName;
                    });
                    return deptName
                }
            },
            {
                field: 'url', title: '合同文件', width: '5%', align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='" + row.url + "'>点击查看</a>";
                }
            },
            {
                field: 'createBy', title: '创建人', width: '5%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },

            {field: 'createDate', title: '创建时间', width: '5%', align: 'center',formatter:formatDatebox}

        ]],
        onLoadSuccess: function (data) {
            if(parent.check){
                statusType='0';
                parent.check=false;
            }
                $.messager.progress('close');


        }
    });

    $("#primaryGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page = opts.pageNumber = pageNumber;
            rows = opts.pageSize = pageSize;
            searchAllData(page, rows);
        }
    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search = true;
        contractCode = $("#contractCode").textbox('getValue');
        contractName = $("#contractName").textbox('getValue');
        contractType = $("#contractType").combobox('getValue');
        statusType = $("#statusType").combobox('getValue');
        endStartDate = $("#startDate").datebox('getValue');
        endEndDate = $("#endDate").datebox('getValue');
        searchAllData(page, rows);

    });
    //按条件查询


    var searchAllData = function (page, rows) {


        $("#primaryGrid").datagrid('reload', basePath + '/contract/contract-list?' +
            'contractType=' + contractType +
            '&contractName=' + contractName +
            '&contractCode=' + contractCode +
            '&endStartDate=' + endStartDate +
            '&endEndDate=' + endEndDate +
            '&orgId=' + orgId +
            '&statusType='+statusType+
            '&page=' + page +
            '&rows=' + rows);

        if (search) {
            search = false;
            if(page!='1')
            {
              $("#primaryGrid").datagrid('getPager').pagination('select', 1);
            }
        }
    };


    //配置窗口
    $("#editWin").window({
        title: '合同管理',
        closed: true,
        modal: true,
        minimizable: false,

        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $("#chooseUser").show();
            $("#editContractCode").textbox('enable');
        },
        onOpen: function () {
            //查询合同类型
            $("#editContractType").combobox({
                panelWidth: '150px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                loadMsg: '数据正在加载',
                url: '/service/contractType/contract-type-all-list?orgId=' + orgId,
                mode: 'remote',
                method: 'GET'
            });
            //查询合同试用期
            $("#editProbationPeriod").combobox({
                panelWidth: '150px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                loadMsg: '数据正在加载',
                url: '/service/contractProbationPeriod/contract-probation-period-all-list?orgId=' + orgId,
                mode: 'remote',
                method: 'GET'
            });
            arrPerson = [];
        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window("setTitle", "合同新增").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        $("#chooseUser").show();
        $("#editContractCode").textbox('enable');

    });


    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        if (row) {
            $("#editWin").window("setTitle", "合同修改").window('open');
            var contractTypeDes = '';
            userId = row.userId
            $.get("/service/tool/find-person-list?orgId="+orgId+"&userId="+userId+"&staffId=", function (data) {
                var userName = data[0].userName;
                var deptId = data[0].deptId;
                var deptName = data[0].deptName;
                $("#editUserName").textbox('setValue', userId);
                $("#editUserName").textbox('setText', userName);
                $("#editUserName").textbox('disable');

                $("#editDeptName").textbox('setValue', deptId);
                $("#editDeptName").textbox('setText', deptName);
                $("#editDeptName").textbox('disable');
            });

            $.get(basePath + "/contract/sign-num?orgId=" + orgId + "&userId=" + userId,
                function (data) {

                    var editSignNum=data[0].num;
                    $("#editSignNum").textbox('setValue', editSignNum);
                });

            $("#url").val(row.url);


            $("#editStartDate").datebox('setValue', row.startDate);
            $("#editEndDate").datebox('setValue', row.endDate);
            $("#editContractCode").textbox('setValue', row.contractCode);
            $("#editContractName").textbox('setValue', row.contractName);
            $("#chooseUser").hide();
            $("#editContractCode").textbox('disable');

            $("#id").val(row.contractId);
            $("#flag").val('1');
            $("#editContractType").combobox('setValue', row.contractType);
            $("#editContractType").combobox('setText', row.contractTypeDes);
            $("#editProbationPeriod").combobox('setValue', row.probationPeriodId);
            $("#editProbationPeriod").combobox('setText', row.probationPeriodTimes);
        }
        else {
            $.messager.alert('提示', '该合同附件名称重复！', 'info');

        }
    });
    //删除
    $("#removeBtn").on('click', function () {
        flag = '0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {

            contractVo.contractId = row.contractId;
            ;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/contract/contract-del", JSON.stringify(contractVo), function (data) {
                        /*     $.messager.show({
                         title:'提示',
                         msg:'删除成功！',
                         showType:'show',
                         width:300,
                         height:80,
                         timeout:1000,
                         style:{
                         right:'',
                         top:document.body.scrollTop+document.documentElement.scrollTop,
                         bottom:''
                         }
                         });*/
                        $('#primaryGrid').datagrid('reload');
                        row.length = 0;
                    });
                }
            })
        } else {
            $.messager.alert('提示', '请选择一行记录！', 'info');

            return;

        }
    });
    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#contractCode").textbox('setValue', '');
        $("#contractName").textbox('setValue', '');
        $("#contractType").combobox('setValue', '');
        $("#statusType").combobox('setValue', '0');
        $("#statusType").combobox('setText', '全部');
        $("#contractType").combobox('setText', '');
        $("#startDate").datebox('setValue','');
         $("#endDate").datebox('setValue','');
        page='1';
    });


});

function showInfo() {
    var path = document.getElementById("myFiles").value;

    //判断扩展名是否允许
    var list = "png,PNG,jpg,bmp,JPG,BMP,pdf,PDF";
    var ext = path.substr(path.lastIndexOf('.') + 1);
    var rs = list.indexOf(ext);

    if (rs < 0) {
        $.messager.alert('提示', '不允许上传该类型文件！', 'info');
        $("#editContractName").textbox('setValue', '');
        return
    }
    ;
    //判断文件大小
    var maxsize = 10 * 1024 * 1024;//2M
    var errMsg = "";
    var browserCfg = {};
    var ua = window.navigator.userAgent;
    var filesize = 0;
    var obj_file = document.getElementById("myFiles");
    if (ua.indexOf("MSIE") >= 1) {//IE8以及以下的浏览器
        browserCfg.ie = true;
    }
    if (!browserCfg.ie) {
        if (obj_file.files[0]) {
            filesize = Math.round(obj_file.files[0].size);
        }
    } else {
        var ImgObj;
        if (obj_file.value.indexOf("fakepath") > 0) {
            obj_file.select();
            window.parent.document.body.focus();
            var realpath = document.selection.createRange().text;
            if (realpath.indexOf("file:") == 0) {
                realpath = realpath.substr(0, 9) + "|" + realpath.substring(10, realpath.length);
            } else {
                realpath = "file:///" + realpath.substr(0, 1) + "|" + realpath.substring(2, realpath.length);
            }
            ImgObj = document.createElement("img");
            ImgObj.src = realpath;
            filesize = Math.round(ImgObj.fileSize);//取得图片文件的大小
        } else {
            ImgObj = new Image();
            ImgObj.src = obj_file.value;
            filesize = Math.round(ImgObj.fileSize);//取得图片文件的大小
        }
    }
    if (filesize > maxsize) {
        errMsg = "请上传小于10M的文件，当前文件大小为" + Math.round(filesize / 1024 / 1024 * 100) / 100 + "M";
        $.messager.alert('提示', errMsg, 'info');
        $("#editContractName").textbox('setValue', '');

        return;
    }

    var pos1 = path.lastIndexOf("\\");
    var pos2 = path.lastIndexOf(".");
    contractName = path.substring(pos1 + 1, pos2);


    //判断文件名是否重复
    $.get(basePath + "/contract/contract-boolean?orgId=" + orgId + "&contractName=" + contractName,
        function (data) {
            var num = data[0].num;
            if (num == 1) {
                $.messager.alert('提示', '该合同附件名称重复！', 'info');
                $("#editContractName").textbox('setValue', '');

            }
            else {
                $("#editContractName").textbox('setValue', contractName);

            }

        })

}

function upFile() {

    var formData = new FormData($("#editForm")[0]);
    $.ajax({
        url: '/service/upload/upload-file?fileType=HeTongWenBen&orgId=' + orgId,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            fileName = data.fileName;
            fileUrl = data.fileUrl;
            if (fileName != '') {
                $("#url").val(fileUrl)
            }
            save();
        },
        error: function (data) {

        }
    });

}

function save() {

    url = $("#url").val();

    contractName = $("#editContractName").textbox('getValue');
    contractType = $("#editContractType").combobox('getValue');
    contractCode = $("#editContractCode").textbox('getValue');
    probationPeriodId = $("#editProbationPeriod").combobox('getValue');

    startDate = $("#editStartDate").datebox('getValue');
    endDate = $("#editEndDate").datebox('getValue');
    id = $("#id").val();
    flag = $("#flag").val();
    userId = $("#editUserId").val();
    deptId = $("#editDeptId").val();
    userName = $("#editUserName").textbox('getValue');

    var date1 = startDate.replace(/-/g, "\/");

    var date2 = endDate.replace(/-/g, "\/");
    var start = new Date(date1);
    var end = new Date(date2);
    if (start > end) {
        $.messager.alert("提示", "结束时间不能早于开始时间!", "info");
        return
    }
    if (contractCode == '' || contractCode.indexOf(" ") >= 0) {
        $.messager.alert("提示", "请输入有效的合同编号，不能包含空格!", "info");
        return
    }
    if (getRealLen(contractCode) > 20) {
        $.messager.alert("提示", "合同编号输入过长！", "info");
        return
    }
    if (userName == '') {
        $.messager.alert("提示", "请选择签订人员!", "info");
        return
    }
    if (contractType == '') {
        $.messager.alert("提示", "请选择合同类型!", "info");
        return
    }

    if (contractName == '') {
        $.messager.alert('提示', '请选择合同文件！', 'info');
        return
    }

    contractVo.orgId = parent.config.org_Id;
    contractVo.contractId = id;
    contractVo.url = url;
    contractVo.startDate = startDate;
    contractVo.endDate = endDate;
    contractVo.userId = userId;
    contractVo.deptId = deptId;

    contractVo.contractName = contractName;
    contractVo.contractCode = contractCode;
    contractVo.contractType = contractType;
    contractVo.probationPeriodId=probationPeriodId;
//判断是否已存在相同名称数据
    /*$.get(basePath + "/contract/if-exist?orgId=" + orgId + "&contractCode=" + contractCode + "&contractId=" + id,
        function (data) {
            var list = eval(data);
            for (var i = 0; i < 1; i++) {
                var num = list[i]['num'];
            }
            if (num == '1' || num > '1') {
                var str = '该合同编号在系统中已存在！';
                $.messager.alert("提示", str, "info");
                return
            }*/
            $.postJSON(basePath + "/contract/merge", JSON.stringify(contractVo), function (data) {
                $("#editForm").form('reset');
                $("#editWin").window('close');
                $("#primaryGrid").datagrid('reload');
            })
        //});
}


var contractTypeDictList
function contractTypeDict() {
    $.get( '/service/contractType/contract-type-all-list?orgId=' + orgId, function (data) {
        contractTypeDictList = data;

    });
}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}
