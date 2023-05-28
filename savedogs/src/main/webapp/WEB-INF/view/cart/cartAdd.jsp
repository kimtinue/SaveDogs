<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='../css/savedogs_exc.css' /> 
<script type="text/javascript">
	function Ilist(){
		self.close();
	}
	function cart(){
		opener.location.href='cartView.dog'
		self.close();
	}
</script>
<title>장바구니 이동</title>
</head>
<body>
<div>
	<h3>장바구니에 상품이 추가되었습니다.</h3> 
	<h3>계속해서 쇼핑을 진행할까요?</h3>
	<h3>아니요 선택시 장바구니로 이동됩니다.</h3>
</div>
<div>
	<form>
		<table style="width: 100%;">
			<tr>
				<td style="width: 50%;"><input style="margin-left: 33%;" class="s_btn" type="button" value="예" onclick="Ilist()"></td>
				<td style="width: 50%;"><input style="margin-left: 33%;" class="g_btn" type="button" value="아니오" onclick="cart()"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>