
/**
 * Created with IntelliJ IDEA.
 * @Auther: YangTingCheng
 * @Date: 2020/8/20/上午 11:04
 * @Description:
 */
package com.vp.tw.repository.t100;

import com.vp.tw.entity.t100.Inag;
import com.vp.tw.entity.t100.Inayl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
/**
 * class_name: Inayl Dao
 * package: com.vp.tw.repository.t100
 * describe: TODO
 * creat_user: wanwt@senthinkcom
 * creat_date: 2020/8/20
 * creat_time: 上午 11:11
**/
@Repository
public interface InaylDao extends JpaRepository<Inayl, String>, JpaSpecificationExecutor<Inayl> {

}
