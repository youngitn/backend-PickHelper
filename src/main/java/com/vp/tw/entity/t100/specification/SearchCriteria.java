/**
 * Created with IntelliJ IDEA.
 * @auther: YangTingCheng
 * @Date: 2020/8/20/下午 01:09
 * @Description:
 */
package com.vp.tw.entity.t100.specification;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author YangTingCheng
 */
@Data
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private String operation;
	private Object value;
	public boolean isOrPredicate(String isOr) {
		// TODO Auto-generated method stub
		return "or".equals(isOr);
	}
}
