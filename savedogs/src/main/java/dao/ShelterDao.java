package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.ShelterMapper;
import logic.Shelter;



@Repository
public class ShelterDao {
	@Autowired
	private SqlSessionTemplate template;
	Map<String, Object> param = new HashMap<>();
	
	public Shelter getShelter(String shelter_no) {
		return template.getMapper(ShelterMapper.class).selectShelter(shelter_no);
	}

	public List<Shelter> getShelters(String goo) {
		return template.getMapper(ShelterMapper.class).selectShelters(goo);
	}


	public List<Shelter> getShelterlist() {
		return template.getMapper(ShelterMapper.class).selectShelterlist();
	}

	public List<Shelter> getHaplist() {
		return template.getMapper(ShelterMapper.class).getHaplist();
	}

}
