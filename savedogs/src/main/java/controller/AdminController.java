package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.DogService;
import logic.Item;
import logic.Member;
import logic.Shelter;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private DogService service;
	
	@RequestMapping("*")
	public String all() {
		return null;
	}
	
	@GetMapping("adminMypage")
	public ModelAndView adminmainad(String id, HttpSession session)  {
		ModelAndView mav = new ModelAndView();
		Member mem = service.getMember(id);
		mav.addObject("mem", mem);
		return mav;
	}
	
	@GetMapping("adminlistMypage")
	public ModelAndView adminlistad(HttpSession session)  {
		ModelAndView mav = new ModelAndView();
		List<Member> memberlist = service.getMemberList();
		List<Member> smemberlist = service.getSmemberList();
		mav.addObject("memberlist", memberlist);
		mav.addObject("smemberlist", smemberlist);
		return mav;
	}
	
	@GetMapping("adminshopMypage")
	public ModelAndView adminshopad(HttpSession session)  {
		ModelAndView mav = new ModelAndView();
		List<Item> shopalllist = service.getShopall();
		mav.addObject("shopalllist", shopalllist);
		return mav;
	}
	
	@PostMapping("recent")
	public ModelAndView shelterlist(HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		//영현
		//String serviceKey = "vqvwqgN6%2Fq8tULP9RBlZ3%2BiHyy2X4cm2M6%2BimTAeJ1y9HiH1ECvwfptH26lZp%2F%2FZzKZ7AUpKNVKHqDchKnZ7wg%3D%3D";
		//동욱
		//String serviceKey = "LBFXEs0hIGvsna06ms6DL%2BOAQJeCCzkEcJLsSEjqrgxVvyB6owDk7VJh8QnuXz9qthbzx%2FqHbGbPP1MbJH7agA%3D%3D";
		//혜수
		String serviceKey = "y9houM3dCOwi71KDPlZK2Hn8mOJ9YIG1wmQPxF%2F%2BwOvbRNX%2Bi%2BxEFougUn34DEXjCzAs1%2FXjXx5d1%2BuBMrO1Zg%3D%3D";
		String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?&ServiceKey=" + serviceKey;
		URL u = new URL(url);
		HttpURLConnection urlcon = (HttpURLConnection)u.openConnection();
		urlcon.setRequestProperty("Accept","application/xml");
		
		Document doc = null;
		String seoulcode = null;
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"UTF-8"));
			int len = 0;
			char[] buf = new char[8096];
			while((len = in.read(buf)) != -1) {
				sb.append(new String(buf,0,len));
			}
			doc = Jsoup.parse(sb.toString());
			Elements item = doc.select("item");
			for(Element src : item) {
				if(src.select("orgdownNm").html().equals("서울특별시")) {
					seoulcode = src.select("orgCd").html();
					break;
				}
			}
			//out.println("서울특별시 코드값 : " + seoulcode + "<br>");
			doc = null;
			url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sigungu?upr_cd="+ seoulcode +"&ServiceKey=" + serviceKey;
			u = new URL(url);
			urlcon = (HttpURLConnection)u.openConnection();
			urlcon.setRequestProperty("Accept","application/xml");
			in = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"UTF-8"));
			len = 0;
			buf = new char[8096];
			sb = new StringBuilder();
			while((len = in.read(buf)) != -1) {
				sb.append(new String(buf,0,len));
			}
			doc = Jsoup.parse(sb.toString());
			item = doc.select("item");
			List<String> list = new ArrayList<String>();		//구 코드 담길 리스트
			Map<String, String> map = new HashMap<String, String>();	//(구 코드, 구 이름) 담길 맵
			int j = 0;
			for(Element src : item) {
				if(!src.select("orgdownNm").html().equals("가정보호")) {
					list.add(src.select("orgCd").html());
					map.put(list.get(j), src.select("orgdownNm").html());
					j++;
				}
			}
			Map<String, Map<String,String>> data = new HashMap<>();		//(보호소코드, (보호소명, 지역구))
			for(int i = 0; i<list.size(); i++) {
				url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter?upr_cd=" + seoulcode + "&org_cd=" + list.get(i) + "&ServiceKey=" + serviceKey;
				u = new URL(url);
				urlcon = (HttpURLConnection)u.openConnection();
				urlcon.setRequestProperty("Accept","application/xml");
				in = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"UTF-8"));
				len = 0;
				buf = new char[8096];
				sb = new StringBuilder();
				while((len = in.read(buf)) != -1) {
					sb.append(new String(buf,0,len));
				}
				//System.out.println(new String(sb.toString().getBytes(), "UTF-8"));
				doc = Jsoup.parse(sb.toString());
				item = doc.select("item");
				
				for(Element src : item) {
					Map<String, String> name = new HashMap<>();
					name.put(src.select("careNm").html(), map.get(list.get(i)));
					data.put(src.select("careRegNo").html(), name);
				}
			}
//			service.deleteAllList();
//			service.insertInit();
			service.insertSlist(data);
			mav.setViewName("redirect:adminMypage.dog?type=1&id=admin");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mav;
	}
}