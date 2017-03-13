$(function() {
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
    $.extend($.fn.validatebox.defaults.rules, {
        nameIsExisted :{
            validator : function(value){
                var rows = $('#orgSelfService').datagrid('getRows')
                var select_index = $('#orgSelfService').datagrid('getRowIndex',$('#orgSelfService').datagrid('getSelected'))
                for(var index = rows.length - 1;index > -1;index--) {
                    if(index != select_index && $.trim(rows[index].serviceName) == $.trim(value)){
                        return false
                    }
                }
                return true
            },
            message : '名称已存在'
        }
    })

    var currentOrgId = parent.config.org_Id;  // 当前机构ID
    var currentSelectIndex;  // 服务当前选择行
    var operatorFlag ;  // 删除菜单操作标志，只有operatorFlag为true时能取消选择

    var positionArr = [{id:'1',text:'左侧'},{id:'2',text:'顶部'},{id:'3',text:'右侧'},{id:'4',text:'底部'}]
    var styleArr = [{id:'1',text:'样式1'},{id:'2',text:'样式2'}]

    var formatData = function(arr,value){
        if(!arr || !value) return '';
        for(var i= 0,j=arr.length;i<j;i++){
            if(arr[i].id == value){
                return arr[i].text;
            }
        }
    }
    var endEditing = function (){
        if (currentSelectIndex == undefined){
            return true;
        }
        if ($('#orgSelfService').datagrid('validateRow', currentSelectIndex)){
            $('#orgSelfService').datagrid('endEdit', currentSelectIndex);
            return true;
        } else {
            return false;
        }
    }
    var onClickCell = function (index, field){
        if (endEditing()){
            var row = $('#orgSelfService').datagrid('getRows')[index];
            if(row.serviceName != '系统管理' || field != 'serviceName') {
                $('#orgSelfService').datagrid('selectRow', index)
                    .datagrid('editCell', {index: index, field: field});
            }
            currentSelectIndex = index;
        }
    }

    // 机构自定义服务
    $("#orgSelfService").datagrid({
        fit: true,
        border: 0,
        singleSelect: true,
        remoteSort: false,
        url : '/service/org-service/find-self-service?orgId=' + currentOrgId,
        loadMsg: '数据加载中，请稍后...',
        method: 'GET',
        idField: 'id',
        toolbar: '#tbn',
        columns: [[
            {field: 'serviceName', title: '服务', width: 260, halign: "center",align:"left",editor:{
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'服务名不能为空',
                    validType : ['nameIsExisted']
                }
            }},
            {field: 'menuStyle', title: '菜单显示样式', width: 120, align: "center" ,editor:{
                type:'combobox',
                options:{
                    valueField:'id',
                    textField:'text',
                    editable:false,
                    value:'1',
                    data:positionArr
                }
            },formatter:function(value,row){
                if(!value && positionArr && positionArr.length>0){
                    row.menuStyle = positionArr[0].id;
                    return positionArr[0].text;
                }
                return formatData(positionArr,value);
            }},
            {field: 'menuPosition', title: '菜单显示位置', width: 160, align: "center",editor:{
                type:'combobox',
                options:{
                    valueField:'id',
                    textField:'text',
                    editable:false,
                    value:'1',
                    data:styleArr
                }
            },formatter:function(value,row){
                if(!value && styleArr && styleArr.length>0){
                    row.menuPosition = styleArr[0].id;
                    return styleArr[0].text;
                }
                return formatData(styleArr,value);
            }}
        ]],
        onClickCell:onClickCell,
        onBeforeSelect: function(index){
            return $('#orgSelfService').datagrid('validateRow', currentSelectIndex);
        }
    })

    $('#clearButton').click(function(){
        var rows = $('#orgSelfService').datagrid('getRows');
        for(var i= 0,j=rows.length;i<j;i++){
            $('#orgSelfService').datagrid('deleteRow',0);
        }
    })
    $('#addButton').click(function(){
        if(endEditing()) {
            var row = {orgId: currentOrgId};
            $('#orgSelfService').datagrid('appendRow', row);
            onClickCell($('#orgSelfService').datagrid('getRows').length - 1,'serviceName');
        }
    })
    $('#delButton').click(function(){
        if(currentSelectIndex != undefined){
            $('#orgSelfService').datagrid('deleteRow',currentSelectIndex);
            currentSelectIndex = undefined;
        } else {
            $.messager.alert('警告','请选择要删除的服务！','warning');
        }

    })
    $('#menuButton').click(function(){
        if(!endEditing()) return false
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row){
            var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
            crearTreeCheck()
            if(row.menus){
                $('#selectedMenu').tree('loadData', row.menusTreeData)
                loadVsSys();
                createTreeCheck(row.menusTreeData);
            } else if(!row.id){
                $('#selectedMenu').tree('loadData', [])
            } else {
                loadVsSys();
                $.get('/service/org-service/find-self-service-menu', {selfServiceId: row.id,isTree:true}, function (res) {
                    var d = handlerSelfTreeDta(res)
                    $('#selectedMenu').tree('loadData', d)
                    createTreeCheck(d)
                })
            }
            $('#selfServiceMenuWin').window('open')
        } else {
            $.messager.alert('警告','请选择要维护菜单的服务！','warning')
        }
    })
    $('#saveButton').click(function(){
        if(!endEditing()) return
        //  服务删除数据
        var delRows = $('#orgSelfService').datagrid('getChanges','deleted');
        var delIds = ''
        for(var i=0;i<delRows.length;i++){
            delIds += ',' + delRows[i].id;
        }
        // 服务添加的数据(应包含添加的菜单)
        var addRows = $('#orgSelfService').datagrid('getChanges','inserted');
        // 服务修改的数据(不应该包含添加的菜单)
        var updateRows = $('#orgSelfService').datagrid('getChanges','updated');

        //合并处理保存数据
        var rows = addRows.concat(updateRows);

        var allRows = $('#orgSelfService').datagrid('getRows');
        for(var i=0;i<allRows.length;i++){
            delete allRows[i].menusTreeData;
            if(allRows[i].id && allRows[i].updateFlag){
                rows.push({id:allRows[i].id,menus:allRows[i].menus,serviceVs:allRows[i].serviceVs})
                delete allRows[i].menus;
                delete allRows[i].serviceVs;
            }
            delete allRows[i].updateFlag;
        }

        if(delIds.length > 0){
            rows.push({delFlag:'1',id:delIds.substr(1)});
        }
        parent.parent.$.postJSON('/service/org-service/save-self-service',JSON.stringify(rows),function(res){
            if(res == '1'){
                $.messager.alert('保存','保存成功！','info',function(){
                    window.location.reload();
                })
            } else {
                $.messager.alert('保存','保存失败！','error')
            }
        })
    })

    var loadVsSys = function(){
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row && !row.serviceVs) {
            $.ajax({
                type: 'get',
                url: parent.basePath + '/org-service/findVsSys',
                async: false,   // true 异步,false 同步
                data: {selfServiceId: row.id},
                success: function (res) {
                    row.serviceVs = res;
                }
            })
        }
    }

    //使弹出框中菜单选择中没有被选中的节点
    var crearTreeCheck = function(){
        var treeNum = $('#selectServiceMenu .easyui-tree').length
        for(var i=0;i<treeNum;i++){
            var roots = $('#tree'+i).tree('getRoots');
            for(var j=0;j<roots.length;j++){
                operatorFlag = true   //只有operatorFlag为true时能取消选择
                $('#tree'+i).tree('uncheck',roots[j].target);
                operatorFlag = false
            }
        }
    }

    //根据数据选中树中节点
    var createTreeCheck = function(data){
        if(data){
            var treeNum = $('#selectServiceMenu .easyui-tree').length
            var ids = []
            for(var i=0;i<treeNum;i++){
                ids.push($('#selectServiceMenu').accordion('getPanel',i).panel('options').id);
            }
            for(var i=0;i<data.length;i++){
                for(var j=0;j<treeNum;j++){
                    if(data[i].sysServiceId.indexOf(ids[j]) > -1) {
                        var node = $('#tree' + j).tree('find', data[i].id);
                        if (node && $('#tree' + j).tree('isLeaf', node.target)) {
                            $('#tree' + j).tree('check', node.target);
                        }
                    }
                }
                createTreeCheck(data[i].children)
            }
        }
    }

    //服务菜单维护窗口
    $('#selfServiceMenuWin').window({
        title: '服务菜单维护',
        width: '650',
        height: '500',
        collapsible :false,
        minimizable : false,
        maximizable : false,
        modal : true,
        resizable: true
    });
    $('#selfServiceMenuWin').window('close');


    var p ;  // p用来保存当前拖拽节点的原父节点
    $('#selectedMenu').tree({
        animate:true,
        dnd:true,
        lines:true,
        onStopDrag:function(){
            var child ;
            if(p){
                child = $('#selectedMenu').tree('getChildren', p.target);
            }
            while(p && (!child || child.length == 0)){
                var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
                var n = $('#tree'+index).tree('find', p.id);
                if(n && $('#tree'+index).tree('isLeaf',n.target)){
                    p = undefined;
                    break;
                }
                var t = $('#selectedMenu').tree('getParent', p.target);
                $('#selectedMenu').tree('remove', p.target);
                p = t;
                if(p)
                    child = $('#selectedMenu').tree('getChildren', p.target);
            }
        },
        onBeforeDrop:function(t, s){
            p = $('#selectedMenu').tree('getParent', s.target);
        },
        onClick: function(node){
            if(!$(this).tree('isLeaf',node.target)){
                $(this).tree('toggle',node.target)
            }
        }

    })

    // 删除选择的菜单
    $('#delMenu').click(function(){
        var node = $('#selectedMenu').tree('getSelected');
        if(node){
            operatorFlag = true    // 使用来控制取消菜单选择界面
            var treeNum = $('#selectServiceMenu .easyui-tree').length
            var parent;
            var child;
            do{
                parent = $('#selectedMenu').tree('getParent',node.target);
                $('#selectedMenu').tree('remove',node.target);
                unSelectNode(node)
                for(var i=0;i<treeNum;i++){
                    if(parent && $('#tree'+i).tree('find', parent.id)){
                        if($('#tree'+i).tree('isLeaf',  $('#tree'+i).tree('find', parent.id).target)){
                            node = undefined;
                            break;
                        }
                    }
                    if(parent && i == treeNum - 1)
                        child = $('#selectedMenu').tree('getChildren',parent.target);
                }

                if(node)
                    node = parent;
            } while(node && (!child || child.length == 0))

            operatorFlag = false
        } else {
            $.messager.alert('警告','请选择要删除的节点！','warning')
        }
    })

    // 删除node时取消选择
    function unSelectNode (node){
        if(node){
            var treeNum = $('#selectServiceMenu .easyui-tree').length
            for(var i=0;i<treeNum;i++){
                var n = $('#tree'+i).tree('find',node.id);
                if(n && $('#tree'+i).tree('isLeaf',n.target)){
                    $('#tree'+i).tree('uncheck',n.target);
                }
            }
            var c = node.children;
            for(var i= 0,j= c ? c.length : 0; i<j; i++) {
                unSelectNode(c[i]);
            }
        }
    }
    //确认选择
    $('#okMenu').click(function(){
        var row = $('#orgSelfService').datagrid('getSelected');
        if(row) {
            var menusTreeData = $('#selectedMenu').tree('getRoots');
            row.menusTreeData = menusTreeData;
            // 标志此条数据为已修改
            row.updateFlag = '1';
            var menus = []
            for(var i=0;i<menusTreeData.length;i++){
                menus.push(chargeMenusData(menusTreeData[i],i+1))
            }
            row.menus = menus;
            function chargeMenusData(data,sort){
                if(data){
                    var menu = {
                        selfServiceId:row.id,
                        menuId:data.id,
                        sysServiceId:data.sysServiceId,
                        menuEndDate:parent.parent.parseToDate(data.endData)
                    }
                    var childs = data.children
                    if(childs && childs.length > 0){
                        menu.children = []
                        for(var j=0;j<childs.length;j++){
                            menu.children.push(chargeMenusData(childs[j],j+1))
                        }
                    }
                    return menu;
                }
                return data;
            }
            //
            var treeNum = $('#selectServiceMenu .easyui-tree').length;
            var serviceVs = []
            for(var i=0;i<treeNum;i++){
                if($('#tree'+i).tree('getChecked').length > 0){
                    serviceVs.push({
                        sysServiceId: $('#tree'+i).tree('getChecked')[0].sysServiceId
                    });
                }
            }
            row.serviceVs = serviceVs
        }
        $('#selfServiceMenuWin').window('close')
    })
    $('#delAllMenu').click(function () {
        $('#selectedMenu').tree('loadData', []);
        crearTreeCheck()
    })
    $('#allClose').click(function () {
        $('#selectedMenu').tree('collapseAll')
    })
    $('#allOpen').click(function () {
        $('#selectedMenu').tree('expandAll')
    })

    //添加菜单
    var addMenu = function(node,treeId){
        if(node && treeId){
            var data = $('#'+treeId).tree('getData',node.target);
            var parentNode = $('#'+treeId).tree('getParent',node.target);
            var selectParent = $('#selectedMenu').tree('find',node.id);
            var d = data;
            // 处理已选菜单没有的菜单数据
            while(parentNode && !selectParent){
                var temp = d;
                var parentData = $('#'+treeId).tree('getData',parentNode.target);
                d = {
                    id:parentData.id,
                    text:parentData.text,
                    href:parentData.href,
                    sysServiceId:parentData.sysServiceId,
                    endData:parentData.endData,
                    children:[temp]
                }
                selectParent = $('#selectedMenu').tree('find',parentData.id);
                parentNode = $('#'+treeId).tree('getParent',parentNode.target);
            }
            addMenu1(d,selectParent)
        }
    }
    var addMenu1 = function(data,parent){
        if(data){
            var d = $('#selectedMenu').tree('find',data.id);
            if(!d){
                var t = {
                    id:data.id,
                    text:data.text,
                    href:data.href,
                    sysServiceId:data.sysServiceId,
                    endData:data.endData
                }
                var param = {data:t}
                if(parent){
                    param.parent = parent.target
                }
                $('#selectedMenu').tree('append',param)
            } else if(d.sysServiceId.indexOf(data.sysServiceId) == -1){
                d.sysServiceId += ',' + data.sysServiceId
            }
            var children = data.children
            if(children && children.length > 0) {
                for (var i = 0; i < children.length; i++) {
                    addMenu1(children[i],$('#selectedMenu').tree('find',data.id));
                }
            }
        }
    }

    //处理查询的自定义菜单数据
    var handlerSelfTreeDta = function(res){
        var r = [];
        if(res){
            for(var j=0;j<res.length;j++){
                var o = res[j];
                var node = {
                    id: o.menuId,
                    text: o.menuName,
                    sysServiceId: o.sysServiceId,
                    endData: parent.parent.formatDateBoxFull(o.menuEndDate)
                }
                if(o.children){
                    node.children = handlerSelfTreeDta(o.children);
                }
                r.push(node);
            }
        }
        return r;
    }
    //处理服务中的菜单数据
    var handlerTreeData = function(res,endData,sysServiceId){
        var r = [];
        if(res){
            for(var j=0;j<res.length;j++){
                var o = res[j];
                var node = {
                    id: o.id,
                    text: o.menuName,
                    href: o.href,
                    sysServiceId: sysServiceId,
                    endData: parent.parent.formatDateBoxFull(endData)
                }
                if(o.children){
                    node.children = handlerTreeData(o.children,endData,sysServiceId);
                }
                r.push(node);
            }
        }
        return r;
    }

    //加载机构所选择的服务
    $.get('/service/org-service/find-service',{orgId:currentOrgId},function(res){
        if(res) {
            var treeIndex  = 0 ;
            for(var i=0;i<res.length;i++){
                $('#selectServiceMenu').accordion('add', {
                    id: res[i].serviceId,
                    title: res[i].serviceName,
                    content: '<ul class="easyui-tree" id="tree'+treeIndex+'"></ul>',
                    selected: i == 0
                });
                $('#tree'+treeIndex).tree({
                    data:handlerTreeData(res[i].menus,res[i].serviceEndDate,res[i].serviceId),
                    checkbox:true,
                    animate:true,
                    lines:true,
                    onCheck:function(o,check){
                        if(check){
                            var index = $('#selectServiceMenu').accordion('getPanelIndex',$('#selectServiceMenu').accordion('getSelected'))
                            if(index > -1) {
                                addMenu(o, 'tree' + index);
                            }
                        }
                    },onBeforeCheck:function(o,c){
                        if(operatorFlag) return true
                        return c
                    }
                })
                treeIndex++;
            }
        }
    })
})
