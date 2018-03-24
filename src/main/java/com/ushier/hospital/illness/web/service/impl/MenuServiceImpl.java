package com.ushier.hospital.illness.web.service.impl;

import com.ushier.hospital.illness.web.bean.LayUITableBean;
import com.ushier.hospital.illness.web.bean.MenuNodeBean;
import com.ushier.hospital.illness.web.entity.MenuEntity;
import com.ushier.hospital.illness.web.global.ServerCode;
import com.ushier.hospital.illness.web.mapper.MenuMapper;
import com.ushier.hospital.illness.web.service.MenuService;
import com.ushier.hospital.illness.web.util.DateUtil;
import com.ushier.hospital.illness.web.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuEntity> queryMenusByRole(Integer roleId) {

        try{
            List<MenuEntity> rootList = this.menuMapper.queryByRole(roleId);

            return MenuUtil.getMenusTree(rootList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

       return null;
    }

    @Override
    public LayUITableBean<MenuEntity> pageQuery(Integer position, Integer length) {

        LayUITableBean<MenuEntity> bean = new LayUITableBean<>();

        try{
            List<MenuEntity> list = this.menuMapper.pageQuery(position, length);
            int count = this.menuMapper.queryCount();

            bean.setCode(ServerCode.LAYUI_RETURN_OK);
            bean.setCount(count);
            bean.setData(list);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return bean;
    }

    @Override
    public List<MenuEntity> queryMenusByPid(Integer pid) {

        try{
            return this.menuMapper.queryMenusByPid(pid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public int insert(MenuEntity menuEntity) {
        try{
            Date date = DateUtil.getCurrentDate();
            menuEntity.setCreateTime(date);
            menuEntity.setUpdateTime(date);

            return this.menuMapper.insert(menuEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int update(MenuEntity menuEntity) {
        try{
            Date date = DateUtil.getCurrentDate();
            menuEntity.setUpdateTime(date);

            return this.menuMapper.update(menuEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int delById(Integer id) {
        try{
            return this.menuMapper.delById(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<MenuEntity> queryAllMenus() {
        try{
            List<MenuEntity> rootList = this.menuMapper.queryAllMenus();

            return MenuUtil.getMenusTree(rootList);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public List<MenuNodeBean> queryRolePermissionTree(Integer roleId) {

        List<MenuNodeBean> nodeList = new ArrayList<>();

        List<MenuEntity> menuList = this.queryAllMenus();

        for(MenuEntity menuEntity : menuList){
            MenuNodeBean menuNodeBean = new MenuNodeBean();
            menuNodeBean.setId(menuEntity.getId());
            menuNodeBean.setIcon(menuEntity.getIcon());
            menuNodeBean.setText(menuEntity.getTitle());
            List<MenuEntity> childMenuList = menuEntity.getChildrenList();
            List<MenuNodeBean> childNodeList = new ArrayList<>();
            if(null != childMenuList && childMenuList.size() > 0){
                for(MenuEntity cMenu : childMenuList){
                    MenuNodeBean cNodeBean = new MenuNodeBean();
                    cNodeBean.setId(cMenu.getId());
                    cNodeBean.setIcon(cMenu.getIcon());
                    cNodeBean.setText(cMenu.getTitle());
                    childNodeList.add(cNodeBean);
                }
                menuNodeBean.setNodes(childNodeList);
            }
            nodeList.add(menuNodeBean);

        }

        if(null != roleId){
            List<MenuEntity> roleMenuList = this.queryMenusByRole(roleId);
//            Map<String, Object> idsMap = new HashMap<>();
            Set<String> idsSet = new HashSet<>();
            if(null != roleMenuList && roleMenuList.size() > 0){
                for(MenuEntity item : roleMenuList){
//                    idsMap.put(String.valueOf(item.getId()), null);
                    idsSet.add(String.valueOf(item.getId()));
                    List<MenuEntity> childList = item.getChildrenList();
                    if(null != childList && childList.size() > 0){
                        for(MenuEntity cm : childList){
//                            idsMap.put(String.valueOf(cm.getId()), null);
                            idsSet.add(String.valueOf(cm.getId()));
                        }
                    }
                }
            }

            if(null != nodeList && nodeList.size() > 0){
                for(MenuNodeBean n : nodeList){

                    if(idsSet.contains(String.valueOf(n.getId()))){
                        MenuNodeBean.State state = new MenuNodeBean.State();
                        state.setChecked(true);
//                        state.setSelected(true);
                        state.setExpanded(true);
                        n.setState(state);
                    }

                    List<MenuNodeBean> cNodeList = n.getNodes();
                    if(null !=cNodeList && cNodeList.size() > 0){
                        for(MenuNodeBean cn : cNodeList){
                            if(idsSet.contains(String.valueOf(cn.getId()))){
                                MenuNodeBean.State state = new MenuNodeBean.State();
                                state.setChecked(true);
//                                state.setSelected(true);
                                cn.setState(state);
                            }
                        }
                    }
                }
            }

        }

        return nodeList;
    }


}
