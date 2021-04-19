package com.vp.tw.entity.shiro;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:48
 * @Description:
 */
@Data
public class Permission {

    private Long id;
    private String permissionCode;
    private String permissionName;

    public Permission() {
    }

}

