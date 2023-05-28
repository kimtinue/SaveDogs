package controller;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logic.DogService;

@RestController
@RequestMapping("ajax")
public class AjaxController {
	@Autowired
	private DogService service;
	
	@RequestMapping("changestate")
	public String changestate(String item_no, String item_state) {
		service.updateState(item_no, item_state);
		return null;
	}
	
	@RequestMapping("changeauth")
	public String changeauth(String member_id, String member_auth) {
		service.updateAuth(member_id, member_auth);
		return null;
	}
	
	@RequestMapping("changeEtc")
	public String changeEtc(String dog_no, String state) {
		System.out.println("dog_no : " + dog_no + "state : " + state);
		service.updateEtc(dog_no, state);
		return null;
	}
	
	@RequestMapping(value="vworkgraph", produces="text/plain; charset=UTF8")
	public String vworkgraph(String year, String member_id) {
		Map<String, Object> map = service.vworkgraph(year, member_id);
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for(Map.Entry<String,Object> me : map.entrySet()) {
			json.append("{\"m\":\""+me.getKey() + "\"," + "\"cnt\":\""+me.getValue()+"\"}");
			i++;
			if(i<map.size())
				json.append(",");
		}
		json.append("]");
		System.out.println(json.toString());
		return json.toString();
	}
	
	@RequestMapping(value="fundgraph", produces="text/plain; charset=UTF8")
	public String fundgraph(String year, String member_id) {
		Map<String, Object> map = service.fundgraph(year, member_id);
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for(Map.Entry<String,Object> me : map.entrySet()) {
			json.append("{\"m\":\""+me.getKey() + "\"," + "\"cnt\":\""+me.getValue()+"\"}");
			i++;
			if(i<map.size())
				json.append(",");
		}
		json.append("]");
		return json.toString();
	}
	
	@RequestMapping(value="shopgraph", produces="text/plain; charset=UTF8")
	public String shopgraph(String name) {
		Map<String, Object> map = service.shopgraph(name);
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for(Map.Entry<String,Object> me : map.entrySet()) {
			json.append("{\"m\":\""+me.getKey() + "\"," + "\"cnt\":\""+me.getValue()+"\"}");
			i++;
			if(i<map.size())
				json.append(",");
		}
		json.append("]");
		return json.toString();
	}
	
	@RequestMapping(value="shopallgraph", produces="text/plain; charset=UTF8")
	public String shopallgraph() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		Map<String, Object> map = service.shopallgraph(year+"");
		StringBuilder json = new StringBuilder("[");
		int i = 0;
		for(Map.Entry<String,Object> me : map.entrySet()) {
			json.append("{\"m\":\""+me.getKey() + "\"," + "\"cnt\":\""+me.getValue()+"\"}");
			i++;
			if(i<map.size())
				json.append(",");
		}
		json.append("]");
		return json.toString();
	}
}