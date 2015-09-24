package cn.xinguan.damain;

public class Department {
	private String depName; // 部门名称
	private String principal; // 部门负责人
	private int sum; // 部门总人数
	private String phone; // 部门电话

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
