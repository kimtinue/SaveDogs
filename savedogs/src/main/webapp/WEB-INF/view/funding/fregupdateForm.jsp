<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원 모집 수정</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function file_delete() {
	file_desc.style.display = "none";
}
</script>
</head><body>
<h2>기부 모집 수정</h2>
<form:form modelAttribute="funding" method="post" action="fregupdateForm.dog" enctype="multipart/form-data">
<%-- globalErrors error.reject(코드값) --%>
<input type="hidden" name="member_id" value="${sessionScope.loginmem.member_id }">
<input type="hidden" name="fund_no" value="${param.fund_no }">
<spring:hasBindErrors name="funding">
<font color="red">
<c:forEach items="${errors.globalErrors}" var="error">
<spring:message code="${error.code }" />
</c:forEach>
</font>
</spring:hasBindErrors>
<!-- db에서 불러오기 -->
<table style="border-collapse:collapse;">
<tr height="40px"><td>보호소 명</td> <!-- db에서 불러올것 입력값아님~ -->
<td><input type="text" name="sheltername" readonly="readonly" value="${sessionScope.smemName}" /><!-- ${sessionScope.loginUser.sheltername},readonly--> 
<font color="red"><form:errors path="sheltername" /></font></td></tr>

<tr height="40px"><td>기부 제목</td>
<td><input type="text" name="fund_subject" value="${funding.fund_subject}" />
<font color="red">
<form:errors path="fund_subject" /></font></td></tr>

<tr height="40px"><td>목표 금액</td>
<td><form:input path="fund_count" />
<font color="red"><form:errors path="fund_count" /></font></td></tr>

<tr height="40px"><td>시작 날짜</td>
<td><fmt:formatDate value="${funding.start_date}" pattern="yyyy-MM-dd" var="startdate" />
<input type="text" name="start_date" value="${startdate}" readonly="readonly"/>

<font color="red"><form:errors path="start_date" /></font></td></tr>

<tr height="40px"><td>마감날짜</td>
<td><input type="date" name="end_date" />
<font color="red"><form:errors path="end_date" /></font></td></tr>

<tr height="40px"><td>배너사진</td>
<td>
<c:if test="${!empty funding.fund_pic }">
					<div id="file_desc">
						<a href="img/${funding.fund_pic }">${funding.fund_pic}</a>
						<a href="javascript:file_delete()">[파일첨부삭제]</a>
					</div></c:if>
<input type="file" name="picture" /></td></tr>


<tr height="40px">
<td colspan="2" align="center">
<input type="submit" class="s_btn" value="수정하기">
</td></tr>
</table>
</form:form>
</body>
</html>