package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.entity.RoleEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.global.SessionKey;
import com.ushier.hospital.illness.web.service.RoleService;
import com.ushier.hospital.illness.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    public LayUITableBean<RoleEntity> pageQuery(Integer currentPage, Integer limit){

        int position = (currentPage - 1) * limit;

        return this.roleService.pageQuery(position, limit);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> save(RoleEntity roleEntity, String menuIdsStr){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        if(StringUtil.isBlank(menuIdsStr)){
            bean.setData(false);
            bean.setMsg(ServerCode.MSG_NULL_VALUE);
            return bean;
        }

        String[] split = menuIdsStr.split(",");
        Integer[] menuIds = new Integer[split.length];

        for(int i=0; i<split.length; i++){
            menuIds[i] = Integer.valueOf(split[i]);
        }

        int rows = this.roleService.insertWithRoleMenu(roleEntity, menuIds);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(ServerCode.MSG_SAVE_SUCCESS);

        return bean;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> update(RoleEntity roleEntity){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.roleService.update(roleEntity);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS: ServerCode.MSG_UPDATE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> delete(Integer id){
        ResponseMessageBean<Boolean> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        int rows = this.roleService.delById(id);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_DELETE_SUCCESS : ServerCode.MSG_DELETE_FAIL);
        return bean;
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ResponseMessageBean<List<RoleEntity>> queryAll(){
        ResponseMessageBean<List<RoleEntity>> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);

        List<RoleEntity> list = this.roleService.queryAll();
        bean.setData(list);

        return bean;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessageBean<Integer> get(HttpServletRequest request){
        ResponseMessageBean<Integer> bean = new ResponseMessageBean<>(ServerCode.RETURN_OK);
        HttpSession session = request.getSession();
        Integer rid = (Integer) session.getAttribute(SessionKey.RID);
        bean.setData(rid);
        return bean;
    }

}
