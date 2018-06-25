package com.dmy.vue.service;


import com.dmy.vue.domain.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: daimengying
 * @Date: 2018/5/17 17:21
 * @Description:
 */
public interface LoginService {
    List<Users> getUserByUserName(String userName);
    Map<String,Object> loginLogic(Map<String, Object> param, HttpServletRequest request, HttpServletResponse response);
    Users updateUserByPK(Users user, String repeatPassword);

}
