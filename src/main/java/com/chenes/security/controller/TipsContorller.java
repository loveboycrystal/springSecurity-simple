package com.chenes.security.controller;

import lombok.extern.slf4j.Slf4j;
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
public class TipsContorller {

    /**
     *  提示无权限页面
     * @return
     */
    @RequestMapping(value = "/p403",method = RequestMethod.GET)
    public String p403(){
        log.info("to 403 page");
        return "403";
    }


    /**
     *  更目录访问登录页面
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "login";
    }

    /**
     * 系统错误页面
     * @return
     */
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }
}
