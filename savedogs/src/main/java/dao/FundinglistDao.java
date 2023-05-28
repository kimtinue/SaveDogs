package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.FundingMapper;
import dao.mapper.FundlistMapper;
import dao.mapper.vworklistMapper;
import logic.Funding;
import logic.Fundinglist;

@Repository
public class FundinglistDao {
	
	@Autowired
	private SqlSessionTemplate template;
	Map<String, Object> param = new HashMap<>();
	
	public List<Fundinglist> list(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(FundlistMapper.class).list(id);
	}

	public List<Fundinglist> endlist(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(FundlistMapper.class).endlist(id);
	}

	public List<Fundinglist> getOnefundlist(String fund_no) {
		param.clear();
		param.put("fund_no", fund_no);
		return template.getMapper(FundlistMapper.class).getOnefundlist(param);
	}

	public void applyinsert(Fundinglist fundinglist) {
		template.getMapper(FundlistMapper.class).applyinsert(fundinglist);
	}

	public List<Fundinglist> getfundYearlist(String id) {
		return template.getMapper(FundlistMapper.class).getfundYearlist(id);
	}

	public List<Map<String,Object>> fundgraph(String year, String member_id) {
		param.clear();
		param.put("year", year);
		param.put("member_id", member_id);
		return template.getMapper(FundlistMapper.class).fundgraph(param);
	}

	public void deleteFundlist(String member_id) {
		template.getMapper(FundlistMapper.class).deleteFundlist(member_id);
	}
}