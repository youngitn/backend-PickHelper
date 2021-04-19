package com.vp.tw.service;

import com.vp.tw.entity.t100.Inag;

import java.util.List;
/**
 * 
* @ClassName: StockService 
* @Description: 庫存相關服務類別 
* @author ytc
* @date 2020年7月29日 上午9:33:25 
*
 */
public interface StockService {
	/**
	 * 
	* @Title: getStockInfoUseExample 
	* @Description: 使用Example的方式做JPA查詢 
	* @param @return    設定檔案 
	* @return List<Inag>    返回型別 
	* @throws
	 */
	//List<Inag> getStockInfoUseExample(String search);
	
	/**
	 * 
	* @Title: getStockInfo 
	* @Description: 使用 Specification方式解析字串做get的類SQL查詢
	* @param @param search
	* @param @return    設定檔案 
	* @return List<Inag>    返回型別 
	* @throws
	 */
	List<Inag> getStockInfo(String search);
}
