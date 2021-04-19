package com.vp.tw.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:44
 * @Description:
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 5467074955086481181L;

    private String username;

    private String jsonWebToken;

    public JwtToken(String username, String jsonWebToken) {
        this.username = username;
        this.jsonWebToken = jsonWebToken;
        System.out.println("useing JwtToken");
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return jsonWebToken;
    }

}
