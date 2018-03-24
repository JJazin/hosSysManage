var table = layui.table;

$(function(){


    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_HOSPITAL,
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
                    field: 'pic',
                    title: '图片',
                    templet: '<div><img src="{{d.pic}}" alt="{{d.name}}" height="30"></div>',
                    width: 150,
                    height: 200,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'name',
                    title: '医院名',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'address',
                    title: '地址',
                    width: 250,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'tel',
                    title: '联系电话',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'desc',
                    title: '医院简介',
                    width: 350,
                    align: 'center',
                    edit: true
                },
                {
                    title: 'OPT',
                    fixed: 'right',
                    align: 'center',
                    width: 200,
                    toolbar: '#toolBar'
                }
                // ,
                // {
                //     field: 'lastLoginTime',
                //     title: '最后登陆时间',
                //     templet: '<div>{{d.lastLoginTime == null ? "暂未登陆过" : d.lastLoginTime}}</div>',
                //     width: 200,
                //     align: 'center'
                // }
            ]
        ]
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除该医院么', {offset: 't'}, function(index){

                //向服务端发送删除指令
                del(obj, index);
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something


            update(obj);

        } else if(layEvent == 'toIllness'){
            location.href = '../illness/index.html?id=' + data.id;
        } else if(layEvent == 'toDoctor'){
            location.href = '../doctor/index.html?id=' + data.id;
        }
    });

});



var save = function(){

    var data = {};
    var name = $('#name').val() || '';
    var desc = $('#desc').val() || '';
    var pic = $('#picPath').val() || '';
    var address = $('#address').val() || '';
    var tel = $('#tel').val() || '';

    data.name = name;
    data.desc = desc;
    data.address = address;
    data.tel = tel;
    data.pic = pic;

    $.ajax({
       url: SERVER_ROUTER.PATH_HOSPITAL_SAVE,
       type: 'POST',
       dataType: 'JSON',
       data: data,
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               if(data.data){
                   table.reload('table');
                   $('#name').val('');
                   $('#desc').val('');
                   $('#address').val('');
                   $('#tel').val('');
                   $('#picPath').val('');
                   $('#uploadPicImg').attr('src', '../../assets/images/none_picture.png');
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
       url: SERVER_ROUTER.PATH_HOSPITAL_UPDATE + '/' + data.id,
       type: 'DELETE',
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
       url: SERVER_ROUTER.PATH_HOSPITAL_UPDATE + '/' + data.id,
       type: 'PUT',
       dataType: 'JSON',
       data: {
           name: data.name,
           desc: data.desc,
           address: data.address,
           tel: data.tel
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


layui.use('upload', function(){
    var upload = layui.upload;

    //执行实例
    var uploadImageInst = upload.render({
        elem: '#uploadPicBtn' //绑定元素
        ,url: SERVER_ROUTER.PATH_FILE_UPLOAD //上传接口
        ,done: function(res){
            //上传完毕回调
            console.log(res);
            if(res.code == SERVER_RESPONSE_CODE.LAYUI_OK){
                $('#uploadPicImg').attr('src', SERVER_ROUTER.PATH_FILE + res.data.src);
                $('#picPath').val(res.data.src);
            }
        }
        ,error: function(){
            //请求异常回调
            console.log('error');
            $('#uploadPicText').val('Error');
        }
    });

});