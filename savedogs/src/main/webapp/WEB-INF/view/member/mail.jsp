<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='../css/savedogs_main.css' />
<title>메일 보내기</title>
<script>
	function idinputchk(f) {
		if(f.naverid.value == "") {
			alert("네이버 아이디를 입력하세요");
			f.naverid.focus();
			return false;
		}
		if(f.naverpw.value == "") {
			alert("네이버 비밀번호를 입력하세요");
			f.naverpw.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="main_div" style="width:100%;">
	<h2>메일 보내기</h2>
	<form name="mailform" method="post" action="mail.dog" enctype="multipart/form-data" onsubmit="return idinputchk(this)">
	<input type="hidden" value="${param.fund_no }" name="fund_no">
		<table>
			<tr>
				<td>본인 네이버 ID : </td>
				<td><input type="text" name="naverid"></td>
			</tr>
			<tr>
				<td>본인 네이버 PW : </td>
				<td><input type="password" name="naverpw"></td>
			</tr>
			<tr>
				<td>보내는 사람</td>
				<td>${sessionScope.loginsmem.member_email }</td>
			</tr>
			<tr>
				<td>받는 사람</td>
				<td>
					<input style="width:80%;" type="text" name="recipient" size="100" value='<c:forEach items="${list }" var="member">${member.member_id } &lt;${member.member_email }&gt;,</c:forEach>'>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input style="width:80%" type="text" name="title" size="100">
				</td>
			</tr>
			<tr>
				<td>메세지 형식</td>
				<td>
					<select name="mtype">
						<option value="text/html; charset=utf-8">HTML</option>
						<option value="text/plain; charset=utf-8">TEXT</option>		<%--mime 타입 --%>
					</select>
				</td>
			</tr>
			<tr>
				<td>첨부파일1</td>
				<td>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td>첨부파일2</td>
				<td>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="contents" cols="120" rows="10"></textarea>
					<script>CKEDITOR.replace("contents")</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input class="s_btn" type="submit" value="메일전송">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>