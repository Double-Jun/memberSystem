package cn.xinguan.damain;

import java.util.List;

/**
 * ��ҳʵ����
 * 
 * @author MingJun Chen
 * 
 * @param <T>
 */
public class PageBean<T> {
	public static final int PAGE_SIZE = 10; // ÿҳչʾ������ ϵͳ�涨
	private int totalRecord; // �ܼ�¼���� ---- select count(*) from table
	private int pNum; // ��ǰҳ���� ------- ��ǰ̨���ݹ���
	private int totalPage; // �ܵ�ҳ�� ----- ����õ�
	private List<T> listBean; // ��װ�Ľ���� -- limit (pNum-1) *
								// PageBean.getPageSize;
	private String url; // ���ʵĵ�ַ������

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
