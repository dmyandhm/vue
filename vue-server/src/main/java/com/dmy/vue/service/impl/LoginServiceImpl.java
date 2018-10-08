package com.dmy.vue.service.impl;

import com.dmy.vue.dao.UserMapper;
import com.dmy.vue.domain.Users;
import com.dmy.vue.service.LoginService;
import com.dmy.vue.vo.UserAgent;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: daimengying
 * @Date: 2018/5/17 17:42
 * @Description:登录逻辑处理
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;


    @Override
    @Cacheable(value = "lirong_gascard_",key="'user_'+#userName")
    public List<Users> getUserByUserName(@NonNull String userName) {
        Example example=new Example(Users.class);
        Example.Criteria createCriteria = example.createCriteria();
        createCriteria.andEqualTo("username",userName);
        createCriteria.andEqualTo("status",0);
        List<Users> userList=userMapper.selectByExample(example);
        return userList;
    }

    @Override
    public Map<String, Object> loginLogic(Map<String, Object>param, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object>result=new HashMap<>();
        String userName = param.get("username")+"";
        String passWord = param.get("password")+"";
        Boolean loginFlag=false;

        try{
            List<Users> userList=getUserByUserName(userName);
            if(userList==null){
                result.put("resultCode", "-2001");
                result.put("resultMsg", "用户不存在");
                return result;
            }
            for(Users user:userList){
                if(user.getPassword().equals(passWord)){
                    loginFlag=true;
                    //将登录用户信息放入session
                    HttpSession session = request.getSession();
                    session.setAttribute("userInfo", user);
                    result.put("resultCode", "0");
                    result.put("userInfo", user);
                    break;
                }
            }
            if(!loginFlag){
                //密码错误
                result.put("resultCode", "-2002");
                result.put("resultMsg", "用户名或密码错误");
            }
        }catch (Exception e){
            _logger.error("【系统登录模块】登录异常，错误信息：" + e.getMessage());
            result.put("resultCode", "-2005");
            result.put("resultMsg", "系统异常");
        }
        return result;
    }

    /**
     * 更新密码
     * @param user
     * @param repeatPassword
     * @return
     */
    @Override
    @CachePut(value = "lirong_gascard_",key="'user'+#user.id")
    public Users updateUserByPK(Users user, String repeatPassword) {
        user.setPassword(repeatPassword);
        Integer num= userMapper.updateByPrimaryKeySelective(user);
        if(num<1){
            throw new RuntimeException("更新用户密码失败");
        }else{
            return user;
        }
    }

}
