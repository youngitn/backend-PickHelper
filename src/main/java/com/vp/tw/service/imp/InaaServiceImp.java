package com.vp.tw.service.imp;

import com.vp.tw.entity.t100.Inaa;
import com.vp.tw.entity.t100.embeddedId.InaaId;
import com.vp.tw.repository.t100.InaaDao;
import com.vp.tw.service.InaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/上午 10:55
 * @Description:
 */
@Service
public class InaaServiceImp implements InaaService {
    @Autowired
    InaaDao dao;


    @Override
    public List<Inaa> findAllByInaa001(String inaa001) {
        InaaId id = new InaaId();
        id.setInaa001(inaa001);
        id.setInaaent(100);
        id.setInaasite("TWVP");
        Inaa inaa =new Inaa();
        inaa.setInaaId(id);
        //複合鍵用EXAMPLE吧
        List<Inaa> list = dao.findAll(Example.of(inaa)) ;
        //List<Inaa> list = dao.findAll() ;
        return list;
    }

    @Override
    public List<Inaa> findAll() {
        return dao.findAll();
    }

    @Override
    public Map<String,String> getInaaMap(){
        Map<String, String> map = new HashMap<>();
        for (Inaa inaa:findAll()) {
            map.put(inaa.getInaaId().getInaa001(),inaa.getInaa007());
        }

        return map;

    }
}
