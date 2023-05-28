<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 공지사항</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
.b_th{
	width: 200px;
}

</style>
</head>
<body>
<div class="main_div">
<h2>Q&amp;A</h2>
<hr>
	<table class="b_table">
		<tr><th class="b_th">제목</th><td class="b_td">${board.subject }</td></tr>
		<tr><th class="b_th">작성자</th><td class="b_td">${board.member_id }</td></tr>
		<tr><th class="b_th">작성일</th><td class="b_td"><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/></td></tr>
		<tr><th class="b_th">내용</th><td class="b_td"><div class="ctxt_div">${board.content }</div></td></tr>
	</table>
<div class="btn_div">  
	<input type="button" value="목록" class="s_btn" onclick="location.href='qnaList.dog?type=2'">
	<c:if test="${sessionScope.loginmem.member_id == board.member_id || sessionScope.loginsmem.member_id == board.member_id || sessionScope.loginadmin.member_id == board.member_id }">
		<input type="button" value="수정" class="g_btn" onclick="location.href='qnaUpdate.dog?no=${board.board_no}'">
		<input type="button" value="삭제" class="g_btn" onclick="location.href='qnaDelete.dog?no=${board.board_no}'">
	</c:if>
	<c:if test="${sessionScope.loginsmem != null || sessionScope.loginadmin != null }">
		<c:if test="${board.grplevel == 0 }">
		<input type="button" value="답변" class="s_btn" onclick="location.href='qnaReply.dog?no=${board.board_no}'">
		</c:if>
	</c:if>
</div>
</div>
</body>
</html>