
var uid = '';
$(function(){

    uid = getUrlParam('id');
    var caseObj;

    $.ajax({
        url: SERVER_ROUTER.PATH_CASE_GET,
        type: 'GET',
        dataType: 'JSON',
        data:{id:uid},
        success: function(data1){


            caseObj = data1.data;
            $("#dName").text(caseObj.dName);
            $("#hName").text(caseObj.hName);
            $("#time").text(caseObj.createTime);
            $("#sName").text(caseObj.sName);
            $("#sex").text(caseObj.sex);
            $("#content").text(!caseObj.reason?"":caseObj.reason);
            $("#advice").text(!caseObj.advice?"":caseObj.advice);

            $("#reason").text(!caseObj.content?"":caseObj.content);

            $("#recipel").text(!caseObj.recipel?"":caseObj.recipel);
        }
    });

    $.ajax({
        url: SERVER_ROUTER.PATH_QUERY_SICKNESS,
        type: 'GET',
        dataType: 'JSON',
        success: function(data){
            console.log(data);
            var list=data;

        }
    });





    $('#sickness-select').select2({
        width: '150px',
        placeholder: '病症',
        allowClear: false
    });
});



var save = function(){

    var data = {};

    data.uid = uid;
    data.content = $('#content').val();
    data.sicknessId = $('#sickness-select').val();
    data.recipel = $('#recipel').val();


    $.ajax({
        url: SERVER_ROUTER.PATH_CASE_SAVE_FOR_DOCTOR,
        type: 'POST',
        dataType: 'JSON',
        data: data,
        success: function(data){
            if(data && data.status == SERVER_RESPONSE_CODE.OK){
                if(data.data){
                    $('#content').val('');
                    $('#recipel').val('');
                    window.location.href = '../case/index.html';
                }
            }
            layer.msg(data.msg, {
                offset: 't'
            });
        }
    });

    return false;
}

