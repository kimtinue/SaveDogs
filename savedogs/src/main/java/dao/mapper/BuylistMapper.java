package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.Buylist;

public interface BuylistMapper {
	
	@Select("select ifnull(max(buy_no),0) from buylist")
	int maxsaleid();

	@Insert("insert into buylist (buy_no,member_id,buy_date,buy_state,buy_postcode,buy_address,buy_daddress,buy_tel) values(#{buy_no},#{member_id}, now(),'배송중',#{buy_postcode},#{buy_address},#{buy_daddress},#{buy_tel})")
	void insert(Buylist buylist);

	@Select("select * from buylist where member_id = #{member_id}")
	List<Buylist> select(Map<String, Object> param);

}
