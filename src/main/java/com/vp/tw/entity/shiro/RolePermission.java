package com.vp.tw.entity.shiro;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 03:50
 * @Description:
 */
@Data
public class RolePermission {
    private Long id;
    private Long roleId;
    private Long permissionId;
}
