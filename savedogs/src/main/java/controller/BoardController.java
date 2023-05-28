package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.BoardException;
import logic.Board;
import logic.DogService;
import logic.Member;
import logic.Reply;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private DogService service;
	
	@RequestMapping("*")
	public ModelAndView noticeList() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@GetMapping(value= {"noticeWrite","reviewWrite","qnaWrite"}) 
	public ModelAndView chkmwriteform(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav= new ModelAndView();
		model.addAttribute(new Board());
		model.addAttribute(new Reply()); //review만 사용 
		return mav;
	}
	
	@PostMapping(value= {"noticeWrite","reviewWrite","qnaWrite"})
	public ModelAndView chkmwrite(@Valid Board board, BindingResult bresult, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			service.boardWrite(board, request);
			if(board.getType().equals("0")) {
				mav.setViewName("redirect:reviewDetail.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("1")) {
				mav.setViewName("redirect:noticeDetail.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("2")) {
				mav.setViewName("redirect:qnaDetail.dog?no="+board.getBoard_no());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			if(board.getType().equals("0")) {
				throw new BoardException("게시글 등록에 실패했습니다","reviewWrite.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("1")) {
				throw new BoardException("게시글 등록에 실패했습니다","noticeWrite.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("2")) {
				throw new BoardException("게시글 등록에 실패했습니다","qnaWrite.dog?no="+board.getBoard_no());
			}
			
		}
		return mav;
	}
	
	@GetMapping(value= {"noticeUpdate","reviewUpdate","qnaUpdate"})
	public ModelAndView chkmupdateForm(String no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(no);
		mav.addObject("board",board);
		return mav;
	}
	
	@GetMapping(value= {"noticeDetail","reviewDetail","qnaDetail"})
	public ModelAndView chkmnoticeDetail(String no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Board board = service.boardDetail(no);
		mav.addObject("board",board);
		return mav;
	}
	
	@RequestMapping("noticeList")
	public ModelAndView noticeList(Integer pageNum, String type) { 
		ModelAndView mav = new ModelAndView();
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		int limit = 10; 
		int noticecnt = service.noticecnt(type);
		List<Board> boardlist = service.boardlist(pageNum, limit, type);
		
		int maxpage = (int)((double)noticecnt/limit + 0.95);
		int startpage = ((int)(pageNum/10.0 + 0.9)-1) * 10 + 1; 
		int endpage = startpage+9; 
		if(endpage>maxpage) endpage = maxpage; 
		int boardno = noticecnt - (pageNum-1) *limit;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(new Date());
		mav.addObject("today",today);
		mav.addObject("pageNum",pageNum);
		mav.addObject("maxpage",maxpage);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("noticecnt",noticecnt);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardno",boardno);
		return mav;
	}

	
	
	@RequestMapping("qnaList")
	public ModelAndView qnalist(Integer pageNum,String searchtype, String searchcontent,String type) {
		ModelAndView mav = new ModelAndView();
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		int limit = 10; 
		
		//검색 부분
		if(searchtype == null || searchtype.trim().equals("")||searchcontent == null || searchcontent.trim().equals("")) {
			searchtype = null;
			searchcontent = null;
		}
		//검색 정보까지 집어넣기
		int listcount = service.qnacnt(searchtype,searchcontent,type);  
		List<Board> boardlist = service.qnalist(pageNum, limit,searchtype,searchcontent,type);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)(pageNum/10.0 + 0.9)-1) * 10 + 1; 
		int endpage = startpage+9; 
		if(endpage>maxpage) endpage = maxpage; 
		int boardno = listcount - (pageNum-1) *limit;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(new Date());
		mav.addObject("today",today);
		mav.addObject("pageNum",pageNum);
		mav.addObject("maxpage",maxpage);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardno",boardno);
		
		return mav;
	}
	

	
	@RequestMapping("reviewList")
	public ModelAndView reviewlist(Integer pageNum,String searchtype, String searchcontent,String type) {
		ModelAndView mav = new ModelAndView();
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		int limit = 6; 
		if(searchtype == null || searchtype.trim().equals("")||searchcontent == null || searchcontent.trim().equals("")) {
			searchtype = null;
			searchcontent = null;
		}
		int listcount = service.reviewcnt(searchtype,searchcontent,type);  
		List<Board> boardlist = service.reviewlist(pageNum, limit,searchtype,searchcontent,type);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)(pageNum/10.0 + 0.9)-1) * 10 + 1; 
		int endpage = startpage+9; 
		if(endpage>maxpage) endpage = maxpage; 
		int boardno = listcount - (pageNum-1) *limit;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(new Date());
		mav.addObject("today",today);
		mav.addObject("pageNum",pageNum);
		mav.addObject("maxpage",maxpage);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardno",boardno);
		return mav;
	}
	
	@PostMapping(value= {"noticeUpdate","qnaUpdate","reviewUpdate"})
	public ModelAndView chkmupdate(@Valid Board board, BindingResult bresult, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			service.boardUpdate(board, request);
			
		} catch(Exception e) {
			e.printStackTrace();
			if(board.getType().equals("0")) {
				throw new BoardException("게시글 등록에 실패했습니다","reviewDetail.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("1")) {
				throw new BoardException("게시글 등록에 실패했습니다","noticeDetail.dog?no="+board.getBoard_no());
			}else if(board.getType().equals("2")) {
				throw new BoardException("게시글 등록에 실패했습니다","qnaDetail.dog?no="+board.getBoard_no());
			}
		}		
		if(board.getType().equals("0")) {
			mav.setViewName("redirect:reviewDetail.dog?no="+board.getBoard_no());
		}else if(board.getType().equals("1")) {
			mav.setViewName("redirect:noticeDetail.dog?no="+board.getBoard_no());
		}else if(board.getType().equals("2")) {
			mav.setViewName("redirect:qnaDetail.dog?no="+board.getBoard_no());
		}
		return mav;
	}
	
	@GetMapping(value= {"noticeDelete","qnaDelete","reviewDelete"})
	public ModelAndView chkmdeleteform(String no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("board_no", no);
		return mav;
	}
	
	@PostMapping(value= {"noticeDelete","qnaDelete","reviewDelete"})
	public ModelAndView chkmdelete(String no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String type = service.getBoardType(no);
		try {
			service.boardDelete(no);
			
			if(type.equals("0")) {
				mav.setViewName("redirect:reviewList.dog?type=0");
			}else if(type.equals("1")) {
				mav.setViewName("redirect:noticeList.dog?type=1");
			}else if(type.equals("2")) {
				mav.setViewName("redirect:qnaList.dog?type=2");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			if(type.equals("0")) {
				throw new BoardException("게시글 삭세에 실패했습니다","reviewWrite.dog?no="+no);
			}else if(type.equals("1")) {
				throw new BoardException("게시글 삭제에 실패했습니다","noticeWrite.dog?no="+no);
			}else if(type.equals("2")) {
				throw new BoardException("게시글 삭제에 실패했습니다","qnaWrite.dog?no="+no);
			}
		}
		return mav;
	} 
	
	@GetMapping("qnaReply")
	public ModelAndView chkadminsmemreplyform(Model model, String no,HttpSession session) {
		ModelAndView mav= new ModelAndView();
		Board board = service.getBoard(no);
		model.addAttribute(new Board());
		mav.addObject("board",board);
		return mav;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView chkadminsmemreply(@Valid Board board, BindingResult bresult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			String no =  String.valueOf(board.getBoard_no());
			Board dbBoard = service.getBoard(no);
			Map<String, Object> map = bresult.getModel();
			Board b = (Board)map.get("board");
			b.setSubject(dbBoard.getSubject());
			return mav;
		}
		try {
			//board : 화면에서 전달받은 파라미터 정보 저장
			/*
			 * num, grp, grplevel, grpstep : 원글 정보
			 * name, pass, subject, content : 답글 정보
			 */
			service.boardReply(board);
			mav.setViewName("redirect:qnaList.dog?type=2");
		} catch(Exception e) {
			e.printStackTrace();
			throw new BoardException("답변글 등록에 실패했습니다","qnaReply.dog?no="+board.getBoard_no());
		}
		return mav;
	}
	
//===========댓글===============
	
	@PostMapping(value="replyList", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String replyList(String board_no, HttpServletRequest request, HttpSession session) {
		List<Reply> list = service.replyList(board_no);
		StringBuilder html = new StringBuilder();
		html.append("<table>");
		if(list.size()>0 ) {
			for(Reply r : list) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(r.getBoard_regdate());
				html.append("<tr><th>"+r.getMember_id()+"</th><td rowspan='2' style='width:70%;' class='l_td'>"+r.getBoard_comment()+"</td><td rowspan='2'>");
				Member loginmem = (Member)session.getAttribute("loginmem");
				Member loginsmem = (Member)session.getAttribute("loginsmem");
				
				String login_id = "";
				if(loginmem != null) {
					login_id = loginmem.getMember_id();
				} else if(loginsmem != null) {
					login_id = loginsmem.getMember_id();
				} 
				if(r.getMember_id().equals(login_id)) {
					html.append("<input type='button' value='삭제' class='small_btn' onclick='replyDelete("+r.getBoard_replyno()+");'>");
				}
				html.append("</td></tr>");
				html.append("<tr><td class='l_td' style='text-align:center;'>"+date+"</td></tr>");
			}
		} else {
			html.append("<tr><td colspan='2'>해당 게시글의 댓글이 없습니다.</td></tr>");
		}
		html.append("</table>");
		return html.toString();
	}
	
	@PostMapping(value="replyInsert", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public void replyInsert(Reply reply, HttpServletRequest request, HttpSession session) {
		int rmax = service.getRmax();
		reply.setBoard_replyno(++rmax);
		service.insertReply(reply);
	}

	@PostMapping(value="replyDelete", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public void replyDelete(String rno, HttpServletRequest request, HttpSession session) {
		service.deleteReply(rno);
	}
	
	@RequestMapping("imgupload")
	public String imgupload(MultipartFile upload, String CKEditorFuncNum, HttpServletRequest request, Model model) {
		String path = request.getServletContext().getRealPath("/") + "board/imgfile/"; 
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		if(!upload.isEmpty()) { 
			File file = new File(path, upload.getOriginalFilename()); 
			try {
				upload.transferTo(file); 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		String fileName = request.getServletContext().getContextPath()+"/board/imgfile/"+upload.getOriginalFilename();
		model.addAttribute("fileName",fileName);
		model.addAttribute("CKEditorFuncNum",CKEditorFuncNum);
		return "ckedit";
	}
	
}