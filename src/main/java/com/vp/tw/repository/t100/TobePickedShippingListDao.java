package com.vp.tw.repository.t100;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TobePickedShippingListDao extends JpaRepository<Isaf, String> {

	String QUERY_STR = "SELECT DISTINCT " + "a.xmdhsite," // 營運據點
			+ "a.xmdhent, "// 企業編號
			+ "(select ooag011 from dsdata.ooag_t where ooag001 = c.xmdg002 AND ooagent = 100) xmdg002, "// 業務人員
			+ "a.xmdh001 xmdh001, "// 訂單單號
			+ "a.xmdhseq xmdhseq, " // 項次
			+ "c.xmdg005 xmdg005, " // 訂單客戶
			+ "c.xmdg028 xmdg028, " // 預計出貨日期
	 		+ "c.xmdgdocdt xmdgdocdt,"   //出通單日期
			+ "a.xmdh006 xmdh006, " // 料件編號
			+ "d.imaal003 imaal003, " // 品名
			+ "d.imaal004 imaal004, " // 規格
			+ "a.xmdh007 xmdh007, " // 產品特徵
			+ "a.xmdhdocno xmdhdocno," //出通單號
			+ "a.xmdh016 xmdh016, " // 申請出通數量
			+ "a.xmdh017 xmdh017," // 實際出通數量"
			+ "a.xmdh030 xmdh030," // 已轉出貨量"

			+ "a.xmdh015 xmdh015," // 出貨單位
			+ "X.sfec001 sfec001," // 工單單號
			+ "X.sfaa050, " // 已入庫合格量
			+ "X.sfec012, " // 庫位
			+ "(select  (SELECT inab003 FROM dsdata.INAB_T WHERE inab002 = inag005) from dsdata.inag_t where inag001 = a.xmdh006 and inag006 = a.xmdh001 AND inag008 > 0) sfec013, " // 儲位
			+ "X.sfec014, " // 批號
			//+ "X.inag008, " // 帳面庫存數量
			+ "b.xmda033, " // 客戶訂購單號
			+ "e.pmaal004, " // 交易對象簡稱
			/**************20200930增加件數(l_box)資訊 ***************/
			+ "("
			+ " SELECT CASE "
			+ " WHEN ( "
				+ "SELECT imaa004 "
				+ "FROM dsdata.imaa_t "
				+"WHERE imaaent = xmdhent "
				+"AND imaa001 = xmdh006 "
			+") <> 'E' "
			+"THEN CEIL(xmdh016 / xmam008) "
			+"ELSE 0 "
			+"END "
			+"FROM dsdata.xmdc_t, dsdata.xmam_t "
			+"WHERE xmdcent = xmdhent "
			+"AND xmament = xmdhent "
			+"AND xmdhent = xmament "
			+"AND xmdhseq = xmdhseq "
			+"AND xmdhdocno = xmdhdocno "
			+"AND xmdcdocno = xmdh001 "
			+"AND xmdcseq = xmdh002 "
			+"AND xmdcud001 = xmam001 "
				+ ") l_box "
			+ " FROM dsdata.xmdh_t a"
			+ " LEFT JOIN dsdata.xmda_t b ON xmdaent = xmdhent"
			+ " AND xmdadocno = xmdh001" + " AND xmdasite = xmdhsite"
			+ " LEFT JOIN (SELECT DISTINCT sfecent,sfecsite,sfec001,inag008,sfec014,sfec012,sfaa050,sfaa023,sfaa022,sfaa006,sfaa007,sfaadocno "
			+ " FROM dsdata.sfec_t, dsdata.inag_t,dsdata.sfaa_t "
			+ " WHERE sfecent = inagent  "
			+ " AND sfecsite = inagsite "
			+ " AND inag001 = sfec005 "
			+ " AND inag002 = sfec006 "
			+ " AND inag004 = sfec012 "
			+ " AND inag005 = sfec013 "
			+ " AND inag006 = sfec014"
			+ " AND sfaasite = sfecsite "
			+ " AND sfaaent = sfecent "
			+ " AND sfaadocno = sfec001) X "
			+ " ON xmdaent = X.sfecent  "
			+ " AND xmdhsite = X.sfecsite "
			/********20200619優化SQL*************/
			+ "AND ((X.sfaa006 = xmdh001 AND X.sfaa007 = xmdh002 ) OR (X.sfaa022 = xmdh001 AND X.sfaa023 = xmdh002))"
//			+ " AND EXISTS (SELECT sfaa007 FROM dsdata.sfaa_t WHERE sfaaent = xmdhent AND sfec001 = sfaadocno AND ((sfaa006 = xmdh001 AND sfaa007 = xmdh002 )"
//			+ " OR (sfaa022 = xmdh001 AND sfaa023 = xmdh002))) "
			/**********************************/
			+ " , dsdata.xmdg_t c, dsdata.imaal_t d, dsdata.pmaal_t e ";
			
	@Query(value = QUERY_STR + " WHERE xmdgdocno = xmdhdocno" + " AND xmdgent = 100 " // 企業編號
			+ " AND imaalent = xmdhent " // 企業編號
			+ " AND imaal001 = xmdh006 " // 料件編號
			+ " AND imaal002 = 'zh_TW' "
			+ " AND pmaalent = xmdhent " // 企業編號
			+ " AND pmaal001 = xmdg005 " // 訂單客戶
			+ " AND pmaal002 = 'zh_TW' "  // 後續參數化
			+ " AND xmdhsite = 'TWVP' " 
			+ " AND xmdh056 > xmdh030 "
			+ " AND xmdgdocno = :XMDGDOCNO "
			+ " ORDER BY xmdhdocno , xmdhseq ,xmdg028"
			, nativeQuery = true)
	List<TobePickedShippingInfo> getTobePickedShippingListByXmdgdocno(@Param("XMDGDOCNO") String xmdgdocno);

	/**
	 * 根據客戶編號查詢所指定出貨日期之前所有該客戶的出通單明細項目
	 * @param xmdg005
	 * @param xmdg028
	 * @return
	 */
	@Query(value = QUERY_STR + " WHERE xmdgdocno = xmdhdocno" + " AND xmdgent = 100 " // 企業編號
			+ " AND imaalent = xmdhent " // 企業編號
			+ " AND imaal001 = xmdh006 " // 料件編號
			+ " AND imaal002 = 'zh_TW' "
			+ " AND pmaalent = xmdhent " // 企業編號
			+ " AND pmaal001 = xmdg005  " // 訂單客戶
			+ " AND pmaal002 = 'zh_TW' "  // 後續參數化
			+ " AND xmdhsite = 'TWVP' "
			+ " AND xmdh056 > xmdh030 "
			+ " AND xmdg028 <= to_date(:XMDG028,'yyyy-mm-dd') "
			+ " AND xmdg005 = :XMDG005 "
			+ " ORDER BY xmdhdocno , xmdhseq ,xmdg028"
			, nativeQuery = true)
	List<TobePickedShippingInfo> getTobePickedShippingListByXmdg005AndXmdg028(
			@Param("XMDG005") String xmdg005,
			@Param("XMDG028") String xmdg028
	);

}
