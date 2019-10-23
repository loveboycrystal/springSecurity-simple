package com.chenes.security.service;

import com.chenes.security.bean.PdspMenu;
import com.chenes.security.bean.PdspUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 用户接口
 * @author 277280289@qq.com
 * @date 2019/10/21
 * springSecurity 样例
 * 修改记录
 *   修改人：  修改日期:        版本：         修改内容:
 *   chenes  2019/10/21 15:41     1.0              新增
 */
@Service
public class MenuService {

    private final List<PdspMenu> pdspMenuList = new ArrayList<>();

    private void initPdspUserToMenus(){
        PdspMenu pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(1L);
        pdspMenu.setUId(1L);
        pdspMenu.setUrl("/user/list");
        pdspMenuList.add(pdspMenu);

        pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(2L);
        pdspMenu.setUId(1L);
        pdspMenu.setUrl("/menu/list");
        pdspMenuList.add(pdspMenu);

        pdspMenu = new PdspMenu();
        pdspMenu.setMenuId(3L);
        pdspMenu.setUId(2L);
        pdspMenu.setUrl("/user/list");
        pdspMenuList.add(pdspMenu);

    }

    public  List<PdspMenu> getMenusByUserId(Long uId){
        if (pdspMenuList.size() == 0) {
            initPdspUserToMenus();
        }

        List<PdspMenu> authorityMenuList = new ArrayList<>();
        for (PdspMenu pdspMenu : pdspMenuList) {
            if (pdspMenu.getUId().equals(uId)) {
                authorityMenuList.add(pdspMenu);
            }
        }
        return authorityMenuList;
    }
}
