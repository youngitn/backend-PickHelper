package com.vp.tw.exception;

import com.vp.tw.commom.Result;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:53
 * @Description:
 */
@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -3637509466035760684L;

    private Integer code;

    public CustomException(Result<Object> result) {
        super(result.getMsg());
        this.code = result.getCode();
    }
}
