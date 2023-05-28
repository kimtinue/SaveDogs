<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 공지사항 게시판</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
.g_btn{
	width: 120px;
}
.sel{
	width: 120px;
	padding: 10px;
}
th{
	padding-bottom: 20px;
	font-size: 20px;
}
td{
	text-align: center;
}
.l_table {
	width: 75%;
}
</style>
<script type="text/javascript">
	function listpage(page){
		document.f.pageNum.value=page;
		document.f.submit();
	}
</script>
</head>
<body>
<div class="main_div">
<h2>공지사항 게시판</h2>
<hr>
<form action="noticeList.dog" method="post" name="f">
	<input type="hidden" name="pageNum" value="1">
	<input type="hidden" name="type" value="1">
</form>
<table class="l_table">
	<c:if test="${noticecnt > 0 }">  
		<caption style="text-align: right;">글 개수 : ${noticecnt }</caption>
		<tr style="background-color: #B3E7E2;"><th class="l_th">번호</th><th class="l_th" style="width: 3%;">&nbsp;</th><th class="l_th" style="width: 50%;">제목</th><th class="l_th">작성자</th><th class="l_th">작성일</th><th class="l_th">조회수</th></tr>
		<c:forEach var="board" items="${boardlist }">
			<tr><td class="l_td">${boardno}</td><c:set var="boardno" value="${boardno-1 }"/>
				<td class="l_td">
					<c:if test="${!empty board.fileurl }"> 
						<a href="file/${board.fileurl }">@</a>
					</c:if>
					<c:if test="${empty board.fileurl }">&nbsp;&nbsp;&nbsp;</c:if>
					
				</td>
				<td class="l_td" style="text-align: left; ">
					<a href="noticeDetail.dog?no=${board.board_no }">${board.subject }</a>
				</td>
				<td class="l_td">${board.member_id }</td>
				<td class="l_td">
				<fmt:formatDate var="rdate" value="${board.regdate }" pattern="yyyyMMdd"/>
				<c:if test="${rdate == today }">
					<fmt:formatDate value="${board.regdate }" pattern="HH:mm:ss"/>
				</c:if>
				<c:if test="${rdate != today}">
					<fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/>
				</c:if>
				</td>
				<td class="l_td">${board.readcnt }</td>
			</tr>
		</c:forEach>
		<%-- 페이징 --%>
		<tr><td colspan="6" class="btn_td" > 
			<c:if test="${pageNum > 1 }"><a href="javascript:listpage('${pageNum-1 }')">[이전]</a></c:if>
			<c:if test="${pageNum <= 1 }">[이전]</c:if>
			<c:forEach var="a" begin="${startpage }" end="${endpage}">
				<c:if test="${a == pageNum }">[${a }]</c:if>
				<c:if test="${a != pageNum }"><a href="javascript:listpage('${a }')">[${a }]</a></c:if>
			</c:forEach>
			<c:if test="${pageNum < maxpage }">
				<a href="javascript:listpage('${pageNum+1 }')">[다음]</a>
			</c:if>
			<c:if test="${pageNum >= maxpage }">[다음]</c:if>
			</td>
		</tr>
	</c:if>
	<c:if test="${noticecnt == 0 }"> <%-- 등록된 게시물 없음 --%>
		<tr><td colspan="6">등록된 게시물이 없습니다.</td></tr>
	</c:if>
	<c:if test="${sessionScope.loginadmin != null }">
		<tr><td colspan="6" class="btn_td"><input type="button" value="작성" class="s_btn" onclick="location.href='noticeWrite.dog'"></td></tr>
	</c:if>
</table>
</div>
</body>
</html>