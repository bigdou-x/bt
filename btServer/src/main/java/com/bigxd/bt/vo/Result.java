package com.bigxd.bt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tkom
 * @version 1.0
 * @date 2019/11/4 0004
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private String msg;
    private String status;

}
