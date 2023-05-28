package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.Funding;
import logic.Fundreply;
import dao.mapper.FundingMapper;

@Repository
public class FundingDao {
	@Autowired
	  private SqlSessionTemplate template;
	  private Map<String,Object> param = new HashMap<>();
	  
	  
	// fund_no 증가
	  public int maxfundno() { return
	  template.getMapper(FundingMapper.class).maxfundno(); }
	 
	 
	  public void fundinsert(Funding funding) {
		   template.getMapper(FundingMapper.class).fundinsert(funding);
		}

	
	 public Funding selectOne(String fund_no) {
	 param.clear();
	 param.put("fund_no",fund_no); 
	 return template.getMapper(FundingMapper.class).select(param).get(0);
	 
	 }

	 public void fundupdate(Funding funding) {
	 template.getMapper(FundingMapper.class).fundupdate(funding);
		}
	 
	 public void fundDelete(String fund_no) {
		 param.clear();
		 param.put("fund_no",fund_no);
		 template.getMapper(FundingMapper.class).funddelete(param);
			}

	public List<Funding> getwritelist2(String id) {
		param.clear();
		param.put("member_id", id);
		return template.getMapper(FundingMapper.class).getwritelist2(param); 
	}

	public List<Funding> duefunding() {
		return template.getMapper(FundingMapper.class).duefunding();
	}
	
public List<Funding> list(Integer pageNum, int limit) {
	param.clear();
	param.put("startrow", (pageNum-1) * limit);
    param.put("limit",limit);
	return template.getMapper(FundingMapper.class).select(param);
}


public int listcount() {
	return template.getMapper(FundingMapper.class).listcount();
}


public List<Fundreply> freplyList(String fund_no) {
	return template.getMapper(FundingMapper.class).freplyList(fund_no);
}


public int getFRmax() {
	return template.getMapper(FundingMapper.class).getFRmax();
}


public void insertReply(Fundreply reply) {
	template.getMapper(FundingMapper.class).insertReply(reply);	
}


public void deleteFreply(String rno) {
	template.getMapper(FundingMapper.class).deleteFreply(rno);	
}
	

	/*
	 * public void delete(String userid) { param.clear();
	 * param.put("userid",userid);
	 * template.getMapper(FundingMapper.class).delete(param); }
	 * 
	 
	 * 
	 * public List<Funding> list(String[] idchks) { //select * from useraccount
	 * where userid in ('test1','test3') param.clear(); param.put("userids",idchks);
	 * return template.getMapper(FundingMapper.class).select(param); }
	 */
		}