package com.bigxd.bt.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>文件描述：写代码的第一步</P>
 *
 * @author xuqing
 * @version V1.0.0
 * @date 2019-9-22 23:25:01
 */
@RestController
public class HelloWorldController {

    @ApiOperation(value = "helloWorld123", notes = "第一次写代码都写这个")
    @RequestMapping(name = "/helloWorld")
    public String helloWorld() {
        return "helloWorld456";
    }

}
