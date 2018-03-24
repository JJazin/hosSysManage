var table = layui.table;

$(function(){

    table.render({
        elem: '#table',
        id: 'table',
        even: false,// 隔行背景
        skin: 'line',// 行边框风格
        url: SERVER_ROUTER.PATH_QUERY_SICKNESS_SEASON,
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
                    field: 'sicknessName',
                    title: '疾病',
                    width: 150,
                    align: 'center',
                    edit: false
                },
                {
                    field: 'number',
                    title: '数量',
                    width: 250,
                    align: 'center',
                    edit: false
                }
            ]
        ]
    });

});