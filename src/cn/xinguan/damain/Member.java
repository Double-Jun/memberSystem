package cn.xinguan.damain;

import java.util.Date;

public class Member {
	/******* ��Ա��Ȼ��Ϣ *******/
	private String id; // ���
	private String name; // ����
	private String gender; // �Ա�
	private String nation; // ����
	private Date birthday; // ��������
	private String IDcard; // ���֤��
	private String address; // ����
	private String telphone; // ��ϵ��ʽ
	private String schoolTag; // ����
	private String marriage; // ����״��
	private String photo; // ��Ƭ
	private String depName; // �������� ���

	/***** ��Ա���� *****/
	private String politics; // ������ò
	private String profession; // �ִ���רҵ����
	private String degree; // ѧ��
	private Date workingTime; // ����ʱ��
	private String workStatus; // ����״̬
	private String position; // ְ��
	private Date startTime; // ��Уʱ��
	private String partyPosition; // ����ְ��

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
