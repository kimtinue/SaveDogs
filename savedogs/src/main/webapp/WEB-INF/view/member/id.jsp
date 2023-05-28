<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel='stylesheet' href='../css/savedogs_exc.css' />
<script type="text/javascript">
	function find(){
		var id = "${id}";
		opener.document.getElementById('member_id').value = id;
		self.close();
	}
</script>
</head>
<body>
	<div align="center">
		<h2>아이디 찾기</h2>
	</div>
	<hr>
	<div>
		<h3>요청하신 아이디는 ${id} 입니다.</h3>
	</div>
	<div>
		<form>
			<table style="width: 100%;">
				<tr>
					<td><input class="s_btn" type="button" value="확인" onclick="find()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>