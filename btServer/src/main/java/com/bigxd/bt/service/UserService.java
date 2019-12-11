package com.bigxd.bt.service;

import com.bigxd.bt.entity.User;
import com.bigxd.bt.mapper.UserMapper;
import com.bigxd.bt.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/2 0002
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(User user){
        if(userMapper.loadUserByEmail(user.getEmail()) != null){
            return user;
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.saveUser(user);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername:"+s);
        User user = userMapper.loadUserByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("该用户名不存在");
        }

        return user;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public Result deleteUserById(String ids) {
        Result result = new Result();

        userMapper.deleteUserById(ids.split(","));
        result.setStatus("success");
        result.setMsg("删除成功");
        return result;
    }
}
