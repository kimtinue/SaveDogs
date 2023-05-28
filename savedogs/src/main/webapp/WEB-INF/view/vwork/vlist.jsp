<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 목록</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
.vlistdiv{
	padding: 30px;
}
.btn_th{
	width: 260px;
}
th{
	width: 200px;
	padding: 15px;
	font-size: 22px;
}
td{
	text-align: center;
}


</style>
<script type="text/javascript">
function date_search() {
	var date = $('#date').val();
	location.href='vlist.dog?date='+date;
}

</script>
</head>
<body>
<div class="main_div">
	<h2>봉사 신청</h2>
	<hr>
	<table class="sel_table"><tr><th>보호소 선택</th><th>></th><th style="color: #AAAAAA;">최종 신청</th></tr></table>
	
	<div class="vlistdiv">
		<div class="vlistdiv">
			날짜&nbsp;:&nbsp;&nbsp;<input type="date" value="${param.date }" id="date">
			<input type="button" class="small_btn" value="검색" onclick="date_search()"> 
		</div>
		<div class="vlistdiv">
			<table>
			<c:if test="${listcnt > 0 }"> 
				<tr><th>no</th><th>지역</th><th>보호소</th><th>모집인원</th><th class="btn_th">&nbsp;</th></tr>
				
				<c:forEach var="vwork" items="${list }" varStatus="stat">
				<tr>
					<td>${stat.count}</td>
					<td>${vwork.address }</td>
					<td>${vwork.name }</td>
					<td>${vwork.Nmem }/${vwork.Vmem }</td>
					
					<td>
						<input type="button" class="g_btn" value="상세보기" onclick="location.href='vdetail.dog?vwork_no=${vwork.vwork_no}'">
						<c:if test="${vwork.Nmem != vwork.Vmem && sessionScope.loginmem != null}">
							<input type="button" value="신청" class="s_btn" onclick="location.href='vjoin.dog?vwork_no=${vwork.vwork_no}'">
						</c:if>
						<c:if test="${vwork.Nmem == vwork.Vmem }">
							<input type="button" value="모집완료" class="g_btn">
						</c:if>
					</td>
				</tr>
				</c:forEach>
					
			</c:if>
			<c:if test="${listcnt == 0 }"> 
				<tr><td colspan="5">해당 날짜 봉사활동이 없습니다.</td></tr>
			</c:if>
			</table>
		</div>
	</div>
</div>


</body>
</html>