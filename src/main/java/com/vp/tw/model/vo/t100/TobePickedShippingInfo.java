package com.vp.tw.model.vo.t100;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface TobePickedShippingInfo {

	String getXmdhsite(); // 營運據點siteNo

	String getXmdhent(); // 企業編號entNo

	String getXmdhdocno(); // 單據編號docNo

	String getXmdhseq(); // 項次seq

	String getXmdg005(); // 訂單客戶cusNo

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	Date getXmdg028(); // 預計出貨日期
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	Date getXmdgdocdt(); // 出通單據日期
	
	String getXmdh006(); // 料件編號ProductNo

	String getXmdh001(); // 訂單單號
	
	String getXmdg002(); // 業務人員

	String getImaal003(); // 品名 ProductName

	String getImaal004(); // 規格 spec

	String getXmdh007(); // 產品特徵SignatureNo

	String getXmdh016(); // 申請出通數量ApplyShipNotifyQty
	
	String getXmdh030(); // 已轉出貨量ApplyShipNotifyQty
	
	String getXmdh017(); // 實際出通數量
	
	String getXmdh015(); // 出貨單位ShippingUnit

	String getSfec001(); // 工單單號WorkOrderNo

	String getSfaa050(); // 已入庫合格量QualifiedQty

	String getSfec012(); // 庫位locationNo

	String getSfec013(); // 儲位storageNo

	String getSfec014(); // 批號batchNo

	//String getInag008(); // 帳面庫存數量bookStockQty

	String getXmda033(); // 客戶訂購單號 cusOrderNo

	String getPmaal004(); // 交易對象簡稱cusAbbreviation

	String getL_box(); // 件數

}
