package com.vp.tw.commom;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:57
 * @Description:
 */
public class ResultUtil {
    public static <T> Result<T> success(T data){
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(){
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> error(T data){
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
    }
}
