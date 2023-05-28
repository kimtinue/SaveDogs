<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 참여 신청</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
.vdiv{
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


</head>
<body>
<div class="main_div">
	<h2>봉사 신청</h2>
	<hr>
	<table class="sel_table"><tr><th style="color: #AAAAAA;">보호소 선택</th><th style="color: #AAAAAA;">></th><th >최종 신청</th></tr></table>
	<div class="vdiv">
		<form:form modelAttribute="vworklist" action="vjoin.dog" method="post" name="f">
			<form:hidden path="vwork_no" value="${param.vwork_no }"/> 
			<form:hidden path="vwork_id" value="${mem.member_id }"/> 
			
			<table>
				<tr>
					<th>날짜</th>
					<td>${vwork.date}</td>
				</tr>
				<tr>
					<th>보호소</th>
					<td>${vwork.address}&nbsp;${vwork.name}</td>
				</tr>
				<tr>
					<th>모집인원</th>
					<td>${vwork.Nmem}/${vwork.Vmem}&nbsp;명</td>
				</tr>
				<tr><th colspan="2">&nbsp;</th></tr>
				<tr><td colspan="2">[ 봉사자 정보 ]</td></tr>
				<tr>
					<th>이름</th>
					<td>${mem.member_name }</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<form:input path="vwork_tel" value="${mem.member_tel }"/><font color="red"><form:errors path="vwork_tel"/></font>			
					</td>
				</tr>
				<tr><th colspan="2">
						<input type="button" class="g_btn" value="이전" onclick="history.back(-1);">
						
						<input type="submit" class="s_btn" value="신청"></th>
			</table>	
		</form:form>
	</div>
</div>
</body>
</html>