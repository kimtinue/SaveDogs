<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 쇼핑몰 물품 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="http://www.chartjs.org/dist/2.9.3/Chart.min.js"></script>
<script>
	function drawgraph() {
		var name = document.getElementById("shoplist").value;
		$.ajax({
			url : "../ajax/shopgraph.dog",
			type : "POST",
			data : "name="+ name,
			success : function(data) {
				shopgraph(data, name);
			},
			error : function(e) {
				alert("데이터가 충분하지 않습니다");
			}
		})
	}
	
	function drawgraph2() {
		$.ajax("../ajax/shopallgraph.dog", {
			success : function(data) {
				shopgraph(data, '2020년');
			},
			error : function(e) {
				alert("서버 오류 : " + e.status);
			}
		})
	}

	var randomColorFactor = function() {
		return Math.round(Math.random()*255);
	}
	var randomColor = function(opacity) {	//opacity : 투명도
		return "rgba(" + randomColorFactor() + ","
				+ randomColorFactor() + ","
				+ randomColorFactor() + ","
				+ (opacity || '.3') + ")";
	};
	
	function shopgraph(data, name) {
		console.log(data)
		var name = name;
		var rows = JSON.parse(data)
		var dates = []
		var datas = []
		var colors = []
		$.each(rows, function(index, item) {
			/* names[index] = item.name; */
			dates[index] = item.m;
			datas[index] = item.cnt;
			colors[index] = randomColor(0.7);
		})

		var chartData = {
			labels : dates,
			datasets : [
				{
					type:'line',
					borderWidth : 2,
					borderColor:colors,
					label : '건수',
					fill : false,
					data : datas
				},
				{
					type:'bar',
					label : '건수',
					backgroundColor:colors,
					data : datas,
				}
			]
		};
		
		var ctx = document.getElementById("canvas").getContext("2d");
		new Chart(ctx, {
			type : 'bar',
			data : chartData,
			options : {
				responsive : true,
				title : {
					display : true,
					text : name + ' 월별 판매금액 분석'
				},
				legend : {display : false},
				scales : {
					xAxes : [{
						display : true,
						scaleLabel : {
							display : true,
							labelString : '월'
						}
					}],
					yAxes : [{
						display : true,
						scaleLabel : {
							display : true,
							labelString : '판매량'
						},
						stacked : true	//(0부터 시작)
					}]
				}
			}
		});
	}
	
	function changestate(no, state) {
		var state = state;
		$.ajax({
			url : "../ajax/changestate.dog",
			type : "POST",
			data : "item_no="+ no + "&item_state="+state,
			success : function(data) {
				alert("설정 변경 완료")
				document.location.reload();
			},
			error : function(e) {
				alert("ajax 오류");
			}
		})
	}
</script>
<style>
	.data {
		text-align:center;
	}
</style>
<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
	<div class="main_div" style="width: 100%;">
		<h3>후원 물품 판매 관리</h3>
		<table>
			<tr>
				<th class="l_th">상품코드</th>
				<th class="l_th">상품명</th>
				<th class="l_th">단가</th>
				<th class="l_th">판매량</th>
				<th class="l_th">소계</th>
				<th class="l_th">상태</th>
				<th class="l_th">선택시 변경</th>
			</tr>
			<c:forEach items="${shopalllist }" var="list">
				<tr>
					<td class="l_td">${list.item_code }</td>
					<td class="data l_td">${list.item_name }</td>
					<fmt:formatNumber value="${list.item_price }" pattern="##,###원" var="price" />
					<td class="data l_td">${price }</td>
					<td class="data l_td">${list.sellCnt }개</td>
					<fmt:formatNumber value="${list.item_price * list.sellCnt }" pattern="##,###원" var="total" />
					<td class="data l_td">${total }</td>
					<td class="data l_td">${list.item_state==0?"판매중":"매진" }</td>
					<td class="data l_td">
						<c:if test="${list.item_state==0 }">
							<a href="javascript:changestate(${list.item_no }, 1)">
								<font color="blue">매진</font>
							</a>
						</c:if>
						<c:if test="${list.item_state==1 }">
							<a href="javascript:changestate(${list.item_no }, 0)">
								<font color="blue">판매중</font>
							</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<h3>판매량 분석</h3>
		<select id="shoplist">
			<c:forEach items="${shopalllist }" var="shop">
				<option>${shop.item_name }</option>
			</c:forEach>
		</select>
		<input class="s_btn" type="button" value="출력" onclick="drawgraph()">
		<input class="s_btn" style="width: 250px;" type="button" value="전체 월별 판매금액량 보기" onclick="drawgraph2()">
		<div id="main_div" align="center" style="width:50%; margin-left:25%">
			<canvas id="canvas" style="width:50%; height:100%;"></canvas>
		</div>
	</div>
</body>
</html>