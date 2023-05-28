package logic;

public class BuyItem {
	private int buy_no;
	private int seq;
	private String item_no;
	private int item_each;
	private Item item;
	public BuyItem() {}
	public BuyItem(int buy_no, int seq, ItemSet itemSet) {
		this.buy_no=buy_no;
		this.seq =seq;
		this.item = itemSet.getItem();
		this.item_no = item.getItem_no();
		this.item_each = itemSet.getItem_each();
	}
	//getter,setter,toString
	public int getBuy_no() {
		return buy_no;
	}
	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public int getItem_each() {
		return item_each;
	}
	public void setItem_each(int item_each) {
		this.item_each = item_each;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "BuyItem [buy_no=" + buy_no + ", seq=" + seq + ", item_no=" + item_no + ", item_each=" + item_each
				+ ", item=" + item + "]";
	}
}
