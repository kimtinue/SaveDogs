package logic;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Member {
	@Size(min=4, message="아이디는 4자 이상으로 입력하세요.")
	private String member_id;
	@NotNull(message="이름을 입력하세요.")
	private String member_name;
	@Size(min=4, message="비밀번호는 4자리 이상으로 입력하세요.")
	private String member_pass;
	private String member_pass2;
	@NotEmpty(message="이메일을 입력하세요.")
	@Email(message="email형식에 맞게 입력하세요.")
	private String member_email;
	@NotNull(message="전화번호를 입력하세요.")
	private String member_tel;
	private int member_postcode;
	private String member_address;
	private String member_daddress;
	@Past(message="생일은 과거 날짜만 가능합니다.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date member_birthday;
	private boolean del_df;
	private String shelter_no;
	private String file1;
	private String file2;
	private MultipartFile f1;
	private MultipartFile f2;
	private int member_type;
	private int member_auth;
	private String shelter_name;
	private String shelter_address;
	
	//getter,setter
	public String getMember_id() {
		return member_id;
	}
	public String getShelter_address() {
		return shelter_address;
	}
	public void setShelter_address(String shelter_address) {
		this.shelter_address = shelter_address;
	}
	public String getShelter_name() {
		return shelter_name;
	}
	public void setShelter_name(String shelter_name) {
		this.shelter_name = shelter_name;
	}
	public int getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(int member_auth) {
		this.member_auth = member_auth;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	public String getMember_pass2() {
		return member_pass2;
	}
	public void setMember_pass2(String member_pass2) {
		this.member_pass2 = member_pass2;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public int getMember_postcode() {
		return member_postcode;
	}
	public void setMember_postcode(int member_postcode) {
		this.member_postcode = member_postcode;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_daddress() {
		return member_daddress;
	}
	public void setMember_daddress(String member_daddress) {
		this.member_daddress = member_daddress;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}
	public boolean isDel_df() {
		return del_df;
	}
	public void setDel_df(boolean del_df) {
		this.del_df = del_df;
	}
	public String getShelter_no() {
		return shelter_no;
	}
	public void setShelter_no(String shelter_no) {
		this.shelter_no = shelter_no;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public MultipartFile getF1() {
		return f1;
	}
	public void setF1(MultipartFile f1) {
		this.f1 = f1;
	}
	public MultipartFile getF2() {
		return f2;
	}
	public void setF2(MultipartFile f2) {
		this.f2 = f2;
	}
	public int getMember_type() {
		return member_type;
	}
	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_name=" + member_name + ", member_pass=" + member_pass
				+ ", member_pass2=" + member_pass2 + ", member_email=" + member_email + ", member_tel=" + member_tel
				+ ", member_postcode=" + member_postcode + ", member_address=" + member_address + ", member_daddress="
				+ member_daddress + ", member_birthday=" + member_birthday + ", del_df=" + del_df + ", shelter_no="
				+ shelter_no + ", file1=" + file1 + ", file2=" + file2 + ", f1=" + f1 + ", f2=" + f2 + ", member_type="
				+ member_type + "]";
	}
}