package com.bigxd.bt.controller;

import com.bigxd.bt.entity.User;
import com.bigxd.bt.service.UserService;
import com.bigxd.bt.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/1 0001
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/registry")
    public User registry(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{ids}")
    public Result deleteUserById(@PathVariable String ids) {
        return userService.deleteUserById(ids);
    }
}
