<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function check(href) {
		if(!$("input:checkbox[id='btn1']").is(":checked")) {
			alert("이용 약관에 동의하세요.")
			//location.href=selectSignup.dog
		}
		if(!$("input:checkbox[id='btn2']").is(":checked")) {
			alert("개인정보 수집 이용 약관에 동의하세요.")
			//location.href=selectSignup.dog
		}
		if($("input:checkbox[id='btn1']").is(":checked") && $("input:checkbox[id='btn2']").is(":checked")) {
			location.href=href;
		}
	}
</script>
<style type="text/css">
.s_btn{
	width: 150px;
}

h4{
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div align="right" style="margin-left: 30%; width:40%;">
		<div align="center">
			<h4>이용약관 동의</h4>
		</div>
		<textarea style="width:100%; height:200px;">
제 1조 총칙
    제 1조 (목적)
        이 이용약관은 구해독 (이하 “당 사이트”)에서 제공하는 인터넷 서비스(이하 '서비스')의 가입조건 및 이용에 관한 제반 사항과 기타 필요한 사항을 구체적으로 규정함을 목적으로 합니다. 
    제 2 조 (용어의 정의)
    ① "이용자"라 함은 당 사이트에 접속하여 이 약관에 따라 당 사이트가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.
		</textarea>
		<table>
			<tr>
				<td colspan="3"><h4>위의 이용약관에 동의합니다</h4></td>
				<td><input type="checkbox" id="btn1" class="chk"></td>
			</tr>
		</table>
		<br>
		<br>
		<br>
		<div align="center">
			<h4>개인정보 수집 이용 동의</h4>
		</div>
		<textarea style="width:100%; height:200px;">
1. 개인정보의 수집·이용에 관한 사항
  I. 개인정보의 수집·이용 목적
        가. 구해독 정책연동 : 입양, 펀딩,  후원품 쇼핑, 봉사 본인확인 제공을 위하여 개인의 정보를 수집, 이용하고 있습니다.
        나. 회원관리 : 회원원제 서비스 이용 및 제한적 본인 확인제에 따른 본인확인, 개인식별, 가입의사 확인, 가입 및 가입횟수 제한, 추후 법정 대리인 본인확인, 분쟁 조정을 위한 기록보존, 불만처리 등 민원처리,
		</textarea>
		<table>
			<tr>
				<td colspan="3"><h4>위의 이용약관에 동의합니다</h4></td>
				<td><input type="checkbox" id="btn2" class="chk"></td>
			</tr>
		</table>
		<br>
		<br>
		<br>
		<br>
		<div align="center">
			<input class="s_btn" type="button" value="일반회원" onclick="check('memberSignup.dog')">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="s_btn" type="button" value="보호소관리자" onclick="check('smemberSignup.dog')">
		</div>
	</div>
</body>
</html>