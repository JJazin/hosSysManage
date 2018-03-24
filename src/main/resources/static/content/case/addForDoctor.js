
var uid = '';
$(function(){

    uid = getUrlParam('id');

    $.ajax({
        url: SERVER_ROUTER.PATH_QUERY_SICKNESS,
        type: 'GET',
        dataType: 'JSON',
        success: function(data){
            console.log(data);
            var html = '';
            for(var i=0; i<data.length; i++){
                var obj = data[i];
                html += '<option value = "' + obj.id + '">' + obj.desc + '</option>';
            }
            $('#sickness-select').html(html);
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

