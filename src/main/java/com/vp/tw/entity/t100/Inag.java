package com.vp.tw.entity.t100;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.vp.tw.entity.t100.embeddedId.InagPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * T100 TABLE inag_t 庫存明細檔項發票來源明細檔
 * 
 * @author USER
 *
 */

@Data
@Entity
@Table(name = "inag_t", schema = "dsdata",
		uniqueConstraints = @UniqueConstraint(name = "unique_name_mail", columnNames = {"inag001","inag003", "inag005", "inag006", "inag007"}))
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InagPK.class)
public class Inag implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 企業編號
	 */
	@Id
	@Column(name = "inagent")
	private String inagent;

	/**
	 * 營運據點
	 */
	@Id
	@Column(name = "inagsite")
	private String inagsite;

	@Id
	@Column(name = "inag001",unique = true,nullable = false)
	private String inag001;// 料件編號
	@Id
	@Column(name = "Inag004")
	private String inag004;// 庫位編號

	@Transient
	private String inag100;// 庫存單位

	@Id
	@Column(name = "inag005")
	private String inag005;// 儲位編號

	@Id
	@Column(name = "inag007")
	private String inag007;// 庫存單位

	@Id
	@Column(name = "inag006",unique = true)
	private String inag006;// 批號

	@Column(name = "inag002")
	private String inag002;// 產品特徵

	@Column(name = "inag003")
	private String inag003;// 庫存管理特徵

	@Column(name = "inag008")
	private String inag008;// 帳面庫存數量

	@Column(name = "inag009")
	private String inag009;// 實際庫存數量

	@Column(name = "inag010")
	private String inag010;// 庫存可用否

	@Column(name = "inag011")
	private String inag011;// MRP可用否

	@Column(name = "inag012")
	private String inag012;// 成本庫否

	@Column(name = "inag013")
	private String inag013;// 揀貨優先序

	@Column(name = "inag014")
	private String inag014;// 最近一次盤點日期

	@Column(name = "inag015")
	private String inag015;// 最後異動日期

	@Column(name = "inag016")
	private String inag016;// 呆滯日期

	@Column(name = "inag017")
	private String inag017;// 第一次入庫日期

	@Column(name = "inag018")
	private String inag018;// No Use

	@Column(name = "inag019")
	private String inag019;// 留置否

	@Column(name = "inag020")
	private String inag020;// 留置原因

	@Column(name = "inag021")
	private String inag021;// 備置數量

	@Column(name = "inag022")
	private String inag022;// No Use

	@Column(name = "inag023")
	private String inag023;// Tag二進位碼

	@Column(name = "inag024")
	private String inag024;// 參考單位

	@Column(name = "inag025")
	private String inag025;// 參考數量

	@Column(name = "inag026")
	private String inag026;// 最近一次檢驗日期

	@Column(name = "inag027")
	private String inag027;// 下次檢驗日期

	@Column(name = "inag028")
	private String inag028;// 留置日期

	@Column(name = "inag029")
	private String inag029;// 留置人員

	@Column(name = "inag030")
	private String inag030;// 留置部門

	@Column(name = "inag031")
	private String inag031;// 留置單號

	@Column(name = "inag032")
	private String inag032;// 基礎單位

	@Column(name = "inag033")
	private String inag033;// 基礎單位數量


}
