package com.chenes.security.service;

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
public class UserService {

    private final List<PdspUser> pdspUserList = new ArrayList<>();

    private void initPdspUsers(){
        PdspUser pdspUser = new PdspUser();
        pdspUser.setUId(1L);
        pdspUser.setUName("chenes");
        pdspUser.setNickName("陈帅帅");
        pdspUser.setPWd("123456");
        pdspUserList.add(pdspUser);

        pdspUser = new PdspUser();
        pdspUser.setUId(2L);
        pdspUser.setUName("crystal");
        pdspUser.setNickName("妖姬");
        pdspUser.setPWd("888888");
        pdspUserList.add(pdspUser);
    }

    public PdspUser loadUserByUname(String uName){

        if (pdspUserList.size() == 0) {
            initPdspUsers();
        }

        if(StringUtils.isEmpty(uName)){
            return null;
        }
        PdspUser resultPdspUser = null;
        for (PdspUser pdspUser : pdspUserList) {
            if (pdspUser.getUName().equals(uName)) {
                resultPdspUser = pdspUser;
                break;
            }
        }
        return resultPdspUser;
    }
}
