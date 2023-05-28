<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--/webapp/WEB-INF/view/cart/checkout.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<style type="text/css">
	th{
		background-color: #eaeaea;
	}
	th,td{
		border: 1px solid #e0e0e0; 
	}
	#input {
		width: 100%;
	}
	#input_70 {
		width: 80%;
	}
</style>
<title>주문 전 상품 목록 보기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?autoload=false"></script>
<script>
	$(document).ready(function(){
		$("#chkF").show();
		$("#chkT").hide();
	})
	function disp_div(){
		if($("input:checkbox[name='info']").is(":checked")){
			$(".info").each(function(){
				$(this).hide();
			})
			$("#chkT").show();
		}else{
			$(".info").each(function(){
				$(this).hide();
			})
			$("#chkF").show();
		}
	}	
	function openDaumZipAddress() {
		new daum.Postcode({
			oncomplete: function(data) {
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                   //document.getElementById("member_daddress").value = extraAddr;
                } else {
                   //document.getElementById("member_daddress").value = '';
                }
                document.getElementById('buy_postcode').value = data.zonecode;
                document.getElementById("buy_address").value = addr + extraAddr;
                document.getElementById("buy_daddress").focus();
            }
        }).open();
	}
</script>

<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
<div style="width: 80%; margin-left: 10%;" >
	<div align="center" >
		<h2>상품구매</h2>
	</div>
	<hr>
	<h3 align="left" style="margin-left: 15%;">구매 정보</h3>
	<br>
	<div align="center">
	<table>
		<tr><th>상품명</th><th>상품코드</th><th>가격</th><th>수량</th></tr>
		<c:forEach items="${sessionScope.CART.itemSetList}" var="itemSet" varStatus="stat">
			<tr>
				<td style="text-align: center;">${itemSet.item.item_name}</td><td style="text-align: center;">${itemSet.item.item_code}</td><td style="text-align: center;">${itemSet.item.item_price*itemSet.item_each}</td>
				<td style="text-align: center;">${itemSet.item_each}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<br>
	<hr>
	<h3 align="left" style="margin-left: 15%;">배송지 정보</h3>
	<br>
	<div align="center">
		<div align="right">
			<table style="width: 44%; margin-left:56%; float: right;">
				<tr>
					<td align="right">회원정보와 동일<input name="info" type="checkbox" onchange="disp_div()"></td>
				</tr>
			</table>
		</div>
		<div id = "chkT" class="info">
			<table>
				<tr>
					<th width="30%">아이디</th>
					<td width="70%">${sessionScope.loginmem.member_id}</td>		
				</tr>
				<tr>
					<th width="30%">이름</th>
					<td width="70%">${sessionScope.loginmem.member_name }</td>
				</tr>
				<tr>
					<th width="30%">우편번호</th>	
					<td width="70%"><fmt:formatNumber pattern="00000" value="${sessionScope.loginmem.member_postcode }"/>&nbsp;&nbsp;<input style="width: 20%;" type="button" value="찾기"></td>
				</tr>
				<tr>
					<th width="30%">주소</th>
					<td width="70%">${sessionScope.loginmem.member_address}</td>
				</tr>
				<tr>
					<th width="30%">상세 주소</th>
					<td width="70%">${sessionScope.loginmem.member_daddress}</td>
				</tr>		
				<tr>
					<th width="30%">전화번호</th>
					<td width="70%">${sessionScope.loginmem.member_tel}</td>
				</tr>
			</table>
			<h3 align="left" style="margin-left: 15%;">총 구입 금액 : <fmt:formatNumber pattern="0,000" value="${sessionScope.CART.total}"/> 원</h3>
			<input type="button" value="주문 완료" class="s_btn" onclick="location.href='end.dog'">
			<input type="button" value="취소" class="g_btn"  onclick="location.href='../item/list.dog'">
		</div>
		<form:form modelAttribute="buylist" action="checkout.dog" method="post">
		<input type="hidden" value="${sessionScope.loginmem.member_id}">
		<div id="chkF" class="info">
			<table>
				<tr>
					<th width="30%">아이디</th>
					<td width="70%">${sessionScope.loginmem.member_id}</td>
				</tr>
				<tr>
					<th width="30%">이름</th>
					<td width="70%">${sessionScope.loginmem.member_name }</td>
				</tr>
				<tr>
					<th width="30%">우편번호</th>
					<td>
						<form:input path="buy_postcode"/>
						<input class="small_btn" type="button" value="찾기" onclick="openDaumZipAddress();">
					</td>
				</tr>
				<tr>
					<th width="30%">주소</th>
					<td>
						<form:input path="buy_address"/>
					</td>
				</tr>
				<tr>
					<th width="30%">상세 주소</th>
					<td>
						<form:input path="buy_daddress" />
					</td>
				</tr>		
				<tr>
					<th width="30%">전화번호</th>
					<td>
						<form:input path="buy_tel" placeholder="010-0000-0000"/>
					</td>
				</tr>
			</table>
		<h3 align="left" style="margin-left: 15%;">총 구입 금액 : <fmt:formatNumber pattern="0,000" value="${sessionScope.CART.total}"/> 원</h3>
		<input type="submit" class="s_btn" value="주문 완료">
		<input type="button" class="g_btn" value="취소" onclick="location.href='../item/list.dog'">
		</form:form>
		</div>
	</div>
</div>	
</body></html>