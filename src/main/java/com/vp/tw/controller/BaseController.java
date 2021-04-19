package com.vp.tw.controller;

import com.vp.tw.commom.Result;
import com.vp.tw.exception.UnauthorizedException;
import org.apache.shiro.authz.AuthorizationException;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/10/05/下午 03:08
 * @Description:
 */
public class BaseController {
    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public Object authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            // 输出JSON
            Map<String, Object> resp = new HashMap<String, Object>();
            Result result = new Result();
            result.setCode(2);
            result.setMsg("無權限");
            resp.put("result",result);
            writeJson(resp, response);
            return null;
        } else {
            System.out.println("else");
            return "redirect:/tologin";
        }
    }

    /**
     * 输出JSON
     */
    private void writeJson(Map<String, Object> resp, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSONObject.toJSONString(resp));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static class WebUtilsPro {

        /**
         * 是否是Ajax请求
         *
         */
        public static boolean isAjaxRequest(HttpServletRequest request) {
            String requestType = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestType)) {
                System.out.println("----------------"+requestType);
                return true;
            } else {
                return false;
            }
        }
    }
}
