package com.vp.tw.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TobePickedShippingInfoRequestDto {

	int page = 1;
	int per_page = 1;
	String xmdgdocno;
	String expShipEndDate;
	String expShipStartDate;
	String xmdg005;
	String xmdg028;


}
