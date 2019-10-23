package com.chenes.security.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * @description 交换平台用户
 * @author 277280289@qq.com
 * @date 2019/10/21
 * springSecurity 样例
 * 修改记录
 *   修改人：  修改日期:        版本：         修改内容:
 *   chenes  2019/10/21 15:43     1.0              新增
 */
@Data
public class PdspUser  implements UserDetails, Serializable {

    private Long uId;
    private String uName;
    private String pWd;
    private String nickName;

    private List<? extends  GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return pWd;
    }

    @Override
    public String getUsername() {
        return uName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
