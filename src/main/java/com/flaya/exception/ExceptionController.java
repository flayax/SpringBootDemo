package com.flaya.exception;

import com.flaya.domain.param.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
//@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public CommonResp doException(Exception e) {
        log.error("请求出现异常==：" + e);
        // 异常处理逻辑
        CommonResp resp = new CommonResp(-1, "fail", null);
        return resp;
    }

    @ExceptionHandler({Error.class})
    public CommonResp doError(Error e) {
        log.error("请求出现异常==：" + e);
        // 异常处理逻辑
        CommonResp resp = new CommonResp(-100, "fail", null);
        return resp;
    }

}
