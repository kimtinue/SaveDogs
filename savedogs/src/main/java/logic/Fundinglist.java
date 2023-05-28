package logic;
//후원자리스트
import java.util.Date;

public class Fundinglist {

	private int fund_no;
	private String fund_subject;
	private String fund_id;
	private Date fund_date;
	private int fund_cost;
	private String member_email;
	private String year;
	private Date start_date;
	private Date end_date;
	
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getFund_subject() {
		return fund_subject;
	}
	public void setFund_subject(String fund_subject) {
		this.fund_subject = fund_subject;
	}
	public int getFund_no() {
		return fund_no;
	}
	public void setFund_no(int fund_no) {
		this.fund_no = fund_no;
	}
	public String getFund_id() {
		return fund_id;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	public Date getFund_date() {
		return fund_date;
	}
	public void setFund_date(Date fund_date) {
		this.fund_date = fund_date;
	}
	public int getFund_cost() {
		return fund_cost;
	}
	public void setFund_cost(int fund_cost) {
		this.fund_cost = fund_cost;
	}

	@Override
	public String toString() {
		return "Fundinglist [fund_no=" + fund_no + ", fund_subject=" + fund_subject + ", fund_id=" + fund_id
				+ ", fund_date=" + fund_date + ", fund_cost=" + fund_cost + "]";
	}
}