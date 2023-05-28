package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.AdminMapper;
import dao.mapper.AdoptMapper;
import logic.Adopt;
import logic.AdoptSign;

@Repository
public class AdoptDao {
	@Autowired
	private SqlSessionTemplate template;
	Map<String, Object> param = new HashMap<>();
	
	public void adoptInsert(AdoptSign a) {
		template.getMapper(AdoptMapper.class).adoptInsert(a);
	}

	public List<AdoptSign> getMyadoptlist(String id) {
		return template.getMapper(AdoptMapper.class).getMyadoptlist(id);
	}

	public List<AdoptSign> getShelteradoptlist(String id) {
		return template.getMapper(AdoptMapper.class).getShelteradoptlist(id);
	}

	public void updateEtc(String dog_no, String state) {
		param.clear();
		param.put("dog_no",dog_no);
		param.put("adopt_etc", state);
		template.getMapper(AdoptMapper.class).updateEtc(param);
	}

	public List<AdoptSign> getAdoptlist() {
		return template.getMapper(AdoptMapper.class).getAdoptList();
	}

}
