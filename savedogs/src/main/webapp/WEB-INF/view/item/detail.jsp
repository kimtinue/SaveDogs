<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- WEB-INF/view/item/detail.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>상품 상세 보기</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script type="text/javascript">
	function add(f){
		var item_each = f.item_each.value;
		var item_no = "${item.item_no}";
		var op = "width=780,height=430,left=150,top=150";
		open('../cart/cartAdd.dog?item_no='+item_no+'&item_each='+item_each,"",op)
	}
	function soldout(f){
		var op = "width=780,height=430,left=150,top=150";
		open('soldout.dog',"",op)
	}
	function deleteform(){
		var no = "${item.item_no}";
		var op = "width=780,height=430,left=150,top=150";
		open('deleteform.dog?item_no='+no,"",op)
	}
</script>
</head>
<body>
<div style="width: 80%; margin-left: 10%;" >
	<div align="center">
		<h2>상품 상세 보기</h2>
	</div>
	<hr>
	<br>
	<div align="center">
		<table style="width: 70%;">
			<tr>
				<td width="50%">
					&nbsp;<img src="img/${item.item_picture}" style="width: 80%; height: 80%;">&nbsp;
				</td>
				<td>
					<table style="width: 100%;">
						<tr>
							<td align="center" width="30%"><h3>상품명</h3> </td>
							<td><h4>&nbsp;${item.item_name}&nbsp;<c:if test="${item.item_state==1}"><span style="color: #f8591b">품절</span></c:if></h4></td>
						</tr>
						<tr>
							<td align="center" width="30%"><h3>가격</h3></td>
							<td><h4>&nbsp;<fmt:formatNumber pattern="##,###" value="${item.item_price}"/>원</h4></td>
						</tr>
						<tr>
							<td align="center" width="30%"><h3>상품코드</h3></td>
							<td><h4>&nbsp;${item.item_code}</h4></td>
						</tr>
						
						<tr>
							<td colspan="2">
							<c:if test="${item.item_state != 1}">
								<form action="list.dog" name="f" onsubmit="return add(this)" >
									<input type="hidden" name="item_no" value="${item.item_no}">
									<table style="width: 100%; height: 100%;">
										<tr>
											<td style="width: 110px;" align="center">
												<h3>수량</h3>
											</td>
											<td style="width: 259px">
												<select style="width: 70%;" name="item_each" id="item_each">
					 								<c:forEach begin="1" end="10" var="i">
					 									<option>${i}</option>
					 								</c:forEach>
					 							</select>
					 						</td>
					 					</tr>
					 					<tr style="height: 15%;">
				 							<td colspan="2" width="100%" align="center"> 
				 								<input style="width: 100%;" type="submit" class="s_btn" value="장바구니" >
				 							</td>
				 						</tr>
				 						
				 						<tr style="height: 15%;">
				 							<td colspan="2" width="100%" align="center">
				 								<input style="width: 100%;" type="button" class="g_btn" value="상품목록" onclick="location.href='list.dog'">
				 							</td>
				 						</tr>
									</table>
								</form>
								</c:if>
								<c:if test="${item.item_state==1}">
									<form action="list.dog" name="f" onsubmit="return soldout(this)" >
										<table style="width: 100%; height: 100%;">
											<tr style="height: 15%;">
				 								<td width="100%" align="center"> 
				 									<input style="width: 100%;" type="submit" value="품절" >
				 								</td>
				 							</tr>
										</table>
									</form>
								</c:if>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<div>
		<h3 style="margin-left: 15%;">상품 정보</h3>
		<hr>
		<br>
		<h4 style="margin-left: 15%;">${item.item_content}</h4>
		<br>
		<hr>
		
		<div align="center">
			<c:if test="${!empty sessionScope.loginadmin}"> 
			<table>
				<tr>
					<td><input type="button" value="수정" onclick="location.href='update.dog?item_no=${item.item_no}'">&nbsp;</td>
					<td><input type="button" value="삭제" onclick="deleteform()"></td>
				</tr>
			</table>
			</c:if>
		</div>
	</div>
</div>
</body></html>