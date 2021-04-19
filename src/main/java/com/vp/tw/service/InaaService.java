package com.vp.tw.service;

import com.vp.tw.entity.t100.Inaa;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/上午 10:51
 * @Description:
 */
public interface InaaService {
    public List<Inaa> findAll();
    Map getInaaMap();
    List<Inaa> findAllByInaa001(String inaa001);
}
