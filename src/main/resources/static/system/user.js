var table = layui.table;
var roles;

$(function(){
    $.ajax({
        url: SERVER_ROUTER.PATH_ROLE_QUERY_ALL,
        type: 'GET',
        dataType: 'JSON',
        async: false,
        data: {pid: 0},
        success: function(data){
            console.log(data);
            roles = data.data;
        }
    });


    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_USER,
        method: 'GET',
        page: true,
        limits: [10, 20, 50],
        limit: 20,
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
                    field: 'roleName',
                    title: '角色组',
                    align: 'center',
                    templet: '<div><a href="javascript:void(0);" onclick="toUpdPid(\'{{d.id}}\', \'{{d.roleId}}\');">{{d.roleName}}</a></div>',
                    width: 100,
                },
                {
                    field: 'name',
                    title: '登录账号',
                    width: 100,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'realName',
                    title: '真实姓名',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'idcard',
                    title: '身份证号',
                    width: 120,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'phone',
                    title: '联系电话',
                    width: 120,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'email',
                    title: '邮箱',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                // {
                //     field: 'qqNumber',
                //     title: 'qq号',
                //     width: 100,
                //     align: 'center',
                //     edit: true
                // },
                {
                    field: 'wx',
                    title: '医院',
                    width: 100,
                    align: 'center',
                    edit: false
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
            layer.confirm('真的删除该用户么', {offset: 't'}, function(index){

                //向服务端发送删除指令
                userDelete(obj, index);
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something


            userUpdate(obj);

        }
    });


    $('#role-select').select2({
        width: '150px',
        allowClear: false
    });

    $('#role-select1').select2({
        width: '150px',
        allowClear: false,
        placeHolder:"角色"
    });

    fillSelect('role-select', null);
    fillSelect('role-select1', null);
    $('#role-select1').prepend("<option value='0' selected>全部角色</option>>");

    $("#searchUser").on("click",search);
});


/**
 *
 * @param id
 * @param default_id 默认选中id
 */
var fillSelect = function (id, default_id){
    var str = '';
    $.each(roles, function(i, item){
        if(default_id != null && item.id == default_id){
            str += '<option selected value="'+item.id+'">'+item.name+'</option>';
        }else{
            str += '<option value="'+item.id+'">'+item.name+'</option>';
        }
    });
    $('#' + id).html(str);
}

var userSave = function(){

    var data = {};
    var rid = $('#role-select').val() || '';
    var name = $('#name').val() || '';
    var password = $('#password').val() || '';
    var realName = $('#realName').val() || '';
    var idcard = $('#idcard').val() || '';
    var phone = $('#phone').val() || '';
    var email = $('#email').val() || '';
    var qqNumber = $('#qqNumber').val() || '';
    var wx = $('#wx').val() || '';

    data.roleId = rid;
    data.name = name;
    data.password = password;
    data.realName = realName;
    data.idcard = idcard;
    data.phone = phone;
    data.email = email;
    data.qqNumber = qqNumber;
    data.wx = wx;

    $.ajax({
       url: SERVER_ROUTER.PATH_USER_SAVE,
       type: 'POST',
       dataType: 'JSON',
       data: data,
       success: function(data){
           if(data && data.status == SERVER_RESPONSE_CODE.OK){
               if(data.data){
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


var userDelete = function(obj, index){
    var data = obj.data;
    $.ajax({
       url: SERVER_ROUTER.PATH_USER_DELETE,
       type: 'POST',
       dataType: 'JSON',
       data: {id: data.id},
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

var userUpdate = function(obj){

    var data = obj.data;

    $.ajax({
       url: SERVER_ROUTER.PATH_USER_UPDATE,
       type: 'POST',
       dataType: 'JSON',
       data: {
           id: data.id,
           roleId: data.roleId,
           name: data.name,
           password: data.password,
           realName: data.realName,
           idcard: data.idcard,
           phone: data.phone,
           email: data.email,
           qqNumber: data.qqNumber,
           wx: data.wx
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

var toUpdPid = function(id, roleId){

    layer.open({
        title: '选择角色组',
        content: '<select id="rid_upd_'+id+'"></select>',
        offset: 't',
        yes: function(index, layero){

            var updRid = $('#rid_upd_' + id).val();
            $.ajax({
                url: SERVER_ROUTER.PATH_USER_UPDATE,
                type: 'POST',
                dataType: 'JSON',
                data: {id: id, roleId: updRid},
                success: function(data){
                    if(data && data.status == SERVER_RESPONSE_CODE.OK){
                        if(data.data){
                            layer.msg(data.msg);
                            table.reload('table');
                        }
                    }else{
                        layer.msg(data.msg);
                        return false;
                    }
                }
            });
        }
    });

    $('#rid_upd_' + id).select2({
        width: '150px',
        allowClear: false
    });
    fillSelect('rid_upd_' + id, roleId);

}

var search = function () {
    var rid = $('#role-select1').val() || '';
    var filterValue = $('#nameAndPhone').val() || '';
    console.log(123);
    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_PAGE_USER,
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
        where: { roleId: rid,filterValue:filterValue },
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
                    field: 'roleName',
                    title: '角色组',
                    align: 'center',
                    templet: '<div><a href="javascript:void(0);" onclick="toUpdPid(\'{{d.id}}\', \'{{d.roleId}}\');">{{d.roleName}}</a></div>',
                    width: 100,
                },
                {
                    field: 'name',
                    title: '登录账号',
                    width: 100,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'realName',
                    title: '真实姓名',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'idcard',
                    title: '身份证号',
                    width: 120,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'phone',
                    title: '联系电话',
                    width: 120,
                    align: 'center',
                    edit: true
                },
                {
                    field: 'email',
                    title: '邮箱',
                    width: 150,
                    align: 'center',
                    edit: true
                },
                // {
                //     field: 'qqNumber',
                //     title: 'qq号',
                //     width: 100,
                //     align: 'center',
                //     edit: true
                // },
                {
                    field: 'wx',
                    title: '医院',
                    width: 100,
                    align: 'center',
                    edit: false
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
}