package cn.xinguan.damain;

public class Department {
	private String depName; // ��������
	private String principal; // ���Ÿ�����
	private int sum; // ����������
	private String phone; // ���ŵ绰

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Department [depName=" + depName + ", principal=" + principal
				+ ", sum=" + sum + ", phone=" + phone + "]";
	}

}
