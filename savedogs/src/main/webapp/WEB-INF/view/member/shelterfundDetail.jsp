<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후원내역 상세보기</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
	.data {
		text-align: center;
	}
</style>
<script type="text/javascript">
	function allchkbox(allchk) {
		var chks = document.getElementsByName("idchks");
		for(var i=0; i<chks.length; i++) {
			chks[i].checked = allchk.checked;
		}
	}
	
</script>
</head>
<body>

<div class="maiv_div" style="width: 100%;">
		<h3>나의 후원 리스트 상세보기</h3>
		<hr>
		<table>
			<tr>
				<th class="b_th">제목</th>
				<td class="b_td">${showFund.fund_subject}</td>
			</tr>
			<tr>
				<th class="b_th">기간</th>
				<fmt:formatDate value="${showFund.start_date }" pattern="yyyy-MM-dd" var="start"/>
				<fmt:formatDate value="${showFund.end_date }" pattern="yyyy-MM-dd" var="end"/>
				<td class="b_td">${start} ~ ${end}</td>
			</tr>
			<tr>
				<th class="b_th">목표금액</th>
				<td class="b_td">
					${showFund.fund_count}
				</td>
			</tr>
			<tr>
				<td class="data btn_td" colspan="2">
					<input class="small_btn" type="button" value="목록으로" onclick="location.href='shelterfundMypage.dog?type=3&id=${sessionScope.loginsmem.member_id}'">
				</td>
			</tr>
		</table>
		<hr>
		<h3>후원신청자 현황</h3>
		<form method="post" action="mailForm.dog?fund_no=${param.fund_no }">
			<input type="hidden" value="${param.fund_no }" name="fund_no">
			<table>
				<tr>
					<th class="l_th">아이디</th>
					<th class="l_th">이메일</th>
					<th class="l_th">후원금액</th>
					<th>
						<input type="checkbox" name="allchk" onchange="allchkbox(this)">
					</th>
				</tr>
				<c:forEach items="${detaillist }" var="dlist">
					<tr>
						<td class="data l_th">
							${dlist.fund_id }
						</td>
						<td class="data l_th">
							${dlist.member_email }
						</td>
						<td class="data l_th">
							${dlist.fund_cost }
						</td>
						<td style="text-align: center;" class="l_td">
							<input type="checkbox" name="idchks" class="idchks" value="${dlist.fund_id }">
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="data btn_td" colspan="4">
						<input class="s_btn" type="submit" value="메일전송">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>