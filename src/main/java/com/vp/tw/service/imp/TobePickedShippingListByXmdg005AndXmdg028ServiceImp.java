package com.vp.tw.service.imp;

import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.repository.t100.TobePickedShippingListDao;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.service.TobePickedShippingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("tobePickedShippingListByXmdg005AndXmdg028Service")
public class TobePickedShippingListByXmdg005AndXmdg028ServiceImp implements TobePickedShippingListService {
	
	@Autowired
	private TobePickedShippingListDao dao;


	@Override
	public List<TobePickedShippingInfo> getList(TobePickedShippingInfoRequestDto dto) {
		
		
		return dao.getTobePickedShippingListByXmdg005AndXmdg028(dto.getXmdg005(), dto.getXmdg028());
	}

}
