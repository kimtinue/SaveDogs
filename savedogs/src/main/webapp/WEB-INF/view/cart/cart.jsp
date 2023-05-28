<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--/webapp/WEB-INF/view/cart/cart.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html><html><head><meta charset="UTF-8">
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
	th {
		background-color: #eaeaea;
	}
</style>
<script>
	var path = "${path}";
	console.log(path);
	
</script>
<title>장바구니</title></head>
<body>
<div class="main_div">
	<div>
	<form>
		<table>
			<tr>
				<td><img src="cart_img.jpg" style="width: 15%; height: 20%;">장바구니</td>
				<td><input type="button" onclick="location.href='../item/list.dog'" class="g_btn" value="상품목록"></td>
			</tr>
		</table>
		</form>
	</div>
	<hr>
	<div align="center">
	<table> 
	<tr style="width: 100%;"><th width="250px">상품명</th><th width="250px">수량</th><th width="250px">합계</th></tr>
	<c:set var="tot" value="${0}"/>
	<c:forEach items="${cart.itemSetList}" var="itemSet" varStatus="stat">
		<tr><td width="250px" style="text-align: center;">${itemSet.item.item_name}</td>
			<td width="250px" style="text-align: center;">${itemSet.item_each}</td>
			<td width="250px" style="text-align: center;"><fmt:formatNumber pattern="##,###" value="${itemSet.item_each * itemSet.item.item_price}"/>  
		<c:set var="tot" value="${tot +(itemSet.item_each * itemSet.item.item_price)}"/>
		<a href="cartDelete.dog?index=${stat.index}">ⓧ</a></td></tr>
		</c:forEach>
	</table>
	</div>
	<hr>
	<h3 align="left" style="margin-left: 15%; ">총 구입 금액 : ${tot}원</h3>
	<div>
	<form>
		<table>
			<tr>
				<td><input type="button" class="s_btn" style="width: 200px;" onclick="location.href='checkout.dog'" value="구매하러가기"></td>
			</tr>
		</table>
	</form>
	</div>
</div>
</body></html>