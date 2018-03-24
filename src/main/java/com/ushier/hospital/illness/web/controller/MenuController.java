package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.bean.MenuNodeBean;
import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.entity.MenuEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.global.SessionKey;
import com.ushier.hospital.illness.web.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;


    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<MenuEntity> pageQuery(Integer currentPage, Integer limit){

        int position = (currentPage - 1) * limit;

        return this.menuService.pageQuery(position, limit);
    }

    @RequestMapping(value = "/queryMenusByPid", method = RequestMethod.GET)
    public ResponseMessageBean<List<MenuEntity>> queryMenusByPid(Integer pid){
        ResponseMessageBean<List<MenuEntity>> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        List<MenuEntity> list = this.menuService.queryMenusByPid(pid);
        bean.setData(list);

        return bean;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> save(MenuEntity menuEntity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.menuService.insert(menuEntity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_SAVE_SUCCESS : ServerCode.MSG_SAVE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> update(MenuEntity menuEntity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.menuService.update(menuEntity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> delById(Integer id){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.menuService.delById(id);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_FAIL);
        return bean;
    }


    @RequestMapping(value = "/queryMenusTree", method = RequestMethod.GET)
    public ResponseMessageBean<List<MenuNodeBean>> queryMenusTree(@RequestParam(value = "roleId", required = false) Integer roleId){
        ResponseMessageBean<List<MenuNodeBean>> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);


        List<MenuNodeBean> nodeList = this.menuService.queryRolePermissionTree(roleId);

        bean.setData(nodeList);

        return bean;
    }


    /**
     * 初始化index.html左侧树形菜单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryMenus", method = RequestMethod.GET)
    public ResponseMessageBean<List<MenuEntity>> queryMenus(HttpServletRequest request){
        ResponseMessageBean<List<MenuEntity>> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        try{
            Integer rid = (Integer) request.getSession().getAttribute(SessionKey.RID);

            if(null == rid){
                bean.setMsg(ServerCode.MSG_RID_NOT_FOUND);
                return bean;
            }

            List<MenuEntity> menuList = this.menuService.queryMenusByRole(rid);

            bean.setData(menuList);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            bean.setStatus(ServerCode.RETURN_ERROR);
            bean.setMsg(ServerCode.MSG_SERVER_ERROR);
        }

        return bean;
    }


}
