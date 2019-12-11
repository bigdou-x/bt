package com.bigxd.bt.entity;

import lombok.*;

import java.util.List;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/10/3 0003
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Integer id;
    private Integer parentId;
    private String name;
    private String iconCls;
    private String path;
    private String url;
    private Object component;
    private List<Menu> children;
    private List<Role> roles;
}
