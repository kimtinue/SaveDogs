<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 입양 후기</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style type="text/css">
.b_th{
	width: 200px;
}

.replywrite{
	padding-bottom: 50px;
}
</style>
<script>
var board_no = '${board.board_no}'; 

//페이지 로딩시 댓글 목록 출력
$(document).ready(function(){
  replyList();  
});

//댓글 목록 
function replyList(){
  $.ajax({
      url : "replyList.dog",
      type : 'POST',
      data : {'board_no':board_no},
      success : function(data){
      	$("#replys").html(data);
      	
      },
		error : function(e) {
			alert("서버오류: " + e.status);
		}
	})
}

//댓글 등록
$(function() {
  $("#replyinsert_btn").on("click", function() {
	var insertData = $('[name=replyf]').serialize(); 
      replyInsert(insertData); 
  })
});
  
//댓글 등록
function replyInsert(insertData){
  $.ajax({
      url : "replyInsert.dog",
      type : 'POST',
      data : insertData,
      success : function(data){
    	  replyList(); 
    	  $('[name=board_comment]').val('');
      }
  });
}

//댓글 삭제 
function replyDelete(rno){
  $.ajax({
      url : "replyDelete.dog?rno="+rno,
      type : 'POST',
      success : function(data){
          replyList(); 
  }});
}

</script>
<style type="text/css">
.reply_div{
	padding-top: 50px;
}
</style>
</head>
<body>
<div class="main_div">
<h2>입양 후기</h2>
<hr>
	<table class="b_table">
		<tr><th class="b_th">제목</th><td class="b_td">${board.subject }</td></tr>
		<tr><th class="b_th">작성자</th><td class="b_td">${board.member_id }</td></tr>
		<tr><th class="b_th">작성일</th><td class="b_td"><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm"/></td></tr>
		<tr><th class="b_th">내용</th><td class="b_td"><div class="ctxt_div">${board.content }</div></td></tr>
	</table>

<div class="reply_div" >
	<hr>
	<h3>댓글</h3>
	<hr>
	<div class="replywrite" >
		<!-- action="replyInsert.dog"  -->
	<form:form modelAttribute="reply" name="replyf" >
			<input type="hidden" name="board_no" value="${board.board_no }">
			
			<c:if test="${sessionScope.loginmem != null || sessionScope.loginsmem != null}">
			<c:if test="${sessionScope.loginmem != null}">	
				<input type="hidden" name="member_id" value="${sessionScope.loginmem.member_id}">
				${sessionScope.loginmem.member_id} &nbsp;&nbsp;
			</c:if>
			<c:if test="${sessionScope.loginsmem != null}">	
				<input type="hidden" name="member_id" value="${sessionScope.loginsmem.member_id}">
				${sessionScope.loginsmem.member_id} &nbsp;&nbsp;
			</c:if>
			<input type="text" name="board_comment" class="content_txt" style="width: 500px;">
			<input type="button" value="등록" id="replyinsert_btn" class="s_btn">
			</c:if>			
	</form:form>
				
	</div>
	<div class="replys" id="replys"></div>
	
</div>

<div class="btn_div">
	<input type="button" value="목록" class="s_btn" onclick="location.href='reviewList.dog?type=${board.type}'">
	<c:if test="${sessionScope.loginmem.member_id == board.member_id }">
		<input type="button" value="수정" class="g_btn" onclick="location.href='reviewUpdate.dog?no=${board.board_no}'">
		<input type="button" value="삭제" class="g_btn" onclick="location.href='reviewDelete.dog?no=${board.board_no}'">
	</c:if>
</div>

</div>
</body>
</html>