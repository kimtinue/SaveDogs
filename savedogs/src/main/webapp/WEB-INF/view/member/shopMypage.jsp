<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My 쇼핑</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#minfo").show();
		$("#oinfo").hide();
		$(".saleLine").each(function(){
			$(this).hide();
		})
		$("#tab1").addClass("selection");
	})
	function disp_div(id,tab){
		$(".info").each(function(){
			$(this).hide();
		})
		$(".tab").each(function(){
			$(this).removeClass("selection");
		})
		$("#"+id).show();
		$("#"+ tab).addClass("selection");
	}
	function list_disp(id){
		$("#"+id).toggle();
	}
</script>
<style type="text/css">
	.selection{
		padding: 3px;
	}
	.selection > a {
		color: #000000;
		text-decoration : none;
		font-weight : bold;	
	}
	.data {
		text-align: center;
	}
	.l_th{
		width: 270px;
	}
</style>
<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
	<div class="maiv_div" style="width: 100%;">
	<div align="center" style="width: 100%;">
	<table>
		<tr>
			<td id="tab1" class ="tab" style="width: 50%;" align="center">
				<a href="javascript:disp_div('minfo','tab1')" style="color: gray; width: 50%;">구매내역</a>
			</td>
			<td id="tab2" class="tab" align="center">
				<a href="javascript:disp_div('oinfo','tab2')" style="color: gray; width: 50%;">장바구니</a>
			</td>
		</tr>
	</table>
	</div>
	<div id="oinfo" class="info" style=" display:none; width:100%;">
	<hr>
	<c:if test="${empty cart }">
		<h3>장바구니에 물품이 없습니다.</h3>
	</c:if>
	<c:if test="${!empty cart }">	
	<div align="center">
		<table> 
		<tr style="width: 100%;"><th width="250px">상품명</th><th width="250px">수량</th><th width="250px">합계</th></tr>
		<c:set var="tot" value="${0}"/>
		<c:forEach items="${cart.itemSetList}" var="itemSet" varStatus="stat">
			<tr><td width="250px" style="text-align: center;">${itemSet.item.item_name}</td>
				<td width="250px" style="text-align: center;">${itemSet.item_each}</td>
				<td width="250px" style="text-align: center;">${itemSet.item_each * itemSet.item.item_price}
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
				<td><input type="button" onclick="location.href='../cart/checkout.dog'" value="구매하러가기"></td>
			</tr>
		</table>
	</form>
	</div>
	</c:if>
	</div>
	<div id="minfo" class="info" style="width:100%;">
		<c:if test="${empty buylist }" >
			<h3>구매 내역이 없습니다.</h3>
		</c:if>
		<c:if test="${!empty buylist }">
		<table>
			<tr>
				<th class="l_th">주문번호</th>
				<th class="l_th">주문일자</th>
				<th class="l_th">총 주문금액</th>
			</tr>
			<c:forEach items="${buylist }" var="buy" varStatus="stat">
				<tr>
					<td class="data l_td">
						<a href="javascript:list_disp('saleLine${stat.index+1 }')">
							<font color="blue">${stat.index+1 }</font>
						</a>
					</td>
					<td class="data l_td">
						<fmt:formatDate value="${buy.buy_date }" pattern="yyyy-MM-dd" />
					</td>
					<td class="data l_td">
						<fmt:formatNumber value="${buy.total }" pattern="#,###" />원
					</td>
				</tr>
				<tr id="saleLine${stat.index+1 }" class="saleLine">
					<td colspan="3" align="center">
						<table style="width: 100%;">
							<tr>
								<th width="15%" class="b_th">상품번호</th>
								<th width="45%" class="b_th">상품명</th>
								<th width="15%" class="b_th">수량</th>
								<th width="25%" class="b_th">합계</th>
							</tr>
							<c:forEach items="${buy.itemList }" var="buyItem">
								<tr>
									<td class="data b_td" >${buyItem.item.item_no }</td>
									<td class="data b_td">${buyItem.item.item_name }</td>
									<td class="data b_td">${buyItem.item_each }개</td>
									<td class="b_td"><fmt:formatNumber value="${buyItem.item_each * buyItem.item.item_price }" pattern="#,###" />원</td>
								</tr>
							</c:forEach>
							<tr>
								<th colspan="3" class="b_th">배송지정보</th>
								<th class="b_th">상태</th>
							</tr>
							<tr>
								<td colspan="3" class="data b_td">
									${buy.buy_postcode }
									${buy.buy_address }
									${buy.buy_daddress }
								</td>
								<td class="data b_td">
									${buy.buy_state }
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
	</div>
</body>
</html>