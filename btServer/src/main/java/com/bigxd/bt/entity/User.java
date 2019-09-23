package com.bigxd.bt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class User {

    private String userName;
    private String password;

}
