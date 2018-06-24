package com.dmy.vue.controller;

import com.dmy.vue.domain.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: daimengying
 * @Date: 2018/5/23 09:39
 * @Description:
 */
public class BaseController {
    /**
     * 获取当前登录用户
     * @param req
     * @return
     */
    public Users getCurrentUser(HttpServletRequest req){
        return (Users)getSession(req).getAttribute("userInfo");
    }

    public HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

}
