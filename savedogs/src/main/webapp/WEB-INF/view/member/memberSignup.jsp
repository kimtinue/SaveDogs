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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?autoload=false"></script>
<script>
	$(function() {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("#member_pass2").keyup(function() {
			var pass=$("#member_pass").val();
			var pass2=$("#member_pass2").val();
			if(pass != "" || pass2 != "") {
				if(pass == pass2) {
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#submit").removeAttr("disabled");
				} else {
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#submit").attr("disabled", "disabled");
				}
			}
		});
	});

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
                document.getElementById('member_postcode').value = data.zonecode;
                document.getElementById("member_address").value = addr + extraAddr;
                document.getElementById("member_daddress").focus();
            }
        }).open();
	}
</script>
</head>
<body>
	<div class="main_div">
	<h2>일반 회원 가입</h2>
	<hr>
	<form:form modelAttribute="member" method="post" action="memberSignup.dog">
		<spring:hasBindErrors name="member">
			<font color="red">
				<%--globalErrors : error.reject(코드값) --%>
				<c:forEach items="${errors.globalErrors }" var="error">
					<spring:message code="${error.code }" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<br>
		<table style="border-collapse:collapse; ">
			<tr height="40px">
				<th>아이디</th>
				<td>
					<form:input path="member_id"/>
					<font color="red">
						<form:errors path="member_id" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>비밀번호</th>
				<td>
					<form:password path="member_pass" />
					<font color="red">
						<form:errors path="member_pass" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>비밀번호 확인</th>
				<td>
					<form:password path="member_pass2" />
					<div id="alert-success" style="font:red;">
						비밀번호가 일치합니다.
					</div>
					<div id="alert-danger" style="font:red;">
						비밀번호가 일치하지 않습니다.
					</div>
				</td>
			</tr>
			<tr height="40px">
				<th>이름</th>
				<td>
					<form:input path="member_name" />
					<font color="red">
						<form:errors path="member_name" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>생년월일</th>
				<td>
					<form:input path="member_birthday" />
					<font color="red">
						<form:errors path="member_birthday" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>우편번호</th>
				<td>
					<form:input path="member_postcode" style="width:120px;"/>
					<input class="small_btn" type="button" value="우편번호 찾기" onclick="openDaumZipAddress();">
				</td>
				<td>
					<font color="red">
						<form:errors path="member_postcode" />
					</font>
					
				</td>
			</tr>
			<tr height="40px">
				<th>주소</th>
				<td>
					<form:input path="member_address" />
					<font color="red">
						<form:errors path="member_address" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>상세주소</th>
				<td>
					<form:input path="member_daddress" />
					<font color="red">
						<form:errors path="member_daddress" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>전화번호</th>
				<td>
					<form:input path="member_tel" value="010-0000-0000"/>
					<font color="red">
						<form:errors path="member_tel" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>이메일</th>
				<td>
					<form:input path="member_email" />
					<font color="red">
						<form:errors path="member_email" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<td class="btn_td" colspan="2" align="center">
					<input class="s_btn" type="submit" value="회원가입">
					<input class="g_btn" type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>
