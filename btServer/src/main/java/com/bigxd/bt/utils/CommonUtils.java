package com.bigxd.bt.utils;

import com.bigxd.bt.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/19 0019
 */

public class CommonUtils {

    public static User getCurrentUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
