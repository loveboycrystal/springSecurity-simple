package com.chenes.security.controller;

import com.chenes.security.bean.PdspMenu;
import com.chenes.security.bean.PdspUser;
import com.chenes.security.service.MenuService;
import com.chenes.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

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

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @PreAuthorize("hasAuthority('/menu/list')")
    @RequestMapping(value = "/menu/list",method = RequestMethod.GET)
    public List<PdspMenu> menulist(){
        return menuService.getAllPdspMenuList();
    }


    @ResponseBody
    @PreAuthorize("hasAuthority('/user/list')")
    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    public List<PdspUser> userlist(){
        return userService.getAllPdspUserist();
    }

}
