<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
</head>
<body>
<div style="width: 80%; margin-left: 10%;" >
	<div align="center">
		<h2>상품 수정</h2>
	</div>
	<hr>
	<br>
	<div align="center">
	<form:form modelAttribute="item" action="update.dog" enctype="multipart/form-data">
	<input type="hidden" value="${item.item_picture}" name="item_picture" >
	<input type="hidden" value="${item.item_no}" name="item_no" >	
	<table>
		<tr>
		<td style="width: 40%;" rowspan="3">
			<input type="file" name="picture">
		</td>

		<td style="width: 10%;">상품명</td>
		<td style="width: 30%;"><input name="item_name" value="${item.item_name}" /></td>
		<td style="width: 20%;"><font color="#e65407"><form:errors path="item_name"/></font></td>
		</tr>
		<tr>
		<td style="width: 10%;">가격</td>
		<td style="width: 30%;"><input name="item_price" value="${item.item_price}" /></td>
		<td style="width: 20%;"><font color="#e65407"><form:errors path="item_price"/></font></td>
		</tr>
		<tr>
		<td style="width: 10%;">상품코드</td>
		<td style="width: 30%;"><input name="item_code" value="${item.item_code}" /></td>
		<td style="width: 20%;"><font color="#e65407"><form:errors path="item_code"/></font></td>
		</tr>
		<tr>
			<td colspan="3" align="center">상품설명</td>
		</tr>
		<tr>
			<td colspan="3" align="center"><textarea name="item_content">${item.item_content}</textarea></td>
		</tr>
		<tr>
			<td colspan="4"><font color="#e65407"><form:errors path="item_content"/></font></td>
		</tr>
		<tr>
			<td class="btn_td" colspan="3" align="center"><input type="submit" class="s_btn" value="수정"></td>
		</tr>
	</table>
	</form:form>
	</div>
</div>
</body>
</html>