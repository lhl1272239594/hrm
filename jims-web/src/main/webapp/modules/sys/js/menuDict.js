/**
 * 菜单
 * @author tangxb
 * @version 2016-04-25
 */


$(function () {
    $.extend($.fn.treegrid.defaults, {
        onBeforeDrag: function(row){},	// 在拖动之前触发
        onStartDrag: function(row){},// 在开始拖动触发
        onStopDrag: function(row){},// 在停止拖动触发
        onDragEnter: function(targetRow, sourceRow){},	// r在被拖拽元素到放置区内的时候触发
        onDragOver: function(targetRow, sourceRow){},	// 在被拖拽元素经过放置区的时候触发
        onDragLeave: function(targetRow, sourceRow){},// 在被拖拽元素离开放置区的时候触发
        onBeforeDrop: function(targetRow, sourceRow, point){},//
        onDrop: function(targetRow, sourceRow, point){}	// 在被拖拽元素放入到放置区的时候触发
});

    $.extend($.fn.treegrid.methods, {
        enableDnd: function(jq, id){
            if (!$('#treegrid-dnd-style').length){
                $('head').append(
                    '<style id="treegrid-dnd-style">' +
                    '.treegrid-row-top td{border-top:1px solid red}' +
                    '.treegrid-row-bottom td{border-bottom:1px solid red}' +
                    '.treegrid-row-append .tree-title{border:1px solid red}' +
                    '</style>'
                );
            }
            return jq.each(function(){
                var target = this;
                var state = $.data(this, 'treegrid');
                state.disabledNodes = [];
                var t = $(this);
                var opts = state.options;
                if (id){
                    var nodes = opts.finder.getTr(target, id);
                    var rows = t.treegrid('getChildren', id);
                    for(var i=0; i<rows.length; i++){
                        nodes = nodes.add(opts.finder.getTr(target, rows[i][opts.idField]));
                    }
                } else {
                    var nodes = t.treegrid('getPanel').find('tr[node-id]');
                }
                nodes.draggable({
                    disabled:false,
                    revert:true,
                    cursor:'pointer',
                    proxy: function(source){
                        var row = t.treegrid('find', $(source).attr('node-id'));
                        var p = $('<div class="tree-node-proxy"></div>').appendTo('body');
                        p.html('<span class="tree-dnd-icon tree-dnd-no">&nbsp;</span>'+row[opts.treeField]);
                        p.hide();
                        return p;
                    },
                    deltaX: 15,
                    deltaY: 15,
                    onBeforeDrag:function(e){
                        if (opts.onBeforeDrag.call(target, getRow(this)) == false){return false}
                        if ($(e.target).hasClass('tree-hit') || $(e.target).parent().hasClass('datagrid-cell-check')){return false;}
                        if (e.which != 1){return false;}
                        $(this).next('tr.treegrid-tr-tree').find('tr[node-id]').droppable({accept:'no-accept'});
//						var tr = opts.finder.getTr(target, $(this).attr('node-id'));
//						var treeTitle = tr.find('span.tree-title');
//						e.data.startX = treeTitle.offset().left;
//						e.data.startY = treeTitle.offset().top;
//						e.data.offsetWidth = 0;
//						e.data.offsetHeight = 0;
                    },
                    onStartDrag:function(){
                        $(this).draggable('proxy').css({
                            left:-10000,
                            top:-10000
                        });
                        var row = getRow(this);
                        opts.onStartDrag.call(target, row);
                        state.draggingNodeId = row[opts.idField];
                    },
                    onDrag:function(e){
                        var x1=e.pageX,y1=e.pageY,x2=e.data.startX,y2=e.data.startY;
                        var d = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
                        if (d>3){	// when drag a little distance, show the proxy object
                            $(this).draggable('proxy').show();
                            var tr = opts.finder.getTr(target, $(this).attr('node-id'));
                            var treeTitle = tr.find('span.tree-title');
                            e.data.startX = treeTitle.offset().left;
                            e.data.startY = treeTitle.offset().top;
                            e.data.offsetWidth = 0;
                            e.data.offsetHeight = 0;
                        }
                        this.pageY = e.pageY;
                    },
                    onStopDrag:function(){
                        $(this).next('tr.treegrid-tr-tree').find('tr[node-id]').droppable({accept:'tr[node-id]'});
                        for(var i=0; i<state.disabledNodes.length; i++){
                            var tr = opts.finder.getTr(target, state.disabledNodes[i]);
                            tr.droppable('enable');
                        }
                        state.disabledNodes = [];
                        var row = t.treegrid('find', state.draggingNodeId);
                        opts.onStopDrag.call(target, row);
                    }
                }).droppable({
                    accept:'tr[node-id]',
                    onDragEnter: function(e, source){
                        if (opts.onDragEnter.call(target, getRow(this), getRow(source)) == false){
                            allowDrop(source, false);
                            var tr = opts.finder.getTr(target, $(this).attr('node-id'));
                            tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                            tr.droppable('disable');
                            state.disabledNodes.push($(this).attr('node-id'));
                        }
                    },
                    onDragOver:function(e,source){
                        var nodeId = $(this).attr('node-id');
                        if ($.inArray(nodeId, state.disabledNodes) >= 0){return}
                        var pageY = source.pageY;
                        var top = $(this).offset().top;
                        var bottom = top + $(this).outerHeight();

                        allowDrop(source, true);
                        var tr = opts.finder.getTr(target, nodeId);
                        tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                        if (pageY > top + (bottom - top) / 2){
                            if (bottom - pageY < 5){
                                tr.addClass('treegrid-row-bottom');
                            } else {
                                tr.addClass('treegrid-row-append');
                            }
                        } else {
                            if (pageY - top < 5){
                                tr.addClass('treegrid-row-top');
                            } else {
                                tr.addClass('treegrid-row-append');
                            }
                        }
                        if (opts.onDragOver.call(target, getRow(this), getRow(source)) == false){
                            allowDrop(source, false);
                            tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                            tr.droppable('disable');
                            state.disabledNodes.push(nodeId);
                        }
                    },
                    onDragLeave:function(e,source){
                        allowDrop(source, false);
                        var tr = opts.finder.getTr(target, $(this).attr('node-id'));
                        tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                        opts.onDragLeave.call(target, getRow(this), getRow(source));
                    },
                    onDrop:function(e,source){
                        var dest = this;
                        var action, point;
                        var tr = opts.finder.getTr(target, $(this).attr('node-id'));
                        if (tr.hasClass('treegrid-row-append')){
                            action = append;
                            point = 'append';
                        } else {
                            action = insert;
                            point = tr.hasClass('treegrid-row-top') ? 'top' : 'bottom';
                        }

                        var dRow = getRow(this);
                        var sRow = getRow(source);
                        if (opts.onBeforeDrop.call(target, dRow, sRow, point) == false){
                            tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                            return;
                        }
                        //dRow获取id添加到拖拽pid  sRow获取拖拽后的菜单组
                        appendMenu(sRow,dRow,point);

                        //action(sRow, dRow, point);
                        //tr.removeClass('treegrid-row-append treegrid-row-top treegrid-row-bottom');
                        loadMenu();



                    }
                });

                function allowDrop(source, allowed){
                    var icon = $(source).draggable('proxy').find('span.tree-dnd-icon');
                    icon.removeClass('tree-dnd-yes tree-dnd-no').addClass(allowed ? 'tree-dnd-yes' : 'tree-dnd-no');
                }
                function getRow(tr){
                    var nodeId = $(tr).attr('node-id');
                    return t.treegrid('find', nodeId);
                }
                function append(sRow, dRow){
                    doAppend();
                    if (dRow.state == 'closed'){
                        t.treegrid('expand', dRow[opts.idField]);
                    }

                    function doAppend(){
                        var data = t.treegrid('pop', sRow[opts.idField]);
                        t.treegrid('append', {
                            parent: dRow[opts.idField],
                            data: [data]
                        });
                        opts.onDrop.call(target, dRow, data, 'append');
                    }
                }
                function insert(sRow, dRow, point){
                    var param = {};
                    if (point == 'top'){
                        param.before = dRow[opts.idField];
                    } else {
                        param.after = dRow[opts.idField];
                    }

                    var data = t.treegrid('pop', sRow[opts.idField]);
                    param.data = data;
                    t.treegrid('insert', param);
                    opts.onDrop.call(target, dRow, data, point);
                }

                function appendMenu(sRow,dRow,point){
                    var menuList = [];
                    var flag = 0;

                    var menu = {};
                    menu.id = sRow.id ;
                    menu.menuName = sRow.menuName ;
                    menu.href = sRow.href ;
                    menu.icon = sRow.icon;
                    if(point == "append"){
                        menu.target = 1 ;
                        menu.pid = dRow.id ;
                        menu.sort = dRow.children.length  ;//已经添加完孩子节点
                        menu.menuLevel = parseInt(dRow.menuLevel) + 1 ;

                    }
                    if(point == "top"){
                        //console.log($(dRow.target).nextAll());
                        console.log(t.treegrid('find', dRow.pid));
                        if (dRow.pid){
                            menu.target = 1 ;
                            menu.pid = dRow.pid ;
                            menu.sort = parseInt(dRow.sort)  ;

                            var childs = t.treegrid('find', dRow.pid);

                            if (dRow.pid != sRow.pid) {
                                //跨级增加
                                for (var e = 0 ; e < childs.children.length ; e++ ){
                                    if (childs.children[e].sort >= dRow.sort){
                                        var m = {};
                                        m.id = childs.children[e].id ;
                                        m.menuName = childs.children[e].menuName ;
                                        m.href = childs.children[e].href ;
                                        m.icon = childs.children[e].icon;
                                        m.target = childs.children[e].target ;
                                        m.pid = childs.children[e].pid ;
                                        m.sort = parseInt(childs.children[e].sort) + 1  ;
                                        m.menuLevel = childs.children[e].menuLevel;
                                        menuList.push(m);
                                    }
                                }
                            }else {
                                //同级变化
                                if ( sRow.sort < dRow.sort){
                                    // 向下移动
                                    for (var e = 0 ; e < childs.children.length ; e++ ){
                                        if (childs.children[e].sort >= sRow.sort && childs.children[e].sort < dRow.sort){
                                            var m = {};
                                            m.id = childs.children[e].id ;
                                            m.menuName = childs.children[e].menuName ;
                                            m.href = childs.children[e].href ;
                                            m.icon = childs.children[e].icon;
                                            m.target = childs.children[e].target ;
                                            m.pid = childs.children[e].pid ;
                                            m.sort = parseInt(childs.children[e].sort) - 1;
                                            if (childs.children[e].sort == sRow.sort ){
                                                m.sort = parseInt(dRow.sort) - 1;
                                            }
                                            m.menuLevel = childs.children[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }
                                }else {
                                    // 向上移动
                                    for (var e = 0 ; e < childs.children.length ; e++ ){
                                        if (childs.children[e].sort >= dRow.sort && childs.children[e].sort <= sRow.sort){
                                            var m = {};
                                            m.id = childs.children[e].id ;
                                            m.menuName = childs.children[e].menuName ;
                                            m.href = childs.children[e].href ;
                                            m.icon = childs.children[e].icon;
                                            m.target = childs.children[e].target ;
                                            m.pid = childs.children[e].pid ;
                                            m.sort =parseInt(childs.children[e].sort) + 1   ;
                                            if (sRow.sort == childs.children[e].sort){
                                                m.sort =parseInt(dRow.sort)    ;
                                            }
                                            m.menuLevel = childs.children[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }

                                }

                                flag = 1;
                            }

                        }else{
                            menu.target = 2 ;
                            menu.pid = null ;
                            menu.sort = parseInt(dRow.sort)  ;
                            var childs = t.treegrid('getRoots');

                            if (dRow.pid != sRow.pid) {
                                //跨级增加
                                for (var e = 0 ; e < childs.length ; e++ ){
                                    if (childs[e].sort >= dRow.sort){
                                        var m = {};
                                        m.id = childs[e].id ;
                                        m.menuName = childs[e].menuName ;
                                        m.href = childs[e].href ;
                                        m.icon = childs[e].icon;
                                        m.target = childs[e].target ;
                                        m.pid = childs[e].pid ;
                                        m.sort = parseInt(childs[e].sort) + 1  ;
                                        m.menuLevel = childs[e].menuLevel;
                                        menuList.push(m);
                                    }
                                }
                            }else {
                                //同级变化
                                if (sRow.sort < dRow.sort) {
                                    // 向下移动
                                    for (var e = 0; e < childs.length; e++) {
                                        if (childs[e].sort >= sRow.sort && childs[e].sort < dRow.sort) {
                                            var m = {};
                                            m.id = childs[e].id;
                                            m.menuName = childs[e].menuName;
                                            m.href = childs[e].href;
                                            m.icon = childs[e].icon;
                                            m.target = childs[e].target;
                                            m.pid = childs[e].pid;
                                            m.sort = parseInt(childs[e].sort) - 1;
                                            if (childs[e].sort == sRow.sort) {
                                                m.sort = parseInt(dRow.sort) - 1;
                                            }
                                            m.menuLevel = childs[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }
                                } else {
                                    // 向上移动
                                    for (var e = 0; e < childs.length; e++) {
                                        if (childs[e].sort >= dRow.sort && childs[e].sort <= sRow.sort) {
                                            var m = {};
                                            m.id = childs[e].id;
                                            m.menuName = childs[e].menuName;
                                            m.href = childs[e].href;
                                            m.icon = childs[e].icon;
                                            m.target = childs[e].target;
                                            m.pid = childs[e].pid;
                                            m.sort = parseInt(childs[e].sort) + 1;
                                            if (sRow.sort == childs[e].sort) {
                                                m.sort = parseInt(dRow.sort);
                                            }
                                            m.menuLevel = childs[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }

                                }
                                flag = 1;
                            }
                        }
                        menu.menuLevel = parseInt(dRow.menuLevel);

                    }
                    if(point == "bottom"){
                        if (dRow.pid){
                            menu.target = 1 ;
                            menu.pid = dRow.pid ;
                            menu.sort = parseInt(dRow.sort) + 1  ;
                            var childs = t.treegrid('find', dRow.pid);
                            if (dRow.pid != sRow.pid) {
                                //跨级增加
                                for (var e = 0 ; e < childs.children.length ; e++ ){
                                    if (childs.children[e].sort > dRow.sort){
                                        var m = {};
                                        m.id = childs.children[e].id ;
                                        m.menuName = childs.children[e].menuName ;
                                        m.href = childs.children[e].href ;
                                        m.icon = childs.children[e].icon;
                                        m.target = childs.children[e].target ;
                                        m.pid = childs.children[e].pid ;
                                        m.sort = parseInt(childs.children[e].sort) + 1  ;
                                        m.menuLevel = childs.children[e].menuLevel;
                                        menuList.push(m);
                                    }
                                }
                            }else {
                                //同级变化
                                if ( sRow.sort < dRow.sort){
                                    // 向下移动
                                    for (var e = 0 ; e < childs.children.length ; e++ ){
                                        if (childs.children[e].sort >= sRow.sort && childs.children[e].sort <= dRow.sort){
                                            var m = {};
                                            m.id = childs.children[e].id ;
                                            m.menuName = childs.children[e].menuName ;
                                            m.href = childs.children[e].href ;
                                            m.icon = childs.children[e].icon;
                                            m.target = childs.children[e].target ;
                                            m.pid = childs.children[e].pid ;
                                            m.sort = parseInt(childs.children[e].sort) - 1;
                                            if (childs.children[e].sort == sRow.sort ){
                                                m.sort = dRow.sort;
                                            }
                                            m.menuLevel = childs.children[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }
                                }else {
                                    // 向上移动
                                    for (var e = 0 ; e < childs.children.length ; e++ ){
                                        if (childs.children[e].sort > dRow.sort  && childs.children[e].sort <= sRow.sort){
                                            var m = {};
                                            m.id = childs.children[e].id ;
                                            m.menuName = childs.children[e].menuName ;
                                            m.href = childs.children[e].href ;
                                            m.icon = childs.children[e].icon;
                                            m.target = childs.children[e].target ;
                                            m.pid = childs.children[e].pid ;
                                            m.sort =parseInt(childs.children[e].sort) + 1   ;
                                            if (sRow.sort == childs.children[e].sort){
                                                m.sort =parseInt(dRow.sort) +  1   ;
                                            }
                                            m.menuLevel = childs.children[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }

                                }

                                flag = 1;
                            }

                        }else{
                            menu.target = 2 ;
                            menu.pid = null ;
                            menu.sort = parseInt(dRow.sort) + 1  ;

                            var childs = t.treegrid('getRoots');
                            if (dRow.pid != sRow.pid) {
                                //跨级增加
                                for (var e = 0 ; e < childs.length ; e++ ){
                                    if (childs[e].sort > dRow.sort){
                                        var m = {};
                                        m.id = childs[e].id ;
                                        m.menuName = childs[e].menuName ;
                                        m.href = childs[e].href ;
                                        m.icon = childs[e].icon;
                                        m.target = childs[e].target ;
                                        m.pid = childs[e].pid ;
                                        m.sort = parseInt(childs[e].sort) + 1  ;
                                        m.menuLevel = childs[e].menuLevel;
                                        menuList.push(m);
                                    }
                                }
                            }else {
                                //同级变化
                                if ( sRow.sort < dRow.sort){
                                    // 向下移动
                                    for (var e = 0 ; e < childs.length ; e++ ){
                                        if (childs[e].sort >= sRow.sort && childs[e].sort <= dRow.sort){
                                            var m = {};
                                            m.id = childs[e].id ;
                                            m.menuName = childs[e].menuName ;
                                            m.href = childs[e].href ;
                                            m.icon = childs[e].icon;
                                            m.target = childs[e].target ;
                                            m.pid = childs[e].pid ;
                                            m.sort = parseInt(childs[e].sort) - 1;
                                            if (childs[e].sort == sRow.sort ){
                                                m.sort = dRow.sort;
                                            }

                                            m.menuLevel = childs[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }
                                }else {
                                    // 向上移动
                                    for (var e = 0 ; e < childs.length ; e++ ){
                                        if (childs[e].sort > dRow.sort && childs[e].sort <= sRow.sort){
                                            var m = {};
                                            m.id = childs[e].id ;
                                            m.menuName = childs[e].menuName ;
                                            m.href = childs[e].href ;
                                            m.icon = childs[e].icon;
                                            m.target = childs[e].target ;
                                            m.pid = childs[e].pid ;
                                            m.sort =parseInt(childs[e].sort) + 1   ;
                                            if (sRow.sort == childs[e].sort){
                                                m.sort =parseInt(dRow.sort) +  1   ;
                                            }

                                            m.menuLevel = childs[e].menuLevel;
                                            menuList.push(m);
                                        }
                                    }

                                }

                                flag = 1;
                            }

                        }
                        menu.menuLevel = parseInt(dRow.menuLevel);
                    }

                    if (flag == 0){
                        menuList.push(menu);
                    }

                    sRow.menuLevel = menu.menuLevel;
                    changeMenuValue(sRow);


                    //添加菜单级别
                    function changeMenuValue(sRow){
                        if(!sRow.children){
                            return;
                        }
                        var children = sRow.children;
                        for (var i = 0 ; i < children.length ;i++){
                            if(children.children && children.children.length > 0){
                                changeMenuValue(children.children)
                            }else {
                                var menuChild = {};
                                menuChild.id = children[i].id ;
                                menuChild.pid = children[i].pid ;
                                menuChild.menuName = children[i].menuName ;
                                menuChild.href = children[i].href ;
                                menuChild.icon = children[i].icon;
                                menuChild.sort = children[i].sort ;
                                menuChild.target = children[i].target ;
                                menuChild.menuLevel = parseInt(sRow.menuLevel) + 1;
                                menuList.push(menuChild);
                            }


                        }
                    }

                    for(var x = 0 ; x < menuList.length ; x++){
                        $.postJSON(basePath + "/menuDict/updateReturnObject", JSON.stringify(menuList[x]), function (data) {
                        });
                    }
                    loadMenu();
                }


            });
        }
    });
    $("#tt").treegrid({
        idField: 'id',
        treeField: 'menuName',
        //title: '系统菜单维护',
        fit: true,
        toolbar: '#tb',
        singleSelect:true,
        columns: [[{
            title: '菜单名称',
            field: 'menuName',
            width: "20%"
        }, {
            title: '路径',
            field: 'href',
            width: "20%"
        }, {
            title: '图标',
            field: 'icon',
            width: "20%"
        }, {
            title: '排序',
            field: 'sort',
            width: "20%"
        }, {
            title: '打开方式',
            field: 'target',
            width: "10%"
        },{
            title: '菜单等级',
            field: 'menuLevel',
            width: "10%"
        },{
            title: 'id',
            field: 'id',
            width: "10%",
            hidden:true
        }]],
        onLoadSuccess:function(row, data){
            //启用拖动
            //enableDnd($('#tt'));
            $(this).treegrid('enableDnd', row?row.id:null);
        }
    });


    var loadMenu = function () {
        var menus = [];//菜单列表
        var menuTreeData = [];//菜单树的列表
        var menuPromise = $.get(basePath + "/menuDict/list", function (data) {
            $.each(data,function(index,item){
                var menu ={} ;
                menu.id = item.id ;
                menu.pid = item.pid ;
                menu.menuName = item.menuName ;
                menu.href = item.href ;
                menu.icon = item.icon;
                menu.sort = item.sort ;
                menu.target = item.target ;
                menu.menuLevel = item.menuLevel ;
                menu.children=[] ;
                menus.push(menu) ;
            });
            for(var i = 0 ;i<menus.length;i++){
                //判断儿子节点
                for(var j = 0 ;j<menus.length;j++){
                    if(menus[i].id ==menus[j].pid){
                        menus[i].children.push(menus[j]) ;
                    }
                }
                //判断是不是根节点  start
                if(menus[i].children.length>0 && !menus[i].pid){
                    menuTreeData.push(menus[i]) ;
                }

                if(!menus[i].pid&&menus[i].children.length<=0){
                    menuTreeData.push(menus[i]) ;
                }
                //判断是不是根节点  end
            }
        });

        menuPromise.done(function () {
            $("#tt").treegrid('loadData',menuTreeData) ;
            $("#tt").treegrid("selectRow", 1);
        });
    };


    loadMenu();

    /**
     * 全部合并
     */
    $("#allMenuClose").on('click', function () {
        var rows= $("#tt").treegrid('getRoots');
        for(var i=0;i<rows.length;i++){
            $("#tt").treegrid('collapseAll', rows[i].id);
        }

    });
    /**
     * 全部展开
     */
    $("#allMenuOpen").on('click', function () {
        var rows = $("#tt").treegrid('getRoots');
        for (var i = 0; i < rows.length; i++) {
            $("#tt").treegrid('expandAll', rows[i].id);
        }
    });
    /**
     * 保存
     */
    $("#saveBtn").on('click', function () {
        if ($("#fm").form('validate')) {
            var menuDict = {};
            menuDict.menuName = $("#menuName").textbox('getValue');
            menuDict.href = $("#href").textbox('getValue');
            menuDict.pid = $("#pid").textbox('getValue');
            menuDict.icon = $("#icon").textbox('getValue');
            menuDict.sort = $("#sort").textbox('getValue');
            menuDict.target = $("#target").textbox('getValue');
            menuDict.menuLevel = $("#menuLevel").val();
            menuDict.id = $("#id").val();

            //打开方式为直接打开时必须有路径
            if (menuDict.target == 1 && !menuDict.href){
                $.messager.alert("系统提示","请输入路径！");
                return ;
            }
            var str = "";//删除修改标志
            if(!menuDict.id){
                str = "insertReturnObject";
            }else{
                str = "updateReturnObject";
            }


            $.postJSON(basePath + "/menuDict/"+str, JSON.stringify(menuDict), function (data) {
                $('#dlg').dialog('close');
                if(!menuDict.id){
                    $("#tt").treegrid('append',{
                        parent:menuDict.pid,
                        data:[data]
                    })
                }else{
                    $("#tt").treegrid('update',{
                        id:menuDict.id,
                        row:data
                    })
                }
                $.messager.alert("系统提示", "保存成功");
            }, function (data, status) {
                $.messager.alert("系统提示", "失败");
            });
        }
    });

    /**
     * 添加同级菜单
     */
    $("#addSameLevelBtn").on('click', function () {
        var node = $("#tt").treegrid('getSelected');
        if (!node) {
            $.messager.alert("系统提示", "请选择，所添加菜单的同一级的任意一个菜单");
            return;
        }
        if (node) {
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加同级菜单');
            $('#fm').form('clear');
            if(node.pid) {
                var parentNode = $("#tt").treegrid('find',node.pid)
                $("#parentName").textbox('setValue', parentNode.menuName);
                $("#pid").textbox('setValue', parentNode.id);
            }
            $("#menuLevel").val(node.menuLevel);
        }
    });

    /**
     * 添加下级菜单
     */
    $("#addNextLevelBtn").on('click', function () {
        var node = $("#tt").treegrid('getSelected');
        if (!node) {
            $.messager.alert("系统提示", "请选择，所添加菜单的同一级的任意一个菜单");
            return;
        }

        if(!node.id){
            $.messager.alert("系统提示",'所选菜单为新添加菜单，请刷新后，添加子菜单','info') ;
            return ;
        }
        if (node) {
            var menuLevel = 0;
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加子菜单');
            $('#fm').form('clear');
            $("#parentName").textbox('setValue', node.menuName);
            $("#pid").textbox('setValue', node.id);
            if(node.menuLevel){
                menuLevel = parseInt(node.menuLevel) + 1;
            }
            $("#menuLevel").val(menuLevel);
        }
    });

    /**
     * 删除某一个菜单
     */
    $("#removeBtn").on('click', function () {
        var row = $("#tt").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的菜单");
            return;
        }



        var children = $("#tt").treegrid('getChildren', row.id);
        if (children.length > 0) {
            $.messager.alert("系统提示", "请先删除子菜单");
            return;
        } else {
            if(!row.id){
                $.messager.alert("系统提示","对于新添加的菜单请先刷新在进行删除",'error') ;
                return
            }
            $.messager.confirm("系统提示", "确认删除:【" + row.menuName + "】的菜单吗?", function (r) {
                if (r) {
                    $.postJSON(basePath + "/menuDict/del" ,row.id, function (data) {
                        $.messager.alert("系统提示", "删除成功");
                        $("#tt").treegrid('remove', row.id);
                    });
                }
            })

        }
    });

    /**
     * 修改一个菜单
     */
    $("#updateBtn").on('click', function () {

        var node = $("#tt").treegrid('getSelected');
        if (node == null) {
            $.messager.alert("系统提示", "请选中要修改的菜单");
            return;
        }
        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改菜单');
        //$('#fm').form('clear');
        $("#menuName").textbox('setValue', node.menuName);
        $("#href").textbox('setValue', node.href);
        $("#pid").textbox('setValue', node.pid);
        if (node.pid){
            $.postJSON(basePath + "/menuDict/get",node.pid, function (data) {
                $("#parentName").textbox('setValue', data.menuName);
            });
        } else {
            $("#parentName").textbox('setValue', '');
        }

        $("#sort").textbox('setValue', node.sort);
        $("#target").textbox('setValue', node.target);
        $("#icon").textbox('setValue', node.icon);
        $("#id").val(node.id);
        $("#menuLevel").val(node.menuLevel);

    });
});