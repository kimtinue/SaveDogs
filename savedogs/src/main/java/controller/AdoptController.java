package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.AdoptException;
import logic.Adopt;
import logic.AdoptSign;
import logic.ApiExplorer;
import logic.DogService;
import logic.Member;
import logic.Shelter;

@Controller
@RequestMapping("adopt")
public class AdoptController {

	@Autowired
	private DogService service;

	@RequestMapping("amain")
	public ModelAndView main2(Adopt adopt, String state, String kind, Integer pageNo) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (pageNo == null || pageNo.toString().equals(""))
			pageNo = 1;
		if (state == null || state.trim().equals(""))
			state = null;
		if (kind == null || kind.trim().equals(""))
			kind = null;
		/*
		 * pageNo : 현재 페이지 번호 
		 * maxpage : 최대 페이지 
		 * startpage : 보여지는 시작 페이지 번호 
		 * endpage : 보여지는 끝 페이지 번호 
		 * listcount : 전체 등록된 게시글 건수
		 */
		int limit = 16; // 한 페이지에 보여질 게시물 건수
		long totalcount = (long) ApiExplorer.getTotalCount(state, kind, pageNo);
		long listcount = totalcount; // 등록 게시물 건수
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (int) ((pageNo / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		mav.addObject("pageNo", pageNo);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		try {
			List<Adopt> go = ApiExplorer.getDogsJson(state, kind, pageNo);
			mav.addObject("go", go);
			mav.setViewName("/adopt/amain");
		} catch (Exception e) {
			// e.printStackTrace();
			String message = "검색결과가 없습니다.";
			mav.addObject("message", message);
		}
		return mav;
	}

	@RequestMapping("adetail")
	public ModelAndView detail(String noticeNo) throws Exception {
		ModelAndView mav = new ModelAndView();
		Adopt go = ApiExplorer.getDogJson(noticeNo);
		mav.addObject("go", go);
		return mav;
	}

	@GetMapping("adoptSignup")
	public ModelAndView chkmasignup(Model model, String noticeNo, String careNm, String orgNm, HttpSession session)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		model.addAttribute(new AdoptSign());
		List<AdoptSign> list = service.getAdoptlist();
		for (AdoptSign ad : list) {
			if (ad.getDog_no().equals(noticeNo) && ad.getAdopt_etc() != 1) {
				throw new AdoptException("입양 절차 진행 중입니다.", "amain.dog");
			}
		}
		return mav;
	}

	@PostMapping("adoptSignup")
	public ModelAndView asignup2(String careNm, String orgNm, AdoptSign a, MultipartFile adopt_f,
			HttpServletRequest request) throws Exception {
		String[] orgNms = orgNm.split(" ");
		String split1 = orgNms[0];
		String split2 = null;
		if (orgNms.length > 1)
			split2 = orgNms[1];
		String co = "";
		if (split2 == "") {
			co = split1.concat(careNm); // 서울특별시 구디보호소
		} else if (split2 != "") {
			co = split2.concat(" " + careNm); // 구로구 구디보호소
		}

		ModelAndView mav = new ModelAndView();
		List<Shelter> hap = service.getHaplist();
		String num = "";
		for (Shelter ss : hap) {
			if (ss.getHap().equals(co)) {
				num = ss.getShelter_no();
			}
		}

		List<Member> sm = service.getSmemberList();
		for (Member m : sm) {
			if (m.getShelter_no().equals(num)) {
				a.setShelter_no(num);
				a.setF(adopt_f);
				service.adoptInsert(a, request);
				mav.setViewName("redirect:../member/adoptMypage.dog?type=4&id=" + a.getMember_id());
			}
		}

		if (a.getShelter_no() == null) {
			throw new AdoptException("보호소 관리자가 존재하지 않습니다. 해당 보호소로 문의 바랍니다.", "adetail.dog?noticeNo=" + a.getDog_no());
		}

		return mav;
	}

}
