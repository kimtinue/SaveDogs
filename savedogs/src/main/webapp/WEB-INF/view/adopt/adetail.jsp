<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기견 상세</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
img {
	width: 500px;
	height: 500px;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#minfo").show();
		$("#oinfo").hide();
		$("#tab1").addClass("g_font");
	})
	function disp_div(id, tab) {
		$(".info").each(function() {
			$(this).hide();
		})
		$(".tab").each(function() {
			$(this).removeClass("g_font");
		})
		$("#" + id).show();
		$("#" + tab).addClass("g_font");
	}
	function list_disp(id) {
		$("#" + id).toggle();
	}
</script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c39f4555ea6d603b34e2414423cc41bf&libraries=services"></script>

<style type="text/css">
th {
	width: 150px;
	text-align: left;
}
</style>
</head>
<body>
	<form action="adoptSignup.dog" method="get">
		<input type="hidden" name="noticeNo" value="${go.noticeNo}"> <input
			type="hidden" name="careNm" value="${go.careNm}"> <input
			type="hidden" name="orgNm" value="${go.orgNm}">

		<div>
			<h2>상세 정보</h2>
			<br>
			<table>
				<tr>
					<td><a id="tab1" class="tab"
						href="javascript:disp_div('minfo','tab1')">유기견 정보</a></td>
					<td><a id="tab2" class="tab"
						href="javascript:disp_div('oinfo','tab2')">보호소 정보</a></td>
				</tr>
			</table>
			<div id="minfo" class="info">
				<img src="${go.popfile}"> <br>
				<table>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>공고 기간</th>
						<td><fmt:formatDate value="${go.noticeSdt}"
								pattern="yyyy-MM-dd" /> 부터 <fmt:formatDate
								value="${go.noticeEdt}" pattern="yyyy-MM-dd" /> 까지</td>
					</tr>
					<tr>
						<th>공고 번호</th>
						<td>${go.noticeNo}</td>
					</tr>
					<tr>
						<th>구조일
						<td><fmt:formatDate value="${go.happenDt}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<th>구조 장소
						<td>${go.happenPlace}</td>
					</tr>
					<tr>
						<th>상태</th>
						<td>${go.processState}</td>
					</tr>
					<tr>
						<th>종류</th>
						<td>${go.kindCd}</td>
					</tr>
					<tr>
						<th>색상</th>
						<td>${go.colorCd}</td>
					</tr>
					<tr>
						<th>성별
						<td>${go.sexCd}</td>
					</tr>
					<tr>
						<th>나이
						<td>${go.age}</td>
					</tr>
					<tr>
						<th>무게
						<td>${go.weight}</td>
					</tr>
					<tr>
						<th>특징
						<td>${go.specialMark}</td>
					</tr>
				</table>
			</div>
			<div id="oinfo" class="info">
				<div id="map"
					style="width: 800px; height: 500px; display: inline-block;"></div>
				<br>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};

					// 지도 생성
					var map = new kakao.maps.Map(mapContainer, mapOption);

					// 주소-좌표 변환 객체 생성
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색
					geocoder
							.addressSearch(
									'${go.careAddr}',
									function(result, status) {

										// 정상적으로 검색이 완료
										if (status === kakao.maps.services.Status.OK) {

											var coords = new kakao.maps.LatLng(
													result[0].y, result[0].x);

											// 결과값으로 받은 위치를 마커로 표시
											var marker = new kakao.maps.Marker(
													{
														map : map,
														position : coords
													});

											// 인포윈도우로 장소에 대한 설명을 표시
											var infowindow = new kakao.maps.InfoWindow(
													{
														content : '<div style="width:200px;text-align:center;padding:6px 0;">${go.careNm}</div>'
													});
											infowindow.open(map, marker);

											// 지도의 중심을 결과값으로 받은 위치로 이동
											map.setCenter(coords);
										}
									});
				</script>
				<table>
					<tr>
						<th>주소</th>
						<td>${go.careAddr}</td>
					</tr>
					<tr>
						<th>보호소명</th>
						<td>${go.careNm}</td>
					</tr>
					<tr>
						<th>보호소 연락처</th>
						<td>${go.careTel}</td>
					</tr>
					<tr>
						<th>담당자</th>
						<td>${go.chargeNm}</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>${go.officetel}</td>
					</tr>
				</table>
			</div>
			<br>
			<c:if test="${go.processState == '보호중'}">
				<!-- 입양 가능(보호중) 상태인 경우만 [입양 신청]버튼 표시 -->
				<input type="submit" value="입양 신청" class="s_btn"
					onclick="location.href='adoptSignup.dog'">
			</c:if>
		</div>
	</form>
</body>
</html>