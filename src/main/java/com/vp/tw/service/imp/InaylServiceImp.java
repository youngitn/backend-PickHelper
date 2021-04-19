package com.vp.tw.service.imp;

import com.vp.tw.entity.t100.Inayl;
import com.vp.tw.repository.t100.InaylDao;
import com.vp.tw.service.InaylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/下午 03:10
 * @Description:
 */
@Service
public class InaylServiceImp implements InaylService {
    @Autowired
    InaylDao inaylDao;



    @Override
    public List<Inayl> findAll() {
        Inayl inayl = new Inayl();
        inayl.setInayl002("zh_TW");

        List<Inayl> list = inaylDao.findAll(Example.of(inayl));

        return list;
    }

    @Override
    public Map<String,String> getIanylMap(){


        Map<String, String> map = findAll().stream().collect(Collectors.toMap(Inayl::getInayl001, Inayl::getInayl003));

        return map;

    }
}
