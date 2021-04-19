package com.vp.tw.service;

import com.vp.tw.entity.t100.Inayl;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/下午 03:06
 * @Description:
 */
public interface InaylService {

    List<Inayl> findAll();

    Map getIanylMap();
}
