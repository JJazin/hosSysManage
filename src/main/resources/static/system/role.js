var table = layui.table;

$(function(){


    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_ROLE,
        method: 'GET',
        page: true,
        limits: [10, 20, 50],
        limit: 10,
        loading: true,
        width: '100%',
        request: {
            pageName: 'currentPage',
            limitName: 'limit'
        },
        cols: [
            [
                {
                    checkbox: false,
                    sort: false
                },
                {
                    field: 'id',
                    title: 'ID',
                    align: 'center'
                },
                {
                    field: 'name',
                    title: '角色名',
                    width: 200,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'updateTime',
                    title: '最后更新时间',
                    width: 200,
                    align: 'center'
                },
                {
                    title: 'OPT',
                    fixed: 'right',
                    align: 'center',
                    width: 200,
                    toolbar: '#toolBar'
                }
            ]
        ]
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        var id = data.id;

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除该角色么', {offset: 't'}, function(index){

                //向服务端发送删除指令
                roleDelete(obj, index);
            });
        } else if(layEvent === 'edit'){ //编辑

            roleUpdate(obj);
        } else if(layEvent == 'rolePermission'){// 查看角色菜单权限
            layer.open({
                title: '菜单权限',
                content: '<div id="role_tree_'+id+'"></div>',
                offset: 't',
                yes: function(index, layero){

                    var selectedIds = $('#role_tree_' + id).treeview('getChecked');

                    var menuIdsStr = '';

                    for(var i=0; i<selectedIds.length; i++){
                        if(i == 0){
                            menuIdsStr += selectedIds[i].id;
                        }else{
                            menuIdsStr += ',' + selectedIds[i].id;
                        }
                    }

                    var data = {};
                    data.roleId = id;
                    data.menuIdsStr = menuIdsStr;

                    $.ajax({
                        url: SERVER_ROUTER.PATH_ROLE_MENU_UPDATE,
                        type: 'POST',
                        dataType: 'JSON',
                        data: data,
                        success: function(data){
                            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                                if(data.data){

                                }
                            }
                            layer.msg(data.msg);
                        }
                    });
                },
                end: function(){
                    tree_obj.tree_id = 'role_tree';
                }
            });

            $.ajax({
                url: SERVER_ROUTER.PATH_MENU_QUERY_TREE,
                type: 'GET',
                dataType: 'JSON',
                data: {
                    roleId: id
                },
                success: function(data){

                    if(data && data.status == SERVER_RESPONSE_CODE.OK){
                        tree_obj.tree_id = 'role_tree_' + id;
                        $('#role_tree_' + id).treeview({
                            data: data.data,
                            showCheckbox: true,
                            showIcon: true,
                            onNodeChecked:nodeChecked,
                            onNodeUnchecked:nodeUnchecked
                        });
                    }
                }
            })

        }
    });



    tree_obj.tree_id = 'role_tree';
    $.ajax({
        url: SERVER_ROUTER.PATH_MENU_QUERY_TREE,
        type: 'GET',
        dataType: 'JSON',
        data: {
            roleId: null
        },
        success: function(data){
            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                $('#role_tree').treeview({
                    data: data.data,
                    showCheckbox: true,
                    showIcon: true,
                    onNodeChecked:nodeChecked,
                    onNodeUnchecked:nodeUnchecked
                });
            }
        }
    })
});



var roleSave = function(){

    var selectedIds = $('#role_tree').treeview('getChecked');

    var menuIdsStr = '';

    for(var i=0; i<selectedIds.length; i++){
        if(i == 0){
            menuIdsStr += selectedIds[i].id;
        }else{
            menuIdsStr += ',' + selectedIds[i].id;
        }
    }

    var data = {};
    var name = $('#name').val() || '';

    data.name = name;
    data.menuIdsStr = menuIdsStr;

    $.ajax({
        url: SERVER_ROUTER.PATH_ROLE_SAVE,
        type: 'POST',
        dataType: 'JSON',
        data: data,
        success: function(data){
            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                if(data.data){
                    table.reload('table');
                }
                layer.msg(data.msg, {
                    offset: 't'
                });
            }

        }
    });


    return false;
}


var roleDelete = function(obj, index){
    var data = obj.data;
    $.ajax({
       url: SERVER_ROUTER.PATH_ROLE_DELETE,
       type: 'POST',
       dataType: 'JSON',
       data: {id: data.id},
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
               layer.close(index);
               layer.msg(data.msg, {
                   offset: 't'
               });
           }
       }
    });
}

var roleUpdate = function(obj){

    var data = obj.data;

    $.ajax({
       url: SERVER_ROUTER.PATH_ROLE_UPDATE,
       type: 'POST',
       dataType: 'JSON',
       data: {
           id: data.id,
           name: data.name,
       },
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               if(data.data) {
                   obj.update(data);
               }
               layer.msg(data.msg, {
                   offset: 't'
               });
           }


       }
    });
}
