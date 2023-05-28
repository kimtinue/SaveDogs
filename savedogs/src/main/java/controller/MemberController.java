package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.LoginException;
import logic.AdoptSign;
import logic.BuyItem;
import logic.Buylist;
import logic.Cart;
import logic.DogService;
import logic.Funding;
import logic.Fundinglist;
import logic.Idpw;
import logic.Item;
import logic.Mail;
import logic.Member;
import logic.Shelter;
import logic.Vwork;
import logic.Vworklist;
import util.MemberValidator;
import util.ShelterValidator;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private DogService service;
	@Autowired
	private MemberValidator memvalidator;
	@Autowired
	private ShelterValidator shelvalidator;
	
	@GetMapping("alerturl")
	public ModelAndView alerturl(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		/*
		 * Member smem = (Member)session.getAttribute("loginsmem"); if(smem != null) {
		 * mav.addObject("smem",smem); } else { Member mem =
		 * (Member)session.getAttribute("loginmem"); mav.addObject("mem",mem); }
		 */
		return mav;
	}
	
	@GetMapping("*")
	public ModelAndView memberEntry() {
		ModelAndView mav = new ModelAndView();
		Member mem = new Member();
		mav.addObject(mem);
		return mav;
	}
	
	@PostMapping("memberSignup")
	public ModelAndView memberSignup(@Valid Member mem, BindingResult bresult) {
		ModelAndView mav = new ModelAndView();
		if (bresult.hasErrors()) {
			bresult.reject("error.input.member");
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			service.memberInsert(mem);
			mav.setViewName("redirect:login.dog");
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bresult.rejectValue("member_id", "error.duplicate.user");
			mav.getModel().putAll(bresult.getModel());
		} catch(Exception e) {
			e.printStackTrace();
			bresult.reject("error.input.member");
			mav.getModel().putAll(bresult.getModel());
		}
		return mav;
	}
	
	@GetMapping("shelterlist")
	public ModelAndView shelterlistmain() {
		ModelAndView mav = new ModelAndView();
		List<Shelter> list = service.getShelterAddress();
		mav.addObject("list",list);
		return mav;
	}
	
	@PostMapping("shelterlist")
	public ModelAndView shelterlist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String goo = request.getParameter("goo");
		List<Shelter> list = service.getShelterAddress();
		List<Shelter> namelist = service.getShelterName(goo);
		mav.addObject("list",list);
		mav.addObject("namelist", namelist);
		return mav;
	}
	
	@PostMapping("smemberSignup")
	public ModelAndView smemberSignup(@Valid Member mem, BindingResult bresult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (bresult.hasErrors()) {
			bresult.reject("error.input.member");
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			service.smemberInsert(mem, request);
			mav.setViewName("redirect:login.dog");
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bresult.rejectValue("member_id","error.duplicate.user");
			mav.getModel().putAll(bresult.getModel());
		} catch(Exception e) {
			e.printStackTrace();
			bresult.reject("error.input.member");
			mav.getModel().putAll(bresult.getModel());
		}
		return mav;
	}
	
	@GetMapping("memberMypage")
	public ModelAndView memberMypageMainchkm(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member mem = service.getMember(id);
		mav.addObject("type", type);
		mav.addObject("mem", mem);
		return mav;
	}
	
	@GetMapping("vworkMypage")
	public ModelAndView vworkMypageMainchkm(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Vwork> list = service.getMyvworkList(id);
		List<Vworklist> yearlist = service.getYearlist(id);
		mav.addObject("type", type);
		mav.addObject("list", list);
		mav.addObject("yearlist", yearlist);
		return mav;
	}
	
	@GetMapping("shopMypage")
	public ModelAndView shopMypageMainchkm(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Buylist> buylist = service.getbuylist(id);
		for(Buylist bl : buylist) {
			List<BuyItem> buyitemlist = service.getbuyitemlist(bl.getBuy_no());
			for(BuyItem bi : buyitemlist) {
				Item item = service.itemselect(Integer.parseInt(bi.getItem_no()));
				bi.setItem(item);
			}
			bl.setItemList(buyitemlist);
		}
		mav.addObject("buylist", buylist);
		Cart cart = (Cart)session.getAttribute("CART");
		mav.addObject("cart",cart);
		return mav;
	}
	
	@GetMapping("fundMypage")
	public ModelAndView fundMypageMainchkm(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Fundinglist> fundlist = service.getMyfundlist(id);
		List<Fundinglist> endfundlist = service.getMyendfundlist(id);
		List<Fundinglist> yearlist = service.getfundYearlist(id);
		mav.addObject("yearlist", yearlist);
		mav.addObject("fundlist", fundlist);
		mav.addObject("endfundlist", endfundlist);
		return mav;
	}
	
	@GetMapping("adoptMypage")
	public ModelAndView adoptMypagechkm(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<AdoptSign> myadoptlist = service.getMyadoptlist(id);
		mav.addObject("myadoptlist", myadoptlist);
		return mav;
	}
	
	@PostMapping("checkpass")
	public ModelAndView checkpass(String member_pass, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member loginmem = (Member)session.getAttribute("loginmem");
		Member loginsmem = (Member)session.getAttribute("loginsmem");
		if(loginmem != null) {
			Member mem = service.getMember(loginmem.getMember_id());
			if(member_pass.equals(mem.getMember_pass())) {
				mav.setViewName("redirect:updateMember.dog?type=1&id=" + mem.getMember_id());
			} else {
				throw new LoginException("비밀번호 오류","../member/memberMypage.dog?type=1&id=" + mem.getMember_id());
			}
		} else {
			Member mem = service.getMember(loginsmem.getMember_id());
			if(member_pass.equals(mem.getMember_pass())) {
				mav.setViewName("redirect:updateMember.dog?type=1&id=" + mem.getMember_id());
			} else {
				throw new LoginException("비밀번호 오류","../member/shelterMypage.dog?type=1&id=" + mem.getMember_id());
			}
		}		
		return mav;
	}
	
	@GetMapping({"updateMember", "updateShelter"})
	public ModelAndView updateformupck(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member mem = service.getMember(id);
		mav.addObject("member", mem);
		return mav;
	}
	
	@PostMapping("updateMember")
	public ModelAndView memberupdate(Member mem, String id, BindingResult bresult, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Member login = null;
		int type = 0;
		int admin = 0;
		if(session.getAttribute("loginmem") != null) {
			memvalidator.validate(mem, bresult);
			login = (Member)session.getAttribute("loginmem");
		} else if(session.getAttribute("loginsmem") != null){
			shelvalidator.validate(mem, bresult);
			login = (Member)session.getAttribute("loginsmem");
			type = 1;
		} else {
			login = (Member)session.getAttribute("loginadmin");
			admin = 1;
			if(mem.getMember_type()==0) {
				memvalidator.validate(mem, bresult);
			} else {
				shelvalidator.validate(mem, bresult);
				type = 1;
			}
		}
		if (bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			mav.setViewName("redirect:updateMember.dog?id="+mem.getMember_id());
			return mav;
		}
		try {
			if(login.getMember_id() == "admin") {
				mem.setMember_pass(service.getMemberPass(mem.getMember_id()));
				service.memUpdate(mem, request);
			} else {
				mem.setMember_pass(login.getMember_pass());
				service.memUpdate(mem, request);
			}
			if(type == 0) {
				if(admin==1) {
					mav.setViewName("redirect:../admin/adminlistMypage.dog?type=2&id=admin");
				} else {
					mav.setViewName("redirect:memberMypage.dog?type=1&id="+mem.getMember_id());
				}
				if(login.getMember_id().equals(mem.getMember_id())) {
					session.setAttribute("loginmem", mem);
				}
			} else {
				if(admin==1) {
					mav.setViewName("redirect:../admin/adminlistMypage.dog?type=2&id=admin");
				} else {
					mav.setViewName("redirect:shelterMypage.dog?type=1&id="+mem.getMember_id());
				}
				if(login.getMember_id().equals(mem.getMember_id())) {
					session.setAttribute("loginsmem", mem);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			mav.getModel().putAll(bresult.getModel());
		}
		return mav;
	}
	
	@PostMapping("delete")
	public ModelAndView delete(String member_id, String member_pass, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member loginmem = (Member)session.getAttribute("loginmem");
		Member loginsmem = (Member)session.getAttribute("loginsmem");
		Member loginadmin = (Member)session.getAttribute("loginadmin");
			String dbpass = service.getMemberPass(member_id);
			if(loginadmin == null) {	//일반탈퇴
				if(member_pass.equals(dbpass)) {
					try {
						service.deleteMember(member_id);
						service.deleteVworklist(member_id);
						service.deleteFundlist(member_id);
					} catch (Exception e) {
						throw new LoginException("삭제 오류","../member/memberMypage.dog?type=1&id=" + member_id);
					}
					if(loginmem != null) {
						session.removeAttribute("loginmem");
					} else if(loginsmem != null) {
						session.removeAttribute("loginsmem");
					}
					mav.setViewName("redirect:../main.dog");
				} else {
					throw new LoginException("비밀번호 오류","../member/memberMypage.dog?type=1&id=" + member_id);
				}
			} else {					//관리자의 강제탈퇴
				if(member_pass.equals(loginadmin.getMember_pass())) {
					try {
						service.deleteMember(member_id);
						service.deleteVworklist(member_id);
						service.deleteFundlist(member_id);
					} catch (Exception e) {
						throw new LoginException("삭제 오류","../admin/adminlistMypage.dog?type=2&id=admin");
					}
					mav.setViewName("redirect:../admin/adminlistMypage.dog?type2&id=admin");
				} else {
					throw new LoginException("관리자 비밀번호 오류","../admin/adminlistMypage.dog?type=2&id=admin");
				}
			}
		return mav;
	}
	
	@PostMapping("changepass")
	public ModelAndView changepass(String inputpass, String newpass, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member mem = service.getMember(id);
		Member loginmem = (Member)session.getAttribute("loginmem");
		if(inputpass.equals(mem.getMember_pass())) {
			service.memPassUpdate(newpass, id);
		} else {
			throw new LoginException("현재 비밀번호가 틀립니다.", "../member/changepass.dog?id="+id);
		}
		mav.addObject("msg", "비밀번호 변경 완료");
		if(loginmem != null) {
			mav.addObject("url", "memberMypage.dog?type=1&id="+id);
		} else {
			mav.addObject("url", "shelterMypage.dog?type=1&id="+id);
		}
		
		mav.setViewName("redirect:alerturl.dog");
		return mav;
	}
	
	@PostMapping("login")
	public ModelAndView login(@Valid Member mem, BindingResult bresult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			bresult.reject("error.login.member");
			System.out.println(bresult);
			return mav;
		}	
		try {
		   Member dbmem = service.getMember(mem.getMember_id());
		   if(mem.getMember_pass().equals(dbmem.getMember_pass())) {
			  int type = dbmem.getMember_type();
			  if(type == 0) {
				  session.setAttribute("loginmem", dbmem);
			  } else if (type == 1) {
				  session.setAttribute("loginsmem", dbmem);
				  session.setAttribute("smemName", service.getShelter(dbmem.getShelter_no()).getShelter_name());
			  } else if (type == 2) {
				  session.setAttribute("loginadmin", dbmem);
			  }
			  mav.setViewName("redirect:../main.dog");
		   } else {
			  bresult.reject("error.login.member_pass");
		   }
		} catch (Exception e) {
			e.printStackTrace();
			bresult.reject("error.login.member_id");
		}
		return mav;
	}
	
	@RequestMapping("logout")
	public String loginChecklogout(HttpSession session) {
		session.invalidate();
		return "redirect:login.dog";
	}
	
	//보호소관리자
	
	@GetMapping("shelterMypage")
	public ModelAndView shelterMypageMainchks(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member mem = service.getMember(id);
		mav.addObject("type", type);
		mav.addObject("mem", mem);
		return mav;
	}
	
	@GetMapping("shelteradoptMypage")
	public ModelAndView shelteradoptMypageMainchks(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<AdoptSign> shelteradoptlist = service.getShelteradoptlist(id);
		mav.addObject("type", type);
		mav.addObject("shelteradoptlist",shelteradoptlist);
		return mav;
	}
	
	@GetMapping("sheltervworkMypage")
	public ModelAndView sheltervworkMypageMainchks(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Vwork> writelist = service.getwritelist(id);		
		mav.addObject("writelist", writelist);
		mav.addObject("type", type);
		
		//calendar 관련 정보 불러오기
		HashSet<String> hashSet = new HashSet<>(); 
		Member mem = (Member)session.getAttribute("loginsmem");
		hashSet = service.sheltervwork(mem.getShelter_no());
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for(String h : hashSet) {
			json.append("{\"start\":\""+h +"\",");
			json.append("\"title\":\"봉사신청\",");
			json.append("\"color\":\"#9EE2DA\"},");
			i++;
		}
		if(i<hashSet.size()) json.append(",");	
		json.append("]");
		mav.addObject("json", json.toString().trim());
		
		return mav;
	}
	
	@GetMapping("sheltervworkDetail")
	public ModelAndView sheltervworkDetailchks(String vwork_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Vwork showVwork = service.getVwork(vwork_no);
		int Nowmem = service.getNowmem(Integer.parseInt(vwork_no));
		List<Vworklist> detaillist = service.getOnevworklist(vwork_no);
		mav.addObject("showVwork", showVwork);
		mav.addObject("Nowmem",Nowmem);
		mav.addObject("detaillist", detaillist);
		return mav;
	}
	
	@GetMapping("shelterfundMypage")
	public ModelAndView shelterfundMypageMainchks(String type, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Funding> writelist = service.getwritelist2(id);
		mav.addObject("writelist", writelist);
		mav.addObject("type", type);
		return mav;
	}
	
	@GetMapping("shelterfundDetail")
	public ModelAndView shelterfundDetailchks(String fund_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Funding showFund = service.getfundingdetail(fund_no);
		List<Fundinglist> detaillist = service.getOnefundlist(fund_no);
		mav.addObject("showFund", showFund);
		mav.addObject("detaillist", detaillist);
		return mav;
	}
	
	@RequestMapping("mailForm")
	public ModelAndView mailformchks(String[] idchks, String fund_no, HttpSession session) {
		ModelAndView mav = new ModelAndView("member/mail");
		if(idchks == null || idchks.length == 0) {
			throw new LoginException("메일을 보낼 대상자를 선택하세요.", "shelterfundDetail.dog?fund_no="+fund_no);
		}
		List<Member> list = service.memberList(idchks);
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("mail")
	public ModelAndView mail(Mail mail, HttpSession session, String fund_no) {
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println(mail);
			mailSend(mail);
		} catch (Exception e) {
			throw new LoginException("메일 전송 실패", "../main.dog");
		}
		mav.setViewName("redirect:shelterfundDetail.dog?fund_no="+fund_no);
		return mav;
	}
	
	private final class MyAuthenticator extends Authenticator {
		private String id;
		private String pw;
		public MyAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}
	
	private void mailSend(Mail mail) {
		//네이버 메일 전송을 위한 인증 객체
		MyAuthenticator auth = new MyAuthenticator(mail.getNaverid(), mail.getNaverpw());
		//메일 전송을 위한 환경 변수 설정
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("C:/Users/GDJ24/Documents/git/TeamProject/savedogs/src/main/resources/mail.properties");
			prop.load(fis);	//mail.properties의 내용을 Properties(Map 객체)객체로 로드.
			prop.put("mail.smtp.user", mail.getNaverid());
		} catch(IOException e) {
			e.printStackTrace();
		}
		//session : 메일 전송을 위한 객체
		Session session = Session.getInstance(prop, auth);
		//mimeMsg : 메일 내용을 저장하기 위한 객체
		MimeMessage mimeMsg = new MimeMessage(session);
		try {
			//보내는이 설정.
			mimeMsg.setFrom(new InternetAddress(mail.getNaverid()+"@naver.com"));
			List<InternetAddress> addrs = new ArrayList<InternetAddress>();
			//홍길동 <hong@aaa.bbb>, 김삿갓 <kim@bbb.ccc> 형태의 수신자를 ,를 기준으로 분리
			String[] emails = mail.getRecipient().split(",");
			for(String email : emails) {
				try {
					addrs.add(new InternetAddress(new String(email.getBytes("utf-8"), "8859_1")));
				} catch (UnsupportedEncodingException ue) {
					ue.printStackTrace();
				}
			}
			InternetAddress[] arr = new InternetAddress[emails.length];
			for(int i=0; i<addrs.size(); i++) {
				arr[i] = addrs.get(i);
			}
			//보낸일자
			mimeMsg.setSentDate(new Date());
			//받는사람들
			mimeMsg.setRecipients(Message.RecipientType.TO,arr);
			//제목
			mimeMsg.setSubject(mail.getTitle());
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart message = new MimeBodyPart();
			//내용
			message.setContent(mail.getContents(), mail.getMtype());
			multipart.addBodyPart(message);
			//첨부파일
			for(MultipartFile mf : mail.getFile1()) {
				if((mf != null) && (!mf.isEmpty())) {
					multipart.addBodyPart(bodyPart(mf));
				}
			}
			mimeMsg.setContent(multipart);
			Transport.send(mimeMsg);	//메일 전송.
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	private BodyPart bodyPart(MultipartFile mf) {
		MimeBodyPart body = new MimeBodyPart();
		//업로드 파일의 이름
		String orgFile = mf.getOriginalFilename();
		//업로드 되는 위치
		String path = "d:/mailupload/";
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		File f1 = new File(path + orgFile);	//업로드된 내용을 저장하는 파일
		try {
			mf.transferTo(f1);	//업로드 완성
			body.attachFile(f1);	//메일 첨부
			//첨부파일이름 설정
			body.setFileName(new String(orgFile.getBytes("UTF-8"), "8859_1"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return body;
	}
	
	@PostMapping("idfind")
	public ModelAndView idfind(String email, String tel) {
		ModelAndView mav =  new ModelAndView("member/id");
		Idpw member = service.getFindID(tel,email);
		mav.addObject("id", member.getMember_id());
		return mav;
	}
	
	@PostMapping("pwfind")
	public ModelAndView pwfind(String id,String email, String tel) {
		ModelAndView mav =  new ModelAndView("member/pw");
		Idpw member = service.getFindPW(id,tel,email);
		mav.addObject("pw", member.getMember_pass());
		return mav;
	}
}