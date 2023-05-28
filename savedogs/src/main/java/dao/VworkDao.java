package dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.VworkMapper;
import logic.Board;
import logic.Vwork;
import logic.Vworklist;

@Repository
public class VworkDao {
	@Autowired
	private SqlSessionTemplate template;
	Map<String, Object> param = new HashMap<>();
	
	public int maxno() {
		return template.getMapper(VworkMapper.class).maxno();
	}

	public void insertVwork(Vwork vwork) {
		template.getMapper(VworkMapper.class).insertVwork(vwork);
		
	}

	public HashSet<String> allvwork() {
		return template.getMapper(VworkMapper.class).allvwork();
	}

	public HashSet<String> sheltervwork(String shelter_no) {
		return template.getMapper(VworkMapper.class).sheltervwork(shelter_no);
	}

	public int getNowmem(int Vno) {
		return template.getMapper(VworkMapper.class).getNowmem(Vno);
	}

	public int getListcnt(String date) {
		return template.getMapper(VworkMapper.class).getListcnt(date);
	}

	public Vwork getVwork(String vwork_no) {
		return template.getMapper(VworkMapper.class).getVwork(vwork_no);
	}

	public List<Vwork> getVlist(String date) {
		return template.getMapper(VworkMapper.class).getVlist(date);
	}

	public void vJoin(Vworklist vworklist) {
		template.getMapper(VworkMapper.class).vJoin(vworklist);
		
	}

	public void updateVwork(Vwork vwork) {
		template.getMapper(VworkMapper.class).vUpdate(vwork);
		
	}

	public void deleteVwork(String vwork_no) {
		template.getMapper(VworkMapper.class).vDelete(vwork_no);
		
	}

	public List<Vwork> getwritelist(String id) {
		param.clear();
		param.put("id", id);
		return template.getMapper(VworkMapper.class).getwritelist(id);
	}



}
