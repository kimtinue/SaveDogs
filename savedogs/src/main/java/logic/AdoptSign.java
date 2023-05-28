package logic;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class AdoptSign {
	private String member_id;
	private String dog_no;
	private String shelter_no;
	private Date adopt_date;
	private int adopt_etc;
	private String adopt_file;
	private MultipartFile adopt_f;
	private String shelter_address;
	private String shelter_name;

	// getter, setter
	public String getMember_id() {
		return member_id;
	}

	public String getShelter_address() {
		return shelter_address;
	}

	public void setShelter_address(String shelter_address) {
		this.shelter_address = shelter_address;
	}

	public MultipartFile getAdopt_f() {
		return adopt_f;
	}

	public void setAdopt_f(MultipartFile adopt_f) {
		this.adopt_f = adopt_f;
	}

	public String getShelter_name() {
		return shelter_name;
	}

	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getDog_no() {
		return dog_no;
	}

	public void setDog_no(String dog_no) {
		this.dog_no = dog_no;
	}

	public String getShelter_no() {
		return shelter_no;
	}

	public void setShelter_no(String shelter_no) {
		this.shelter_no = shelter_no;
	}

	public Date getAdopt_date() {
		return adopt_date;
	}

	public void setAdopt_date(Date adopt_date) {
		this.adopt_date = adopt_date;
	}

	public int getAdopt_etc() {
		return adopt_etc;
	}

	public void setAdopt_etc(int adopt_etc) {
		this.adopt_etc = adopt_etc;
	}

	public String getAdopt_file() {
		return adopt_file;
	}

	public void setAdopt_file(String adopt_file) {
		this.adopt_file = adopt_file;
	}

	public MultipartFile getF() {
		return adopt_f;
	}

	public void setF(MultipartFile adopt_f) {
		this.adopt_f = adopt_f;
	}

	@Override
	public String toString() {
		return "AdoptSign [member_id=" + member_id + ", dog_no=" + dog_no + ", shelter_no=" + shelter_no
				+ ", adopt_date=" + adopt_date + ", adopt_etc=" + adopt_etc + ", adopt_file=" + adopt_file
				+ ", adopt_f=" + adopt_f + "]";
	}

}
