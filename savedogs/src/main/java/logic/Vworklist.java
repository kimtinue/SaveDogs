package logic;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Vworklist {
	private String vwork_no;
	private String vwork_id;
	private Date vwork_date;
	@NotNull(message="전화번호를 입력하세요.")
	private String vwork_tel;
	private String member_name;
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getVwork_no() {
		return vwork_no;
	}
	public void setVwork_no(String vwork_no) {
		this.vwork_no = vwork_no;
	}
	public String getVwork_id() {
		return vwork_id;
	}
	public void setVwork_id(String vwork_id) {
		this.vwork_id = vwork_id;
	}
	public Date getVwork_date() {
		return vwork_date;
	}
	public void setVwork_date(Date vwork_date) {
		this.vwork_date = vwork_date;
	}
	public String getVwork_tel() {
		return vwork_tel;
	}
	public void setVwork_tel(String vwork_tel) {
		this.vwork_tel = vwork_tel;
	}
	@Override
	public String toString() {
		return "Vworklist [vwork_no=" + vwork_no + ", vwork_id=" + vwork_id + ", vwork_date=" + vwork_date
				+ ", vwork_tel=" + vwork_tel + "]";
	}
}
