
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.FundingMapper;
import dao.mapper.FundingreplyMapper;
import logic.Funding;
import logic.Fundreply;

@Repository
public class FundingreplyDao {
	@Autowired
	 private SqlSessionTemplate template;
	  private Map<String,Object> param = new HashMap<>();
	  
	  public List<Fundreply> list() { //list() -> foreach items
		  return template.getMapper(FundingreplyMapper.class).select(null); 
	  }
	  public void fundreplyInsert(String fundreply) {
		   template.getMapper(FundingreplyMapper.class).fundreplyinsert(fundreply);
		}
	  public void fundreplyUpdate(String fund_comment) {
			 template.getMapper(FundingreplyMapper.class).fundreplyupdate(fund_comment);
				}
	  public void fundreplyDelete(int fund_replyno) {
			 param.clear();
			 param.put("fund_replyno",fund_replyno);
			 template.getMapper(FundingreplyMapper.class).fundreplydelete(param);
				}
}
