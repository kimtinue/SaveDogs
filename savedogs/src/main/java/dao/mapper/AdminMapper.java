package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import logic.Member;
import logic.Shelter;

public interface AdminMapper {

	@Insert("insert into shelter "
			+ "(shelter_no, shelter_name, shelter_address, shelter_tel) "
			+ " values(#{shelter_no}, #{shelter_name}, #{shelter_address}, #{shelter_tel})")
	void insert(Shelter shelter);

	@Delete("delete from shelter")
	void deleteAll();

	@Insert("insert into shelter (shelter_no, shelter_name) values('111111111111111', '일반회원')")
	void insertInit();

	@Select("select distinct shelter_address from shelter where shelter_name != '일반회원'")
	List<Shelter> getAddressList();

	@Select("select * from shelter where shelter_address=#{goo} and shelter_name != '일반회원'")
	List<Shelter> getShelterName(Map<String, Object> param);

	@Select("select shelter_name from shelter where shelter_no=#{no}")
	String getOneShelterName(Map<String, Object> param);

	@Select("select * from member where member_id != 'admin' and member_type=0")
	List<Member> getMemberList();

	@Select("select m.member_id, m.member_tel, m.member_email, m.file1, m.file2, m.member_auth, s.shelter_name, s.shelter_address,s.shelter_no from member m, shelter s where m.shelter_no=s.shelter_no and member_type=1")
	List<Member> getSmemberList();

	

}
