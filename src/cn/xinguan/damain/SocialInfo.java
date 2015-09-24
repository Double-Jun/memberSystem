package cn.xinguan.damain;

import java.util.Date;

public class SocialInfo {
	private String id; // 编号
	private String politics; // 政治面貌
	private String profession; // 现从事专业方向
	private String degree; // 学历
	private Date workingTime; // 工作时间
	private String workStatus; // 工作状态
	private String position; // 职务
	private Date startTime; // 来校时间
	private String partyPosition; // 党政职务

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Date getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(Date workingTime) {
		this.workingTime = workingTime;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getPartyPosition() {
		return partyPosition;
	}

	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}

	public SocialInfo() {

	}

	public SocialInfo(String id, String politics, String profession,
			String degree, Date workingTime, String workStatus,
			String position, Date startTime, String partyPosition) {
		super();
		this.id = id;
		this.politics = politics;
		this.profession = profession;
		this.degree = degree;
		this.workingTime = workingTime;
		this.workStatus = workStatus;
		this.position = position;
		this.startTime = startTime;
		this.partyPosition = partyPosition;
	}

	@Override
	public String toString() {
		return "SocialInfo [id=" + id + ", politics=" + politics
				+ ", profession=" + profession + ", degree=" + degree
				+ ", workingTime=" + workingTime + ", workStatus=" + workStatus
				+ ", position=" + position + ", startTime=" + startTime
				+ ", partyPosition=" + partyPosition + "]";
	}

}
