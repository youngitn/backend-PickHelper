package com.vp.tw.service;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 04:01
 * @Description:
 */
public interface ShiroService {
    //取得權限BY username
    Set<String> listPermissions(String username);

    //取得角色BY username
    Set<String> listRoles(String username);
}
