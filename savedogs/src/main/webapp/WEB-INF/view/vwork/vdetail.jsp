<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 상세</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8354affff1aee80d0217dedba05a99ab&libraries=services"></script>
<style type="text/css">
th{
	padding: 15px;
	width: 20%;
}
.btn_div{

	text-align: center;
}
</style>
<script type="text/javascript">
var protocol = location.protocol;
var hostName = location.hostname;
var port = location.port;
 
console.log(protocol); // http:
console.log(hostName); // includestdio.tistory.com
console.log(port);



</script>
</head>
<body>
<div class="main_div">
<h2>봉사 정보</h2>
<hr>
<table>
	<tr><th>날짜</th><td>${vwork.date }</td></tr>
	<tr><th>보호소</th><td>${vwork.address }${vwork.name }</td></tr>
	<tr><th>위치</th><td><div id="map" style="width:500px;height:400px;"></div></td></tr>
	<tr><th>모집인원</th><td>${vwork.Nmem }/${vwork.Vmem } &nbsp;명</td></tr>
	<tr><th>설명</th><td style="white-space:pre;">${vwork.vwork_content }</td></tr>
</table>
<input type="hidden" value="${vwork.address } ${vwork.name }" id="shelter">
<script type="text/javascript">
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	var mapContainer = document.getElementById('map'),  
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), 
	        level: 3
	    };  

	var map = new kakao.maps.Map(mapContainer, mapOption); 
	var ps = new kakao.maps.services.Places(); 
	var shelter = document.getElementById('shelter').value;
	ps.keywordSearch(shelter, placesSearchCB); 

	function placesSearchCB (data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {
	        var bounds = new kakao.maps.LatLngBounds();
	        for (var i=0; i<data.length; i++) {
	            displayMarker(data[i]);    
	            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	        }       
	        map.setBounds(bounds);
	    } 
	}

	function displayMarker(place) {
	    var marker = new kakao.maps.Marker({
	        map: map,
	        position: new kakao.maps.LatLng(place.y, place.x) 
	    });
	    kakao.maps.event.addListener(marker, 'click', function() {
	        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
	        infowindow.open(map, marker);
	    });
	}
</script>
<br>
<div class="btn_div">
<c:if test="${smem != null }">
	<input type="button" value="목록" class="g_btn" onclick="location.href='vlist.dog?date=${vwork.vwork_date}'">
	<c:if test="${smem.member_id == vwork.member_id }">
		<input type="button" class="g_btn" value="수정" onclick="location.href='vupdate.dog?vwork_no=${vwork.vwork_no}'">
		<input type="button" class="g_btn" value="삭제" onclick="location.href='vdelete.dog?vwork_no=${vwork.vwork_no}'">
	</c:if>
</c:if>
<c:if test="${mem != null }">
	<input type="button" class="g_btn" value="목록" onclick="history.back(-1);">
	<c:if test="${vwork.Nmem != vwork.Vmem }">
		<input type="button" value="신청" class="s_btn" onclick="location.href='vjoin.dog?vwork_no=${vwork.vwork_no}'">
	</c:if>
	<c:if test="${vwork.Nmem == vwork.Vmem }">
		<input type="button" value="모집완료" class="g_btn">
	</c:if>
	
</c:if>

</div>
</div>

</body>
</html>