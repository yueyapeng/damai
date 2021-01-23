package com.damai.wine.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询结果返回
 *
 * @author yueyp
 * @date 2020-12-15
 * @detail
 * @param <T>
 */
public class PageResult<T> implements Serializable {

	private List<T> records;
	
	private Integer total;

	public List<T> getRecords() {
		return records;
	}

	public Integer getTotal() {
		return total;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public static PageResult emptyPageResult(){
		PageResult result = new PageResult();
		result.setRecords(new ArrayList());
		result.setTotal(0);
		return result;
	}

}
