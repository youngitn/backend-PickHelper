package com.vp.tw.commom;

import org.apache.commons.lang3.ArrayUtils;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:55
 * @Description:
 */
public class CommonUtils {
    public static String getJsonWebToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if ("shiro-jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
