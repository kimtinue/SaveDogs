<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='../css/savedogs_exc.css' /> 
<script type="text/javascript">
	function winclose(){
		self.close();
	}
	function idelete(){
		var no = "${item.item_no}";
		opener.location.href='delete.dog?item_no='+no;
		self.close();
	}
</script>
<title>아이템 삭제</title>
</head>
<body>
<div>
	<h3>삭제시 되돌릴 수 없습니다.</h3> 
	<h3>상품을 삭제하겠습니까?</h3>
</div>
<div>
	<form>
		<table style="width: 100%;">
			<tr>
				<td style="width: 50%;"><input style="margin-left: 33%;" class="s_btn" type="button" value="예" onclick="idelete()"></td>
				<td style="width: 50%;"><input style="margin-left: 33%;" class="s_btn" type="button" value="아니오" onclick="winclose()"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>