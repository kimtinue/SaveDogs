<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- /webapp/WEB-INF/view/user/userEntry.jsp 
   http://localhost:8080/shop3/user/userEntry.shop 요청시 화면 출력하기 --%>
<title>후원 모집 등록</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head> 
<body> 
<!-- submit일때 hidden값  -->
 <div style="margin-left: 30%; width:40%;">
	<h2>기부 모집 등록</h2>
	<form:form modelAttribute="funding" method="post" action="fregForm.dog" enctype="multipart/form-data">
		<%-- globalErrors error.reject(코드값) --%>
		<input type="hidden" name="member_id" value="${sessionScope.loginsmem.member_id }">
		<spring:hasBindErrors name="funding">
			<font color="red"> <c:forEach items="${errors.globalErrors}"
					var="error">
					<spring:message code="${error.code }" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<table style="border-collapse: collapse;">
			<tr height="40px">
				<td>보호소 명</td>
				<!-- db에서 불러올것 입력값아님~ -->
				<td><input type="text" name="sheltername" readonly="readonly" value="${sessionScope.smemName}" />
				 <font color="red">
				 <form:errors path="sheltername" /></font></td>
			</tr>
			<tr height="40px">
				<td>기부 제목</td>
				<td><input type="text" name="fund_subject" /> 
				<font color="red">
				<form:errors path="fund_subject" /></font></td>
			</tr>

			<tr height="40px">
				<td>목표 금액</td>
				<td><input type="text" name="fund_count" /> 
				<font color="red">
				<form:errors path="fund_count" /></font></td>
			</tr>

			<tr height="40px">
				<td>시작 날짜</td>
				<td><input type="date" name="start_date" value="${today}" /> 
				<font color="red">
				<form:errors path="start_date" /></font></td>
			</tr>

			<tr height="40px">
				<td>마감날짜</td>
				<td><input type="date" name="end_date" /> 
				<font color="red">
				<form:errors path="end_date" /></font></td>
			</tr>

			<tr height="40px">
				<td>배너사진</td>
				<td><input type="file" name="picture"/></td>
				
			</tr>

			<tr height="40px" >
				<td colspan="2" align="center"><input type="submit" value="등록"  class="s_btn"  >
				</td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>