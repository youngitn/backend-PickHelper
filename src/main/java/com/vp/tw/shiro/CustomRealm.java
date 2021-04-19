package com.vp.tw.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vp.tw.service.ShiroService;
import com.vp.tw.shiro.constant.Constant;
import com.vp.tw.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:45
 * @Description:
 */
public class CustomRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private JwtUtil jwtUtil;

    public CustomRealm() {
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持 JwtToken
        return token instanceof JwtToken;
    }

    //身分認證過關的話將會進入這裡取得角色與權限
    //如有多權限或多身分這部分會執行多次
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("————權限認證 [ roles、permissions]————");
        String username = null;
        // 取得username
        if (principals != null) {
            username = (String) principals.getPrimaryPrincipal();
        }

        // 取得認證資訊Permissions BY username
        // 取得username從 DB select出perm and role 並擺進去
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("in doGetAuthorizationInfo >>>>" + username);
        //模擬從資料庫get Permissions By UserName
        Set<String> permissionSet = shiroService.listPermissions(username);
        permissionSet.add("perm-1"); // 造数据, 假装是从数据库查出来的
        permissionSet.add("perm-2");
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        // 模擬從資料庫get Roles By UserName
        Set<String> roleSet = shiroService.listRoles(username);
        roleSet.add("role-1"); // 造数据, 假装是从数据库查出来的
        roleSet.add("role-2");
        simpleAuthorizationInfo.setRoles(roleSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 功能： 用来进行身份认证，也就是说验证用户输入的账号和密码是否正确，获取身份验证信息，错误抛出异常 在上面doGetAuthorizationInfo()之前執行
     *
     * @param token 用户身份信息 token 包含username和JWT等資訊
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("AuthenticationInfo 身分驗證");
        String username = (String) token.getPrincipal();
        String jsonWebToken = (String) token.getCredentials();
//		Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_SECRET);
//		JWTVerifier verifier = JWT.require(algorithm).build();

        //token中有來自cookie的JWT = jsonWebToken ,也有username,
        //驗證時將拿username+secret組新JWT和來自coolie的jsonWebToken做比較
        try {
            //如果不是用JWT驗證 只需在此比對帳號
            boolean isPass = jwtUtil.verify(jsonWebToken, username, Constant.JWT_SECRET);
            System.out.println("isPass----------------->"+isPass);

            //新舊jWT 驗證過關
            if (isPass) {
                logger.debug("*********************身分驗證PASS ***********************");
                System.out.println("*********************身分驗證PASS ***********************");
            }
            //沒過關 回傳null值 使其拋出例外
            else {
                logger.debug("*********************身分驗證失敗**********************");
                System.out.println("*********************身分驗證失敗 **********************");
                jsonWebToken = "invalid jwt";
                //return null;

            }

        } catch (JWTVerificationException e) {
            System.out.println("JWTVerificationException");
            jsonWebToken = "invalid jwt";

        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, jsonWebToken,
                getName());

        //以下是帳號密碼認證作法():非JWT
        // 通过username从数据库中查找 UserInfo 对象
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        /*
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (null == userInfo) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo, // 用户名
                userInfo.getPassword(), // 密码
                ByteSource.Util.bytes(userInfo.getSalt()), // salt=username+salt
                getName() // realm name
        );*/
        return simpleAuthenticationInfo;
    }

}
