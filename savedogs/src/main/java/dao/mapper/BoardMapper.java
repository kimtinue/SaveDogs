package dao.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;



import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;
import logic.Reply;
import logic.Board;

public interface BoardMapper {

	

	@Select("select ifnull(max(board_no),0) from board")
	int maxno();


	@Insert("insert into board (board_no, member_id, subject, content, type, grp, grplevel, grpstep, regdate, readcnt, file1) values "
			+ "(${board_no},#{member_id},#{subject},#{content},#{type},#{grp},#{grplevel},#{grpstep},now(),0,#{fileurl})")
	void insertBoard(Board board);

	@Select("select board_no, member_id, subject, content, type, grp, grplevel, grpstep, regdate, readcnt, file1 fileurl from board where board_no = #{board_no}")
	Board getBoard(String board_no);

	@Select("select count(*) from board where type = #{type}")
	int getTypecnt(String type);

	@Select({"<script>",
		"select board_no,member_id,subject,content,type,grp,grplevel,grpstep,regdate,readcnt,file1 fileurl from board ",
		"<if test='type != null'> where type = #{type} </if>",
		"<if test='startrow != null and limit != null'>order by grp desc, grpstep limit #{startrow}, #{limit} </if>",
		"</script>"})
	List<Board> boardlist(Map<String, Object> param);

	@Update("update board set readcnt = readcnt+1 where board_no = #{board_no}")
	void cntup(String board_no);

	@Select("select * from board where type = 1 order BY regdate desc limit 0,4")
	List<Board> mainnotice();

	@Update("update board set subject = #{subject}, content = #{content}, file1 = #{fileurl} where board_no = #{board_no}")
	void updateBoard(Board board);

	@Delete("delete from board where board_no=#{board_no}")
	void deleteBoard(String board_no);

	@Select("select type from board where board_no = #{board_no}")
	String getBoardType(String board_no);

	@Select("select * from reply where board_no = #{board_no}")
	List<Reply> getReplyList(String board_no);

	@Insert("insert into reply (board_replyno, board_no, member_id, board_comment, board_regdate) values (#{board_replyno}, #{board_no}, #{member_id}, #{board_comment}, now())")
	void insertReply(Reply reply);

	@Select("select ifnull(max(board_replyno),0) from reply")
	int getRmax();

	@Delete("delete from reply where board_replyno=#{rno}")
	void deleteReply(String rno);
	
	@Select({"<script>",
		"select board_no,member_id,subject,content,file1 fileurl,regdate,readcnt,grp,grplevel,grpstep,type from board ",
		"<if test='type != null'> where type = #{type} </if>",
		"<if test='searchtype != null and searchcontent != null'> and ${searchtype} like #{searchcontent} </if>",
	
		"<if test='startrow != null and limit != null'>order by grp desc, grpstep limit #{startrow}, #{limit} </if>",
		"</script>"})
	List<Board> qnalist(Map<String, Object> param);

	@Select({"<script>",
		"select count(*) from board ",
		"<if test='type != null'> where type = #{type} </if>",
		"<if test='searchtype != null and searchcontent != null'> and ${searchtype} like #{searchcontent} </if>",
		
		"</script>"})
	int qnacnt(Map<String, Object> param);

	@Select({"<script>",
		"select count(*) from board ",
		"<if test='type != null'> where type = #{type} </if>",
		"<if test='searchtype != null and searchcontent != null'> and ${searchtype} like #{searchcontent} </if>",
		"</script>"})
	int reviewcnt(Map<String, Object> param);

	@Select({"<script>",
		"select board_no,member_id,subject,content,file1 fileurl,regdate,readcnt,grp,grplevel,grpstep,type from board ",
		"<if test='type != null'> where type = #{type} </if>",
		"<if test='searchtype != null and searchcontent != null'> and ${searchtype} like #{searchcontent} </if>",
		"<if test='startrow != null and limit != null'>order by grp desc, grpstep limit #{startrow}, #{limit} </if>",
		"</script>"})
	List<Board> reviewlist(Map<String, Object> param);

	@Update("update board set grpstep=grpstep+1 where grp = #{grp} and grpstep > #{grpste}")
	void updateGrpStep(Map<String, Object> param);

	@Select({"<script>",
		"select board_no,member_id,subject,content,file1 fileurl,regdate,readcnt,grp,grplevel,grpstep,type from board ",
		"<if test='searchtype != null and searchcontent != null'> where ${searchtype} like #{searchcontent} and type = #{type}</if>",
		
		"<if test='startrow != null and limit != null'>order by grp desc, grpstep limit #{startrow}, #{limit} </if>",
		"</script>"})
	List<Board> postqnalist(Map<String, Object> param);

	


}
