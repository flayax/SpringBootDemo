package com.flaya.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP拦截器
 */
@Aspect
@Component
public class HttpAspect {

    private static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 设置切面位置点
     */
    @Pointcut("execution(public * com.flaya.controller..*(..))")
    public void log() {
    }

    /**
     * 方法开始前
     * @param joinPoint
     */
    @Before("log()")
    public void douBefore(JoinPoint joinPoint) {
        logger.info("start...");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        logger.info("url={}", request.getRequestURL());
        // method
        logger.info("method={}", request.getMethod());
        // ip
        logger.info("ip={}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        logger.info("args={}", joinPoint.getArgs());
    }

    /**
     * 方法执行后
     */
    @After("log()")
    public void doAfter() {
        logger.info("end...");
    }

    /**
     * 方法返回值
     * @param object
     */
    @AfterReturning(returning = "object" , pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }
}
