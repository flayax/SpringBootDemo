package com.flaya.exception;


import com.flaya.domain.param.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一异常处理
 */
@Slf4j
@RestController
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // url
//        log.info("url: {}", request.getRequestURL());
        return "";
    }

    @RequestMapping(value = "/error")
//    @ResponseBody
    public Object error(WebRequest webRequest, DefaultErrorAttributes errorAttributes) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        // url
        log.info("url: {}", request.getRequestURL());
        // 获取异常信息
        Map<String, Object> errorMap = errorAttributes.getErrorAttributes(webRequest, true);
        log.error("请求出现异常：" + errorMap.toString());
        log.error("errorPath: {}", errorMap.get("path"));
        // 异常处理逻辑
        CommonResp resp = new CommonResp(-1, "fail", null);
        errorMap.get("path");
        response.setStatus(200);
//        logger.info(errorAttributes.getError(webRequest).getMessage());
        return resp;
    }

}
