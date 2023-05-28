<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양 신청</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
input[type=checkbox] {
	width: 17px;
	height: 17px;
}

input[type=file] {
	text-align: center;
}
</style>
<script type="text/javascript">
	function check() {
		if (!$("input:checkbox").is(":checked")) {
			alert("개인정보 취급방침에 동의하세요.")
		} else
			adoptform.submit()
	}
</script>
</head>
<body>
	<div>
		<h2>입양 신청</h2>
		<br>
		<div>
			<div>
				<h3>개인정보 취급방침</h3>
				<form:form modelAttribute="adoptSign" method="POST"
					action="adoptSignup.dog" id="adoptform" enctype="multipart/form-data">
					<table>
						<tr>
							<td colspan="2"><textarea
									style="width: 500px; height: 150px;">
구해독은 입양신청자의 개인정보를 중요시하며, "정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다.
개인정보취급방침을 통하여 입양신청자께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.

■ 개인정보 수집에 대한 동의

1) 구해독은 이용자의 개인정보를 수집하는 경우에는 [위의 개인정보취급방침에 동의합니다]의 체크박스에 체크하는 절차를 마련하고 있으며, [위의 개인정보취급방침에 동의합니다]의 체크박스에 체크하였을 경우 개인정보 수집에 대하여 동의한 것으로 봅니다.
2) 구해독은 다음 사항에 해당하는 경우에 이용자의 별도 동의 없이 개인정보를 수집.이용할 수 있습니다.
- 서비스의 제공에 관한 계약의 이행을 위하여 필요한 개인정보로서 경제적.기술적인 사유로 통상의 동의를 받는 것이 현저히 곤란한 경우
- 서비스의 제공에 따른 요금정산을 위하여 필요한 경우
- 이 법 또는 다른 법률에 특별한 규정이 있는 경우

■ 수집하는 개인정보 항목

구해독은 회원가입, 상담, 서비스 신청 등등을 위해 아래와 같은 개인정보를 수집하고 있습니다.

ο 수집항목 : 이름, 생년월일, 로그인ID, 비밀번호, 자택 전화번호, 자택 주소, 휴대전화번호, 이메일, 직업, 단체명, 단체전화번호, 주민등록번호, 은행계좌 정보, 접속 로그, 쿠키, 후원회비, 자동이체날짜, 봉사희망분야, 단체주소, 자기소개, 자기정보공개여부
ο 개인정보 수집방법 : 홈페이지(회원가입,후원하기)

■ 개인정보의 수집 및 이용목적

구해독은 수집한 개인정보를 다음의 목적을 위해 활용합니다..

ο 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산
- 콘텐츠 제공
ο 회원 관리
- 회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불만처리 등 민원처리 , 고지사항 전달
ο 마케팅 및 광고에 활용
- 이벤트 등 광고성 정보 전달

■ 개인정보의 보유 및 이용기간

원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 단체는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.

- 보존 항목 : 이름, 휴대전화번호, 이메일, 은행계좌 정보, 후원회비, 자동이체날짜, 봉사희망분야, 단체주소, 자기소개, 자기정보공개여부
- 보존 근거 : 전자상거래등에서의 소비자보호에 관한 법률
- 보존 기간 : 5년</textarea></td>
						<tr>
							<td colspan="2" style="text-align: right;">위 개인정보 취급방침에
								동의합니다.&emsp;<input type="checkbox">
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td><a href="입양신청서 .docx" class="g_btn">다운로드</a></td>
							<td>해당 파일을 다운받아 작성 후 첨부하세요</td>
						</tr>
						<tr>
							<td colspan="2"><input type="file" name="adopt_f"></td>
						</tr>
					</table>
					<br>
					<form:hidden path="member_id"
						value="${sessionScope.loginmem.member_id}" />
					<form:hidden path="dog_no" value="${param.noticeNo}" />
					<input type="hidden" name="careNm" value="${param.careNm}">
					<input type="hidden" name="orgNm" value="${param.orgNm}">
					<input type="button" value="제출" class="s_btn" onclick="check()">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>