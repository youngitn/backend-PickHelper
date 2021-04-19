package com.vp.tw.repository.t100;

import com.vp.tw.entity.t100.Inaa;
import com.vp.tw.entity.t100.embeddedId.InaaId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/上午 10:12
 * @Description:
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InaaDaoTest {
    @Autowired
    private InaaDao dao;

    @Test
    public void inaaDaoReadTest(){
        InaaId id = new InaaId();
        id.setInaa001("1105");
        id.setInaaent(100);
        id.setInaasite("TWVP");
        Inaa inaa =new Inaa();
        inaa.setInaaId(id);

        //複合鍵用EXAMPLE吧
        List<Inaa> list = dao.findAll(Example.of(inaa)) ;
        Assertions.assertEquals("5",list.get(0).getInaa007());

    }
}
