package org.xd.common.global.config.aspect;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/** 
 * @Description: 类说明: 切面日志  TODO 后面迭代将日志记录到数据库中
 * @author: xiong  
 * @GitConfig name: xiong  email: shao200815@163.com
 * @date: 2018-11-29 11:46:26 
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {


    ThreadLocal<Long> startTime = new ThreadLocal<>();
    //匹配包名前缀为org.xd的，任何包下类名后缀为Controller的，所有public方法。
    @Pointcut("execution(public * org.xd..*.*Controller.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        
        Enumeration<String> headers = request.getHeaderNames();
        if (log.isDebugEnabled()) {
            while (headers.hasMoreElements()) {
                String headerName = (String) headers.nextElement();
                log.debug("HTTP_METHOD NAMES: " + headerName + ",value: " + request.getHeader(headerName));
            }
        }
        
        log.info("HTTP_METHOD X-AUTH-TOKEN: " + request.getHeader("x-auth-token"));
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


}

