<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 입양후기 게시판</title>
<link rel='stylesheet' href='../css/savedogs_main.css' /><style type="text/css">
.g_btn{
	width: 200px;
}
.sel{
	width: 120px;
	padding: 10px;	
}
img{
	width: 250px;
	height: 250px;
	
}
td{
	text-align: center;
}
.img_td{
	text-align: center;
	padding: 20px;
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
<h2>입양 후기 게시판</h2>
<hr>
<table>
	<tr><td colspan="5">
		<div style="display: inline;">
			<form action="reviewList.dog" method="post" name="searchform">
				<input type="hidden" name="pageNum" value="1">
				<input type="hidden" name="type" value="0">
				<select name="searchtype" class="sel">
					<option value="">선택하세요</option>
					<option value="subject">제목</option>
					<option value="member_id">작성자</option>
				</select>
				<script type="text/javascript">
					searchform.searchtype.value="${param.searchtype}";
				</script>
				<input type="text" name="searchcontent" value="${param.searchcontent }" style="width: 250px;">
				<input type="submit" value="검색" class="s_btn">
				<input type="button" value="전체 게시물 보기" class="g_btn" onclick="location.href='reviewList.dog?type=0'">
			</form>	
		</div></td>
	</tr>
</table>
<br>
<c:if test="${listcount > 0 }">  	
	<c:forEach var="board" items="${boardlist }" varStatus="stat">
		<c:if test="${stat.first}"><div></c:if>	
				<table>
					<tr><td class="img_td"><img src="review/${board.fileurl }" onclick="location.href='reviewDetail.dog?no=${board.board_no }'"></td></tr>
					<tr><th><a href="reviewDetail.dog?no=${board.board_no }">${board.subject }</a></th></tr>
					<tr><td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/></td></tr>
					<tr><td>${board.member_id }</td></tr>
				</table>
		<c:if test="${stat.count==3 }"><br></c:if>	
		<c:if test="${stat.last}"></div></c:if>
	</c:forEach>
	<br>	
	<table>
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
	</table>
</c:if>

	<c:if test="${listcount == 0 }">
	
		<h3 style="padding-top: 30px;">등록된 게시물이 없습니다.</h3>
		
	</c:if>
	<br>
	<!-- 
	<c:if test="${sessionScope.loginmem != null }">
		<table>
			<tr><td colspan="5" class="btn_td"><input type="button" value="작성" class="s_btn" onclick="location.href='reviewWrite.dog'"></td></tr>
		</table>
	</c:if>
	 -->
</div>
</body>
</html>