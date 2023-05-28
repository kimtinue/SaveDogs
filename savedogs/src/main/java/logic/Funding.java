package logic;


import java.util.Date;

import javax.validation.constraints.Future;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GDJ24
 *
 */
public class Funding {

	private int fund_no;
	private String member_id;
	private String sheltername;
	@NotNull(message="제목을 입력하세요")
	private String fund_subject;
	@NotNull(message="목표금액을 입력하세요")
	private int fund_count;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start_date;
	@NotNull(message="날짜를 선택하세요")
	@Future(message="시작날짜 이후만 가능합니다.")
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date end_date;
	private int restdate; //마감날짜-오늘날짜
	private String fund_pic; //사진 파일 명
	private MultipartFile picture; //사진
	private int complete; 
	public int getFund_no() {
		return fund_no;
	}
	public void setFund_no(int fund_no) {
		this.fund_no = fund_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getSheltername() {
		return sheltername;
	}
	public void setSheltername(String sheltername) {
		this.sheltername = sheltername;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getRestdate() {
		return restdate;
	}
	public void setRestdate(int restdate) {
		this.restdate = restdate;
	}
	public String getFund_pic() {
		return fund_pic;
	}
	public void setFund_pic(String fund_pic) {
		this.fund_pic = fund_pic;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getFund_subject() {
		return fund_subject;
	}
	public void setFund_subject(String fund_subject) {
		this.fund_subject = fund_subject;
	}
	public int getFund_count() {
		return fund_count;
	}
	public void setFund_count(int fund_count) {
		this.fund_count = fund_count;
	}
	public int getComplete() {
		return complete;
	}
	public void setComplete(int complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "Funding [fund_no=" + fund_no + ", member_id=" + member_id + ", sheltername=" + sheltername
				+ ", fund_subject=" + fund_subject + ", fund_count=" + fund_count + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", restdate=" + restdate + ", fund_pic=" + fund_pic + ", picture="
				+ picture + ", complete=" + complete + "]";
	}
}