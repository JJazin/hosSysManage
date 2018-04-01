package com.green.hospital.illness.web.util;

import com.green.hospital.illness.web.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    public static List<MenuEntity> getMenusTree(List<MenuEntity> rootList){
        // 最后的结果
        List<MenuEntity> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootList.size(); i++) {
            // 一级菜单没有parentId
            MenuEntity me = rootList.get(i);
            if(null != me){
                if (null == me.getPid() || 0 == me.getPid()) {
                    menuList.add(rootList.get(i));
                }
            }

        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuEntity menu : menuList) {
            menu.setChildrenList(getChild(menu.getId(), rootList));
        }
        return menuList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private static List<MenuEntity> getChild(Integer id, List<MenuEntity> rootMenu) {
        // 子菜单
        List<MenuEntity> childList = new ArrayList<>();
        for (MenuEntity menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if(null != menu){
                if (null != menu.getPid() && 0 != menu.getPid()) {
                    if (menu.getPid().equals(id)) {
                        childList.add(menu);
                    }
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuEntity menu : childList) {// 没有url子菜单还有子菜单
            if (StringUtil.isBlank(menu.getHref())) {
                // 递归
                menu.setChildrenList(getChild(menu.getId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
