package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.springframework.web.servlet.ModelAndView;

import exception.VworkException;
import logic.DogService;
import logic.Member;
import logic.Shelter;
import logic.Vwork;
import logic.Vworklist;


@Controller
@RequestMapping("vwork")
public class VworkController {
	@Autowired
	private DogService service;
	
	@RequestMapping("gotoMain")
	public ModelAndView gotoMain(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}		
		return mav;
	}
	
	@GetMapping("vmain")
	public ModelAndView chkloginvmainchkauth(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}
		
		HashSet<String> hashSet = new HashSet<>(); 
		hashSet = service.allvwork();
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
		
		List<Shelter> list = service.getShelterlist();
		mav.addObject("list",list);
		
		return mav;
	}
	
	@PostMapping("vmain")
	public ModelAndView chkloginvmain2(String shelter_no,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}	
		HashSet<String> hashSet = new HashSet<>(); 
		if (shelter_no == null || shelter_no.equals("")) {
			hashSet = service.allvwork();
		} else {
			hashSet = service.sheltervwork(shelter_no);
		}
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
		List<Shelter> list = service.getShelterlist();
		mav.addObject("list",list);
		mav.addObject("shelter_no",shelter_no);
		
		return mav;
	}	
	
	@GetMapping("vwrite")
	public ModelAndView chksmvwriteform(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		model.addAttribute(new Vwork());
		Member smem = (Member)session.getAttribute("loginsmem");
		String shelter_no = smem.getShelter_no();
		Shelter shelter = service.getShelter(shelter_no);
		mav.addObject("shelter",shelter);
		return mav;
	}
	
	@PostMapping("vwrite")
	public ModelAndView chksmvwrite(@Valid Vwork vwork, BindingResult bresult, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		String shelter_no = smem.getShelter_no();
		Shelter shelter = service.getShelter(shelter_no);
		mav.addObject("shelter",shelter);
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
			service.vWrite(vwork, request);		
		} catch(Exception e) {
			e.printStackTrace();
			throw new VworkException("봉사 등록에 실패했습니다","vwrite.dog?date="+vwork.getVwork_date());
		}
		mav.setViewName("redirect:gotoMain.dog");
		return mav;
	}
	
	//(value="replyList", produces="text/plain; charset=UTF-8")
	@PostMapping("shelterNames")
	@ResponseBody
	public List<Shelter> shelterNames(String goo) {
		List<Shelter> shelters = service.getShelters(goo);
		return shelters;
	}
	
	@RequestMapping("vlist")
	public ModelAndView chkmvlist(String date, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}

		List<Vwork> vworklist = service.getVlist(date); //해당 날짜의 봉사들
		//ArrayList<String> list = new ArrayList(); //지역구, 보호소. 현재인원, 총 인원
		ArrayList<Map<String,String>> list = new ArrayList();
		for (Vwork v : vworklist) {
			Shelter shelter = service.getShelter(v.getShelter_no());
			Map<String,String> map = new HashMap();
			map.put("address",shelter.getShelter_address());
			map.put("name",shelter.getShelter_name());
			
			int Nowmem = service.getNowmem(v.getVwork_no());
			String Nmem = String.valueOf(Nowmem);
			map.put("Nmem",Nmem);
			String Vmem = String.valueOf(v.getVwork_member()); 
			map.put("Vmem",Vmem);
			String vno = String.valueOf(v.getVwork_no());
			map.put("vwork_no",vno);
			list.add(map);
		}
		int listcnt = service.getListcnt(date);
		mav.addObject("listcnt",listcnt);
		mav.addObject("list",list);

		return mav;
	}
	
	@GetMapping("vdetail")
	public ModelAndView chkmvdetail(String vwork_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}
		Map<String,String> map = new HashMap<>();
		Vwork dbvwork = service.getVwork(vwork_no);
		Shelter shelter = service.getShelter(dbvwork.getShelter_no());
		map.put("address",shelter.getShelter_address());
		map.put("name",shelter.getShelter_name());		
		int Nowmem = service.getNowmem(dbvwork.getVwork_no());
		String Nmem = String.valueOf(Nowmem);
		map.put("Nmem",Nmem);
		String Vmem = String.valueOf(dbvwork.getVwork_member()); 
		map.put("Vmem",Vmem);
		String vno = String.valueOf(dbvwork.getVwork_no());
		map.put("vwork_no",vno);
		map.put("vwork_content",dbvwork.getVwork_content());
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = format.format(dbvwork.getVwork_date());
		map.put("date",date);
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String vwork_date = format2.format(dbvwork.getVwork_date());
		map.put("vwork_date",vwork_date);		
		map.put("member_id",dbvwork.getMember_id());
		mav.addObject("vwork",map);
		return mav;
	}
	
	@GetMapping("vjoin") 
	public ModelAndView chkmvjoinform(Model model, String vwork_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} else {
			Member mem = (Member)session.getAttribute("loginmem");
			mav.addObject("mem",mem);
		}
		model.addAttribute(new Vworklist());
		Map<String,String> map = new HashMap<>();
		Vwork dbvwork = service.getVwork(vwork_no);
		Shelter shelter = service.getShelter(dbvwork.getShelter_no());
		map.put("address",shelter.getShelter_address());
		map.put("name",shelter.getShelter_name());		
		int Nowmem = service.getNowmem(dbvwork.getVwork_no());
		String Nmem = String.valueOf(Nowmem);
		map.put("Nmem",Nmem);
		String Vmem = String.valueOf(dbvwork.getVwork_member()); 
		map.put("Vmem",Vmem);
		String vno = String.valueOf(dbvwork.getVwork_no());
		map.put("vwork_no",vno);
		map.put("vwork_content",dbvwork.getVwork_content());
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = format.format(dbvwork.getVwork_date());
		map.put("date",date);
		mav.addObject("vwork",map);
		return mav;
	}
		
	@PostMapping("vjoin")
	public ModelAndView chkmvjoin(@Valid Vworklist vworklist, Model model,BindingResult bresult, String vwork_no, HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();	
		Member smem = (Member)session.getAttribute("loginsmem");
		Member mem = (Member)session.getAttribute("loginmem");
		if(smem != null) {
			mav.addObject("smem",smem);
		} 
		if(mem != null) {
			mav.addObject("mem",mem);
		}
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}	
		Vwork dbvwork = service.getVwork(vwork_no);
		Date date = dbvwork.getVwork_date();		
		vworklist.setVwork_date(date);
		vworklist.setVwork_no(vwork_no);
		try {
			service.vJoin(vworklist, request);
		} catch(Exception e) {
			throw new VworkException("이미 해당 봉사를 신청했습니다.","vdetail.dog?vwork_no="+vwork_no);
		}		
		mav.setViewName("redirect:../member/vworkMypage.dog?type=2&id="+mem.getMember_id()); //해당 멤버의 마이페이지(봉사신청)
		return mav;
	}
	
	@GetMapping("vupdate")
	public ModelAndView chkadminsmemvupdateform(String vwork_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Vwork dbvwork = service.getVwork(vwork_no);
		Shelter shelter = service.getShelter(dbvwork.getShelter_no());
		mav.addObject("shelter",shelter);
		mav.addObject("vwork",dbvwork);
		return mav;
	}
	
	@PostMapping("vupdate")
	public ModelAndView chkadminsmemvupdate(@Valid Vwork vwork, String vwork_no, BindingResult bresult, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member smem = (Member)session.getAttribute("loginsmem");
		
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		int vworkno =  Integer.parseInt(vwork_no);
		vwork.setVwork_no(vworkno);
		vwork.setMember_id(smem.getMember_id());
		Vwork dbvwork = service.getVwork(vwork_no);
		vwork.setShelter_no(dbvwork.getShelter_no());
		try {
			service.vUpdate(vwork, request);
		} catch(Exception e) {
			throw new VworkException("봉사 수정에 실패했습니다","vupdate.dog?vwork_no="+vwork_no);
		}
		mav.setViewName("redirect:vdetail.dog?vwork_no="+vwork.getVwork_no());
		return mav;
	}
	
	@GetMapping("vdelete")
	public ModelAndView chkadminsmemvdeleteform(String vwork_no, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Vwork dbvwork = service.getVwork(vwork_no);
		Shelter shelter = service.getShelter(dbvwork.getShelter_no());
		int Nmem = service.getNowmem(dbvwork.getVwork_no());
		mav.addObject("shelter",shelter);
		mav.addObject("vwork",dbvwork);
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = format.format(dbvwork.getVwork_date());
		mav.addObject("date",date);
		mav.addObject("Nmem",Nmem);
		return mav;
	}
	
	@PostMapping("vdelete")
	public ModelAndView chkadminsmemvdelete(String vwork_no, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		try {
			service.vdelete(vwork_no);
		} catch(Exception e) {
			e.printStackTrace();
			throw new VworkException("봉사 삭제에 실패했습니다","vdetail.dog?vwork_no="+vwork_no);
		}
		mav.setViewName("redirect:vmain.dog");
		return mav;
	}

}
