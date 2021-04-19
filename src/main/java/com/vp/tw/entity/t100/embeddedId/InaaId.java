package com.vp.tw.entity.t100.embeddedId;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 *
 * @author YangTingCheng
 *
 * @Date: 2020/08/25/上午 11:15
 * @Description:
 */
@Data
@Embeddable
@NoArgsConstructor
public class InaaId  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "inaaent")
     int inaaent;

    @Column(name = "inaasite")
     String inaasite;

    @Column(name = "inaa001")
     String inaa001;
}
