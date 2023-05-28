package logic;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<ItemSet> itemSetList = new ArrayList<ItemSet>();
	public List<ItemSet> getItemSetList() {
		return itemSetList;
	}
	public void push(ItemSet itemSet) {
		for(int a = 0 ; a< itemSetList.size() ; a++) {
			if(itemSet.getItem().getItem_no().equals(itemSetList.get(a).getItem().getItem_no())){
				itemSetList.get(a).setItem_each(itemSetList.get(a).getItem_each()+itemSet.getItem_each());
				return;
			}
		}
		itemSetList.add(itemSet);
	}
	public long getTotal() {
		long sum = 0 ;
		for(ItemSet is : itemSetList) {
			sum += is.getItem().getItem_price() * is.getItem_each();
		}
		return sum;
	}
}
