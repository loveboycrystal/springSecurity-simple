package com.chenes.security.defined;

import com.chenes.security.bean.PdspMenu;
import com.chenes.security.bean.PdspUser;
import com.chenes.security.service.MenuService;
import com.chenes.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 用户认证和授权
 * @author 277280289@qq.com
 * @date 2019/10/21
 * springSecurity 样例
 * 修改记录
 *   修改人：  修改日期:        版本：         修改内容:
 *   chenes  2019/10/21 15:05     1.0              新增
 */
public class PdspUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        PdspUser pdspUser = userService.loadUserByUname(s);

        //--------------------认证账号
        if (pdspUser == null) {
            throw new UsernameNotFoundException("账号不存在。");
        }


        //-------------------开始授权
        List<PdspMenu> menus = menuService.getMenusByUserId(pdspUser.getUId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (PdspMenu menu : menus) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
            grantedAuthorityList.add(grantedAuthority);
        }

        pdspUser.setAuthorities(grantedAuthorityList);

        return pdspUser;
    }
}
