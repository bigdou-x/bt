package com.bigxd.bt.mapper;

import com.bigxd.bt.entity.Role;
import com.bigxd.bt.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/2 0002
 */
public interface UserMapper {

    void saveUser(User user);

    User loadUserByEmail(String email);

    List<Role> loadRolesByUserId(String id);

    List<User> getAllUsers();

    void deleteUserById(@Param("ids") String[] ids);
}
