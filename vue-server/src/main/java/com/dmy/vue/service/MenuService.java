package com.dmy.vue.service;

import com.dmy.vue.domain.Menu;
import com.dmy.vue.domain.UserMenu;
import com.dmy.vue.vo.Tree;
import lombok.NonNull;

import java.util.List;


/**
 * @Author: daimengying
 * @Date: 2018/5/19 14:33
 * @Description:
 */
public interface MenuService {
    List<Tree<Menu>> getMenuTree(@NonNull Integer userId);

    List<Menu>getMenuList();

    List<UserMenu>getMenuListByUserId(@NonNull Integer userId);

}
