<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--/webapp/WEB-INF/view/cart/end.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<link rel='stylesheet' href='../css/savedogs_main.css' />
<title>주문확정상품</title>
<style type="text/css">
	th,td {
		border: 1px solid gray; 
	}
</style> </head>
<body><h2>${buylist.member.member_name}님이 주문하신 정보 입니다.</h2>
<h2>배송지 정보</h2>
<table>
<tr><td width="30%">주문아이디</td>
		<td width="70%">${sessionScope.loginmem.member_id}</td></tr>
	<tr><td width="30%">이름</td>
		<td width="70%">${sessionScope.loginmem.member_name}</td></tr>
	<tr><td width="30%">우편번호</td>	
		<td width="70%">${sessionScope.loginmem.member_postcode}</td></tr>
	<tr><td width="30%">주소</td>
		<td width="70%">${sessionScope.loginmem.member_address} ${sessionScope.loginmem.member_daddress}</td></tr>		
	<tr><td width="30%">전화번호</td>
		<td width="70%">${sessionScope.loginmem.member_tel}</td></tr>
</table>
<h2>주문 완료 상품</h2>
<table>
	<tr><th>상품명</th><th>가격</th><th>수량</th><th>합계</th></tr>
	<c:forEach items="${buylist.itemList}" var="buyitem">
		<tr><td>${buyitem.item.item_name}</td><td>${buyitem.item.item_price}</td>
		<td>${buyitem.item_each}</td>
		<td>${buyitem.item.item_price * buyitem.item_each}</td></tr>
	</c:forEach>
	<tr><td colspan="4" align="right">
		총 구입 금액 : 
		<fmt:formatNumber value="${total}" pattern="###.###"/>원
	</td></tr>
	<tr><td colspan="4">
		<a href="../item/list.dog">상품목록</a>&nbsp;
	</td></tr></table></body></html>