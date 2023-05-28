/*
 * package controller;
 * 
 * import java.util.ArrayList; import java.util.HashMap; import java.util.List;
 * 
 * import javax.annotation.Resource; import
 * javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import logic.DogService; import logic.Funding; import logic.Fundreply; import
 * logic.Member;
 * 
 * @Controller public class FundreplyController {
 * 
 * @Autowired private DogService service;
 * 
 * 
 *//**
	 * 댓글 등록(Ajax)
	 * 
	 * @param boardVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
/*
 * @RequestMapping(value="/funding/addComment.dog")
 * 
 * @ResponseBody public String ajax_addComment(@ModelAttribute("Funding")
 * Funding funding, HttpServletRequest request) throws Exception{
 * 
 * HttpSession session = request.getSession(); Member member =
 * (Member)session.getAttribute("member");
 * 
 * try{
 * 
 * funding.setMember_id(member.getMember_id()); service.addComment(funding);
 * 
 * } catch (Exception e){ e.printStackTrace(); }
 * 
 * return "success"; }
 * 
 *//**
	 * 게시물 댓글 불러오기(Ajax)
	 * 
	 * @param boardVO
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
		 * @RequestMapping(value="/board/commentList.do",
		 * produces="application/json; charset=utf8")
		 * 
		 * @ResponseBody public ResponseEntity
		 * ajax_commentList(@ModelAttribute("fundreply") Fundreply fundreply,
		 * HttpServletRequest request) throws Exception{
		 * 
		 * HttpHeaders responseHeaders = new HttpHeaders(); ArrayList<HashMap> hmlist =
		 * new ArrayList<HashMap>();
		 * 
		 * // 해당 게시물 댓글 List<Fundreply> commentVO =
		 * service.selectBoardCommentByCode(boardVO);
		 * 
		 * if(commentVO.size() > 0){ for(int i=0; i<commentVO.size(); i++){ HashMap hm =
		 * new HashMap(); hm.put("c_code", commentVO.get(i).getC_code());
		 * hm.put("comment", commentVO.get(i).getComment()); hm.put("writer",
		 * commentVO.get(i).getWriter());
		 * 
		 * hmlist.add(hm); }
		 * 
		 * }
		 * 
		 * JSONArray json = new JSONArray(hmlist); return new
		 * ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
		 * 
		 * } }
		 */