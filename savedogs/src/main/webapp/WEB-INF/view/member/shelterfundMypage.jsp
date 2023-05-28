<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My 기부</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
	.data {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="main_div" style="width: 100%">
		<c:if test="${empty writelist }">
			<h3>작성한 기부가 없습니다.</h3>
		</c:if>
		<c:if test="${!empty writelist }">
		<h3>나의 기부 리스트</h3>
		<hr>
		<table>
			<tr>
				<th class="l_th">펀딩제목</th>
				<th class="l_th">진행일자</th>
				<th class="l_th">비고</th>
			</tr>
			<c:forEach items="${writelist }" var="list">
				<tr>
					<td class="data l_td" style="width:360px">
						<a href="shelterfundDetail.dog?fund_no=${list.fund_no }">${list.fund_subject }</a>
					</td>
					<td class="data l_td" style="width:360px">
						<fmt:formatDate value="${list.start_date }" pattern="yyyy-MM-dd" var="start"/>
						<fmt:formatDate value="${list.end_date }" pattern="yyyy-MM-dd" var="end" />
						${start } ~ ${end }
					</td>
					<td class="data l_td">
						${list.restdate>0?"진행중":"마감" }
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>