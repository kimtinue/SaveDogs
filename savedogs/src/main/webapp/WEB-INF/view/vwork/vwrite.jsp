<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 등록</title>
<link rel='stylesheet' href='../css/style.css' />
<link rel='stylesheet' href='../css/savedogs_exc.css' />
</head>
<body>
<div class="vdiv">
<h3>봉사 등록</h3>
<form:form modelAttribute="vwork" action="vwrite.dog" method="post" name="f">
	<form:hidden path="member_id" value="${sessionScope.loginsmem.member_id }"/>
	<form:hidden path="shelter_no" value="${sessionScope.loginsmem.shelter_no }"/>
	<table>
		<tr><th>날짜</th><td><form:input type="text" path="vwork_date" readonly="readonly" value="${param.date }"/></td></tr>
		<tr><th>보호소</th><td><input type="text" value="${shelter.shelter_address }" readonly="readonly">
							 <input type="text" value="${shelter.shelter_name }" readonly="readonly"></td></tr>
		<tr><th>모집인원</th><td><form:input path="vwork_member"/>&nbsp;명&nbsp;<font color="red"><form:errors path="vwork_member"/></font></td></tr>
		<tr><th>설명</th><td><form:textarea path="vwork_content" rows="15" cols="80"/><br><font color="red"><form:errors path="vwork_content"/></font></td></tr>
		<tr><th colspan="2"><input type="submit" class="s_btn" value="등록"></th></tr>
	</table>
</form:form>
</div>
</body>
</html>
