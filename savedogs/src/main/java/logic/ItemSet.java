package logic;

public class ItemSet {
	private Item item;
	private Integer item_each;
	public ItemSet(Item item, Integer item_each) {
		this.item = item;
		this.item_each= item_each;		
	}
	//getter setter toString
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getItem_each() {
		return item_each;
	}
	public void setItem_each(Integer item_each) {
		this.item_each = item_each;
	}
	@Override
	public String toString() {
		return "ItemSet [item=" + item + ", item_each=" + item_each + "]";
	}
}
