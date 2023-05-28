package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.vworklistMapper;
import logic.Vwork;
import logic.Vworklist;

@Repository
public class VworklistDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, String> param = new HashMap<>();

	public List<Vwork> getMyvworkList(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(vworklistMapper.class).getMyvworkList(id);
	}

	public List<Vworklist> getOnevworklist(String vwork_no) {
		param.clear();
		param.put("vwork_no", vwork_no);
		return template.getMapper(vworklistMapper.class).getOnevworklist(param);
	}

	public List<Map<String,Object>> vworkgraph(String year, String member_id) {
		param.clear();
		param.put("year", year);
		param.put("member_id", member_id);
		return template.getMapper(vworklistMapper.class).vworkgraph(param);
	}

	public List<Vworklist> getYearlist(String id) {
		return template.getMapper(vworklistMapper.class).getYearlist(id);
	}

	public void deleteVworklist(String member_id) {
		template.getMapper(vworklistMapper.class).deleteVworklist(member_id);
	}
}