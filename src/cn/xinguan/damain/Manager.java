package cn.xinguan.damain;

import java.util.Date;

public class Manager {
	private String username; // �û��˺�
	private String password; // �û�����
	private String email; // ��¼����
	private Date loginTime; // �ϴε�¼ʱ��
	private String loginIP; // ��¼IP

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	@Override
	public String toString() {
		return "Manager [username=" + username + ", password=" + password
				+ ", email=" + email + ", loginTime=" + loginTime
				+ ", loginIP=" + loginIP + "]";
	}

}
