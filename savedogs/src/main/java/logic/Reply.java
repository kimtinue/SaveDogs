package logic;

import java.util.Date;

public class Reply {
	private int board_replyno;
	private String board_no;
	private String member_id;
	private String board_comment;
	private Date board_regdate;
	public int getBoard_replyno() {
		return board_replyno;
	}
	public void setBoard_replyno(int board_replyno) {
		this.board_replyno = board_replyno;
	}
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public String getBoard_comment() {
		return board_comment;
	}
	public void setBoard_comment(String board_comment) {
		this.board_comment = board_comment;
	}
	public Date getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(Date board_regdate) {
		this.board_regdate = board_regdate;
	}
	@Override
	public String toString() {
		return "Reply [board_replyno=" + board_replyno + ", board_no=" + board_no + ", member_id=" + member_id
				+ ", board_comment=" + board_comment + ", board_regdate=" + board_regdate + "]";
	}
	
	
}
