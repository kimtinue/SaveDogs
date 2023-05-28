package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BuylistMapper;
import logic.Buylist;

@Repository
public class BuylistDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<String, Object>();
	public int getMaxSaleid() {
		return template.getMapper(BuylistMapper.class).maxsaleid();
	}
	public void insert(Buylist buylist) {
		template.getMapper(BuylistMapper.class).insert(buylist);
	}
	public List<Buylist> list(String member_id) {
		param.clear();
		param.put("member_id", member_id);
		return template.getMapper(BuylistMapper.class).select(param);
	}
}
