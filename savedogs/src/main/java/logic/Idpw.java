package logic;

public class Idpw {
	private String member_id;
	private String member_pass;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	@Override
	public String toString() {
		return "Idpw [member_id=" + member_id + ", member_pass=" + member_pass + "]";
	}
}
