<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원목록 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script>
	function changeauth(id, auth) {
		$.ajax({
			url : "../ajax/changeauth.dog",
			type : "POST",
			data : "member_id="+ id + "&member_auth="+auth,
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
</head>
<body>
	<div class="main_div" style="width: 100%;">
		<h3>일반회원목록</h3>
		<hr>
		<table>
			<tr>
				<th class="l_th">아이디</th>
				<th class="l_th">이름</th>
				<th class="l_th">핸드폰</th>
				<th colspan="2" class="l_th">주소</th>
				<th class="l_th">생년월일</th>
				<th class="l_th">이메일</th>
				<th class="l_th">관리</th>
			</tr>
			<c:forEach items="${memberlist }" var="list">
				<tr>
					<td class="data l_td">
						${list.member_id }
					</td>
					<td class="data l_td">
						${list.member_name }
					</td>
					<td class="data l_td">
						${list.member_tel }
					</td>
					<td class="data l_td" colspan="2">
						${list.member_postcode} ${list.member_address} ${list.member_daddress}
					</td>
					<td class="data l_td">
						<fmt:formatDate value="${list.member_birthday }" pattern="yyyy.MM.dd" var="day" />
						${day }
					</td>
					<td class="data l_td">
						${list.member_email }
					</td>
					<td class="data l_td">
						<a href="../member/updateMember.dog?type=1&id=${list.member_id }&update=1&member_type=0">
							<font color="blue">수정</font></a>
						<a href="../member/deleteMember.dog?type=1&id=${list.member_id }">
							<font color="blue">탈퇴</font></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<h3>보호소 관리자 목록</h3>
		<table>
			<tr>
				<th class="l_th">아이디</th>
				<th class="l_th">핸드폰</th>
				<th class="l_th" colspan="2">보호소</th>
				<th class="l_th">이메일</th>
				<th class="l_th">첨부서류</th>
				<th class="l_th">가입여부</th>
				<th class="l_th">관리</th>
			</tr>
			<c:forEach items="${smemberlist }" var="slist">
				<tr>
					<td class="data l_td">
						${slist.member_id }
					</td>
					<td class="data l_td">
						${slist.member_tel }
					</td>
					<td class="data l_td" colspan="2">
						${slist.shelter_address} ${slist.shelter_name}
					</td>
					<td class="data l_td">
						${slist.member_email }
					</td>
					<td class="data l_td">
						<c:if test="${slist.file1 != null }">
							<a href="../member/img/${slist.file1 }">
							<font color="blue">${slist.file1 }</font>
							</a>
						</c:if>
						<c:if test="${slist.file2 != null }">
							<a href="../member/img/${slist.file2 }">
								<font color="blue">${slist.file2 }</font>
							</a>
						</c:if>
					</td>
					<td class="data l_td">
						<c:if test="${slist.member_auth == 1 }">
							<a href="javascript:changeauth('${slist.member_id }', 0)">
								<font color="blue">승인</font>
							</a>
							<!-- <a href="javascript:changeauth('${slist.member_id }', 1)">거부</a>  -->
						</c:if>
						<c:if test="${slist.member_auth == 0 }">
							승인완료
							<!-- <a href="javascript:changeauth('${slist.member_id }', 0)">승인</a>  -->
							<!-- <a href="javascript:changeauth('${slist.member_id }', 1)">거부</a>  -->
						</c:if>
					</td>
					<td class="data l_td">
						<a href="../member/updateMember.dog?type=1&id=${slist.member_id }&update=2&member_type=1">
							<font color="blue">수정</font></a>
						<a href="../member/deleteMember.dog?type=1&id=${slist.member_id }">
							<font color="blue">탈퇴</font></a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>