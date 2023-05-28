package logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiExplorer {
	public static String getDogData(String state, String kind, Integer pageNo) throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"); // 유기동물 조회
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=LkKlaYPxZnedB774kYO9uNzE0Eaa07yjZfE05Sh3BR%2F7GLjPuQd%2BdSErZFAPjdkiyM4cDxpkdBOQZ1ckRjyERw%3D%3D"); // 서비스키
		urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" 
				+ URLEncoder.encode("417000", "UTF-8")); // upkind : 개(417000)
		urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") 
				+ "=" + URLEncoder.encode("6110000", "UTF-8")); // upr_cd : 서울특별시(6110000)
		if (pageNo != 1) {
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") 
			+ "=" + URLEncoder.encode(pageNo.toString(), "UTF-8")); // pageNo : 페이지 번호(1)
		}
		if (state == null || state.equals("")) {
			state = "null";
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") 
				+ "=" + URLEncoder.encode(state, "UTF-8")); // state : 상태
		} else if (state != null || !state.equals("")) {
			urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8"));
		}
		if (kind != null) {
			urlBuilder.append("&" + URLEncoder.encode("kind", "UTF-8") 
				+ "=" + URLEncoder.encode(kind, "UTF-8")); // kind : 품종
		}
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") 
				+ "=" + URLEncoder.encode("17", "UTF-8")); // numOfRows : 페이지당 보여줄 갯수 (4*4=16)
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") 
				+ "=" + URLEncoder.encode("json", "UTF-8")); // json으로 받음
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "application/json");

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}

	public static List<Adopt> getDogsJson(String state, String kind, Integer pageNo) throws Exception {
		String result = getDogData(state, kind, pageNo);
		// Json처럼 생긴 String을 json으로 만들기
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(result); // 리턴값 object -> json

		// response
		JSONObject j_response = (JSONObject) jsonObj.get("response"); // 리턴값 object -> json
		JSONObject j_body = (JSONObject) j_response.get("body");
		JSONObject j_items = (JSONObject) j_body.get("items");
		JSONArray a_item = (JSONArray) j_items.get("item");
		a_item.remove(0);
		Gson gson = new Gson();

		List<Adopt> list = gson.fromJson(a_item.toString(), new TypeToken<List<Adopt>>() {
		}.getType());
		for (Adopt adopt : list) {
			adopt.setKindCd(adopt.getKindCd().substring(4));
		}
		return list;
	}

	private static String getDogData() throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=LkKlaYPxZnedB774kYO9uNzE0Eaa07yjZfE05Sh3BR%2F7GLjPuQd%2BdSErZFAPjdkiyM4cDxpkdBOQZ1ckRjyERw%3D%3D");
		urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" + URLEncoder.encode("6110000", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode("null", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("400", "UTF-8")); // totalCount
		urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "application/json");

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}

	public static Adopt getDogJson(String noticeNo) throws Exception {
		String result = getDogData();
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(result);
		JSONObject j_response = (JSONObject) jsonObj.get("response");
		JSONObject j_body = (JSONObject) j_response.get("body");
		JSONObject j_items = (JSONObject) j_body.get("items");
		JSONArray a_item = (JSONArray) j_items.get("item");
		a_item.remove(0);
		Gson gson = new Gson();

		List<Adopt> list = gson.fromJson(a_item.toString(), new TypeToken<List<Adopt>>() {
		}.getType());
		Adopt aa = new Adopt();
		for (Adopt a : list) {
			a.setKindCd(a.getKindCd().substring(4));
			a.setSexCd(a.getSexCd() == "F" ? "암컷" : "수컷");
			if (a.getNoticeNo().equals(noticeNo)) {
				aa = a;
			}
		}
		return aa;
	}

	public static Object getTotalCount(String state, String kind, Integer pageNo) throws Exception {
		String result = getDogData(state, kind, pageNo);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(result);
		JSONObject j_response = (JSONObject) jsonObj.get("response");
		JSONObject j_body = (JSONObject) j_response.get("body");
		return j_body.get("totalCount");
	}

}
