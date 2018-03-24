var table = layui.table;
var menus;

$(function(){
    $.ajax({
        url: SERVER_ROUTER.PATH_QUERY_PARENT_MENU,
        type: 'GET',
        dataType: 'JSON',
        async: false,
        data: {pid: 0},
        success: function(data){
            console.log(data);
            menus = data.data;
        }
    });


    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_MENU,
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
                    field: 'pTitle',
                    title: '父菜单',
                    align: 'center',
                    templet: '<div><a href="javascript:void(0);" onclick="toUpdPid(\'{{d.id}}\', \'{{d.pid}}\');">{{d.pTitle}}</a></div>',
                    width: 100,
                },
                {
                    field: 'title',
                    title: '菜单名',
                    width: 100,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'icon',
                    title: '图标',
                    templet: '<div><span class="{{d.icon}}"></span></div>',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'href',
                    title: '路径',
                    width: 100,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'weight',
                    title: '权重',
                    width: 100,
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
                    width: 150,
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

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除该菜单么', {offset: 't'}, function(index){

                //向服务端发送删除指令
                menuDelete(obj, index);
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something


            menuUpdate(obj);
            // obj.update({
            //     pid: pid,
            //     title: title,
            //     icon: icon,
            //     weight: weight
            // });

        }
    });


    $('#menu-select').select2({
        width: '150px',
        placeholder: '父菜单',
        allowClear: true
    });


    fillSelect('menu-select', null);


});


/**
 *
 * @param id
 * @param default_id 默认选中id
 */
var fillSelect = function (id, default_id){
    var str = '<option value=""></option>';
    $.each(menus, function(i, item){
        if(default_id != null && item.id == default_id){
            str += '<option selected value="'+item.id+'">'+item.title+'</option>';
        }else{
            str += '<option value="'+item.id+'">'+item.title+'</option>';
        }
    });
    $('#' + id).html(str);
}

var menuSave = function(){

    var data = {};
    var pid = $('#menu-select').val() || 0;
    var title = $('#title').val() || '';
    var href = $('#href').val() || '';
    var icon = $('#icon').val() || '';
    var weight = $('#weight').val() || '';

    data.pid = pid;
    data.title = title;
    data.href = href;
    data.icon = icon;
    data.weight = weight;

    $.ajax({
        url: SERVER_ROUTER.PATH_MENU_SAVE,
        type: 'POST',
        dataType: 'JSON',
        data: data,
        success: function(data){
            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                if(data.data){
                    table.reload('table');
                }
                layer.msg(data.msg);
            }
        }
    });

    return false;
}


var menuDelete = function(obj, index){
    var data = obj.data;
    $.ajax({
        url: SERVER_ROUTER.PATH_MENU_DELETE,
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

var menuUpdate = function(obj){

    var data = obj.data;

    $.ajax({
        url: SERVER_ROUTER.PATH_MENU_UPDATE,
        type: 'POST',
        dataType: 'JSON',
        data: {
            id: data.id,
            pid: data.pid,
            title: data.title,
            icon: data.icon,
            href: data.href,
            weight: data.weight
        },
        success: function(data){
            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                if(data.data){
                    obj.update(data);
                }
                layer.msg(data.msg, {
                    offset: 't'
                });
            }
        }
    });
}

var toUpdPid = function(id, pid){
    console.log('id = ' + id + ', pid = ' + pid);



    layer.open({
        title: '选择父菜单',
        content: '<select id="pid_upd_'+id+'"></select>',
        offset: 't',
        yes: function(index, layero){

            var updPid = $('#pid_upd_' + id).val() || 0;

            console.log('upPid====' + updPid);

            $.ajax({
                url: SERVER_ROUTER.PATH_MENU_UPDATE,
                type: 'POST',
                dataType: 'JSON',
                data: {id: id, pid: updPid},
                success: function(data){
                    if(data && data.status == SERVER_RESPONSE_CODE.OK){
                        if(data.data){
                            table.reload('table');
                        }
                        layer.msg(data.msg, {
                            offset: 't'
                        });
                    }else{
                        layer.msg(data.msg, {
                            offset: 't'
                        });
                        return false;
                    }
                }
            });
        }
    });

    $('#pid_upd_' + id).select2({
        width: '150px',
        placeholder: '父菜单',
        allowClear: true
    });
    fillSelect('pid_upd_' + id, pid);

}