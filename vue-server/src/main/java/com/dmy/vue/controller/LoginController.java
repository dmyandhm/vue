package com.dmy.vue.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmy.vue.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: daimengying
 * @Date: 2018/5/17 18:35
 * @Description:
 */

@Controller
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    Logger _logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 极验证初始化
     * @param request
     * @return
     */
//    @RequestMapping(value = "/startCaptcha")
//    @ResponseBody
//    protected String startCaptcha(HttpServletRequest request){
//
//        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
//                GeetestConfig.isnewfailback());
//
//        String resStr = "{}";
//
//        String userid = "test";
//
//        //自定义参数,可选择添加
//        HashMap<String, String> param = new HashMap<String, String>();
//        param.put("user_id", userid); //网站用户id
//        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
//        param.put("ip_address", request.getRemoteAddr()); //传输用户请求验证时所携带的IP
//
//        //进行验证预处理
//        int gtServerStatus = gtSdk.preProcess(param);
//
//        //将服务器状态设置到session中
//        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
//        //将userid设置到session中
//        request.getSession().setAttribute("userid", userid);
//
//        resStr = gtSdk.getResponseStr();
//        return resStr;
//    }

    /**
     * 登录逻辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody  String params,  HttpServletRequest request, HttpServletResponse response){
        Map<String, String>result=new HashMap<>();
        JSONObject parObject= JSON.parseObject(params);
        String username = parObject.getString("username");
        String password = parObject.getString("password");
        if (StrUtil.isEmpty(username)) {
            result.put("resultCode", "-1001");
            result.put("error_msg", "请输入用户名");
            return result;
        }
        if (StrUtil.isEmpty(password)) {
            result.put("resultCode", "-1002");
            result.put("error_msg", "请输入密码");
            return result;
        }
//        _logger.info("Login [{}] - username[{}] - password[{}]",ipUtil.getIpAddr(request),username,password);
//        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
//                GeetestConfig.isnewfailback());
//        String challenge = parObject.getString(GeetestLib.fn_geetest_challenge);
//        String validate = parObject.getString(GeetestLib.fn_geetest_validate);
//        String seccode = parObject.getString(GeetestLib.fn_geetest_seccode);
//        //自定义参数,可选择添加
//        HashMap<String, String> param = new HashMap<>();
//        //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
//        param.put("client_type", "web");
//        //传输用户请求验证时所携带的IP
//        param.put("ip_address", ipUtil.getIpAddr(request));
//
//        int gtResult = 0;
//        Integer gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
//        if (null !=gt_server_status_code && gt_server_status_code == 1) {
//            //gt-server正常，向gt-server进行二次验证
//            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
//        }
//        if (gtResult == 1) {
//            Map<String, Object>parMap=new HashMap<>();
//            parMap.put("username",username);
//            parMap.put("password",password);
//            Map<String, String>loginLogicResult=loginService.loginLogic(parMap,request,response);
//            result.put("resultCode",loginLogicResult.get("resultCode"));
//            result.put("resultMsg",loginLogicResult.get("resultMsg"));
//        }else {
//            // 验证失败
//            result.put("resultCode", "-1003");
//            result.put("resultMsg", "验证失败" );
//            result.put("version", gtSdk.getVersionInfo());
//        }
        Map<String, Object>parMap=new HashMap<>();
            parMap.put("username",username);
            parMap.put("password",password);
            Map<String, String>loginLogicResult=loginService.loginLogic(parMap,request,response);
            result.put("resultCode",loginLogicResult.get("resultCode"));
            result.put("resultMsg",loginLogicResult.get("resultMsg"));
        return result;
    }




    /**
     * 退出登陆
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
        return "/login";
    }


}
