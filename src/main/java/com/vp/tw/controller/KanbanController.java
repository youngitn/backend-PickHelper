package com.vp.tw.controller;

import com.vp.tw.entity.t100.Inaa;
import com.vp.tw.entity.t100.Inag;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;
import com.vp.tw.repository.t100.InagDao;
import com.vp.tw.requestdto.TobePickedShippingInfoRequestDto;
import com.vp.tw.responsedto.TobePickedShippingInfoResponseDto;
import com.vp.tw.service.InaaService;
import com.vp.tw.service.InaylService;
import com.vp.tw.service.StockService;
import com.vp.tw.service.TobePickedShippingListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: KanbanController 
* @Description: T100 看板&相關API
* @author ytc
* @date 2020年7月29日 上午9:35:05 
*
 */
@CrossOrigin
@Api(tags = "T100 檢貨輔助工具")
@RestController
@RequestMapping("/kanbanApi")
public class KanbanController {


	@Qualifier("tobePickedShippingListByXmdgdocnoService")
	@Autowired
	private TobePickedShippingListService tobePickedShippingListByXmdgdocnoService;

	@Qualifier("tobePickedShippingListByXmdg005AndXmdg028Service")
	@Autowired
	private TobePickedShippingListService tobePickedShippingListByXmdg005AndXmdg028Service;

	@Autowired
	private InagDao inagDao;

	@Autowired
	private StockService stockService;

	@Autowired
	private InaaService inaaService;
	@Autowired
	private InaylService inaylService;
	private void checkListIsEmpty(List<?> list) throws NotFoundException {
		if (list.isEmpty()) {
			throw new NotFoundException("因data.size() == 0 ,卻依舊對data作取值操作導致錯誤.");
		}
	}


	@ApiOperation(value="*待檢貨出通單清單By出貨通知單單號",notes="根據**出貨通知單單號**取得待檢貨出貨清單 參考cxmr999 ")
	@GetMapping("/getTobePickedShippingInfoListByXmdgdocno")
	public ResponseEntity<TobePickedShippingInfoResponseDto> getTobePickedShippingInfoListByXmdgdocno(
			@ModelAttribute TobePickedShippingInfoRequestDto dto) throws Exception {

		List<TobePickedShippingInfo> data = tobePickedShippingListByXmdgdocnoService.getList(dto);

		checkListIsEmpty(data);

		return ResponseEntity.ok(new TobePickedShippingInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}

	@ApiOperation(value="*待檢貨出通單清單By客戶編號&出貨日期",notes="根據**客戶編號**取得待檢貨出貨清單中所有**出貨日期**之前的明細資料")
	@GetMapping("/getTobePickedShippingInfoListByXmdg005AndXmdg028")
	public ResponseEntity<TobePickedShippingInfoResponseDto> tobePickedShippingListByXmdg005AndXmdg028(
			@ModelAttribute TobePickedShippingInfoRequestDto dto) throws Exception {

		List<TobePickedShippingInfo> data = tobePickedShippingListByXmdg005AndXmdg028Service.getList(dto);

		checkListIsEmpty(data);

		return ResponseEntity.ok(new TobePickedShippingInfoResponseDto(data, dto.getPage(), dto.getPer_page()));

	}



	/**
	 * 取得存貨資訊
	 * 
	 * @param  search
	 * @return ResponseEntity<List<Inag>>
	 * @throws NotFoundException
	 */
	@ApiOperation(value="存貨資訊BY料號",notes="GET query參數使用查詢語言=>  ?search=where:inag001@<料號>,and:inag008!=<庫存數量>,and:inag006:VP,")
	@GetMapping("/getStock")
	public ResponseEntity<List<Inag>> getStockInfo(@RequestParam(value = "search") String search) throws NotFoundException {
		// Prepare Employee key with all available search by keys (6 in my case)

		// Setting remaining 4 fields

		// Create new Employee ans set the search key
//		Inag inag = new Inag();
//		inag.setInag001("4071005001");
//		inag.setInagsite("TWVP");
//		inag.setInagent("100");
//		
//		 InagSpecification spec = 
//			      new InagSpecification(new SearchCriteria("inag008", "!=", "0"));
//		 InagSpecification spec2 = 
//			      new InagSpecification(new SearchCriteria("inag001", "=", "4ZZ050700223"));
//		return ResponseEntity.ok(inagDao.findAll(Specification.where(spec).and(spec2)));

		return ResponseEntity.ok(stockService.getStockInfo(search));

	}

	@ApiOperation(value="庫位清單",notes="取得所有庫位清單")
	@GetMapping("/getLocationList")
	public ResponseEntity<List<Inaa>> getLocationList() throws NotFoundException {

		return ResponseEntity.ok(inaaService.findAll());
	}

	@ApiOperation(value="取得儲位管理碼",notes="取得該庫位編號所屬的儲位管理碼")
	@GetMapping("/getInaa007ByInaa001")
	public ResponseEntity<List<Inaa>> getInaa007ByInaa001(
			@RequestParam(value = "inaa001", defaultValue = "") String inaa001)
			throws NotFoundException {

		return ResponseEntity.ok(inaaService.findAllByInaa001(inaa001));
	}

	@ApiOperation(value="取得儲位管理碼",notes="取得該庫位編號所屬的儲位管理碼")
	@GetMapping("/getInaylMap")
	public ResponseEntity<Map> getInaylMap() throws NotFoundException {

		return ResponseEntity.ok(inaylService.getIanylMap());
	}

}
