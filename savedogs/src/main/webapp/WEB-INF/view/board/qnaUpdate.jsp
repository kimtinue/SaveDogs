<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 공지사항 수정</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
<style type="text/css">
#subject {
	width : 800px;
}
</style>
</head>
<body>
<div class="main_div">
<h2>Q&amp;A 수정</h2>
<hr>
<form:form modelAttribute="board" action="qnaUpdate.dog" enctype="multipart/form-data" name="f">
	<form:hidden path="board_no" value="${param.no }"/>	
	<form:hidden path="type" value="2"/>
	<form:hidden path="member_id" value="${sessionScope.loginadmin.member_id }"/>
	<table>
		<tr><td>제목</td><td><form:input path="subject"/><font color="red"><form:errors path="subject"/></font></td></tr>
		<tr><td>내용</td><td><form:textarea path="content" rows="15" cols="80"/>
		<script>CKEDITOR.replace("content",{
			filebrowserImageUploadUrl : "imgupload.dog"
		});</script>
		<font color="red"><form:errors path="content"/></font></td></tr>
		
		<tr><td colspan="2" class="btn_td">
				<input type="submit" value="등록" class="s_btn">
			</td>
		</tr>
	</table>
</form:form>
</div>
</body>
</html>