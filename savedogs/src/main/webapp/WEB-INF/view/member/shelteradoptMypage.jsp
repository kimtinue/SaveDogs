<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My 보호소</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
	.data {
		text-align: center;
	}
</style>
<script>
	function changeEtc(dog_no, state) {
		$.ajax({
			url : "../ajax/changeEtc.dog",
			type : "POST",
			data : "dog_no="+ dog_no + "&state="+state,
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
</head>
<body>
	<div class="main_div" style="width: 100%">
		<c:if test="${empty shelteradoptlist }">
			<h3>입양 신청 내역이 없습니다.</h3>
		</c:if>
		<c:if test="${!empty shelteradoptlist }">
		<table>
			<tr>
				<th class="l_th">신청자아이디</th>
				<th class="l_th">공고번호</th>
				<th class="l_th">신청날짜</th>
				<th class="l_th">진행단계</th>
				<th class="l_th">신청서</th>
				<th class="l_th">처리</th>
			</tr>
			<c:forEach items="${shelteradoptlist }" var="list" varStatus="stat">
				<tr>
					<td class="data l_td" style="width:360px">
						${list.member_id}
					</td>
					<td class="data l_td" style="width:200px;"><a href="../adopt/adetail.dog?noticeNo=${list.dog_no }">${list.dog_no }</a></td>
					<td class="data l_td">
						<fmt:formatDate value="${list.adopt_date }" pattern="yyyy-MM-dd" var="day"/>
						${day }
					</td>
					<td class="data l_td">${list.adopt_etc==0?"신청":list.adopt_etc==1?"거부":list.adopt_etc==2?"승인":"완료" }</td>
					<td class="l_td">
						<a href="../adopt/img/${list.adopt_file }">신청서 보기</a>
					</td>
					<td class="data l_td">
						<c:if test="${list.adopt_etc==0 }">
							<input class="s_btn" type="button" value="승인" onclick="changeEtc('${list.dog_no}', 2)">
							<input class="g_btn" type="button" value="거부" onclick="changeEtc('${list.dog_no}', 1)">
						</c:if>
						<c:if test="${list.adopt_etc==2 }">
							<input class="s_btn" type="button" value="완료" onclick="changeEtc('${list.dog_no}', 3)">
						</c:if>
						<c:if test="${list.adopt_etc==3 }">
							
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>