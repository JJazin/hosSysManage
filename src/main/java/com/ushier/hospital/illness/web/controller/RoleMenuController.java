package com.ushier.hospital.illness.web.controller;

import com.ushier.hospital.illness.web.bean.ResponseMessageBean;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.service.RoleMenuService;
import com.ushier.hospital.illness.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public ResponseMessageBean<Boolean> updateRole(Integer roleId, String menuIdsStr){
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

        int rows = this.roleMenuService.updateRole(roleId, menuIds);
        bean.setData(rows > 0 ? true : false);
        bean.setMsg(rows > 0 ? ServerCode.MSG_UPDATE_SUCCESS : ServerCode.MSG_UPDATE_FAIL);

        return bean;
    }

}
