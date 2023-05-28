<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
	<div style="width: 100%;">
		<h3>
			<font style="color: red;">회원 정보 확인을 위해 비밀번호를 한번 더 확인합니다.</font>
		</h3>
		<form name="f" method="post" action="checkpass.dog">
			<h5>비밀번호 입력</h5>
			<input type="password" name="member_pass">
			<input class="g_btn" type="submit" value="입력">
		</form>
	</div>
</body>
</html>