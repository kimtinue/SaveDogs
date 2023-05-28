<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : Q&amp;A 게시판</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
.sel{
	width: 150px;
	padding: 14px;
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
.g_btn{
	width: 200px;
}
.s_form{
	padding-bottom: 30px;
}

</style>
<script type="text/javascript">
	function listpage(page){
		document.searchform.pageNum.value=page;
		document.searchform.submit();
	}
</script>
</head>
<body>
<div class="main_div">
<h2>Q&amp;A 게시판</h2>
<hr>
	<div  style="display: inline;">
		<form action="qnaList.dog" method="post" name="searchform" class="s_form">
			<input type="hidden" name="pageNum" value="1">
			<input type="hidden" name="type" value="2">
			<select name="searchtype" class="sel">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
			</select>
			<script type="text/javascript">
				searchform.searchtype.value="${param.searchtype}";
			</script>
			<input type="text" name="searchcontent" value="${param.searchcontent }" style="width: 250px; padding: 9px;" >
			<input type="submit" value="검색" class="s_btn">
			<input type="button" value="전체 게시물 보기" class="g_btn" onclick="location.href='qnaList.dog?type=2'">
		</form>	
	</div>
		
<table class="l_table">
	<c:if test="${listcount > 0 }">  
		<caption style="text-align: right;">글 개수 : ${listcount }</caption>
		<tr style="background-color: #B3E7E2;"><th class="l_th">번호</th><th class="l_th" style="width: 50%;">제목</th><th class="l_th">작성자</th><th class="l_th">작성일</th><th class="l_th">조회수</th></tr>
		<c:forEach var="board" items="${boardlist }">
			<tr><td class="l_td">${boardno}</td><c:set var="boardno" value="${boardno-1 }"/>
				<td style="text-align: left; " class="l_td">
					<c:if test="${!empty board.fileurl }"> 
						<a href="file/${board.fileurl }">@</a>
					</c:if>
					<c:if test="${empty board.fileurl }">&nbsp;&nbsp;&nbsp;</c:if>
					<c:forEach begin="1" end="${board.grplevel }">&nbsp;&nbsp;</c:forEach>
					<c:if test="${board.grplevel > 0 }">└</c:if>
					<a href="qnaDetail.dog?no=${board.board_no }">${board.subject }</a>
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
		<tr><td colspan="5"> 
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
	
	
</table>
<c:if test="${listcount == 0 }"> 
		<h3>등록된 게시물이 없습니다.</h3>
	</c:if>
<br>
<c:if test="${sessionScope.loginmem != null || sessionScope.loginsmem != null}">
	<input type="button" value="작성" class="s_btn" onclick="location.href='qnaWrite.dog'">
	</c:if>	
</div>
</body>
</html>