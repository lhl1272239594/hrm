/**
 * 公告管理
 * @author
 * @version 2016-09-25
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var name = '';//标题
var notice = {};
var state = '';//状态
var start = '';//发布时间开始
var end = '';//发布时间结束
var arrPerson=[];
var editor;
var deptIds=parent.orgids;
var search=false;
$(function () {
    //初始化富文本编辑器
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            cssPath : '/static/kindeditor/plugins/code/prettify.css',
            uploadJson : 'upload_json.jsp',
            fileManagerJson : 'file_manager_json.jsp',
            allowFileManager : true,
            afterCreate : function() {
                var self = this;
                K.ctrl(document, 13, function() {
                    self.sync();
                    document.forms['example'].submit();
                });
                K.ctrl(self.edit.doc, 13, function() {
                    self.sync();
                    document.forms['example'].submit();
                });
            },
            afterBlur:function(){
                this.sync();
            }

        });
        prettyPrint();
    });

    /**
     * 数据列表
     */
    $("#dataGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        fit: true,
        fitColumns: true,
        toolbar: '#tb',
        method: 'GET',
        striped: true,
        border: true,
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/notice/notice-list?orgId='+orgId+'&name='+name+'&state='+state+'&start='+start+'&end='+end+'&deptIds='+deptIds,
        remoteSort: false,
        //idField: 'noticeId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'noticeId', title: '', hidden: true},
            {field: 'state', title: '当前状态', width:'7%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "起草";
                    }
                    if (value == "1") {
                        return "已发布";
                    }
                }
            },
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'orgName', title: '组织机构',  hidden: true},
            {field: 'name', title: '公告标题', width:'20%', align: 'center'},
            {field: 'man', title: '接收人', width:'22%', align: 'center',formatter : function (value, row, index) {
                if (value.length > 26) value = value.substr(0, 24) + "...";
                return value;
            }},
            {field: 'content', title: '公告内容', hidden: true},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'11%',align: 'center'},
            {field: 'publishBy', title: '发布人', width:'7%', align: 'center'},
            {field: 'publishDate', title: '发布时间', width:'11%', align: 'center',formatter: formatDatebox}
        ]],
        onLoadSuccess:function(data){
            $("#choosePerson").css('display','block');
            $('#newDialog').css('display','block');
            //$('#newDialog1').css('display','block');
            $('#tb').css('display','block');
            $("#dataGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        }
    });



    //格式化时间：时分秒
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
        return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
    }
    loadtree();

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //获取数据
        name = $("#NAME1").textbox('getValue');
        state = $("#STATE").combobox('getValue');
        start = $("#startDate").val();
        end = $("#endDate").val();
        if(state=='请选择'){
            state='';
        }
            /*$.get(basePath + '/notice/notice-list?orgId='+orgId+'&name='+name+'&state='+state+'&deptIds='+deptIds+'&page='+page+'&rows='+rows, function (data) {
                $("#dataGrid").datagrid('loadData', data);*/
                $("#dataGrid").datagrid('reload', basePath + '/notice/notice-list?orgId='+orgId+'&name='+name+'&state='+state+'&start='+start+'&end='+end+'&deptIds='+deptIds+'&page='+page+'&rows='+rows);
                if(search){
                    search=false;
                    if(page!='1') {
                        $("#dataGrid").datagrid('getPager').pagination('select', 1);
                    }
                }
                $("#dataGrid").datagrid('clearSelections');
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });


    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#NAME1").textbox('clear');
        $("#NAME1").textbox("setValue","");
        $("#STATE").combobox('clear');
        $("#STATE").combobox("setValue","请选择");
        $("#startDate").val("");
        start = '';
        $("#endDate").val("");
        end = '';
    };

    /**
     * 新增弹出框
     */
    $("#newDialog").dialog({
        title: '新增公告',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    }
    );

    /**
     * 新增
     */
    $("#addBtn").on('click', function () {
                $("#newDialog").dialog("open");
                $("#submitBtn").show();
                arrPerson=[];
                $("#NOTICE_ID").val('');
                $("#NAME").textbox("setValue", "");
                $("#username").textbox("setValue", "");
                editor.html('');
                $("#newForm").form('reset');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        $("#submitBtn").show();
        var row = $("#dataGrid").datagrid("getSelected");
        var row1 = $("#dataGrid").datagrid("getSelections");
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(!row){
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }
        if (row.state == '1') {
            $.messager.alert("提示", "该条公告已经发布，不可修改!","info");
            return;
        }
        var id=row.noticeId;
        $("#newDialog").dialog("setTitle","公告信息修改").dialog("open");
        $("#NOTICE_ID").val(row.noticeId);
        $("#NAME").textbox("setValue",row.name);
        $("#userName").textbox("setValue",row.man);
        if(row.content!=null){
            //解码
            var val = utf8to16(base64decode(row.content));
            editor.html(val);
        }
        //人员树赋值
        $.get(basePath + "/notice/getPersonById?id=" + id, function (data) {
            arrPerson=[];
            arrPerson=data;
            $.each(arrPerson, function (index, item) {
                var obj=getName(personObj,'userId',item.userId);
                arrPerson[index].name=obj[0].name;
                arrPerson[index].depName=obj[0].depName;
            });
            var value='';
            $.each( arrPerson, function(i, n){
                value+=n.name+',';
            })
        });

    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        //alert(editor.html());
         html = editor.html();
         editor.sync();
        var val = $('#notice_content').val();

        //base64编码处理后才可以作为JSON传递
        var content = base64encode(utf16to8(val));

        if(!$("#NAME").textbox("getValue")||$("#NAME").textbox("getValue").indexOf(" ") >=0){
            $.messager.alert("提示","请输入有效的公告标题，不能包含空格！",'info');
            return ;
        }
        if($("#NAME").textbox("getValue").length>30){
            $.messager.alert("提示","公告标题内容输入过长！",'info');
            return ;
        }
        if(!$("#userName").textbox("getValue")){
            $.messager.alert("提示","请选择接收人！",'info');
            return ;
        }
        if(content==''||!content||content.length==0){
            $.messager.alert("提示","请输入公告内容！",'info');
            return ;
        }
        notice.orgId = parent.config.org_Id;

        // salaryPart.id = $("#ID").val();
        notice.noticeId = $("#NOTICE_ID").val();
        notice.name = $("#NAME").textbox('getValue');
        notice.man = $("#userName").textbox('getValue');
        notice.content = content;
            notice.personVos = arrPerson;
        //执行保存
        $.postJSON(basePath + "/notice/merge", JSON.stringify(notice), function (data) {
            if(data.data=="success")
            {
                // $.messager.alert('系统提示', '保存成功', 'info');
                $('#newDialog').window('close');
                $("#dataGrid").datagrid('reload');
                $("#newForm").form('reset');
                $("#dataGrid").datagrid('clearSelections');
            }
            else{
                $.messager.alert('提示', '保存失败', 'info');
            }
        })

    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length ==0) {
            $.messager.alert("提示", "请选择要删除的公告信息!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].delFlag = '1';
            }
            $.messager.confirm('提示', '确定要删除所选中的公告信息么？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/notice/del-notice", JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }

    });
    /**
     * 详情
     */
    $("#infoBtn").on('click', function () {
        var row = $("#dataGrid").datagrid("getSelected");
        var row1 = $("#dataGrid").datagrid("getSelections");
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(!row){
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }
        var t = formatDatebox(row.publishDate);
        if(t==null){
            t=='';
        }
        $('#w').window('open');
        $("#bt").html(row.name);
        //$("#ry").html(row.man);
        $("#sj").html("发布时间："+t);
        if(row.content!=null){
            //解码
            var val = utf8to16(base64decode(row.content));
            $("#bodys").html(val);
        }
        /*$("#submitBtn").hide();   bodys  createDate
        $("#NOTICE_ID").val(row.noticeId);
        $("#NAME").textbox("setValue",row.name);
        $("#userName").textbox("setValue",row.man);
        if(row.content!=null){
            //解码
            var val = utf8to16(base64decode(row.content));
            editor.html(val);
        }*/

    });

    /**
     * 发布
     */
    $("#dealBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        var row1 = $("#dataGrid").datagrid("getSelections");
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if (row == null||row.length ==0) {
            $.messager.alert("提示", "请选择要发布的招聘信息!","info");
            return;
        }
        //alert(row[0].state);
        if (row[0].state == '1') {
            $.messager.alert("提示", "该条招聘信息已经发布，不可重复发布!","info");
            return;
        }
          $.messager.confirm('提示', '确定要发布所选的数据么？注意:发布后该条公告将不可修改！', function (r) {
                if (r) {
                    $.postJSON(basePath + "/notice/publish-notice", JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        /*}*/

    });
    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });

});
//base64编码加密
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
//base64解码
var base64DecodeChars = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1,　0,　1,　2,　3,  4,　5,　6,　7,　8,　9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1];
function base64encode(str) {
    var out, i, len;
    var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}
function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;
    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        /* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c1 == -1);
        if(c1 == -1)
            break;
        /* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c2 == -1);
        if(c2 == -1)
            break;
        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
        /* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if(c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while(i < len && c3 == -1);
        if(c3 == -1)
            break;
        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
        /* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if(c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while(i < len && c4 == -1);
        if(c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
function utf16to8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >>　6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >>　6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
        }
    }
    return out;
}
function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;
    out = "";
    len = str.length;
    i = 0;
    while(i < len) {
        c = str.charCodeAt(i++);
        switch(c >> 4)
        {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
            // 0xxxxxxx
            out += str.charAt(i-1);
            break;
            case 12: case 13:
            // 110x xxxx　 10xx xxxx
            char2 = str.charCodeAt(i++);
            out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
            break;
            case 14:
                // 1110 xxxx　10xx xxxx　10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) |
                    ((char2 & 0x3F) << 6) |
                    ((char3 & 0x3F) << 0));
                break;
        }
    }
    return out;
}

