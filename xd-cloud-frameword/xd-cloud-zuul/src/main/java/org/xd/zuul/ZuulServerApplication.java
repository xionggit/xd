package org.xd.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import lombok.extern.slf4j.Slf4j;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name: xiong  email: shao200815@163.com
* @date 创建时间：2018-11-21 10:17:10
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@Slf4j
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
        log.info("zuulserver started-----------");
    }
}
