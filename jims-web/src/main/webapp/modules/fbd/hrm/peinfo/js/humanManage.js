/**
 * 科室人员管理
 * @author
 * @version 2016-11-03
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var orgId = parent.config.org_Id;
var deptId='';
var page='1';
var rows='30';
var search=false;
var deptName='';
var name='';
var cardNo='';
var sex='';
var title='';
var title_level='';
var age1='';
var age2='';
var type='';
var classify='';
var education='';
var education_final='';
var politic='';
var skill='';
var level='';
var marry='';
var nation='';
var salaryLevel='';
var roleName='';
var deptIds=parent.orgids;
var orgCount=0;
var orgCount1=0;
var orgCount2=0;
var orgCount3=0;
var orgCount4=0;
var orgCount5=0;
var orgCount6=0;
var orgCount7=0;
var orgCount8=0;
var orgCount9=0;
var orgCount10=0;
var orgCount11=0;
var orgCount12=0;
var orgCount13=0;
var orgCount14=0;
var orgCount15=0;
var orgCount16=0;
var orgCount17=0;
var orgCount18=0;

var num=0;
var num1=0;
var myInfoVo = {};
var myInfoVo1 = {};
$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#detailGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    //人员信息
    $("#staffGrid").datagrid({
        width: '100%',
        height: '100%',
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        nowrap:false,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        border: true,
        url: basePath + "/peinfo/list?orgId=" + orgId + "&deptId=" + deptId+ "&name=" + name+ "&cardNo=" + cardNo+ "&sex=" + sex+ "&title=" + title+
        "&title_level="+title_level+ "&age1=" + age1+ "&age2=" + age2+"&education="+education+"&education_final="+education_final+"&type="+type+"&politic="+politic+
        "&classify="+classify+"&roleName="+roleName+"&salaryLevel="+salaryLevel+"&skill="+skill+"&level="+level+"&marry="+marry+"&nation="+nation+"&deptIds=" + deptIds,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        showFooter: true,
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "姓名",
            field: "name",
            align: 'center',
            width: '6%'

        }, {
            title: "科室",
            field: "dept",
            align: 'center',
            width: '8%'

        },{
            title: "科室ID",
            field: "deptId",
            hidden: 'true'

        },{
            title: '身份证号',
            field: 'cardNo',
            align: 'center',
            width: '13%'
        },{
            title: '职务',
            field: 'roleName',
            align: 'center',
            width: '9%'
        },{
            title: '人员分类',
            field: 'classify',
            align: 'center',
            width: '9%',
            formatter: function (value, row, index) {
                var classify = value;
                $.each(classifyList, function (index, item) {
                    if (item.value == value) {
                        classify = item.label;
                    }
                });
                return classify;
            }
        },{
            title: '人员状态',
            field: 'type',
            align: 'center',
            width: '9%',
            formatter: function (value, row, index) {
                var type = value;
                $.each(typeList, function (index, item) {
                    if (item.value == value) {
                        type = item.label;
                    }
                });
                return type;
            }
        },{
            title: '人员技能',
            field: 'skill',
            align: 'center',
            width: '9%',
            formatter: function (value, row, index) {
                var skill = value;
                $.each(skillList, function (index, item) {
                    if (item.skillId == value) {
                        skill = item.skill;
                    }
                });
                return skill;
            }
        },{
            title: '技能等级',
            field: 'skillLevel',
            align: 'center',
            hidden: true,
            formatter: function (value, row, index) {
                var skillLevel = value;
                $.each(skillLevelList, function (index, item) {
                    if (item.levelId == value) {
                        skillLevel = item.level;
                    }
                });
                return skillLevel;
            }
        },{
            title: "技能名称",
            field: "skillName",
            hidden: true
        },{
            title: "技能等级",
            field: "levelName",
            align: 'center',
            width: '9%'
        },{
            title: '职称类别',
            field: 'title',
            align: 'center',
            width: '10.5%',
            formatter: function (value, row, index) {
                var title = value;
                $.each(titleList, function (index, item) {
                    if (item.value == value) {
                        title = item.label;
                    }
                });
                return title;
            }
        },{
            title: '职称级别',
            field: 'titleLevel',
            align: 'center',
            width: '10.5%',
            formatter: function (value, row, index) {
                var titleLevel = value;
                $.each(titleLevelList, function (index, item) {
                    if (item.value == value) {
                        titleLevel = item.label;
                    }
                });
                return titleLevel;
            }
        },{
            title: '工资级别ID',
            field: 'typeId',
            hidden: true
        },{
            title: '工资级别',
            field: 'typeName',
            align: 'center',
            width: '8%'
        },{
            title: '年龄',
            field: 'age3',
            align: 'center',
            width: '5%'
        },{
                title: '出生日期',
                field: 'age',
                align: 'center',
                width: '7%'
        },{
            title: '原始学历',
            field: 'education',
            align: 'center',
            width: '6%',
            formatter: function (value, row, index) {
                var education = value;
                $.each(educationList, function (index, item) {
                    if (item.value == value) {
                        education = item.label;
                    }
                });
                return education;
            }
        },{
            title: '原始学历获得时间',
            field: 'educationTime',
            align: 'center',
            width: '13%'
        },{
            title: '最终学历',
            field: 'educationFinal',
            align: 'center',
            width: '6%',
            formatter: function (value, row, index) {
                var education = value;
                $.each(educationList, function (index, item) {
                    if (item.value == value) {
                        education = item.label;
                    }
                });
                return education;
            }
        },{
            title: '最终学历获得时间',
            field: 'educationFinalTime',
            align: 'center',
            width: '13%'
        },{
            title: '性别',
            field: 'sex',
            align: 'center',
            width: '5%',
            formatter: function (value, row, index) {
                var sex = value;
                $.each(sexList, function (index, item) {
                    if (item.value == value) {
                        sex = item.label;
                    }
                });
                return sex;
            }
        },{
                title: '联系电话',
                field: 'phoneNum',
                align: 'center',
                width: '9%'
            }, {
                title: '邮箱',
                field: 'email',
                align: 'center',
                width: '11%'
            }, /*{
             title: '昵称',
             field: 'nickName',
             align: 'center',
             width: '10%'
             }, */{
                title: '民族',
                field: 'nation',
                align: 'center',
                width: '6%',
                formatter: function (value, row, index) {
                    var nation = value;
                    $.each(nationList, function (index, item) {
                        if (item.value == value) {
                            nation = item.label;
                        }
                    });
                    return nation;
                }
            }, {
            title: '政治面貌',
            field: 'politic',
            align: 'center',
            width: '6%',
            formatter: function (value, row, index) {
                var politic = value;
                $.each(politicList, function (index, item) {
                    if (item.value == value) {
                        politic = item.label;
                    }
                });
                return politic;
            }
        },{field: 'marry', title: '婚姻状况', width:'5%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "未婚";
                    }
                    if (value == "1") {
                        return "已婚";
                    }
                }
            },{
            title: '参加工作时间',
            field: 'workTime',
            align: 'center',
            width: '13%'
        },{
            title: '来院工作时间',
            field: 'comeTime',
            align: 'center',
            width: '13%'
        },{
                title: '照片地址',
                field: 'pic',
                hidden: 'true'
            },{
                title: '目前住址',
                field: 'address',
                hidden: 'true'
            },{
                title: '工作经历',
                field: 'exp',
                hidden: 'true'
            },{
                title: '备注',
                field: 'remark',
                hidden: 'true'
            },{
                title: '人员id',
                field: 'staffId',
                hidden: 'true'
            }
        ]],onLoadSuccess:function(data){

            $('#ft').css('display','block');
            $('#addStaffFt').css('display','block');
            $("#staffGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#staffGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        }
    });
    /*工作经验表格*/
    $("#expGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        //url:basePath + '/socialSecurityPlan/ss-plan-detail-list?orgId='+orgId+'&ssPlanId=""',
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠
        remoteSort: false,
        idField: 'expId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        fitColumns: true,
        columns: [[
            {field: 'ck', title: '', width: 20, checkbox: true},
            {field: 'expId', title: '',  hidden: true},
            {
                field: 'startTime',
                title: '开始时间',
                width: 50,
                align: 'center',
                editor: {type:'datebox',options: {
                    editable:false
                }}
            },
            {field: 'endTime', title: '结束时间', width: 50, align: 'center',
                editor: {type:'datebox',options: {
                    editable:false
                }}
            },
            {field: 'unit', title: '工作单位', width: 85, align: 'center',
                editor: {type:'textbox'}
            },
            {field: 'post', title: '岗位名称', width: 60, align: 'center',
                editor: {type:'textbox'}
            }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    //新增工作经验信息
    $("#addDetailBtn").on('click', function () {
        stopEdit();
        $("#expGrid").datagrid('appendRow', {expId:'&'+num});
        num++;
        var rows = $("#expGrid").datagrid('getRows');
        var addRowIndex = $("#expGrid").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#expGrid").datagrid('selectRow', editIndex);
        $("#expGrid").datagrid('beginEdit', editIndex);
    });
    //删除工作经验
    $("#removeDetailBtn").on('click', function () {

        if (editIndex || editIndex == 0) {
            $("#expGrid").datagrid("endEdit", editIndex);
        }
        var row1 = $("#expGrid").datagrid('getSelected');
        if (row1 == null||!row1) {
            $.messager.alert("提示", "请选择要删除的工作经历数据", "info");
            return;
        }
        $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
            if (r) {
                var a = [];
                var b=[];
                a.push(row1);
                b.push(row1.expId);
                if (a.length > 0&&row1.expId.substring(0,1)=='&') {
                    for (var j = 0; j < a.length; j++) {
                        var index = $('#expGrid').datagrid('getRowIndex', row1);
                        $("#expGrid").datagrid('deleteRow', index);
                    }
                    $('#expGrid').datagrid('clearSelections');
                }
                if(b.length>0&&row1.expId.substring(0,1)!='&'){
                    for(var j =0;j<b.length;j++){
                        myInfoVo.expId =b[j];
                        $.get(basePath + "/peinfo/exp-del?expId="+b[j],  function (data) {
                            $("#expGrid").datagrid('reload');
                            $('#expGrid').datagrid('clearSelections');
                        });
                    }

                }
            }
        });
    });
    /*社会关系表格*/
    $("#relGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        //url:basePath + '/socialSecurityPlan/ss-plan-detail-list?orgId='+orgId+'&ssPlanId=""',
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠
        remoteSort: false,
        idField: 'relId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        fitColumns: true,
        columns: [[
            {field: 'ck', title: '', width: 20, checkbox: true},
            {field: 'relId', title: '',  hidden: true},
            {
                field: 'relationship',
                title: '与本人关系',
                width: 50,
                align: 'center',
                editor: {type:'textbox'}
            },
            {field: 'relName', title: '姓名', width: 50, align: 'center',
                editor: {type:'textbox'}
            },
            {field: 'relAge', title: '年龄', width: 40, align: 'center',
                editor: {type:'numberspinner',options:{min:1,max:100}}
            },
            {field: 'relUnit', title: '所在单位', width: 90, align: 'center',
                editor: {type:'textbox'}
            },
            {field: 'relPost', title: '所在岗位', width: 60, align: 'center',
                editor: {type:'textbox'}
            },
            {field: 'health', title: '健康状况', width: 50, align: 'center',
                editor: {type:'textbox'}
            }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    //新增社会关系信息
    $("#addDetailBtn1").on('click', function () {
        stopEdit();
        $("#relGrid").datagrid('appendRow', {relId:'&'+num1});
        num1++;
        var rows = $("#relGrid").datagrid('getRows');
        var addRowIndex = $("#relGrid").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#relGrid").datagrid('selectRow', editIndex);
        $("#relGrid").datagrid('beginEdit', editIndex);
    });
    //删除社会关系
    $("#removeDetailBtn1").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#relGrid").datagrid("endEdit", editIndex);
        }
        var row1 = $("#relGrid").datagrid('getSelected');
        if (row1 == null||!row1) {
            $.messager.alert("提示", "请选择要删除的社会关系数据", "info");
            return;
        }
        $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
            if (r) {
                var a = [];
                var b = [];
                a.push(row1);
                b.push(row1.relId);
                if (a.length > 0 && row1.relId.substring(0, 1) == '&') {
                    for (var j = 0; j < a.length; j++) {
                        var index = $('#relGrid').datagrid('getRowIndex', row1);
                        $("#relGrid").datagrid('deleteRow', index);
                    }
                    $('#relGrid').datagrid('clearSelections');
                }
                if (b.length > 0 && row1.relId.substring(0, 1) != '&') {
                    for (var j = 0; j < b.length; j++) {
                        myInfoVo.expId = b[j];
                        $.get(basePath + "/peinfo/rel-del?relId=" + b[j], function (data) {
                            $("#relGrid").datagrid('reload');
                            $('#relGrid').datagrid('clearSelections');
                        });
                    }
                }
            }
        });
    });
    //科室树状列表
    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        treeField: "deptName",
        toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'deptName',
            width: '140%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            deptId = node.id;
            deptName = node.deptName;
            name = $("#NAME_SEARCH").textbox('getValue');
            cardNo = $("#CARD_NUM").textbox('getValue');
            nation = $("#NATION_SEARCH").combogrid('getValue');
            if(nation=='请选择'){
                nation='';
            }
            sex = $("#SEX_SEARCH").combobox('getValue');
            if(sex=='请选择'){
                sex='';
            }
            title = $("#TITLE_SEARCH").combobox('getValue');
            if(title=='请选择'){
                title='';
            }
            education = $("#EDUCATION").combobox('getValue');
            if(education=='请选择'){
                education='';
            }
            education_final = $("#EDUCATION_FINAL").combobox('getValue');
            if(education_final=='请选择'){
                education_final='';
            }
            politic = $("#POLITIC_SEARCH").combobox('getValue');
            if(politic=='请选择'){
                politic='';
            }
            type = $("#TYPE").combobox('getValue');
            if(type=='请选择'){
                type='';
            }
            skill = $("#SKILL_SEARCH").combobox('getValue');
            if(skill=='请选择'||skill==null||$("#SKILL_SEARCH").combobox('getText')=='请选择'){
                skill='';
            }
            level = $("#SKILL_LEVEL_SEARCH").combobox('getValue');
            if(level=='请选择'||level==null||$("#SKILL_LEVEL_SEARCH").combobox('getText')=='请选择'){
                level='';
            }
            salaryLevel = $("#SALARY_LEVEL").combobox('getValue');
            if(salaryLevel=='请选择'){
                salaryLevel='';
            }
            roleName = $("#ROLE_SEARCH").combobox('getText');
            if(roleName=='请选择'){
                roleName='';
            }
            marry = $("#MARRY").combobox('getValue');
            if(marry=='请选择'){
                marry='';
            }
            age1 = $("#AGE1").numberspinner('getValue');
            age2 = $("#AGE2").numberspinner('getValue');
            var url = basePath + "/peinfo/list?orgId=" + orgId + "&deptId=" + deptId+ "&name=" + name+ "&cardNo=" + cardNo+ "&sex=" + sex+ "&title=" + title+
                "&title_level="+title_level+ "&age1=" + age1+ "&age2=" + age2+"&education="+education+"&education_final="+education_final+"&type="+type+"&politic="+politic+
                "&classify="+classify+"&roleName="+roleName+"&skill="+skill+"&salaryLevel="+salaryLevel+"&level="+level+"&marry="+marry+"&nation="+nation+"&deptIds=" + deptIds;
            $("#staffGrid").datagrid("reload", url);

        }
    });

    // var orgId = parent.config.org_id;
    //加载树形结构的treegrid数据
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/salary-data/list?orgId=" + orgId+"&deptIds="+deptIds, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.deptName = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.children = [];

                depts.push(obj);

            });

        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }

            $("#staff").treegrid('loadData', treeDepts);
        })
    };
    loadDept();


    //展示全部
    $("#searchAllBtn").on("click", function () {
        deptId = '';
        var url = basePath + "/peinfo/list?orgId=" + orgId + "&deptId=" + deptId;
        $("#staffGrid").datagrid("reload", url);

    });
    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);

    });
    var searchData= function (page,rows){
        //获取级别名称
        name = $("#NAME_SEARCH").textbox('getValue');
        cardNo = $("#CARD_NUM").textbox('getValue');
        sex = $("#SEX_SEARCH").combobox('getValue');
        if(sex=='请选择'){
            sex='';
        }
        nation = $("#NATION_SEARCH").combogrid('getValue');
        if(nation=='请选择'){
            nation='';
        }
        title = $("#TITLE_SEARCH").combobox('getValue');
        if(title=='请选择'){
            title='';
        }
        title_level = $("#TITLE_LEVEL_SEARCH").combobox('getValue');
        if(title_level=='请选择'){
            title_level='';
        }
        age1 = $("#AGE1").numberspinner('getValue');
        age2 = $("#AGE2").numberspinner('getValue');
        education = $("#EDUCATION").combobox('getValue');
        if(education=='请选择'){
            education='';
        }
        education_final = $("#EDUCATION_FINAL").combobox('getValue');
        if(education_final=='请选择'){
            education_final='';
        }
        politic = $("#POLITIC_SEARCH").combobox('getValue');
        if(politic=='请选择'){
            politic='';
        }
        type = $("#TYPE").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        classify = $("#CLASSIFY").combobox('getValue');
        if(classify=='请选择'){
            classify='';
        }
        skill = $("#SKILL_SEARCH").combobox('getValue');
        if(skill=='请选择'||skill==null||$("#SKILL_SEARCH").combobox('getText')=='请选择'){
            skill='';
        }
        level = $("#SKILL_LEVEL_SEARCH").combobox('getValue');
        if(level=='请选择'||level==null||$("#SKILL_LEVEL_SEARCH").combobox('getText')=='请选择'){
            level='';
        }
        salaryLevel = $("#SALARY_LEVEL").combobox('getValue');
        if(salaryLevel=='请选择'){
            salaryLevel='';
        }
        if($("#SALARY_LEVEL").combobox('getText')=='全部级别'){
            salaryLevel='';
        }
        roleName = $("#ROLE_SEARCH").combobox('getText');
        if(roleName=='请选择'){
            roleName='';
        }
        marry = $("#MARRY").combobox('getValue');
        if(marry=='请选择'){
            marry='';
        }
        $("#staffGrid").datagrid('reload', basePath + "/peinfo/list?orgId=" + orgId + "&deptId=" + deptId+ "&name=" + name+ "&cardNo=" + cardNo+ "&sex=" + sex+ "&title=" + title+
            "&title_level="+title_level+ "&age1=" + age1+ "&age2=" + age2+"&education="+education+"&education_final="+education_final+"&type="+type+"&politic="+politic+
            "&classify="+classify+"&roleName="+roleName+"&skill="+skill+"&salaryLevel="+salaryLevel+"&level="+level+"&marry="+marry+"&nation="+nation+"&deptIds=" + deptIds+'&rows=' + rows+ '&page=' + page);
        if(search){
            search=false;
            if(page!='1') {
                $("#staffGrid").datagrid('getPager').pagination('select', 1);
            }
        }
        $("#staffGrid").datagrid('clearSelections');
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
    });

    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#NAME_SEARCH").textbox('clear');
        $("#NAME_SEARCH").textbox("setValue","");
        $("#CARD_NUM").textbox('clear');
        $("#CARD_NUM").textbox("setValue","");
        $("#NATION_SEARCH").combogrid('clear');
        $("#NATION_SEARCH").combogrid("setValue","00");
        $("#TITLE_SEARCH").combobox('clear');
        $("#TITLE_SEARCH").combobox("setValue","请选择");
        $("#TITLE_LEVEL_SEARCH").combobox('clear');
        $("#TITLE_LEVEL_SEARCH").combobox("setValue","请选择");
        $("#SEX_SEARCH").combobox('clear');
        $("#SEX_SEARCH").combobox("setValue","请选择");
        $("#NATION_SEARCH").combogrid('clear');
        $("#NATION_SEARCH").combogrid("setText","请选择");
        $("#TYPE").combobox('clear');
        $("#TYPE").combobox("setValue","请选择");
        $("#CLASSIFY").combobox('clear');
        $("#CLASSIFY").combobox("setValue","请选择");
        $("#SKILL_SEARCH").combobox('clear');
        $("#SKILL_SEARCH").combobox("setText","请选择");
        $("#SKILL_LEVEL_SEARCH").combobox('clear');
        $("#SKILL_LEVEL_SEARCH").combobox("setText","请选择");
        $("#SALARY_LEVEL").combobox('clear');
        $("#SALARY_LEVEL").combobox("setValue","请选择");
        $("#MARRY").combobox('clear');
        $("#MARRY").combobox("setValue","请选择");
        $("#POLITIC_SEARCH").combobox('clear');
        $("#POLITIC_SEARCH").combobox("setValue","请选择");
        $("#ROLE_SEARCH").combobox('clear');
        $("#ROLE_SEARCH").combobox("setValue","请选择");
        $("#EDUCATION").combobox('clear');
        $("#EDUCATION").combobox("setValue","请选择");
        $("#EDUCATION_FINAL").combobox('clear');
        $("#EDUCATION_FINAL").combobox("setValue","请选择");
        $("#AGE1").numberspinner('clear');
        $("#AGE1").numberspinner("setValue","");
        $("#AGE2").numberspinner('clear');
        $("#AGE2").numberspinner("setValue","");
    };
    //查询条件:部门树选择
    $("#human_dept").combotree({
        panelWidth: '320px',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        value:'请选择',
        text:'请选择',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'text',
            width: '100%'
        }]],
        onSelect: function (node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            /*if (!isLeaf) {
                //清除选中
                $('#DEPT_ID').combotree('clear');
                $.messager.alert("提示", "请选择具体科室!", "info");
            }*/
        }
    });
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.text = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.children = [];
                depts.push(obj);
            });
        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }
                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#human_dept").combotree('loadData', treeDepts);
        })
    };

    loadDept();
    //查询、新增条件：职称类别
    $("#TITLE_SEARCH").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'TITLE_DICT',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '100px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#title").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT',
        idField: 'value',
        textField: 'label',
       // value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount1=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount1>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //查询、新增条件：职称级别
    $("#TITLE_LEVEL_SEARCH").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'TITLE_LEVEL',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount2=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount2>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#title_level").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'TITLE_LEVEL',
        idField: 'value',
        textField: 'label',
        // value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount3=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount3>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //加载职务
    $('#role').combobox({
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        valueField: 'id',
        textField: 'roleName',
        method: 'GET',
        panelWidth: '320px',
        panelHeight: 'auto',
        onLoadSuccess:function(data){
            orgCount4=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount4>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $('#ROLE_SEARCH').combobox({
        url: basePath + '/org-role/findAllListByOrgId1?orgId=' + orgId,
        valueField: 'id',
        textField: 'roleName',
        value:'请选择',
        method: 'GET',
        panelWidth: '80px',
        panelHeight: 'auto',
        onLoadSuccess:function(data){
            orgCount5=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount5>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //查询、新增条件：性别
    $("#SEX_SEARCH").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'SEX_DICT',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote'
    });
    $("#human_sex").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'SEX_DICT',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote'
    });
    //查询、新增条件：人员状态
    $("#TYPE").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'HUMAN_TYPE',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '100px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount6=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount6>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#human_type").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'HUMAN_TYPE',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount7=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount7>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //查询、新增条件：人员分类
    $("#CLASSIFY").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'HUMAN_CLASSIFY',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '100px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount8=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount8>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#human_classify").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'HUMAN_CLASSIFY',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount9=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount9>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //查询新增条件：原始学历、最终学历
    $("#EDUCATION").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'EDUCATION_DICT',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount10=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount10>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#EDUCATION_FINAL").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'EDUCATION_DICT',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount11=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount11>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#human_education").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'EDUCATION_DICT',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount12=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount12>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#human_education_final").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'EDUCATION_DICT',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount13=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount13>10){
                $(this).combobox('panel').height(160);
            }
        }
    });

    //查询新增条件：人员技能、技能等级
    $("#SKILL_SEARCH").combobox({
        panelWidth: '80px',
        panelHeight: 'auto',
        value:'请选择',
        valueField: 'skillId',
        textField: 'skill',
        loadMsg: '数据正在加载...',
        url: basePath + '/peinfo/skill-downlist1?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onSelect: function (data) {
            $("#SKILL_LEVEL_SEARCH").combobox({
               //disabled: false,
                url: basePath + '/peinfo/level-downlist1?orgId=' + orgId +'&skillId=' + data.skillId,
                valueField: 'levelId',
                textField: 'level',
                value:'请选择',
                panelWidth: '80px',
                panelHeight: 'auto',
                mode: 'remote',
                method: 'GET'
            });
        },
        onChange: function (data) {
            if(data==null){
                $("#SKILL_SEARCH").combobox('setText','全部');
                $("#SKILL_LEVEL_SEARCH").combobox('setText','全部');
            }
        }
    });
    $("#skill").combobox({
        panelWidth: '320px',
        panelHeight: 'auto',
        valueField: 'skillId',
        textField: 'skill',
        loadMsg: '数据正在加载...',
        url: basePath + '/peinfo/skill-downlist?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onSelect: function (data) {
            $("#skillLevel").combobox({
                //disabled: false,
                url: basePath + '/peinfo/level-downlist?orgId=' + orgId +'&skillId=' + data.skillId,
                valueField: 'levelId',
                textField: 'level',
                panelWidth: '320px',
                panelHeight: 'auto',
                mode: 'remote',
                method: 'GET'
            });
            if(data.skillId==''){
                $("#SKILL_LEVEL_SEARCH").combobox('setText','全部');
            }
        }
    });
    $("#SKILL_LEVEL_SEARCH").combobox({
        value:'请选择',
        panelHeight: 'auto'
    });
    $("#skillLevel").combobox({
        //value:'请选择',
        panelHeight: 'auto'
    });
    //查询条件：工资级别
    $("#SALARY_LEVEL").combobox({
        panelWidth: '80px',
        panelHeight: 'auto',
        value:'请选择',
        valueField: 'typeId',
        textField: 'typeName',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary-ht/ht-list1?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onLoadSuccess:function(data){
            orgCount14=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount14>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //查询新增条件：婚姻状况
    $("#MARRY").combobox({
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto'
    });
    $("#human_marry").combobox({
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto'
    });
    //查询新增条件：政治面貌
    $("#POLITIC_SEARCH").combobox({
        url: basePath + '/dict/find-list-by-type1?type=' + 'POLITIC_DICT',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        panelWidth: '80px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount15=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount15>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#politic").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'POLITIC_DICT',
        idField: 'value',
        textField: 'label',
        //value:'请选择',
        panelWidth: '320px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount16=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount16>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    //民族查询新增下拉列表
    $('#nation').combogrid({     //民族
        panelWidth: 320,
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        url: basePath + '/dict/find-list-by-type?type=' + 'NATION_DICT',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'label', title: '民族', align: 'center', width: 320},
            {field: 'value', title: '标签',hidden:true}
        ]],
        onLoadSuccess:function(data){
            orgCount17=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount17>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $('#NATION_SEARCH').combogrid({     //民族
        panelWidth: 80,
        idField: 'value',
        textField: 'label',
        value:'请选择' ,
        loadMsg: '数据正在加载',
        url: basePath + '/dict/find-list-by-type1?type=' + 'NATION_DICT',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'label', title: '民族', align: 'center', width: 80},
            {field: 'value', title: '', hidden:true}
        ]],
        onLoadSuccess:function(data){
            orgCount18=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount18>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    var typeList = [];   //人员状态
    $.get(basePath + '/dict/findListByType?type=HUMAN_TYPE', function (data) {
        typeList = data;
    });
    var classifyList = [];   //人员分类
    $.get(basePath + '/dict/findListByType?type=HUMAN_CLASSIFY', function (data) {
        classifyList = data;
    });
    var skillList = [];   //人员技能
    $.get(basePath + '/peinfo/skill-downlist?orgId=' + orgId, function (data) {
        skillList = data;
    });
    var skillLevelList = [];   //技能等级
    $.get(basePath + '/peinfo/level-list?orgId=' + orgId, function (data) {
        skillLevelList = data;
    });
    var educationList = [];   //学历
    $.get(basePath + '/dict/findListByType?type=EDUCATION_DICT', function (data) {
        educationList = data;
    });
    var sexList = [];   //性别
    $.get(basePath + '/dict/findListByType?type=SEX_DICT', function (data) {
        sexList = data;
    });
    var politicList = [];   //性别
    $.get(basePath + '/dict/findListByType?type=POLITIC_DICT', function (data) {
        politicList = data;
    });
    var nationList = [];    //民族
    $.get(basePath + '/dict/find-list-by-type?type=NATION_DICT', function (data) {
        nationList = data;
    });

    var titleList = [];     //职称类别
    $.get(basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT', function (data) {
        titleList = data
    });
    var titleLevelList = [];     //职称级别
    $.get(basePath + '/dict/find-list-by-type?type=' + 'TITLE_LEVEL', function (data) {
        titleLevelList = data
    });

    //配置详细信息窗口
    $("#w").window({
        title: '人员详细信息',
        closed: true,
        modal: true,
        minimizable: false
    });
    /**
     * 人员详细信息
     */
    $("#infoBtn").on("click", function () {
        //获取选择行
        var row=$('#staffGrid').datagrid('getSelected');
        if(row){
            $("#w").window('open');
            $("#name1").textbox("setValue",row.name);

            $.get(basePath + '/dict/findListByType?type=' + 'SEX_DICT'+'&value='+row.sex, function (data) {
                var sex = '';
                if(row.sex&&row.sex!='') {
                    sex=data[0].label;
                }
                $("#sex1").textbox("setValue",sex);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'TITLE_DICT'+'&value='+row.title, function (data) {
                var title = '';
                if(row.title&&row.title!='') {
                    title=data[0].label;
                }
                $("#title1").textbox("setValue",title);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'TITLE_LEVEL'+'&value='+row.titleLevel, function (data) {
                var titleLevel = '';
                if(row.titleLevel&&row.titleLevel!='') {
                    titleLevel=data[0].label;
                }
                $("#titleLevel1").textbox("setValue",titleLevel);
            });

            $("#card_no1").textbox("setValue",row.cardNo);
            $("#age11").textbox("setValue",row.age);
            $.get(basePath + '/dict/findListByType?type=' + 'NATION_DICT'+'&value='+row.nation, function (data) {
                var nation = '';
                if(row.nation&&row.nation!='') {
                    nation=data[0].label;
                }
                $("#nation1").textbox("setValue",nation);
            });

            $("#dept1").textbox("setValue",row.dept);
            $.get(basePath + '/dict/findListByType?type=' + 'HUMAN_CLASSIFY'+'&value='+row.classify, function (data) {
                var classify = '';
                if(row.classify&&row.classify!='') {
                    classify=data[0].label;
                }
                $("#classify1").textbox("setValue",classify);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'HUMAN_TYPE'+'&value='+row.type, function (data) {
                var type = '';
                if(row.type&&row.type!='') {
                    type=data[0].label;
                }
                $("#type1").textbox("setValue",type);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'POLITIC_DICT'+'&value='+row.politic, function (data) {
                var politic = '';
                if(row.politic&&row.politic!='') {
                    politic=data[0].label;
                }
                $("#politic1").textbox("setValue",politic);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'EDUCATION_DICT'+'&value='+row.education, function (data) {
                var education = '';
                if(row.education&&row.education!='') {
                    education=data[0].label;
                }
                $("#education1").textbox("setValue",education);
            });
            $.get(basePath + '/dict/findListByType?type=' + 'EDUCATION_DICT'+'&value='+row.educationFinal, function (data) {
                var education = '';
                if(row.educationFinal&&row.educationFinal!='') {
                    education=data[0].label;
                }
                $("#education_final1").textbox("setValue",education);
            });
            if(row.marry==0){
                $("#marry1").textbox("setValue",'未婚');
            }
            if(row.marry==1){
                $("#marry1").textbox("setValue",'已婚');
            }
            $("#education_time1").textbox("setValue",row.educationTime);
            $("#education_final_time1").textbox("setValue",row.educationFinalTime);
            $("#politic1").textbox("setValue",row.politic);
            var wt = row.workTime;
            var ct = row.comeTime;
            if(wt==null){
                wt='';
            }
            if(ct==null){
                ct='';
            }
            $("#work_time1").textbox("setValue",wt+ct);
            $("#roleName1").textbox("setValue",row.roleName);
            //$("#come_time1").textbox("setValue",row.comeTime);
            $("#skill1").textbox("setValue",row.skillName);
            $("#skillLevel1").textbox("setValue",row.levelName);
            $("#tel1").textbox("setValue",row.phoneNum);
            $("#email1").textbox("setValue",row.email);
            $("#address").textbox("setValue",row.address);
            //$("#exp").val(row.exp);
            //$("#remark").val(row.remark);
            //document.getElementById("img").src=row.pic;
            if(row.pic==null||row.pic=='') {
                document.getElementById("img").src = '/modules/fbd/hrm/peinfo/js/img.png';
            }
            else{
                document.getElementById("img").src = row.pic;
            }
            //工作经历列表
            $("#expGrid1").datagrid({
                iconCls: 'icon-edit',//图标
                width: '95%',
                height: 'auto',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                url:basePath +'/peinfo/workExp-list?orgId='+orgId+'&personId='+row.cardNo,
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                idField: 'expId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                   /* {field: 'ck', title: '', width: 20, checkbox: true},*/
                    {field: 'expId', title: '',  hidden: true},
                    {
                        field: 'startTime',
                        title: '开始时间',
                        width: 50,
                        align: 'center'
                    },
                    {field: 'endTime', title: '结束时间', width: 50, align: 'center'
                    },
                    {field: 'unit', title: '工作单位', width: 85, align: 'center'
                    },
                    {field: 'post', title: '岗位名称', width: 60, align: 'center'
                    }
                ]]
            });
            /*社会关系表格*/
            $("#relGrid1").datagrid({
                iconCls: 'icon-edit',//图标
                width: '95%',
                height: 'auto',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                url:basePath +'/peinfo/rel-list?orgId='+orgId+'&personId='+row.cardNo,
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                idField: 'relId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                   /* {field: 'ck', title: '', width: 20, checkbox: true},*/
                    {field: 'relId', title: '',  hidden: true},
                    {
                        field: 'relationship',
                        title: '与本人关系',
                        width: 50,
                        align: 'center'
                    },
                    {field: 'relName', title: '姓名', width: 50, align: 'center'
                    },
                    {field: 'relAge', title: '年龄', width: 40, align: 'center'
                    },
                    {field: 'relUnit', title: '所在单位', width: 90, align: 'center'
                    },
                    {field: 'relPost', title: '所在岗位', width: 60, align: 'center'
                    },
                    {field: 'health', title: '健康状况', width: 50, align: 'center'
                    }
                ]]
            });
        }
        else{
            $.messager.alert("提示", "请选择一条人员数据！","info");
        }

    });

    //新增人员模态框
    $("#addStaff").window({
        title: '编辑人员',
        closed: true,
        minimizable: false,
        modal: true,
        onClose: function () {
            $("#editForm").form('reset');
        },
        onOpen: function () {
           var node = $("#staff").treegrid("getSelected");
            if (node) {
                $("#deptName").combobox('setValue', node.id);
                $("#deptName").combobox('setText', node.deptName);
            }
        }
    });
    //新增人员按钮
    $("#addBtn").on('click', function () {
        // $("#hiddenDiv")[0].style.display = '';     //显示检索框
       /* $("#hiddenDiv0")[0].style.display = '';     //显示检索框
        $("#hiddenDiv1")[0].style.display = '';     //显示检索框
        $("#password").validatebox({'disabled': false});*/

       // var node = $("#staff").treegrid("getSelected");
        //if (node) {
            $("#addStaff").window('open');
            $("#editform").form('reset');
            $("#role").combobox('clear');
            $("#role").combobox('setValue','');
            $("#id").val("");
            $("#selectCardNo").val("");
            $("#res-card").html("");
            $("#res-nick").html("");
            $("#res-phone").html("");
            $("#res-password").html("");
            $("#res-email").html("");
            $("#res-name").html("");
            $("#res-title").html("");
        $("#cardNo").textbox('setValue','');
        $("#human_type").combobox('setValue','');
        $("#human_classify").combobox('setValue','');
        $("#skill").combobox('setValue','');
        $("#skillLevel").combobox('setValue','');
        $("#human_education").combobox('setValue','');
        $("#human_education_final").combobox('setValue','');
        $("#phoneNum").textbox('setValue','');
        $("#name").val('');

        $("#human_dept").combobox('setValue','');
        $("#human_dept").combobox('setText','');
        $("#human_birth").datebox('setValue','');
        $("#human_education_time").datebox('setValue','');
        $("#human_education_final_time").datebox('setValue','');
        $("#work_time").datebox('setValue','');
        $("#come_time").datebox('setValue','');
        $("#email").textbox('setValue','');
        //$("#nickName").val(node.nickName);
        $("#title").combobox('setValue', '');
        $("#title_level").combobox('setValue', '');
        $("#human_sex").combobox('setValue', '');
        $("#nation").combogrid('setValue', '');
        $("#education").combobox('setValue', '');
        $("#human_marry").combobox('setValue', '');
        $("#politic").combobox('setValue', '');
        $("#human_address").textbox('setValue','');
        $("#human_exp").val('');
        $("#human_remark").val('');
        $("#pic").val('');
        document.getElementById('imgShow').src = '/modules/fbd/hrm/peinfo/js/img.png';
        num=0;
        num1=0;
        $("#expGrid").datagrid({
            url:basePath +'/peinfo/workExp-list?orgId='+orgId+'&personId='+'',
            idField: 'expId',
            loadMsg: '数据正在加载中，请稍后.....',
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
        $("#relGrid").datagrid({
            url:basePath +'/peinfo/rel-list?orgId='+orgId+'&personId='+'',
            idField: 'relId',
            loadMsg: '数据正在加载中，请稍后.....',
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
    });
    //修改人员按钮
    $("#editBtn").on('click', function () {
        // $("#hiddenDiv")[0].style.display = 'none';     //隐藏检索框
        //$("#hiddenDiv0")[0].style.display = 'none';     //隐藏密码框
        //$("#hiddenDiv1")[0].style.display = 'none';     //隐藏确认密码框
        $("#res-card").html("");
        $("#res-nick").html("");
        $("#res-phone").html("");
        $("#res-password").html("");
        $("#res-email").html("");
        $("#res-name").html("");
        $("#res-title").html("");
        var node = $("#staffGrid").datagrid("getSelected");
        if (node) {
            $("#addStaff").window('open');
            //$("#password").validatebox({'disabled': true});
            //$("#selectCardNo").val("");
            $("#cardNo").textbox('setValue',node.cardNo);
            $("#human_type").combobox('setValue',node.type);
            $("#human_classify").combobox('setValue',node.classify);
            $("#skill").combobox('setValue',node.skill);
            $("#skillLevel").combobox('setValue',node.skillLevel);
            $("#human_education").combobox('setValue',node.education);
            $("#human_education_final").combobox('setValue',node.educationFinal);
            $("#human_education_time").datebox('setValue',node.educationTime);
            $("#human_education_final_time").datebox('setValue',node.educationFinalTime);
            $("#work_time").datebox('setValue',node.workTime);
            $("#come_time").datebox('setValue',node.comeTime);
            $("#phoneNum").textbox('setValue',node.phoneNum);
            $("#name").val(node.name);
            $("#politic").combobox('setValue',node.politic);
            $("#human_dept").combobox('setValue',node.deptId);
            $("#human_dept").combobox('setText',node.dept);
            $("#human_birth").datebox('setValue',node.age);
            $("#email").textbox('setValue',node.email);
            //$("#nickName").val(node.nickName);
            $("#title").combobox('setValue', node.title);
            $("#title_level").combobox('setValue', node.titleLevel);
            $("#human_sex").combobox('setValue', node.sex);
            $("#nation").combogrid('setValue', node.nation);
            $("#education").combobox('setValue', node.education);
            $("#human_marry").combobox('setValue', node.marry);
            $("#human_address").textbox('setValue',node.address);
            $("#human_exp").val(node.exp);
            $("#human_remark").val(node.remark);
            $("#pic").val(node.pic);
            //document.getElementById('imgShow').src = '';
            if(node.pic==null||node.pic=='') {
                document.getElementById("imgShow").src = '/modules/fbd/hrm/peinfo/js/img.png';
            }
            else{
                document.getElementById("imgShow").src = node.pic;
            }
            //$("#up_img").val(node.pic);
            $("#id").val(node.id);
            var staffId = node.staffId;
            var role = [];
            $.get("/service/orgStaff/findRole?staffId=" + staffId, function (data) {
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        role.push(data[i].id);
                    }
                    $("#role").combobox('setValues', role);
                }
            });
            num=0;
            num1=0;
            $("#expGrid").datagrid({
                url:basePath +'/peinfo/workExp-list?orgId='+orgId+'&personId='+node.cardNo,
                idField: 'expId',
                loadMsg: '数据正在加载中，请稍后.....',
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }
            });
            $("#relGrid").datagrid({
                url:basePath +'/peinfo/rel-list?orgId='+orgId+'&personId='+node.cardNo,
                idField: 'relId',
                loadMsg: '数据正在加载中，请稍后.....',
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }
            });
        } else {
            $.messager.alert("系统提示", "请选择要修改的人员");
        }
    });

    //修改人员按钮（只能修改自己的信息）
    $("#editBtn1").on('click', function () {
        // $("#hiddenDiv")[0].style.display = 'none';     //隐藏检索框
        //$("#hiddenDiv0")[0].style.display = 'none';     //隐藏密码框
        //$("#hiddenDiv1")[0].style.display = 'none';     //隐藏确认密码框
        $("#res-card").html("");
        $("#res-nick").html("");
        $("#res-phone").html("");
        $("#res-password").html("");
        $("#res-email").html("");
        $("#res-name").html("");
        $("#res-title").html("");
        var node = $("#staffGrid").datagrid("getSelected");
        if (node) {
            if(node.id==parent.config.persion_Id) {
                $("#addStaff").window('open');
                //$("#password").validatebox({'disabled': true});
                //$("#selectCardNo").val("");
                $("#cardNo").textbox('setValue', node.cardNo);
                $("#human_type").combobox('setValue', node.type);
                $("#human_classify").combobox('setValue', node.classify);
                $("#skill").combobox('setValue', node.skill);
                $("#skillLevel").combobox('setValue', node.skillLevel);
                $("#human_education").combobox('setValue', node.education);
                $("#human_education_final").combobox('setValue', node.educationFinal);
                $("#human_education_time").datebox('setValue', node.educationTime);
                $("#human_education_final_time").datebox('setValue', node.educationFinalTime);
                $("#work_time").datebox('setValue', node.workTime);
                $("#come_time").datebox('setValue', node.comeTime);
                $("#phoneNum").textbox('setValue', node.phoneNum);
                $("#name").val(node.name);
                $("#politic").combobox('setValue', node.politic);
                $("#human_dept").combobox('setValue', node.deptId);
                $("#human_dept").combobox('setText', node.dept);
                $("#human_birth").datebox('setValue', node.age);
                $("#email").textbox('setValue', node.email);
                //$("#nickName").val(node.nickName);
                $("#title").combobox('setValue', node.title);
                $("#title_level").combobox('setValue', node.titleLevel);
                $("#human_sex").combobox('setValue', node.sex);
                $("#nation").combogrid('setValue', node.nation);
                $("#education").combobox('setValue', node.education);
                $("#human_marry").combobox('setValue', node.marry);
                $("#human_address").textbox('setValue', node.address);
                $("#human_exp").val(node.exp);
                $("#human_remark").val(node.remark);
                $("#pic").val(node.pic);
                //document.getElementById('imgShow').src = '';
                if (node.pic == null || node.pic == '') {
                    document.getElementById("imgShow").src = '/modules/fbd/hrm/peinfo/js/img.png';
                }
                else {
                    document.getElementById("imgShow").src = node.pic;
                }
                //$("#up_img").val(node.pic);
                $("#id").val(node.id);
                var staffId = node.staffId;
                var role = [];
                $.get("/service/orgStaff/findRole?staffId=" + staffId, function (data) {
                    if (data != null) {
                        for (var i = 0; i < data.length; i++) {
                            role.push(data[i].id);
                        }
                        $("#role").combobox('setValues', role);
                    }
                });
                num = 0;
                num1 = 0;
                $("#expGrid").datagrid({
                    url: basePath + '/peinfo/workExp-list?orgId=' + orgId + '&personId=' + node.cardNo,
                    idField: 'expId',
                    loadMsg: '数据正在加载中，请稍后.....',
                    onClickRow: function (index, row) {
                        stopEdit();
                        $(this).datagrid('beginEdit', index);
                        editIndex = index;
                    }
                });
                $("#relGrid").datagrid({
                    url: basePath + '/peinfo/rel-list?orgId=' + orgId + '&personId=' + node.cardNo,
                    idField: 'relId',
                    loadMsg: '数据正在加载中，请稍后.....',
                    onClickRow: function (index, row) {
                        stopEdit();
                        $(this).datagrid('beginEdit', index);
                        editIndex = index;
                    }
                });
            }else{
                $.messager.alert("提示", "只允许修改当前登录人自己的信息！");
            }

        } else {
            $.messager.alert("提示", "请选择要修改的人员！");
        }
    });
    //取消添加人员维护
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#addStaff").window('close');
    });



    //删除
    $("#removeBtn").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("提示", '请选择要删除的人员', 'info');
            return;
        }
        $.messager.confirm('提示', '确定要进行删除操作吗？', function (r) {
            if (r) {
                $.postJSON(basePath + "/orgStaff/del", row.id, function (data) {
                    if (data.data == "success") {
                        //$.messager.alert('提示', '删除成功', 'info');
                        $("#staffGrid").datagrid('reload');
                        $("#staffGrid").datagrid('clearSelections');
                    }

                });
            }
        });

    });

    //重置密码
    $("#backBtn").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (!row||row == null) {
            $.messager.alert("提示", "请选择要重置密码的人员！","info");
            return;
        }
            $.messager.confirm('提示', '确定要重置所选人员的登录密码么？', function (r) {
                if (r) {
                    $.post(basePath + "/peinfo/back-password?staffId="+row.id, function (data) {
                        $.messager.alert("提示", "已将 "+row.name+" 的登录密码重置为888888","info");
                        $('#staffGrid').datagrid('reload');
                        $("#staffGrid").datagrid('clearSelections');
                    })
                }
            });
    });

});

var uploadPreview = function(setting) {
    /*
     *author:周祥
     *date:2014年12月11日
     *work:this(当前对象)
     */
    var _self = this;
    /*
     *author:周祥
     *date:2014年12月11日
     *work:判断为null或者空值
     */
    _self.IsNull = function(value) {
        if (typeof (value) == "function") { return false; }
        if (value == undefined || value == null || value == "" || value.length == 0) {
            return true;
        }
        return false;
    }
    /*
     *author:周祥
     *date:2014年12月11日
     *work:默认配置
     */
    _self.DefautlSetting = {
        UpBtn: "",
        DivShow: "",
        ImgShow: "",
        Width: 100,
        Height: 100,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        ErrMsg: "选择文件错误,图片类型必须是(gif,jpeg,jpg,bmp,png)中的一种",
        callback: function() { }
    };
    /*
     *author:周祥
     *date:2014年12月11日
     *work:读取配置
     */
    _self.Setting = {
        UpBtn: _self.IsNull(setting.UpBtn) ? _self.DefautlSetting.UpBtn : setting.UpBtn,
        DivShow: _self.IsNull(setting.DivShow) ? _self.DefautlSetting.DivShow : setting.DivShow,
        ImgShow: _self.IsNull(setting.ImgShow) ? _self.DefautlSetting.ImgShow : setting.ImgShow,
        Width: _self.IsNull(setting.Width) ? _self.DefautlSetting.Width : setting.Width,
        Height: _self.IsNull(setting.Height) ? _self.DefautlSetting.Height : setting.Height,
        ImgType: _self.IsNull(setting.ImgType) ? _self.DefautlSetting.ImgType : setting.ImgType,
        ErrMsg: _self.IsNull(setting.ErrMsg) ? _self.DefautlSetting.ErrMsg : setting.ErrMsg,
        callback: _self.IsNull(setting.callback) ? _self.DefautlSetting.callback : setting.callback
    };
    /*
     *author:周祥
     *date:2014年12月11日
     *work:获取文本控件URL
     */
    _self.getObjectURL = function(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file);
        }
        if(url!=null&&url!=''&&url.length!=0) {
            $("#pic").val(url);
        }
        return url;
    }
    /*
     *author:周祥
     *date:2014年12月11日
     *work:绑定事件
     */
    _self.Bind = function() {
        document.getElementById(_self.Setting.UpBtn).onchange = function() {
            if (this.value) {
                if (!RegExp("\.(" + _self.Setting.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert(_self.Setting.ErrMsg);
                    this.value = "";
                    return false;
                }
                if (navigator.userAgent.indexOf("MSIE") > -1) {
                    try {
                        document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
                    } catch (e) {
                        var div = document.getElementById(_self.Setting.DivShow);
                        this.select();
                        top.parent.document.body.focus();
                        var src = document.selection.createRange().text;
                        document.selection.empty();
                        document.getElementById(_self.Setting.ImgShow).style.display = "none";
                        div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                        div.style.width = _self.Setting.Width + "px";
                        div.style.height = _self.Setting.Height + "px";
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                    }
                } else {
                    document.getElementById(_self.Setting.ImgShow).src = _self.getObjectURL(this.files[0]);
                }
                _self.Setting.callback();
            }
        }
    }
    /*
     *author:周祥
     *date:2014年12月11日
     *work:执行绑定事件
     */
    _self.Bind();
}

function upFile1() {

    //判断文件大小
    var maxsize = 5 * 1024 * 1024;//2M
    var errMsg = "";
    var browserCfg = {};
    var ua = window.navigator.userAgent;
    var filesize = 0;
    var obj_file = document.getElementById("up_img");
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
        errMsg = "请上传小于5M的文件，当前文件大小为" + Math.round(filesize / 1024 / 1024 * 100) / 100 + "M";
        $.messager.alert('提示', errMsg, 'info');
        $("#editContractName").textbox('setValue', '');

        return;
    }
    var oData = new FormData(document.getElementById("editForm"));
    var a = $("#up_img").val();
    if(a!='') {
        $.ajax({
            url: basePath + "/peinfo/upload",
            type: 'POST',
            data: oData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var fileName = '';
                var fileUrl = '';
                fileName = data.fileName;
                fileUrl = data.fileUrl;
                if (fileName != '') {
                    $("#pic").val(fileUrl)
                }
                save();
            },
            error: function (data) {

            }
        });
    }
    else{
        save();
    }
}

function save() {
    if(!$("#name").val()||$("#name").val().indexOf(" ") >=0){
        $.messager.alert("提示","请输入有效的人员姓名，不能包含空格！",'info');
        return ;
    }
    if($("#name").val().length>16){
        $.messager.alert("提示","人员姓名输入过长！",'info');
        return;
    }
    if(!$("#human_dept").combobox('getValue')){
        $.messager.alert("提示","请选择所属科室！",'info');
        return ;
    }
    var isIdCard2 = /^[1-9]\d{5}(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)(\d{4}|\d{3}X)$/;
    if(!$("#cardNo").textbox('getValue')||$("#cardNo").textbox('getValue').indexOf(" ") >=0){
        $.messager.alert("提示","请输入有效的身份证号，不能包含空格！",'info');
        return ;
    }
    if (!isIdCard2.test($("#cardNo").textbox('getValue'))) {
        $.messager.alert("提示","身份证号不合法，请修改！",'info');
        return ;
    }
    if(!$("#role").combobox('getValue')){
        $.messager.alert("提示","请选择职务！",'info');
        return ;
    }
    if(!$("#title").combobox('getValue')){
        $.messager.alert("提示","请选择职称类别！",'info');
        return ;
    }
    if(!$("#title_level").combobox('getValue')){
        $.messager.alert("提示","请选择职称级别！",'info');
        return ;
    }
    if(!$("#human_classify").combobox('getValue')){
        $.messager.alert("提示","请选择人员分类！",'info');
        return ;
    }
    if(!$("#human_type").combobox('getValue')){
        $.messager.alert("提示","请选择人员状态！",'info');
        return ;
    }
    if(!$("#skill").combobox('getValue')){
        $.messager.alert("提示","请选择人员技能！",'info');
        return ;
    }
    if(!$("#skillLevel").combobox('getValue')){
        $.messager.alert("提示","请选择技能等级！",'info');
        return ;
    }
    if(!$("#human_birth").datebox('getValue')){
        $.messager.alert("提示","请选择出生日期！",'info');
        return ;
    }
    if(!$("#human_education").combobox('getValue')){
        $.messager.alert("提示","请选择原始学历！",'info');
        return ;
    }
    if(!$("#human_education_time").datebox('getValue')){
        $.messager.alert("提示","请选择原始学历获得时间！",'info');
        return ;
    }
    if(!$("#human_education_final").combobox('getValue')){
        $.messager.alert("提示","请选择最终学历！",'info');
        return ;
    }
    if(!$("#human_education_final_time").datebox('getValue')){
        $.messager.alert("提示","请选择最终学历获得时间！",'info');
        return ;
    }
    if(!$("#human_sex").combobox('getValue')){
        $.messager.alert("提示","请选择性别！",'info');
        return ;
    }
    if(!$("#phoneNum").textbox('getValue')){
        $.messager.alert("提示","请填写联系电话！",'info');
        return ;
    }
    if($("#phoneNum").textbox('getValue').indexOf(" ") >=0){
        $.messager.alert("提示","请填写有效的联系电话，不能包含空格！",'info');
        return ;
    }
    if(!$("#email").textbox('getValue')){
        $.messager.alert("提示","请填写邮箱！",'info');
        return ;
    }
    if($("#email").textbox('getValue').indexOf(" ") >=0){
        $.messager.alert("提示","请填写有效的邮箱，不能包含空格！",'info');
        return ;
    }
    if($("#phoneNum").textbox('getValue').length>16){
        $.messager.alert("提示","联系电话输入过长！",'info');
        return;
    }
    if($("#email").textbox('getValue').length>36){
        $.messager.alert("提示","邮箱输入过长！",'info');
        return;
    }
    if(!$("#nation").combogrid('getValue')){
        $.messager.alert("提示","请选择民族！",'info');
        return ;
    }
    if(!$("#politic").combobox('getValue')){
        $.messager.alert("提示","请选择政治面貌！",'info');
        return ;
    }
    if(!$("#human_marry").combobox('getValue')){
        $.messager.alert("提示","请选择婚姻状况！",'info');
        return ;
    }
    if(!$("#come_time").combobox('getValue')&&!$("#work_time").combobox('getValue')){
        $.messager.alert("提示","请根据人员分类填写工作时间：编制人员填写参加工作时间，聘用人员填写来院工作时间！",'info');
        return ;
    }
    if($("#come_time").combobox('getValue')!=''&&$("#work_time").combobox('getValue')!=''){
        $.messager.alert("提示","不能同时填写参加工作时间和来院工作时间！编制人员请填写参加工作时间，聘用人员请填写来院工作时间！",'info');
        return ;
    }
    if(!$("#human_address").textbox('getValue')){
        $.messager.alert("提示","请填写目前住址！",'info');
        return ;
    }
    if($("#human_classify").textbox('getText')=='编制'&&$("#come_time").datebox('getValue')){
        $.messager.alert("提示","编制人员请填写参加工作时间！",'info');
        return ;
    }
    if($("#human_classify").textbox('getText')=='聘用'&&$("#work_time").datebox('getValue')){
        $.messager.alert("提示","聘用人员请填写来院工作时间！",'info');
        return ;
    }
    if($("#human_address").textbox('getValue').length>100){
        $.messager.alert("提示","目前住址输入过长！",'info');
        return;
    }
    if($("#human_exp").val().length>600){
        $.messager.alert("提示","工作经历输入过长！",'info');
        return;
    }
    if($("#human_remark").val().length>100){
        $.messager.alert("提示","备注输入过长！",'info');
        return;
    }
    var persion_id = $("#id").val();

    /*工作信息保存start*/
    var rows= $("#expGrid").datagrid("getRows");
    var personId= $("#cardNo").textbox('getValue');
    if(!rows||rows.length==0){
        $.messager.alert("提示", "请新增并输入工作经历信息!", "info");
        return;
    }
    for (var i=0;i<rows.length;i++)
    {
        $("#expGrid").datagrid('endEdit', i);
    }
    for (var i = 0; i < rows.length; i++) {
        if(rows[i].startTime==''){
            $.messager.alert("提示", "请选择工作经历第"+(i+1)+"行:开始时间！", "info");
            return;
        }
        if(rows[i].endTime==''){
            $.messager.alert("提示", "请选择工作经历第"+(i+1)+"行:结束时间！", "info");
            return;
        }
        if(rows[i].unit==''||rows[i].unit.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写工作经历第"+(i+1)+"行:工作单位！不能包含空格！", "info");
            return;
        }
        var rn = rows[i].unit;
        if(rn.length>30){
            $.messager.alert("提示", "工作经历第"+(i+1)+"行工作单位输入过长！", "info");
            return;
        }
        if(rows[i].post==''||rows[i].post.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写工作经历第"+(i+1)+"行:岗位名称！不能包含空格！", "info");
            return;
        }
        var rp = rows[i].post;
        if(rp.length>20){
            $.messager.alert("提示", "工作经历第"+(i+1)+"行岗位输入过长！", "info");
            return;
        }
        var startDate=rows[i].startTime;
        var endDate=rows[i].endTime;
        var date1 = startDate.replace(/-/g,"\/");
        var date2 = endDate.replace(/-/g,"\/");
        var start = new Date(date1);
        var end = new Date(date2);

        if (start > end) {
            $.messager.alert("提示", "工作经历第"+(i+1)+"行开始时间不能大于结束时间!","info");
            return;
        }
    }

    var insertData = $("#expGrid").datagrid("getChanges", "inserted");
    var updateData = $("#expGrid").datagrid("getChanges", "updated");
    if(insertData.length==0){
        insertData=[];
    }
    if(updateData.length==0){
        updateData=[];
    }
    myInfoVo.inserted = insertData;
    myInfoVo.updated = updateData;
    myInfoVo.orgId = orgId;
    myInfoVo.personId = personId;

    /*工作信息保存end*/
    /*社会关系保存start*/
    var rows1= $("#relGrid").datagrid("getRows");
    //var personId= $("#cardNo").textbox('getValue');
    if(!rows1||rows1.length==0){
        $.messager.alert("提示", "请新增并输入社会关系信息!", "info");
        return;
    }
    for (var i=0;i<rows1.length;i++)
    {
        $("#relGrid").datagrid('endEdit', i);
    }
    for (var i = 0; i < rows1.length; i++) {
        if(rows1[i].relationship==''||rows1[i].relationship.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:与本人关系！不能包含空格！", "info");
            return;
        }
        var rel = rows1[i].relationship;
        if(rel.length>20){
            $.messager.alert("提示", "社会关系第"+(i+1)+"行与本人关系输入过长！", "info");
            return;
        }
        if(rows1[i].relName==''||rows1[i].relName.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:姓名！不能包含空格！", "info");
            return;
        }
        var reln = rows1[i].relName;
        if(reln.length>20){
            $.messager.alert("提示", "社会关系第"+(i+1)+"行姓名输入过长！", "info");
            return;
        }
        if(rows1[i].relAge==''){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:年龄", "info");
            return;
        }
        if(rows1[i].relUnit==''||rows1[i].relUnit.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:所在单位！不能包含空格！", "info");
            return;
        }
        var relu = rows1[i].relUnit;
        if(relu.length>30){
            $.messager.alert("提示", "社会关系第"+(i+1)+"行所在单位输入过长！", "info");
            return;
        }
        if(rows1[i].relPost==''||rows1[i].relPost.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:所在岗位！不能包含空格！", "info");
            return;
        }
        var relp = rows1[i].relPost;
        if(relp.length>30){
            $.messager.alert("提示", "社会关系第"+(i+1)+"行所在岗位输入过长！", "info");
            return;
        }
        if(rows1[i].health==''||rows1[i].health.indexOf(" ") >=0){
            $.messager.alert("提示", "请填写社会关系第"+(i+1)+"行:健康状况！不能包含空格！", "info");
            return;
        }
        var relh = rows1[i].health;
        if(relh.length>20){
            $.messager.alert("提示", "社会关系第"+(i+1)+"行健康状况输入过长！", "info");
            return;
        }
    }

    var insertData1 = $("#relGrid").datagrid("getChanges", "inserted");
    var updateData1 = $("#relGrid").datagrid("getChanges", "updated");
    if(insertData1.length==0){
        insertData1=[];
    }
    if(updateData1.length==0){
        updateData1=[];
    }
    myInfoVo1.inserted = insertData1;
    myInfoVo1.updated = updateData1;
    myInfoVo1.orgId = orgId;
    myInfoVo1.personId = personId;

    /*社会关系保存end*/
    var orgStaffVo = {};
    orgStaffVo.id = $("#id").val();
    //orgStaffVo.selectCardNo = $("#selectCardNo").val();
    orgStaffVo.sex = $("#human_sex").combobox('getValue');
    orgStaffVo.name = $("#name").val();
    orgStaffVo.title = $("#title").combobox('getValue');
    orgStaffVo.title_level = $("#title_level").combobox('getValue');
    orgStaffVo.titleLevel = $("#title_level").combobox('getValue');
    orgStaffVo.cardNo = $("#cardNo").textbox('getValue');
    orgStaffVo.phoneNum = $("#phoneNum").textbox('getValue');
    orgStaffVo.email = $("#email").textbox('getValue');
    var name = $("#name").val();
    orgStaffVo.inputCode = makePy(name)[0];
    //orgStaffVo.nickName = $("#nickName").val();
    orgStaffVo.deptId = $("#human_dept").combobox('getValue');
    orgStaffVo.type = $("#human_type").combobox('getValue');
    orgStaffVo.classify = $("#human_classify").combobox('getValue');
    orgStaffVo.skill = $("#skill").combobox('getValue');
    orgStaffVo.skillLevel = $("#skillLevel").combobox('getValue');
    orgStaffVo.education = $("#human_education").combobox('getValue');
    orgStaffVo.educationFinal = $("#human_education_final").combobox('getValue');
    orgStaffVo.age = $("#human_birth").datebox('getValue');
    orgStaffVo.educationTime = $("#human_education_time").datebox('getValue');
    orgStaffVo.workTime = $("#work_time").datebox('getValue');
    orgStaffVo.comeTime = $("#come_time").datebox('getValue');
    orgStaffVo.educationFinalTime = $("#human_education_final_time").datebox('getValue');
    orgStaffVo.marry = $("#human_marry").combobox('getValue');
    orgStaffVo.politic = $("#politic").combobox('getValue');
    orgStaffVo.address = $("#human_address").textbox('getValue');
    orgStaffVo.exp = $("#human_exp").val();
    orgStaffVo.remark = $("#human_remark").val();
    orgStaffVo.roleName = $('#role').combobox('getText');
    var array = [];
    array = $('#role').combobox('getValues');
    if (array == "") {
        orgStaffVo.role = null;
    }
    else {
        orgStaffVo.role = array;
    }
    orgStaffVo.orgId = orgId;
    orgStaffVo.nation = $("#nation").combogrid('getValue');
    orgStaffVo.pic = $("#pic").val();
    $.get("/service/register/getCard?cardNo=" + $("#cardNo").textbox('getValue'), function (data) {
        if (data && data != '' && typeof(data) != 'undefined') {
            if (data.id != persion_id) {
                $.messager.alert("提示","身份证号已经存在！",'info');
                return false;
            }
        }
        $.messager.progress({
            title: '提示！',
            msg:  '数据加载中，请稍候...',
            text: '加载中.......'
        });
        if(insertData.length!==0||updateData.length!==0) {
            $.postJSONAsync(basePath + "/peinfo/exp-merge", JSON.stringify(myInfoVo), function (data) {
                if (data.data == "success") {
                    //$.messager.progress('close');
                }
                else {
                    //$.messager.progress('close');
                    $.messager.alert('提示', '保存失败', 'info');
                }
            });
        }
        if(insertData1.length!==0||updateData1.length!==0) {
            $.postJSONAsync(basePath + "/peinfo/rel-merge", JSON.stringify(myInfoVo1), function (data) {
                if (data.data == "success") {
                    //$.messager.progress('close');
                }
                else {
                    //$.messager.progress('close');
                    $.messager.alert('提示', '保存失败', 'info');
                }
            });
        }
        $.postJSONAsync(basePath + "/orgStaff/save", JSON.stringify(orgStaffVo), function (data) {
            if (data.data == "success") {
                $.messager.progress('close');
                $("#addStaff").window('close');
                $.messager.alert('提示', '保存成功', 'info');
                $("#staffGrid").datagrid('reload');
                $("#editForm").form('reset');
                }
            else {
                $.messager.progress('close');
                $.messager.alert('提示', '保存失败', 'info');
            }
        });

    });


}



