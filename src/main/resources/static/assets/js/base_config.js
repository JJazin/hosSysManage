function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    // if(undefined != projectName)
    //     return localhostPath + projectName;
    return localhostPath;
}

function getHtmlDocName() {
    var str = window.location.href;
    str = str.substring(str.lastIndexOf("/") + 1);
    str = str.substring(0, str.lastIndexOf("."));
    return str;
}

function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r != null) return unescape(r[2]);
    //不存在时返回null
    return null;
}

var rootPath = getRootPath();

var SERVER_ROUTER = {
    PATH_BASE: rootPath,
    PATH_FILE: rootPath + '/',

    PATH_LOGIN_PAGE: rootPath + '/login.html',
    PATH_REG_PAGE: rootPath + '/reg.html',
    PATH_INDEX_PAGE: rootPath + '/index.html',

    PATH_LOGIN: rootPath + '/user/login',
    PATH_LOGIN_OUT: rootPath + '/user/loginOut',
    PATH_REG: rootPath + '/user/reg',
    PATH_QUERY_PAGE_USER: rootPath + '/user/pageQuery',
    PATH_USER_SAVE: rootPath + '/user/save',
    PATH_USER_UPDATE: rootPath + '/user/update',
    PATH_USER_DELETE: rootPath + '/user/delete',
    PATH_USER_GET: rootPath + '/user/get',

    PATH_QUERY_PAGE_ROLE: rootPath + '/role/pageQuery',
    PATH_ROLE_SAVE: rootPath + '/role/save',
    PATH_ROLE_UPDATE: rootPath + '/role/update',
    PATH_ROLE_DELETE: rootPath + '/role/delete',
    PATH_ROLE_QUERY_ALL: rootPath + '/role/queryAll',
    PATH_ROLE_GET: rootPath + '/role/get',

    PATH_ROLE_MENU_UPDATE: rootPath + '/roleMenu/updateRole',

    PATH_QUERY_MENU: rootPath + '/menu/queryMenus',
    PATH_QUERY_PAGE_MENU: rootPath + '/menu/pageQuery',
    PATH_QUERY_PARENT_MENU: rootPath + '/menu/queryMenusByPid',
    PATH_MENU_SAVE: rootPath + '/menu/save',
    PATH_MENU_UPDATE: rootPath + '/menu/update',
    PATH_MENU_DELETE: rootPath + '/menu/delete',
    PATH_MENU_QUERY_TREE: rootPath + '/menu/queryMenusTree',


    // app user
    PATH_QUERY_PAGE_APP_USER: rootPath + '/app/user/pageQuery',
    PATH_APP_USER_SAVE: rootPath + '/app/user/save',
    PATH_APP_USER_UPDATE: rootPath + '/app/user/update',
    PATH_APP_USER_DELETE: rootPath + '/app/user/delete',

    // hospital
    PATH_QUERY_PAGE_HOSPITAL: rootPath + '/hospital/pageQuery',
    PATH_HOSPITAL_SAVE: rootPath + '/hospital/save',
    PATH_HOSPITAL_UPDATE: rootPath + '/hospital',
    PATH_HOSPITAL_QUERY_ALL: rootPath + '/hospital/queryAll',

    // department
    // PATH_QUERY_PAGE_DEPARTMENT: rootPath + '/department/pageQuery',
    // PATH_DEPARTMENT_SAVE: rootPath + '/department/save',
    // PATH_DEPARTMENT_UPDATE: rootPath + '/department',

    // doctor
    PATH_QUERY_PAGE_DOCTOR: rootPath + '/doctor/pageQuery',
    PATH_QUERY_ALL_DOCTOR: rootPath + '/doctor/queryAll',
    PATH_DOCTOR_SAVE: rootPath + '/doctor/save',
    PATH_DOCTOR_UPDATE: rootPath + '/doctor',
    PATH_DEP_DOCTOR_SAVE: rootPath + '/doctor/saveDepDoctors',

    // illness
    PATH_QUERY_PAGE_ILLNESS: rootPath + '/illness/pageQuery',
    PATH_QUERY_PAGE_ILLNESS_FOR_DOCTOR: rootPath + '/illness/pageQueryForDoctor',
    PATH_ILLNESS_UPDATE: rootPath + '/illness',

    // registration
    // PATH_QUERY_REGISTRATION: rootPath + "/registration",

    // case
    PATH_QUERY_SICKNESS: rootPath + '/case/sickness',
    PATH_QUERY_SICKNESS_SEASON: rootPath + '/case/seasonSickness',
    PATH_QUERY_PAGE_CASE: rootPath + '/case/pageQuery',
    PATH_CASE_SAVE: rootPath + '/case/save',
    PATH_CASE_SAVE_FOR_DOCTOR: rootPath + '/case/saveForDoctor',
    PATH_CASE_UPDATE: rootPath + '/case/update',
    PATH_CASE_DELETE: rootPath + '/case/delete',
    PATH_CASE_GET: rootPath + '/case/get',

    // notice
    PATH_QUERY_PAGE_NOTICE: rootPath + '/notice/pageQuery',
    PATH_NOTICE_SAVE: rootPath + '/notice/save',
    PATH_NOTICE_UPDATE: rootPath + '/notice/update',
    PATH_NOTICE_DELETE: rootPath + '/notice/delete',

    //sick
    PATH_QUERY_PAGE_SICKNESS: rootPath + '/sickness/pageQuery',
    PATH_SICKNESS_SAVE: rootPath + '/sickness/save',
    PATH_SICKNESS_UPDATE: rootPath + '/sickness/update',
    PATH_SICKNESS_DELETE: rootPath + '/sickness/delete',

    // result
    // PATH_RESULT_SAVE: rootPath + "/result/save",


    PATH_FILE_UPLOAD: rootPath + '/file/upload',

    DEFAULT: ''
}



var SERVER_RESPONSE_CODE = {
    OK: 'OK',
    FAIL: 'FAIL',
    ERROR: 'ERROR',
    LAYUI_OK: 0,
    LAYUI_ERROR: -1
}