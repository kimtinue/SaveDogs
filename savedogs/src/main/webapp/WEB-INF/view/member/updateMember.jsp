<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?autoload=false"></script>
<script>
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
                document.getElementById("member_postcode").value = data.zonecode;
                document.getElementById("member_address").value = addr + extraAddr;
                document.getElementById("member_daddress").focus();
            }
        }).open();
	}
	
	function file_delete(no) {
		if(no==1) {
			file_desc1.style.display = "none";
		} else if (no==2) {
			file_desc2.style.display = "none";
		}
		
	}
</script>
</head>
<body>
	<div class="main_div">
	<form:form modelAttribute="member" method="post" action="updateMember.dog" name="f" enctype="multipart/form-data">
		<spring:hasBindErrors name="member">
			<font color="red">
				<c:forEach items="${errors.globalErrors }" var="error">
					<spring:message code="${error.code }" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<br>
		<c:if test="${!empty sessionScope.loginmem or param.update==1}">
		<input type="hidden" value="0" name="member_type" />
		<table style="border-collapse:collapse; ">
			<tr height="40px">
				<th>아이디</th>
				<td>
					<input type="text" name="member_id" value="${member.member_id }" readonly="readonly">
					<font color="red">
						<form:errors path="member_id" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>이름</th>
				<td>
					<input type="text" name="member_name" value="${member.member_name }">
					<font color="red">
						<form:errors path="member_name" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>생년월일</th>
				<td>
					<fmt:formatDate var="birth" value="${member.member_birthday }" pattern="yyyy-MM-dd" />
					<input type="text" name="member_birthday" value="${birth }">
					<font color="red">
						<form:errors path="member_birthday" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>우편번호</th>
				<td>
					<input type="text" id ="member_postcode" name="member_postcode" value="${member.member_postcode }" style="width:120px;">
					<input class="small_btn" type="button" value="우편번호 찾기" onclick="openDaumZipAddress();">
				</td>
				<td>
				</td>
			</tr>
			<tr height="40px">
				<th>주소</th>
				<td>
					<input type="text" id="member_address" name="member_address" value="${member.member_address }">
					<font color="red">
						<form:errors path="member_address" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>상세주소</th>
				<td>
					<input type="text" id="member_daddress" name="member_daddress" value="${member.member_daddress }">
					<font color="red">
						<form:errors path="member_daddress" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>전화번호</th>
				<td>
					<input type="text" name="member_tel" value="${member.member_tel }">
					<font color="red">
						<form:errors path="member_tel" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>이메일</th>
				<td>
					<input type="text" name="member_email" value="${member.member_email }">
					<font color="red">
						<form:errors path="member_email" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<td class="btn_td" colspan="2" align="center">
					<input class="s_btn" style="width:150px;" type="button" value="비밀번호 변경" onclick="window.open('changepass.dog?id=${member.member_id}','','width=500, height=250, left=150,top=150')">
					<input class="s_btn" type="submit" value="수정 완료">
				</td>
			</tr>
		</table>
		</c:if>
		<c:if test="${!empty sessionScope.loginsmem or param.update==2}">
		<input type="hidden" value="1" name="member_type" />
		<table style="border-collapse:collapse; ">
			<tr height="40px">
				<th>아이디</th>
				<td>
					<input type="text" name="member_id" value="${member.member_id }" readonly="readonly">
					<font color="red">
						<form:errors path="member_id" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>전화번호</th>
				<td>
					<input type="text" name="member_tel" value="${member.member_tel }">
					<font color="red">
						<form:errors path="member_tel" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<th>이메일</th>
				<td>
					<input type="text" name="member_email" value="${member.member_email }">
					<font color="red">
						<form:errors path="member_email" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<td>보호소 번호</td>
				<td>
					<input type="text" id="shelter_no" name="shelter_no" value="${member.shelter_no }" readonly="readonly">
				</td>
				<td>
					<font color="red">
						<form:errors path="shelter_no" />
					</font>
				</td>
			</tr>
			<tr height="40px">
				<td>첨부파일</td>
				<td>&nbsp;
				<c:if test="${!empty member.file1 }">
					<div id="file_desc1">
						<a href="member/img/${member.file1 }">${member.file1 }</a>
						<a href="javascript:file_delete(1)">[파일첨부삭제]</a>
					</div>
				</c:if>
				<c:if test="${!empty member.file2 }">
					<div id="file_desc2">
						<a href="member/img/${member.file2 }">${member.file2 }</a>
						<a href="javascript:file_delete(2)">[파일첨부삭제]</a>
					</div>
				</c:if>
				<input type="file" name="f1">
				<input type="file" name="f2">
			</td>
			</tr>
			<tr height="40px">
				<td class="btn_td" colspan="2" align="center">
					<input class="s_btn" style="width:150px;" type="button" value="비밀번호 변경" onclick="window.open('changepass.dog?id=${member.member_id}','','width=500, height=250, left=150,top=150')">
					<input class="s_btn" type="submit" value="수정 완료">
				</td>
			</tr>
		</table>
		</c:if>
		</form:form>
	</div>
</body>
</html>
