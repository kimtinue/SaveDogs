<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 삭제</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">

.udiv{
	display: inline-block;
	width: 65%;
}

</style>
</head>
<body>
<div class="main_div">
<div class="udiv">
<h2>봉사 삭제</h2>
<hr>
<form:form modelAttribute="vwork" action="vdelete.dog" method="post" name="f">
	<form:hidden path="vwork_no" value="${param.vwork_no }"/>
	<table>
		<tr><th>날짜</th><td><input type="text" readonly="readonly" value="${date }"></td></tr>
		<tr><th>보호소</th><td><input type="text" readonly="readonly" value="${shelter.shelter_address }">
							 <input type="text" readonly="readonly" value="${shelter.shelter_name }"></td></tr>
		<tr><th colspan="2">&nbsp;</th></tr>
		<tr><th colspan="2">현재 <font class="g_font">${Nmem }명</font>의 봉사 지원자가 있습니다.</th></tr>
		<tr><th colspan="2">정말로 삭제하시겠습니까?</th></tr>
		
		<tr><th colspan="2" class="btn_td">
							<input type="button" class="g_btn" value="취소"  onclick="history.back(-1);">
							<input type="submit" class="s_btn" value="삭제"></th></tr>
	</table>
</form:form>
</div>
</div>
</body>
</html>