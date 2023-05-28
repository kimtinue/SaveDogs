<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 수정</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
th{
	padding: 15px;
	width: 20%;
}
.udiv{
	display: inline-block;
	width: 65%;
}
</style>
</head>
<body>
<div class="main_div">
<div class="udiv">
<h2>봉사 수정</h2>
<hr>
<form:form modelAttribute="vwork" action="vupdate.dog" method="post" name="f">
	<form:hidden path="vwork_no" value="${param.vwork_no }"/>
	<table>
		<tr><th>날짜</th><td><form:input type="text" readonly="readonly" path="vwork_date" /></td></tr>
		<tr><th>보호소</th><td><input type="text" readonly="readonly" value="${shelter.shelter_address }">
							 <input type="text" readonly="readonly" value="${shelter.shelter_name }"></td></tr>
		<tr><th>모집인원</th><td><form:input path="vwork_member" />&nbsp;명&nbsp;<font color="red"><form:errors path="vwork_member"/></font></td></tr>
		<tr><th>설명</th><td><form:textarea path="vwork_content" rows="15" cols="80" /><br><font color="red"><form:errors path="vwork_content"/></font></td></tr>
		<tr><th colspan="2"><input type="submit" class="s_btn" value="수정"></th></tr>
</table>
</form:form>
</div>
</div>
</body>
</html>