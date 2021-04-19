package com.vp.tw.entity.t100;

import com.vp.tw.entity.t100.embeddedId.InaaId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/08/25/上午 09:52
 * @Description:
 */
@Data
@Entity
@Table(name = "inaa_t", schema = "dsdata")
@NoArgsConstructor
public class Inaa implements Serializable {

    private static final long serialVersionUID = 1L;


    @EmbeddedId
    InaaId inaaId;

    @Column(name = "inaa007")
    private String inaa007;

}
