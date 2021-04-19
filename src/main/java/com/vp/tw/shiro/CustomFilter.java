package com.vp.tw.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vp.tw.commom.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:46
 * @Description:
 */
public class CustomFilter extends AccessControlFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 這邊沒有邏輯 可視為生命週期之一 某個部分可以在哪階段先做之類的
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        System.out.println("IN isAccessAllowed");

        //false才會進入onAccessDenied
        return false;
    }


    /**
     * 方法的字面意思是被Denied
     * 但在這邊的邏輯主要是
     * 如果request的jsonWebToken不為空
     * 則從jsonWebToken Decode後分離出username等資訊再包進去[JwtToken token]
     * 送去給realm進行驗證
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("访问的URI: {}", ((HttpServletRequest) request).getRequestURI());
        }
        String jsonWebToken = CommonUtils.getJsonWebToken((HttpServletRequest) request);
        String username = "";
        String nickname = "";
        if (StringUtils.isBlank(jsonWebToken)) {
            jsonWebToken = "";
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>no cookie");
        } else {
            // 解码 jwt
            DecodedJWT decodeJwt = JWT.decode(jsonWebToken);
            username = decodeJwt.getClaim("username").asString();
            nickname = decodeJwt.getClaim("nickname").asString();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + username);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + nickname);
        }

        //如jsonWebToken="" 將丟出例外
        JwtToken token = new JwtToken(username, jsonWebToken);
        try {
            // 交给自定义realm进行jwt验证和对应角色,权限的查询
            //進入customRealm 當中出現例外則由此接收並顯示
            getSubject(request, response).login(token);
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "认证失败");
            // 转发给指定的 controller, 进行统一异常处理
            ((HttpServletRequest) request).getRequestDispatcher("/exception").forward(request, response);
            return false;
        }
        return true;
    }

}
