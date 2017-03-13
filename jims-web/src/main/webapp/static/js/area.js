//区域js

/* 延迟加载
$('#area').combotree({
    animate: true,
    width: 150,
    data: getChildren(),
    onBeforeExpand: function(node){
        if((!node.children || node.children.length == 0) && node.value.substr(4,2) == '00') {
            var childrens = getChildren(node);
            if(childrens.length > 0) {
                $('#area').combotree('tree').tree('append', {
                    parent: node.target,
                    data: childrens
                });
            }
        }
    }
})
*/


//获得区域树
function getAreaTree(){
    var tree
    $.ajax({
        url: '/service/dict/findListByType?type=AREA_DICT',
        type: 'get',
        async: false
    }).always(function(res){
        tree = handleAreaTree(res)
    })
    return tree
}

//获得区域树
function getChildren(node){
    var childrens
    $.ajax({
        url: '/service/dict/findAreaData?area=' + (node ? node.value : ''),
        type: 'get',
        async: false
    }).always(function(res){
        childrens = handleArea(res,node)
    })
    return childrens
}
//处理区域数据为树形
function handleAreaTree(data){
    var tree = []
    if(data && data.length > 0) {
        var root
        var city
        for(var i= 0,j=data.length;i<j;i++){
            var d = data[i]
            if(!d.value || d.value.length != 6) continue;
            var node = {
                id: d.id,
                text: d.label,
                value: d.value,
                label: d.label
            }
            if(d.value.substr(2,2) == '00'){
                root = node
                tree.push(node)
            } else if(d.value.substr(4,2) == '00'){
                city = node
                if(!root.children) {
                    root.children = []
                    root.state = 'closed'
                }
                node.text = removePrefix(node.text,root.label)
                root.children.push(node)
            } else {
                if(!city.children) {
                    city.children = []
                    city.state = 'closed'
                }
                node.text = removePrefix(node.text,city.label)
                city.children.push(node)
            }
        }
    }
    return tree
}

function handleArea(data,parent){
    var areas = []
    if(data && data.length > 0) {
        for(var i= 0,j=data.length;i<j;i++){
            var d = data[i]
            if(!d.value || d.value.length != 6) continue;
            var node = {
                id: d.id,
                text: d.label,
                value: d.value,
                label: d.label
            }
            if(d.value.substr(4,2) == '00'){
                node.children = []
                node.state = 'closed'
            }
            if(parent){
                node.text = removePrefix(node.text,parent.label)
            }
            areas.push(node)
        }
    }
    return areas
}
//去除区域名称前缀
function removePrefix(value,prefix){
    if(value && prefix && value.length >= prefix.length && value != prefix) {
        var index = 0;
        while(prefix.length > index && value.charAt(index) == prefix.charAt(index)){
            index ++
        }
        return value.substr(index)
    }
    return value
}