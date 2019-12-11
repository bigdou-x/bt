package com.bigxd.bt.mapper;

import com.bigxd.bt.entity.Menu;

import java.util.List;

/**
 * Created by Adminstrator on 2019/10/3 0003.
 */
public interface MenuMapper {

    List<Menu> selectById(Integer id);

}
