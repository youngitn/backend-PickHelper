package com.vp.tw.exception;

import com.vp.tw.commom.Result;
import com.vp.tw.commom.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:55
 * @Description:
 */
@ControllerAdvice
public class UnityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e) {
        logger.error(e.getMessage(), e);
        System.out.println("UnityExceptionHandler");
        return ResultUtil.error(e.getMessage());
    }
}
