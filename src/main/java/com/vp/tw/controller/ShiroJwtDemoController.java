package com.vp.tw.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vp.tw.commom.Result;
import com.vp.tw.commom.ResultEnum;
import com.vp.tw.exception.CustomException;
import com.vp.tw.shiro.constant.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YangTingCheng
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 04:10
 * @Description:
 */
@CrossOrigin
@Controller
public class ShiroJwtDemoController {

    @RequestMapping("/tologin")
    public String tologin() {
        return "alogin";
    }


    public String logout(HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "alogin";
    }

    /**
     *
     * @Title: login
     * @Description: 登入驗證
     * @param @param username 必填
     * @param @param password 必填
     * @param @param response 回傳客戶端的res 這邊會主動將jwt加入cookie
     * @param @return    設定檔案
     * @return String    返回型別
     * @throws
     */

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_SECRET);
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        String jwt = JWT.create().withClaim("username", username)
                // .withClaim("nickname", Base64Utils.encodeToUrlSafeString("what".getBytes()))
                // // 中文名
                .withClaim("nickname", password)
                .withExpiresAt(date) // 过期时间
                .sign(algorithm);
        response.addCookie(new Cookie("shiro-jwt", jwt));
        //以上都是產JWT並未驗證,執行驗證的部分在下面return redirect:indexpage.html
        //轉址時進入customFilter->customRealm
        return "redirect:indexpage.html";
    }

    @GetMapping("/testRole")
    @RequiresRoles(value = { "role-1","role-2" }, logical = Logical.OR)
    public String testRole() {

        return "testRoles";
    }

    @GetMapping("/testPermission")
    @RequiresPermissions(value = { "perm-5" }, logical = Logical.AND)
    @ResponseBody
    public String testPermission() {
        System.out.println("in testPermission");
        return "testPermission";
    }

    /**
     * 用来处理 shiro filter 中的异常, 在发生异常的时候 forward 到controller, 然后由 controller 的统一异常处理
     */
    @RequestMapping("/exception")
    public void exception(HttpServletRequest request) {
        throw new CustomException(new Result<>(ResultEnum.ERROR.getCode(), (String) request.getAttribute("msg")));
    }
}

