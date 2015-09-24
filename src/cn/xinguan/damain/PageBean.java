package cn.xinguan.damain;

import java.util.List;

/**
 * 分页实体类
 * 
 * @author MingJun Chen
 * 
 * @param <T>
 */
public class PageBean<T> {
	public static final int PAGE_SIZE = 10; // 每页展示的条数 系统规定
	private int totalRecord; // 总记录条数 ---- select count(*) from table
	private int pNum; // 当前页码数 ------- 由前台传递过来
	private int totalPage; // 总的页数 ----- 计算得到
	private List<T> listBean; // 封装的结果集 -- limit (pNum-1) *
								// PageBean.getPageSize;
	private String url; // 访问的地址及参数

	public int getTotalPage() {
		return totalPage = (totalRecord + PAGE_SIZE - 1) / PAGE_SIZE;
	}

	public int getPageSize() {
		return PAGE_SIZE;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public List<T> getListBean() {
		return listBean;
	}

	public void setListBean(List<T> listBean) {
		this.listBean = listBean;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PageBean [totalRecord=" + totalRecord + ", pNum=" + pNum
				+ ", totalPage=" + totalPage + ", listBean=" + listBean
				+ ", url=" + url + "]";
	}

}
