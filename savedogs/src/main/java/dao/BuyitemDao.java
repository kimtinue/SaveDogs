package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BuyItemMapper;
import logic.BuyItem;

@Repository
public class BuyitemDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<String, Object>();
	public void insert(BuyItem buyItem) {
		template.getMapper(BuyItemMapper.class).insert(buyItem);	
	}
	public List<BuyItem> list(int buy_no) {
		param.clear();
		param.put("buy_no", buy_no);
		return template.getMapper(BuyItemMapper.class).select(param);
	}
}
