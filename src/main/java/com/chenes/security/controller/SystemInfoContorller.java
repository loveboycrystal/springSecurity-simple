package com.chenes.security.controller;

import com.chenes.security.bean.PdspUser;
import com.chenes.security.service.UserService;
import com.chenes.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description 
 * @author 277280289@qq.com
 * @date 2019/10/21
 * springSecurity 样例
 * 修改记录
 *   修改人：  修改日期:        版本：         修改内容:
 *   chenes  2019/10/21 11:46     1.0              新增
 */
@Controller
@Slf4j
@RequestMapping(value = "/system")
public class SystemInfoContorller {


    @Autowired
    private AuthenticationManager myAuthenticationManager;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd, HttpServletRequest request){

        UsernamePasswordAuthenticationToken  upat = new UsernamePasswordAuthenticationToken(uname,pwd);
        Authentication authentication = myAuthenticationManager.authenticate(upat);
        SecurityContextHolder.getContext().setAuthentication(authentication);


       // HttpSession session = request.getSession();
        //session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆

        return "redirect:/system/toindex";
    }


    /**
     *
     * 功能描述: 登出
     * @return:
     * @date: 2019/10/23 16:06
     *
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        SecurityUtils.logout();
        return "login";
    }



    @ResponseBody
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public PdspUser index(){
        return SecurityUtils.getUser();
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "/toindex",method = RequestMethod.GET)
    public String toindex(){
        return "index";
    }
}
