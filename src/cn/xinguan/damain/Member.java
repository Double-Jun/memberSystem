package cn.xinguan.damain;

import java.util.Date;

public class Member {
	/******* 会员自然信息 *******/
	private String id; // 编号
	private String name; // 姓名
	private String gender; // 性别
	private String nation; // 民族
	private Date birthday; // 出生日期
	private String IDcard; // 身份证号
	private String address; // 籍贯
	private String telphone; // 联系方式
	private String schoolTag; // 邮箱
	private String marriage; // 婚姻状况
	private String photo; // 照片
	private String depName; // 部门名称 外键

	/***** 会员档案 *****/
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIDcard() {
		return IDcard;
	}

	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getSchoolTag() {
		return schoolTag;
	}

	public void setSchoolTag(String schoolTag) {
		this.schoolTag = schoolTag;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
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

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", nation=" + nation + ", birthday=" + birthday + ", IDcard="
				+ IDcard + ", address=" + address + ", telphone=" + telphone
				+ ", schoolTag=" + schoolTag + ", marriage=" + marriage
				+ ", photo=" + photo + ", depName=" + depName + ", politics="
				+ politics + ", profession=" + profession + ", degree="
				+ degree + ", workingTime=" + workingTime + ", workStatus="
				+ workStatus + ", position=" + position + ", startTime="
				+ startTime + ", partyPosition=" + partyPosition + "]";
	}

}
