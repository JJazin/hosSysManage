var table = layui.table;
$(function(){

    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_NOTICE,
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
                    field: 'title',
                    title: '标题',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'content',
                    title: '内容',
                    width: 350,
                    align: 'center',
                    edit: true
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
            layer.confirm('真的删除该公告么', {offset: 't'}, function(index){

                //向服务端发送删除指令
                del(obj, index);
            });
        } else if(layEvent === 'edit'){ //编辑
            update(obj);
        }
    });

});



var save = function(){

    var data = {};

    data.title = $('#title').val();
    data.content = $('#content').val();

    $.ajax({
       url: SERVER_ROUTER.PATH_NOTICE_SAVE,
       type: 'POST',
       dataType: 'JSON',
       data: data,
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               if(data.data){
                   table.reload('table');
                   $('#title').val('');
                   $('#content').val('');
               }
           }
           layer.msg(data.msg, {
               offset: 't'
           });
       }
    });

    return false;
}


var del = function(obj, index){
    var data = obj.data;
    $.ajax({
       url: SERVER_ROUTER.PATH_NOTICE_DELETE,
       type: 'POST',
        data: {
           id: data.id
        },
       dataType: 'JSON',
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
               layer.close(index);
           }
           layer.msg(data.msg, {
               offset: 't'
           });
       }
    });
}

var update = function(obj){

    var data = obj.data;

    $.ajax({
       url: SERVER_ROUTER.PATH_NOTICE_UPDATE,
       type: 'POST',
       dataType: 'JSON',
       data: {
           id: data.id,
           title: data.title,
           content: data.content
       },
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               if(data.data){
                   obj.update(data);
               }
           }
           layer.msg(data.msg, {
               offset: 't'
           });
       }
    });
}