package com.vp.tw.repository.t100;

import com.vp.tw.entity.t100.Inaa;
import com.vp.tw.entity.t100.embeddedId.InaaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/上午 10:04
 * @Description:
 */
@Repository
public interface  InaaDao extends JpaRepository<Inaa, InaaId>, QueryByExampleExecutor<Inaa> {

    /**
     *
     * @param inaa001
     * @return
     */
    //@Query("SELECT I FROM Inaa I WHERE I.inaa001 = :inaa001")
    //public List<Inaa> findByInaaIdInaa001(/*@Param("names")*/ String inaa001);

    //public List<Inaa> findAllByInaaIdInaa001(String inaa001);
}
