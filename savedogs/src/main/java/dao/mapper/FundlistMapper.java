package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.Funding;
import logic.Fundinglist;

public interface FundlistMapper {

	@Select("SELECT * FROM funding f join fundinglist fl WHERE f.fund_no=fl.fund_no AND fund_id=#{id} AND DATEDIFF(f.end_date, NOW()) >= 0 order by fund_date desc")
	List<Fundinglist> list(String id);
	
	@Select("select * from funding f join fundinglist fl where f.fund_no=fl.fund_no and fund_id=#{id} and datediff(f.end_date, now()) < 0 order by fund_date desc")
	List<Fundinglist> endlist(String id);

	@Select("SELECT f.fund_id, m.member_email, f.fund_cost FROM member m, fundinglist f WHERE m.member_id=f.fund_id and fund_no=${fund_no} order by fund_date desc")
	List<Fundinglist> getOnefundlist(Map<String, Object> param);
	
	@Insert(" insert into fundinglist (fund_no, fund_id, fund_date, fund_cost)"
			   + " values (#{fund_no}, #{fund_id}, now(), #{fund_cost} )")
	  void applyinsert(Fundinglist fundinglist);

	@Select("select year(fund_date) year from fundinglist where fund_id=#{id} group by year order by year desc")
	List<Fundinglist> getfundYearlist(String id);

	@Select("select concat(month(fund_date),'월') m, count(*) cnt from fundinglist where fund_id=#{member_id} and year(fund_date)=${year} group by m order by m")
	List<Map<String, Object>> fundgraph(Map<String, Object> param);

	@Delete("delete from fundinglist where fund_id=#{member_id}")
	void deleteFundlist(String member_id);
	
}