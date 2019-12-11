package com.bigxd.bt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bigxd.bt.mapper")
public class BtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtApplication.class, args);
    }

}
