package com.qingke.common.beans;

import java.util.List;

/**
 * datagrid数据表数据类
 * @author VIC
 *
 */
public class EUDataGridResult {

	private long total;// 总行数
	private List<?> rows;// 行数据
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
