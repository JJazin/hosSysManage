var table = layui.table;
var hosId = '';
var isDoc =false;
$(function () {
    $.ajax({
        url: SERVER_ROUTER.PATH_ROLE_GET,
        type: 'GET',
        dataType: 'JSON',
        async: false,
        success: function(data){
            console.log(data);
            isDoc = data.data==3?true:false;
        }
    });
    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_ILLNESS_FOR_DOCTOR,
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
                    field: 'realName',
                    title: '姓名',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'phone',
                    title: '联系电话',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'sex',
                    title: '性别',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'history',
                    title: '过敏史',
                    width: 150,
                    align: 'center',
                    edit: false
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
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'del') { //删除
            layer.confirm('真的删除该科室么', {offset: 't'}, function (index) {
                //向服务端发送删除指令
                del(obj, index);
            });
        } else if (layEvent === 'toAddCase') { //添加病例
            location.href = '../case/addForDoctor.html?id=' + data.id;
        }
    });

    $('#doctor-select').select2({
        width: '150px',
        allowClear: false
    });

});
