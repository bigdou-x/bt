package com.bigxd.bt.controller;

import com.bigxd.bt.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <P>文件描述：写代码的第一步</P>
 *
 * @author xuqing
 * @version V1.0.0
 * @date 2019-9-22 23:25:01
 */
@RestController
public class HelloWorldController {

    @ApiOperation(value = "helloWorld", notes = "第一次写代码都写这个")
    @GetMapping(value = "/helloWorld")
    public String helloWorld() {
        return "helloWorld456";
    }

    @ApiOperation(value = "测试参数", notes = "测试一下参数")
    @ApiImplicitParam(name = "param", value = "参数", required = true, paramType = "query")
    @PostMapping(value = "/testParam")
    public String testParam(@RequestParam(name = "param") String param) {
        return param;
    }

    @ApiOperation(value = "测试entity参数", notes = "测试一下")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "body")
    @PostMapping(value = "/testParamObject")
    public String testParamObject(@RequestBody User user) {
        return user.toString();
    }

}
