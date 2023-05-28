<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 입양후기 작성</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>
<style type="text/css">
#subject{
	width : 800px;
}
th{
	text-align: center;
	padding-right: 40px;
}

</style>
<script type="text/javascript">
var Text = oriText.replace(/(<([^>]+)>)/ig,"");
</script>
</head>
<body>
<div class="main_div">
<h2>입양후기 작성</h2>
<hr>
<form:form modelAttribute="board" action="reviewWrite.dog" enctype="multipart/form-data" name="f">	
	<form:hidden path="type" value="0"/>
	<form:hidden path="member_id" value="${sessionScope.loginmem.member_id }"/>
	<table>
		<tr><th>제목</th><td><form:input path="subject" size="20"/><font color="red"><form:errors path="subject"/></font></td></tr>
		<tr><th>내용</th><td><form:textarea path="content" rows="30" cols="120"/>
		<script>CKEDITOR.replace("content",{
			filebrowserImageUploadUrl : "imgupload.dog"
		});
		
		</script>
		<font color="red"><form:errors path="content"/></font></td></tr>
		
		<tr><th>대표이미지</th><td><input type="file" name="file1"/></td></tr>
		<tr><th colspan="2" class="btn_td">
				<input type="submit" value="등록" class="s_btn">
			</th>
		</tr>
	</table>
</form:form>
</div>
</body>
</html>