package com.flaya.exception;

import com.flaya.domain.Result;
import com.flaya.enums.ResultEnum;
import com.flaya.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理
 */
//@ControllerAdvice
public class ExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        if(e instanceof GirlException) {
            logger.error("【自定义异常】", e);
            return ResultUtil.error(((GirlException) e).getCode(), e.getMessage());
        }
        logger.error("【系统异常】", e);
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
    }
}
