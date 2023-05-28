<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/jspHeader.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기부상세</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<link rel='stylesheet' href='../css/savedogs_main.css' /> 
<script>


function win_open(page) {
var op = "width=800, height=700, left=500, top=150";
open(page+".dog?fund_no=${param.fund_no}","",op);

}


var pno = '${param.fund_no}'; //게시글 번호

$(document).ready(function(){
	  replyList();  
});
 
//댓글 목록 
function replyList(){
  $.ajax({
      url : "replyList.dog",
      type : 'POST',
      data : {'pno':pno},
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
      console.log(insertData);
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
    	  $('[name=fund_comment]').val('');
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
</head>
<body>

<div style="width:80%;" class="main_div">
 <form:form modelAttribute="funding" method="post" action="delete.dog">
<input type="hidden" value="${param.fund_no}" name="fund_no">
 <table class="w3-table" style="width:1000px; ">
       <!--  <form action="후원하기.dog=?fund_no=${f.fund_no}" method="POST"> -->
        <tr><td rowspan="7"><img src="img/${funding.fund_pic}" style="width:250px; height:280px" alt="기부 배너 사진"></td></tr>
          <td colspan='1'><h3><${funding.fund_subject}></h3></td>
            <tr><td><i class="fa fa-building fa-fw w3-margin-right w3-large w3-text-teal"></i>"${funding.sheltername}"</td></tr>
            <tr><td><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>기부 기간 : <fmt:formatDate value="${funding.start_date}" pattern="yyyy.MM.dd" var="startdate" />
             <fmt:formatDate value="${funding.end_date}" pattern="yyyy.MM.dd" var="enddate" />
            ${startdate}&nbsp;-&nbsp;${enddate}</td></tr>
            <tr><td><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>${funding.restdate}일 남음</td></tr>
            <tr><td><i class="fa fa-money fa-fw w3-margin-right w3-large w3-text-teal"></i>목표금액 : ${funding.fund_count}원</td></tr>
            <td width="50%">
            <input type="button" disabled="disabled" readonly="readonly" style="width: ${funding.complete}%;  background-color: lightgreen; border-radius: 20px; border: none;" " ></td>
            <td width="8%" align="center">${funding.complete}%</td>
            <tr><td>&nbsp;</tr><td>
             </table> 
             <c:if test="${!empty sessionScope.loginmem && funding.restdate > 0}"><h6><a href="fundingapply.dog?id=${sessionScope.loginmem.member_id}"></a></h6>
             <tr><td><h5 style="text-align: center; margin-right:250px"><input type="button" class="s_btn" value="기부하기" onclick="win_open('fundingapply')"></h5></td></tr>
             </c:if>
         
             <c:if test="${!empty sessionScope.loginsmem && sessionScope.loginsmem.member_id.equals(funding.member_id)}"><h6><a href="fregForm.dog?id=${sessionScope.loginsmem.member_id}"></a></h6>
             <tr><td><h5 style="text-align: center; margin-right:100px"><input type="button" class="s_btn" value="수정하기" onclick="location.href='fregupdateForm.dog?fund_no=${param.fund_no}'"></h5></td>
             <!-- <td><h5><input type="submit" value="삭제하기"></h5></td></tr>-->
             </c:if>
          
                </form:form>
        <!-- 댓글 -->
        <hr>
   		<div class="container" style="text-align: center;">
        <label for="content">comment</label>
        <div class="replywrite" >
        
        <c:if test="${sessionScope.loginmem != null || sessionScope.loginsmem != null}">
		<form:form modelAttribute="fundreply" name="replyf" >
			<input type="hidden" name="fund_no" value="${funding.fund_no }">
			<c:if test="${sessionScope.loginmem != null}">
			<input type="hidden" name="fundreply_id" value="${sessionScope.loginmem.member_id}">
			${sessionScope.loginmem.member_id} &nbsp;&nbsp;
			</c:if>
			<c:if test="${sessionScope.loginsmem != null}">
			<input type="hidden" name="fundreply_id" value="${sessionScope.loginsmem.member_id}">
			${sessionScope.loginsmem.member_id} &nbsp;&nbsp;
			</c:if>
			<input type="text" name="fund_comment" class="content_txt" style="width: 500px;">
			<input type="button" value="등록" id="replyinsert_btn" class="s_btn">			
		</form:form>
		</c:if>
    </div>
    <div class="container">
        <div class="replys" id="replys"></div>
    </div>
</div>
 </div>
</body>
</html>
