package controller;


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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import exception.FundingException;
import exception.VworkException;
import logic.DogService;
import logic.Funding;
import logic.Fundinglist;
import logic.Fundreply;
import logic.Member;
import logic.Reply;
import logic.Shelter;
import logic.Vwork;

@Controller  // @Component + controller(요청을 받을 수 있는 객체)
@RequestMapping("funding") // /funding/ 요청시
public class FundingController {

      @Autowired
      private DogService service;
      
      @GetMapping("fregForm")
      public ModelAndView chksfregForm(HttpSession session) {
          ModelAndView mav = new ModelAndView();
          Funding f = new Funding();
          SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
          mav.addObject("today", sd.format(new Date()));
          mav.addObject("f",f);
          return mav; // WEB-INF/view/userEntry.jsp
        }
      
      @PostMapping("fregForm") 
        public ModelAndView fregForm(@Valid Funding funding, BindingResult bresult, HttpServletRequest request) {
            ModelAndView mav = new ModelAndView();
		  if(bresult.hasErrors())  { 
			  mav.getModel().putAll(bresult.getModel());
			  return mav; 
			  }
            service.fundCreate(funding,request);
            mav.setViewName("redirect:/funding/list.dog");
            return mav;      
          }
      
      @GetMapping("fregupdateForm")
      public ModelAndView fregupdateForm2(String fund_no) {
    	  ModelAndView mav = new ModelAndView();
    	  Funding funding = service.getfundingdetail(fund_no);
    	  
 		 //service.readcnt(num);
 		 mav.addObject("funding",funding);
 		 return mav;
 	 }
 	

  	@PostMapping("fregupdateForm")
  	public ModelAndView fregupdateForm(@Valid Funding funding, 
  			BindingResult bresult,HttpServletRequest request,HttpSession session) {
  		ModelAndView mav = new ModelAndView("funding/fregupdateForm");
  		if(bresult.hasErrors()) {
  			mav.getModel().putAll(bresult.getModel());
  			return mav;
  		}
  		//db,파일업로드
  		service.fundUpdate(funding,request);
  		mav.setViewName("redirect:/funding/detail.dog?fund_no="+funding.getFund_no());
  		return mav;
  	}
	@PostMapping("delete")
	public ModelAndView fundDelete(String fund_no,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println(fund_no);
		service.fundDelete(fund_no);
		mav.setViewName("redirect:/funding/list.dog");
		return mav;		
	}	
	
	/*
	 * @GetMapping("list") // /item/list.shop public ModelAndView list() {
	 * ModelAndView mav = new ModelAndView(); List<Funding> fundinglist =
	 * service.getFundingList(); mav.addObject("fundinglist",fundinglist); return
	 * mav; }
	 */
	 
	 
	 @RequestMapping("list")
	 public ModelAndView chkmlistchkauth(Integer pageNum,HttpSession session) { //int가 아닌 Integer로 써줌 -> pageNum이라는 파라미터 값이 없으면 null임, int는 기본자료형->null값이 없음
      ModelAndView mav = new ModelAndView();	
      
       if(pageNum == null || pageNum.toString().equals("")) {
	   pageNum =1;
      }
   
       
        int limit = 5; //한 페이지에 보여질 게시물의 건수
        int listcount = service.boardcount(); //등록 게시물 건수
        List<Funding> boardlist = service.boardList(pageNum,limit);
        System.out.println(boardlist);
        int maxpage = (int)((double)listcount/limit + 0.95);
        int startpage = (int)((pageNum/10.0 + 0.9) - 1) * 10 + 1;
        int endpage = startpage + 9;
        if(endpage > maxpage) endpage = maxpage;
        int boardno = listcount - (pageNum -1) * limit;
        mav.addObject("pageNum", pageNum);
        mav.addObject("maxpage", maxpage);
        mav.addObject("startpage", startpage);
        mav.addObject("endpage", endpage);
        mav.addObject("listcount", listcount);
        mav.addObject("boardlist", boardlist);
        mav.addObject("boardno", boardno);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sf.format(new Date());
        mav.addObject("today", today);
        return mav;
	 }
	 
	 
	 @GetMapping({"detail", "fundingapply"})
	   public ModelAndView chkmdetail(String fund_no, HttpSession session) {
		 ModelAndView mav = new ModelAndView();
		 Funding funding = service.getfundingdetail(fund_no);
		 //service.readcnt(num);
		 mav.addObject("funding",funding);
		 return mav;
	 }
	

	 @PostMapping("fundingapply")
	  public ModelAndView chkmapply(@Valid Fundinglist fundinglist, BindingResult bresult,HttpSession session, HttpServletRequest request) {
          ModelAndView mav = new ModelAndView();
          Member mem = (Member)session.getAttribute("loginmem");
          if(bresult.hasErrors()) {
  			mav.getModel().putAll(bresult.getModel());
  			return mav; 
  		}	try {
  			service.fundingapply(fundinglist,request);
  		}   catch(Exception e) {
  			throw new FundingException("이미 기부 하셨습니다.", "../vwork/gotoMain.dog");
  		}
          mav.setViewName("redirect:/vwork/gotoMain.dog"); //mypage로~?
          return mav;      
      
        }
	 
	 
//===========댓글===============		
	@PostMapping(value="replyList", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String replyList(String pno, HttpServletRequest request, HttpSession session) {
		List<Fundreply> list = service.freplyList(pno);
		StringBuilder html = new StringBuilder();
		html.append("<table>");
		if(list.size()>0 ) {
			for(Fundreply r : list) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(r.getFund_regdate());
				html.append("<tr><th>"+r.getFundreply_id()+"</th><td rowspan='2' style='width:70%;' class='l_td'>"+r.getFund_comment()+"</td><td rowspan='2'>");
				Member loginmem = (Member)session.getAttribute("loginmem");
				Member loginsmem = (Member)session.getAttribute("loginsmem");
				String login_id = "";
				if(loginmem != null) {
					login_id = loginmem.getMember_id();
				} else if(loginsmem != null) {
					login_id = loginsmem.getMember_id();
				}
				
				if(r.getFundreply_id().equals(login_id)) {
					html.append("<input type='button' value='삭제' class='small_btn' onclick='replyDelete("+r.getFund_replyno()+");'>");
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
	public void replyInsert(Fundreply reply, HttpServletRequest request, HttpSession session) {
		int rmax = service.getFRmax();
		reply.setFund_replyno(++rmax);
		service.insertReply(reply);
	}

	@PostMapping(value="replyDelete", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public void replyDelete(String rno, HttpServletRequest request, HttpSession session) {
		service.deleteFreply(rno);
	}
}