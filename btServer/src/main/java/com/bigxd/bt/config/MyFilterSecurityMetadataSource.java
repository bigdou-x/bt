package com.bigxd.bt.config;

import com.bigxd.bt.entity.Menu;
import com.bigxd.bt.entity.Role;
import com.bigxd.bt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/11/17 0017
 */
@Component
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        String url = invocation.getRequestUrl();
        List<Menu> menus = menuService.getAllMenus();
        for(Menu menu : menus) {
            if(pathMatcher.match(menu.getUrl(), url) && menu.getRoles().size() > 0){
                List<Role> roleList = menu.getRoles();
                int size = roleList.size();
                String []roles = new String[size];
                for (int i = 0; i < roleList.size(); i++) {
                    roles[i] = roleList.get(i).getName();
                }
                SecurityConfig.createList(roles);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
