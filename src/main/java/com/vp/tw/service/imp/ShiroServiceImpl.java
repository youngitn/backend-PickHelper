package com.vp.tw.service.imp;

import com.vp.tw.service.ShiroService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/09/24/下午 04:03
 * @Description:
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Override
    public Set<String> listRoles(String username) {
        // 从数据库中查询
        return new HashSet<>();
    }

    @Override
    public Set<String> listPermissions(String username) {
        // 从数据库中查询
        return new HashSet<>();
    }
}
