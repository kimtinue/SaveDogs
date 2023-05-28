package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import logic.Shelter;

public interface ShelterMapper {

	@Select("select * from shelter where shelter_no=#{shelter_no}")
	Shelter selectShelter(String shelter_no);

	@Select("select * from shelter where shelter_address=#{goo}")
	List<Shelter> selectShelters(String goo);

	@Select("select * from shelter WHERE shelter_name!='일반회원'")
	List<Shelter> selectShelterlist();

	@Select("SELECT shelter_no, CONCAT(shelter_address, ' ', shelter_name) hap FROM shelter WHERE shelter_name!='일반회원'")
	List<Shelter> getHaplist();

}
