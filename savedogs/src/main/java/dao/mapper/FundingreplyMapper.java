
package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.BuyItem;
import logic.Funding;
import logic.Fundreply;

public interface FundingreplyMapper {

	

	@Select(" select count(*) from fundreply")
	 int replycount();
	
	@Select(" select * from fundreply")
	  List<Fundreply> select(Map<String, Object> param);

	
	@Insert(" insert into fundreply (fund_replyno, fund_no, fundreply_id, fund_comment, fund_regdate) " + 
			" values(#{fund_replyno},#{fund_no},#{fundreply_id},#{fund_comment},now())")  
	  void fundreplyinsert(String fundreply);

	
	@Update(" UPDATE fundreply SET fund_comment = #{fund_comment} where fund_replyno = #{fund_replyno}")
	  void fundreplyupdate(String fund_comment);

	
    @Delete( "delete from fund_comment where fund_replyno = #{fund_replyno}")
      void fundreplydelete(Map<String, Object> param);
}
