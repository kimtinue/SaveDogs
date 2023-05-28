package logic;

import java.util.Date;

public class Fundreply {
   private int fund_replyno ; //cno
   private int fund_no;   //bno
   private String fundreply_id; //writer
   private String fund_comment; //content
   private Date fund_regdate; //reg_date
   
public int getFund_replyno() {
	return fund_replyno;
}
public void setFund_replyno(int fund_replyno) {
	this.fund_replyno = fund_replyno;
}
public int getFund_no() {
	return fund_no;
}
public void setFund_no(int fund_no) {
	this.fund_no = fund_no;
}
public String getFundreply_id() {
	return fundreply_id;
}
public void setFundreply_id(String fundreply_id) {
	this.fundreply_id = fundreply_id;
}
public String getFund_comment() {
	return fund_comment;
}
public void setFund_comment(String fund_comment) {
	this.fund_comment = fund_comment;
}
public Date getFund_regdate() {
	return fund_regdate;
}
public void setFund_regdate(Date fund_regdate) {
	this.fund_regdate = fund_regdate;
}
@Override
public String toString() {
	return "Fundreply [fund_replyno=" + fund_replyno + ", fund_no=" + fund_no + ", fundreply_id=" + fundreply_id
			+ ", fund_comment=" + fund_comment + ", fund_regdate=" + fund_regdate + "]";
}

}
