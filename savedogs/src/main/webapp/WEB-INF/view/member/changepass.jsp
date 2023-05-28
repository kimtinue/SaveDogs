<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script>
	function checkclose() {
		f = document.f;
		f.submit();
	}
</script>
<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
	<div class="main_div">
		<h3>비밀번호 수정</h3>
		<hr>
		<form name="f" method="post" action="changepass.dog">
			<table>
				<tr>
					<th>기존 비밀번호</th>
					<td><input type="password" name="inputpass"></td>
				</tr>
				<tr>
					<th>새 비밀번호</th>
					<td><input type="password" name="newpass"></td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td><input type="password" name="newpass2"></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${param.id }">
			<input class="g_btn" type="button" value="수정" onclick="checkclose()">
		</form>
	</div>
</body>
</html>