package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.FundlistMapper;
import dao.mapper.ItemMapper;
import logic.Item;

@Repository
public class ItemDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<String, Object>();

	public List<Item> list() {
		return template.getMapper(ItemMapper.class).select(null);
	}

	public void insert(@Valid Item item) {
		param.clear();
		int item_no = template.getMapper(ItemMapper.class).maxitem_no();
		item.setItem_no(++item_no+"");
		param.put("item", item);
		template.getMapper(ItemMapper.class).insert(item);		
	}

	public Item selectOne(Integer item_no) {
		param.clear();
		param.put("item_no", item_no);
		return template.getMapper(ItemMapper.class).select(param).get(0);
	}

	public List<Item> bestItem() {
 		return template.getMapper(ItemMapper.class).bestitem();
	}

	public List<Item> getShopall() {
		return template.getMapper(ItemMapper.class).getShopall();
	}

	public void updateState(String item_no, String item_state) {
		param.clear();
		param.put("item_no", item_no);
		param.put("item_state", item_state);
		template.getMapper(ItemMapper.class).updateState(param);
	}

	public void updateitem(@Valid Item item) {
		template.getMapper(ItemMapper.class).UpdateItem(item);		
	}

	public void deleteitem(int item_no) {
		param.clear();
		param.put("item_no", item_no);
		template.getMapper(ItemMapper.class).DeleteItem(param);
	}

	public List<Map<String,Object>> shopgraph(String name) {
		return template.getMapper(ItemMapper.class).shopgraph(name);
	}

	public List<Map<String,Object>> shopallgraph(String year) {
		return template.getMapper(ItemMapper.class).shopallgraph(year);
	}

}
