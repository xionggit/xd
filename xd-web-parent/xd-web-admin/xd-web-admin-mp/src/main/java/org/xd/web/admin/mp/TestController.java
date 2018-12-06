package org.xd.web.admin.mp;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xd.common.global.config.pojo.ResultDto;

/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name: xiong  email: shao200815@163.com
* @date 创建时间：2018-12-06 10:47:21
*/
@RestController
@RequestMapping("/test")
public class TestController {
    
    @RequestMapping("/log")
    public ResultDto getTest() throws IOException {
        
        return new ResultDto(2000, "测试aop日志");
    }
    
}
