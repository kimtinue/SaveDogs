<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel='stylesheet' href='../css/savedogs_exc.css' />
</head>
<body>
	<div align="center">
		<h2>비밀번호 찾기</h2>
	</div>
	<hr>
	<form action="pwfind.dog" method="post">
		<table><tr><th>아이디</th><td><input name="id"></td></tr></table>
		<br>
		<table><tr><th>전화번호</th><td><input name="tel"></td></tr></table>
		<br>
		<table><tr><th>이메일</th><td><input name="email"></td></tr></table>
	<input type="submit" value="제출">
	</form>
</body>
</html>