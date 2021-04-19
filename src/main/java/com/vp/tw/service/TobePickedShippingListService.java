package com.vp.tw.service;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;

import java.util.List;

/**
 * @author YangTingCheng
 */
public interface TobePickedShippingListService {
	List<TobePickedShippingInfo> getList(TobePickedShippingInfoRequestDto dto);
}


