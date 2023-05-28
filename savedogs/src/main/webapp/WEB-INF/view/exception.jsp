<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage = "true" %>
<%-- /webapp/WEB-INF/view/exception.jsp 
isErrorPage = "true" : exception 객체를 내장 객체로 할당.
message라고 쓸 수 있는 것은 내장객체 exception의 멤버이기 때문.
--%>
<script>
	alert('${exception.message}');
	location.href = "${exception.url}";
</script>
