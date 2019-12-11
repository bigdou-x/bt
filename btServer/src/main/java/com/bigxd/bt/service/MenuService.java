package com.bigxd.bt.service;

import com.bigxd.bt.entity.Menu;
import com.bigxd.bt.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/3 0003
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenus(){
        return menuMapper.selectById(0);
    }

}
