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
                }
            ]
        ]
    });

});