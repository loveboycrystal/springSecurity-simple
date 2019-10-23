package com.chenes.security.controller;

import com.chenes.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ResourceContorller {


    @Autowired
    private UserService userService;

    @ResponseBody
    @PreAuthorize("hasAuthority('/menu/list')")
    @RequestMapping(value = "/menu/list",method = RequestMethod.GET)
    public String menulist(){
        return "menu/info";
    }


    @ResponseBody
    @PreAuthorize("hasAuthority('/user/list')")
    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    public String userlist(){
        return "user/info";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
