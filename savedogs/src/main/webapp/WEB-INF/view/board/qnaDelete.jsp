<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : Q&amp;A 삭제</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
table{
	display: inline-block;
	text-align: center;
}
th{	
	text-align: center;
	width: 70%; 
}

</style>
</head>
<body>
<div class="main_div">
<h2>Q&amp;A 삭제</h2>
<hr>

<form action="qnaDelete.dog" method="post" name="f">
<input type="hidden" name="no" value="${board_no }"/>
	<table>
		<tr><th>&nbsp;</th></tr>
		<tr><th>해당 게시글 삭제 시 다시 불러올 수 없습니다.</th></tr>
		<tr><th>정말로 삭제하시겠습니까?</th></tr>
		<tr><th style="padding-top: 50px;">	
			<input type="button" value="취소" class="g_btn" onclick="location.href='qnaDetail.dog?no=${board_no}'">		
			<input type="submit" value="삭제" class="s_btn"></th></tr>
	</table>
</form>
</div>
</body>
</html>