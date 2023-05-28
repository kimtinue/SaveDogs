package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Adopt;
import logic.AdoptSign;

public interface AdoptMapper {

	@Insert("insert into adopt "
			+ " (member_id, dog_no, shelter_no, adopt_date, adopt_etc, adopt_file) "
			+ " values (#{member_id}, #{dog_no}, #{shelter_no}, now(), 0, #{adopt_file})")
	void adoptInsert(AdoptSign a);

	@Select("select a.member_id, a.dog_no, a.adopt_date, a.adopt_etc, a.adopt_file, s.shelter_name, s.shelter_address from adopt a, shelter s where a.shelter_no=s.shelter_no and member_id=#{id} order by adopt_date desc")
	List<AdoptSign> getMyadoptlist(String id);

	@Select("select * from adopt" +
			" where shelter_no = (" +
			" select shelter_no from member where member_id=#{id}) order by adopt_date desc")
	List<AdoptSign> getShelteradoptlist(String id);

	@Update("update adopt set adopt_etc=${adopt_etc} where dog_no=#{dog_no}")
	void updateEtc(Map<String, Object> param);

	@Select("select * from adopt")
	List<AdoptSign> getAdoptList();
	
}
