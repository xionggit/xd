package org.xd.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

import lombok.extern.slf4j.Slf4j;

/**
* @Description 配置中心服务端 
* @author 作者 E-mail: xiong
* @GitConfig name: xiong  email: shao200815@163.com
* @date 创建时间：2018-11-20 17:55:17
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@Slf4j
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        log.info("======ConfigServerApplication started=====");
    }
}
