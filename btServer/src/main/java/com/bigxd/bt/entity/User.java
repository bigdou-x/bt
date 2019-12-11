package com.bigxd.bt.entity;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * <P>文件描述：用户类</P>
 *
 * @author xuqing
 * @version V1.0
 * @date 2019-9-23 23:34:43
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "User", description = "用户实体类")
public class User implements UserDetails {
    @ApiModelProperty(value = "用户名", name = "userName", dataType = "String")
    private String nickName;
    @ApiModelProperty(value = "密码", name="password", hidden = true, dataType = "String")
    private String password;

    private String email;

    private String id;

    private String phoneNum;

    private String address;

    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
