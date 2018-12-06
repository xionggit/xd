package org.xd.web.admin.mp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xd.common.global.config.exception.RuntimeServiceException;
import org.xd.common.global.config.i18n.CoreMessageSource;
import org.xd.common.global.config.pojo.ResultDto;

import lombok.extern.slf4j.Slf4j;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name: xiong  email: shao200815@163.com
* @date 创建时间：2018-11-29 11:34:36
*/
@SpringBootApplication
@EnableDiscoveryClient
//@EnableRedisHttpSession
@EnableFeignClients
//@EnableTransactionManagement
@EnableAsync
@Slf4j
@RestController
@ComponentScan(basePackages = {"org.xd"})
public class WebAdminMPAplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminMPAplication.class, args);
        log.info("--WebAdminMPAplication started---------");
    }
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    CoreMessageSource messageSource;
    
    @RequestMapping("/getTest2")
    public ResultDto getTest() throws IOException {
        AclUser user = (AclUser) redisTemplate.opsForValue().get("user");
        ResultDto dto = (ResultDto) redisTemplate.opsForValue().get("dto");
        dto.setData(user);
        return dto;
    }
    @RequestMapping("/getTest")
    public ResultDto getTes1t() throws IOException {
        ResultDto dto = new ResultDto("sdfasdfasdf");
        AclUser user = new AclUser();
        user.setUserName("123");
        redisTemplate.opsForValue().set("user", user);
        redisTemplate.opsForValue().set("dto", dto);
        
        return new ResultDto(messageSource.getMessage("ioException"));
    }
}
