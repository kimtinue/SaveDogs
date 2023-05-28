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

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.DogService;
import logic.Funding;
import logic.Item;
import logic.Member;

@Controller
public class MainController {
	@Autowired
	private DogService service;
	
	@GetMapping("*")
	public String info() {
		return null;
	}
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		Member mem = new Member();
		mav.addObject(mem);
		
		String url = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=%EC%9C%A0%EA%B8%B0%EA%B2%AC";
		List<String> newstitle = new ArrayList<>();
		int a = 0;
		try{
			Document doc = Jsoup.connect(url).get();
			Elements body = doc.select(".type01> li");
			for(Element li : body){
				Elements thumb = li.select(".thumb > a");
				for(Element pic : thumb){
					Elements img = pic.select("img");
					System.out.println(img.html());
				}
				Elements dl = li.select("dl");
				for(Element dt : dl){
					Elements atag = dt.select("dt");
					for(Element val : atag){
						Elements title = val.select("a");
						if(a<6) {
							newstitle.add(title.toString());
							a++;
						}
					}
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		List<String> newsimg= new ArrayList<>();
		int b = 0;
		try{
			Document doc = Jsoup.connect(url).get();
			Elements body = doc.select(".type01> li");
			for(Element li : body){
				Elements thumb = li.select(".thumb > a");
				for(Element pic : thumb){
					Elements img = pic.select("img");
					if(b<6) {
						newsimg.add(img.toString());
						b++;
					}
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		List<Map<String, String>> infolist = new ArrayList<Map<String,String>>();
		String imgurl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?upr_cd=6110000&bgnde=20200801&endde=20200810&upkind=417000&pageNo=1&numOfRows=10&ServiceKey=LBFXEs0hIGvsna06ms6DL%2BOAQJeCCzkEcJLsSEjqrgxVvyB6owDk7VJh8QnuXz9qthbzx%2FqHbGbPP1MbJH7agA%3D%3D";
		URL u = new URL(imgurl); 
		HttpURLConnection urlcon = (HttpURLConnection)u.openConnection(); //실제  url에 맞도록 url에 접속
		urlcon.setRequestProperty("Accept", "application/xml");
		String line = "";
		String title = "";
		Document doc = null ;
		StringBuilder sb = new StringBuilder();
		int im = 0;
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"UTF-8"));
			int len = 0;
			char[] buf = new char[8096];
			while((len = in.read(buf)) != -1){
				sb.append(new String(buf,0,len));
			}
			doc = Jsoup.parse(sb.toString());  
			Elements body = doc.select("item");
			for(Element ele : body){
				Elements picture = ele.select("popfile");
				Elements kindCd = ele.select("kindCd");
				Elements sexCd = ele.select("sexCd");
				Elements age = ele.select("age");
				Elements orgNm = ele.select("orgNm");
				if(im<9) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("picture", picture.html());
					
					map.put("kindCd",kindCd.html().substring(3));
					map.put("age",age.html());
					map.put("sexCd",(sexCd.html()=="F")?"암컷":"수컷");
					map.put("orgNm",orgNm.html().substring(5));
					infolist.add(map);
					im++;
				}
			}
		}catch(IOException e){e.printStackTrace();}
		List<Board> noticelist = service.mainnotice();
		List<Item> bestItem= service.bestItem();
		List<Funding> duefunding = service.duefunding();
		request.setAttribute("duefunding", duefunding);
		request.setAttribute("bestItem", bestItem);
		request.setAttribute("notice", noticelist);
		request.setAttribute("newstitle", newstitle);
		request.setAttribute("newsimg", newsimg);
		request.setAttribute("info", infolist);
		return mav; 
	}
	@RequestMapping("fundinganditem/list")
	public ModelAndView fundinganditem() {
		ModelAndView mav = new ModelAndView();
		Item item = new Item();
		Funding funding = new Funding();
		mav.addObject(item);
		mav.addObject(funding);
		return mav;
	}
}
