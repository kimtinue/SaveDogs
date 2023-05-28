package logic;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Vwork {
	private int vwork_no;
	private String shelter_no;
	private String member_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vwork_date;
	@Min(value = 1, message = "1명 이상 입력해주세요")
	private int vwork_member;
	@NotEmpty(message="설명을 입력해주세요")
	private String vwork_content;
	private String shelter_name;
	private String shelter_address;
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public int getVwork_no() {
		return vwork_no;
	}
	public void setVwork_no(int vwork_no) {
		this.vwork_no = vwork_no;
	}
	public String getShelter_no() {
		return shelter_no;
	}
	public void setShelter_no(String shelter_no) {
		this.shelter_no = shelter_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getVwork_date() {
		return vwork_date;
	}
	public void setVwork_date(Date vwork_date) {
		this.vwork_date = vwork_date;
	}
	public int getVwork_member() {
		return vwork_member;
	}
	public void setVwork_member(int vwork_member) {
		this.vwork_member = vwork_member;
	}
	public String getVwork_content() {
		return vwork_content;
	}
	public void setVwork_content(String vwork_content) {
		this.vwork_content = vwork_content;
	}
	@Override
	public String toString() {
		return "Vwork [vwork_no=" + vwork_no + ", shelter_no=" + shelter_no + ", member_id=" + member_id + ", vwork_date="
				+ vwork_date + ", vwork_member=" + vwork_member + ", vwork_content=" + vwork_content + "]";
	}
}