package com.vp.tw.entity.t100.embeddedId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
* @ClassName: InagPK 
* @Description: 設定Inag複合鍵的類別  
* @author ytc
* @date 2020年7月29日 上午9:30:16 
*
 */
@Data
@Embeddable
@NoArgsConstructor
public class InagPK implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 做關聯之後Id要很注意,關係到資料正確性
	 * 原DB table中沒用到的key可以先省略
	 */
	/**
	 * 企業編號
	 */

	@Column(name = "inagent")
	private String inagent;

	/**
	 * 營運據點
	 */
	@Column(name = "inagsite")
	private String inagsite;

	@Column(name = "inag001",unique = true,nullable = false)
	private String inag001;// 料件編號

	@Column(name = "Inag004")
	private String inag004;// 庫位編號

	@Column(name = "inag005")
	private String inag005;// 儲位編號

	@Column(name = "inag007")
	private String inag007;// 庫存單位

	@Column(name = "inag006",unique = true)
	private String inag006;// 批號
	

}
