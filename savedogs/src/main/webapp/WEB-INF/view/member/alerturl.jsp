<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="msg" value="${param.msg }" />
<c:set var="url" value="${param.url }" />
<script>
   alert("${msg}");
   opener.location.href="${path}/member/${url}";
   self.close();
</script>