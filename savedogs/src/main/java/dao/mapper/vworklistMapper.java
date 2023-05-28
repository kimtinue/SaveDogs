package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import logic.Vwork;
import logic.Vworklist;

public interface vworklistMapper {
	@Select("SELECT a.vwork_no, a.vwork_id, a.vwork_date, shelter.shelter_no, shelter.shelter_name, shelter.shelter_address"
			+ " FROM (SELECT vl.vwork_no, vl.vwork_id, vl.vwork_date, vl.vwork_tel, v.shelter_no FROM vwork v, vworklist vl WHERE v.vwork_no = vl.vwork_no AND vl.vwork_id=#{id}) a, shelter"
			+ " WHERE a.shelter_no=shelter.shelter_no")
	List<Vwork> getMyvworkList(String id);

	@Select("SELECT v.vwork_id, v.vwork_tel, m.member_name FROM vworklist v, member m WHERE vwork_no=#{vwork_no} AND v.vwork_id = m.member_id order by vwork_date desc")
	List<Vworklist> getOnevworklist(Map<String, String> param);

	@Select("select concat(month(vwork_date),'월') m, count(*) cnt from vworklist where vwork_id=#{member_id} and year(vwork_date)=${year} group by m order by m")
	List<Map<String, Object>> vworkgraph(Map<String, String> param);

	@Select("select year(vwork_date) year from vworklist where vwork_id=#{id} group by year order by year desc")
	List<Vworklist> getYearlist(String id);

	@Delete("delete from vworklist where vwork_id=#{member_id}")
	void deleteVworklist(String member_id);

}