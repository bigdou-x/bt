package com.bigxd.bt.controller;

import com.bigxd.bt.entity.Menu;
import com.bigxd.bt.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/3 0003
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取所有菜单")
    @GetMapping("/getAllMenus")
    public List<Menu> getAllMenus(){
        List<Menu> menus = menuService.getAllMenus();
        menus.forEach((menu)-> System.out.println(menu.getName()));
        return menus;
    }
}
