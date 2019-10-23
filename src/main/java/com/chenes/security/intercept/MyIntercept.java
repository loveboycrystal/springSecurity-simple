package com.chenes.security.intercept;

import com.chenes.security.bean.PdspUser;
import com.chenes.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 拦截器
 * @author 277280289@qq.com
 * @date 2019/10/21
 * springSecurity 样例
 * 修改记录
 *   修改人：  修改日期:        版本：         修改内容:
 *   chenes  2019/10/21 18:38     1.0              新增
 */
@Component
@Slf4j
public class MyIntercept extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MyIntercept开始拦截");
        PdspUser user = SecurityUtils.getUser();
        if(null == user){
            log.warn(" 尚未登录，请先登录。");
            //return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        log.info("MyIntercept正在拦截");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        log.info("MyIntercept拦截完成");
    }
}
