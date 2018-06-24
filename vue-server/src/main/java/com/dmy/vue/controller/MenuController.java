package com.dmy.vue.controller;

import com.dmy.vue.domain.Menu;
import com.dmy.vue.domain.Users;
import com.dmy.vue.service.MenuService;
import com.dmy.vue.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: daimengying
 * @Date: 2018/5/19 14:32
 * @Description:菜单栏
 */
@Controller
public class MenuController extends BaseController{
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/menu",method= RequestMethod.POST)
    @ResponseBody
    public  List<Tree<Menu>> index(HttpServletRequest req){
        HttpSession session = req.getSession();
        Users user=getCurrentUser(req);
        List<Tree<Menu>> trees=menuService.getMenuTree(user.getId());
        return  trees;
    }
}
