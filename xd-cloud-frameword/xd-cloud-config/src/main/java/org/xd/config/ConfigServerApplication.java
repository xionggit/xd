package org.xd.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.model.Check;

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
//@EnableScheduling  //开启定时任务
@Slf4j
public class ConfigServerApplication {
    
    @Autowired
    private ConsulClient consulClient;
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        log.info("======ConfigServerApplication started=====");
    }
    
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void getAllServicer(){
        log.info("***********************consul上无效服务清理开始*******************************************");
        //获取所有的members的信息
        List<Member> members = consulClient.getAgentMembers().getValue();
        for (int i=0; i<members.size(); i++){
            //获取每个member的IP地址
            String address = members.get(i).getAddress();
            log.info("member的IP地址为:{}",address);
            //根据role变量获取每个member的角色  role：consul---代表服务端   role：node---代表客户端
            String role =  members.get(i).getTags().get("role");
            log.info("{}机器的role为：{}=====注释*role为consul代表服务端   role为node代表客户端",address,role);
            //判断是否为client
            if (role.equals("node")){
                //将IP地址传给ConsulClient的构造方法，获取对象
                ConsulClient  clearClient =new ConsulClient(address);
                //根据clearClient，获取当前IP下所有的服务 使用迭代方式 获取map对象的值
                Iterator<Map.Entry<String,Service>> it =clearClient.getAgentServices().getValue().entrySet().iterator();
                while (it.hasNext()){
                    //迭代数据
                    Map.Entry<String,Service> serviceMap =  it.next();
                    //获得Service对象
                    Service service = serviceMap.getValue();
                    //获取服务名称
                    String serviceName = service.getService();
                    //获取服务ID
                    String serviceId = service.getId();
                    log.info("在{}客户端上的服务名称 :{}**服务ID:{}",address,serviceName,serviceId);
                    //根据服务名称获取服务的健康检查信息
                    Response<List<Check>> checkList =consulClient.getHealthChecksForService(serviceName,null);
                    List<Check> checks = checkList.getValue();
                    //获取健康状态值  PASSING：正常  WARNING  CRITICAL  UNKNOWN：不正常
                    Check.CheckStatus checkStatus = checks.get(0).getStatus();
                    log.info("在{}客户端上的服务 :{}的健康状态值：{}",address,serviceName,checkStatus);
                    if (checkStatus != Check.CheckStatus.PASSING){
                        log.info("在{}客户端上的服务 :{}为无效服务，准备清理...................",address,serviceName);
                        clearClient.agentServiceDeregister(serviceId);
                    }
                }
            }
        }
    }
}
