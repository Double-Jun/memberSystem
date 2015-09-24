package cn.xinguan.damain;

import java.util.Date;

/**
 * ��Ȼ��Ϣʵ����
 * 
 * @author MingJun Chen
 * 
 */
public class NatureInfo {
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
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NatureInfo [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", nation=" + nation + ", birthday=" + birthday + ", IDcard="
				+ IDcard + ", address=" + address + ", telphone=" + telphone
				+ ", schoolTag=" + schoolTag + ", marriage=" + marriage
				+ ", photo=" + photo + ", depName=" + depName + ", status="
				+ status + "]";
	}

	public NatureInfo() {

	}

	public NatureInfo(String id, String name, String gender, String nation,
			Date birthday, String iDcard, String address, String telphone,
			String schoolTag, String marriage, String photo, String depName,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.nation = nation;
		this.birthday = birthday;
		IDcard = iDcard;
		this.address = address;
		this.telphone = telphone;
		this.schoolTag = schoolTag;
		this.marriage = marriage;
		this.photo = photo;
		this.depName = depName;
		this.status = status;
	}

}
