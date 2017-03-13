/**
 * 我的信息
 * @author
 * @version 2016-08-22
 */
var orgId= parent.config.org_Id;
var personId=parent.config.persion_Id



function getData()
{
    $.get(basePath +"/peinfo/getmyinfo?personId="+personId+'&orgId='+orgId, function (data) {
        $('#name1').textbox('setValue',data[0].name);//赋值val(data[0].name);
        $('#dept1').textbox('setValue',data[0].bm);
        $('#tel1').textbox('setValue',data[0].dh);
        $('#email1').textbox('setValue',data[0].email);
        $('#age11').textbox('setValue',data[0].age);
        $('#nation1').textbox('setValue',data[0].mz);
        $('#sex1').textbox('setValue',data[0].sex);
        $('#politic1').textbox('setValue',data[0].politic);
        $('#card_no1').textbox('setValue',data[0].sfz);
        $('#roleName1').textbox('setValue',data[0].roleName);
        $('#title1').textbox('setValue',data[0].title);
        $('#titleLevel1').textbox('setValue',data[0].titleLevel);
        $('#education1').textbox('setValue',data[0].education);
        $('#education_time1').textbox('setValue',data[0].educationTime);
        $('#education_final1').textbox('setValue',data[0].educationFinal);
        $('#education_final_time1').textbox('setValue',data[0].educationFinalTime);
        $('#type1').textbox('setValue',data[0].type);
        $('#classify1').textbox('setValue',data[0].classify);
        $('#skill1').textbox('setValue',data[0].skill);
        $('#skillLevel1').textbox('setValue',data[0].skillLevel);
        $('#work_time1').textbox('setValue',data[0].workTime);
        $('#marry1').textbox('setValue',data[0].marry);
        $('#address').textbox('setValue',data[0].address);
        $('#exp').val(data[0].exp);
        $('#remark').val(data[0].remark);
        if(data[0].pic==null||data[0].pic=='') {
            document.getElementById("img").src = '/modules/fbd/hrm/peinfo/js/img.png';
        }
        else{
            document.getElementById("img").src = data[0].pic;
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
            url:basePath +'/peinfo/workExp-list?orgId='+orgId+'&personId='+data[0].sfz,
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
            url:basePath +'/peinfo/rel-list?orgId='+orgId+'&personId='+data[0].sfz,
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
    });
}
getData();