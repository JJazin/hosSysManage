var table = layui.table;
var hosId = '';
$(function () {

    hosId = getUrlParam('id');

    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_ILLNESS + '?hosId=' + hosId,
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
                    field: 'email',
                    title: '邮箱',
                    width: 150,
                    align: 'center',
                    edit: true
                }
                ,
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
            location.href = '../case/add.html?id=' + data.id + '&hosId=' + hosId;
        }
    });

    $('#doctor-select').select2({
        width: '150px',
        allowClear: false
    });
    fillIllness();

});

var fillIllness = function () {
    $.ajax({
        url: SERVER_ROUTER.PATH_QUERY_PAGE_ILLNESS,
        data: {
            currentPage: 1,
            limit: 1000000
        },
        type: 'GET',
        dataType: 'JSON',
        success: function(data){
            console.log(data);
            if (data && data.code == SERVER_RESPONSE_CODE.LAYUI_OK) {
                if (data.data) {
                    var list = data.data;
                    var html = '';
                    for(var i=0; i<list.length; i++){
                        var obj = list[i];
                        html += '<option value = "' + obj.id + '"> ' + obj.realName + ' </option>';
                    }
                    $('#doctor-select').html(html);
                }
            }
        }
    });
}

var save = function () {

    var data = {};
    data.hosId = hosId;
    id = $('#doctor-select').val();

    console.log(data);

    $.ajax({
        url: SERVER_ROUTER.PATH_ILLNESS_UPDATE + '/' + id,
        type: 'PUT',
        dataType: 'JSON',
        data: data,
        success: function (data) {
            if (data && data.status == SERVER_RESPONSE_CODE.OK) {
                if (data.data) {
                    table.reload('table');
                }
            }
            layer.msg(data.msg, {
                offset: 't'
            });
        }
    });

    return false;
}


var del = function (obj, index) {
    var data = obj.data;
    $.ajax({
        url: SERVER_ROUTER.PATH_ILLNESS_UPDATE + '/' + data.id,
        type: 'DELETE',
        dataType: 'JSON',
        success: function (data) {
            if (data && data.status == SERVER_RESPONSE_CODE.OK) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
            }
            layer.msg(data.msg, {
                offset: 't'
            });
        }
    });
}

var update = function (obj) {

    var data = obj.data;

    $.ajax({
        url: SERVER_ROUTER.PATH_ILLNESS_UPDATE + '/' + data.id,
        type: 'PUT',
        dataType: 'JSON',
        data: {
            name: data.name,
            tel: data.tel,
            major: data.major
        },
        success: function (data) {
            if (data && data.status == SERVER_RESPONSE_CODE.OK) {
                if (data.data) {
                    obj.update(data);
                }
            }
            layer.msg(data.msg, {
                offset: 't'
            });
        }
    });
}

layui.use('upload', function () {
    var upload = layui.upload;

    //执行实例
    var uploadImageInst = upload.render({
        elem: '#uploadPicBtn' //绑定元素
        , url: SERVER_ROUTER.PATH_FILE_UPLOAD //上传接口
        , done: function (res) {
            //上传完毕回调
            console.log(res);
            if (res.code == SERVER_RESPONSE_CODE.LAYUI_OK) {
                $('#uploadPicImg').attr('src', SERVER_ROUTER.PATH_FILE + res.data.src);
                $('#picPath').val(res.data.src);
            }
        }
        , error: function () {
            //请求异常回调
            console.log('error');
            $('#uploadPicText').val('Error');
        }
    });

});