package com.vp.tw.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:54
 * @Description:
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {

        super(msg);
        System.out.println("UnauthorizedException");
    }

    public UnauthorizedException() {
        super();
    }
}
