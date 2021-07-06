package com.commu.user.vo;

public class UserVO {

	private String id;
	private String password;
	private String name;
	private String role;

	private String postcode;
	private String roadAddress;
	private String jubunAddress;
	private String detailAddress;
	private String extraAddress;
	private String isreport;
	private int pno;
	
	//칼럼 추가(06.16)
	private int phone;
	private String city;
	private int age;
	
	
	private String pageNum;
	private int startRow;
	private int endRow;
	
	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}


	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	private String grade;

	public String getIsreport() {
		return isreport;
	}

	public void setIsreport(String isreport) {
		this.isreport = isreport;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJubunAddress() {
		return jubunAddress;
	}

	public void setJubunAddress(String jubunAddress) {
		this.jubunAddress = jubunAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", jubunAddress=" + jubunAddress
				+ ", isreport=" + isreport + "]";
	}

}
