package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Idpw;
import logic.Member;

public interface MemberMapper {

	@Insert("insert into member "
			+ "(member_id, shelter_no, member_pass, member_name, member_email, member_tel, member_postcode, member_address, member_daddress, member_birthday, member_type, member_auth)"
			+ " values (#{member_id}, '111111111111111', #{member_pass}, #{member_name}, #{member_email}, #{member_tel}, #{member_postcode}, #{member_address}, #{member_daddress}, #{member_birthday}, 0, 0)")
	void memberInsert(Member mem);

	@Insert("insert into member "
			+ "(member_id, member_name, shelter_no, member_pass, member_email, member_tel, member_type, file1, file2, member_auth)"
			+ " values (#{member_id}, #{member_name}, #{shelter_no}, #{member_pass}, #{member_email}, #{member_tel}, 1, #{file1}, #{file2}, 1)")
	void smemberInsert(Member mem);
	
	@Select("select * from member where member_id=#{member_id}")
	Member selectMem(String member_id);

	@Update({"<script>",
			"update member set",
			"<if test='member_name != null'> member_name=#{member_name}, </if>",
			"<if test='member_birthday != null'> member_birthday=#{member_birthday}, </if>",
			"<if test='member_postcode != null'> member_postcode=#{member_postcode}, </if>",
			"<if test='member_address != null'> member_address=#{member_address}, </if>",
			"<if test='member_daddress != null'> member_daddress=#{member_daddress}, </if>",
			"<if test='shelter_no != null'> shelter_no=#{shelter_no}, </if>",
			"<if test='file1 != null'> file1=#{file1}, </if>",
			"<if test='file2 != null'> file2=#{file2}, </if>",
			" member_tel=#{member_tel}, member_email=#{member_email} where member_id=#{member_id}",
			"</script>"
	})
			//"update member set member_name=#{member_name}, member_birthday=#{member_birthday}, member_postcode=#{member_postcode},"
			//+ " member_address=#{member_address}, member_daddress=#{member_daddress}, member_tel=#{member_tel}, member_email=#{member_email}"
			//+ " where member_id=#{member_id}")
	void memUpdate(Member mem);

	@Update("update member set member_pass=#{pass} where member_id=#{id}")
	void memPassUpdate(Map<String, Object> param);

	@Select({
		"<script>",
		"select * from member where member_id in ",
		"<foreach collection='memberids' item='id' separator=',' open='(' close=')'> #{id}</foreach>",
		"</script>"
	})
	List<Member> memberList(Map<String, Object> param);

	@Select("select member_pass from member where member_id=#{member_id}")
	String getMemberPass(String member_id);

	@Select({
			"<script>",
			"SELECT member_id, member_pass FROM member WHERE member_tel = #{member_tel} and member_email = #{member_email}",
			"<if test='member_id != null'> and member_id = #{member_id} </if>",
			"</script>"})
	List<Idpw> selectmem(Map<String, Object> param);

	@Update("update member set member_auth=${member_auth} where member_id=#{member_id}")
	void updateAuth(Map<String, Object> param);

	@Delete("delete from member where member_id=#{member_id}")
	void deleteMember(String member_id);

}