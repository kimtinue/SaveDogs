<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 마이페이지</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script type="text/javascript">
	function newlist() {
		document.f.submit();
		alert("최신화 완료")
	}
</script>
<style type="text/css">
.b_td{
	text-align: left;
}
</style>
</head>
<body>
	<div class="main_div" style="width:100%;">
		<table>
					<tr>
						<th class="b_th">아이디</th>
						<td class="b_td">${mem.member_id }</td>
					</tr>
					<tr>
						<th class="b_th">이름</th>
						<td class="b_td">${mem.member_name }</td>
					</tr>
					<tr>
						<th class="b_th">생년월일</th>
						<fmt:formatDate var="birth" value="${mem.member_birthday }" pattern="yyyy년MM월dd일" />
						<td class="b_td">${birth }</td>
					</tr>
					<tr>
						<th class="b_th">전화번호</th>
						<td class="b_td">${mem.member_tel }</td>
					</tr>
					<tr>
						<th class="b_th">주소</th>
						<td class="b_td">${mem.member_postcode }${mem.member_address }
							${mem.member_daddress }</td>
					</tr>
					<tr>
						<th class="b_th">이메일</th>
						<td class="b_td">${mem.member_email }</td>
					</tr>
				</table>
		<form name="f" method="post" action="recent.dog">
			<input class="s_btn" style="width:200px;" type="button" value="보호소정보 최신화" onclick="newlist()">
		</form>
	</div>
</body>
</html>