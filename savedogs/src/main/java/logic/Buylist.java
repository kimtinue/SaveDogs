package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Buylist {
	private int buy_no;
	private String member_id;
	private Date buy_date;
	private String buy_state;
	private int buy_postcode;
	private String buy_address;
	private String buy_daddress;
	private String buy_tel;
	private Member member;
	private List<BuyItem> itemList = new ArrayList<BuyItem>();
	//getter,setter,toString
	
	public int getBuy_no() {
		return buy_no;
	}
	public long getTotal() {
		long sum = 0;
		for(BuyItem bi :itemList) {
			sum +=bi.getItem().getItem_price() * bi.getItem_each();
		}
		return sum
				;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public String getBuy_state() {
		return buy_state;
	}
	public void setBuy_state(String buy_state) {
		this.buy_state = buy_state;
	}
	public int getBuy_postcode() {
		return buy_postcode;
	}
	public String getBuy_tel() {
		return buy_tel;
	}
	public void setBuy_tel(String buy_tel) {
		this.buy_tel = buy_tel;
	}
	public void setBuy_postcode(int buy_postcode) {
		this.buy_postcode = buy_postcode;
	}
	public String getBuy_address() {
		return buy_address;
	}
	public void setBuy_address(String buy_address) {
		this.buy_address = buy_address;
	}
	public String getBuy_daddress() {
		return buy_daddress;
	}
	public void setBuy_daddress(String buy_daddress) {
		this.buy_daddress = buy_daddress;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	public List<BuyItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<BuyItem> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "Buylist [buy_no=" + buy_no + ", member_id=" + member_id + ", buy_date=" + buy_date + ", buy_state="
				+ buy_state + ", buy_postcode=" + buy_postcode + ", buy_address=" + buy_address + ", buy_daddress="
				+ buy_daddress + ", member=" + member + ", itemList=" + itemList + "]";
	}
}	
