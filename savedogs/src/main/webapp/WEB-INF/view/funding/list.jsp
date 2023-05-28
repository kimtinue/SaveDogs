<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기부</title>
<script type="text/javascript">
 function listpage(page) { 
	document.searchform.pageNum.value=page; 
	document.searchform.submit();
	}
</script><link rel='stylesheet' href='../css/savedogs_main.css' />

</head>
<body>
<div style="width: 40%; margin-left: 30%;" >
	<div align="center" class="hw_div">
		<table>
			<tr>
			    <td align="center">기부</td>
				<td style="width: 50%;" align="center">
				<a href="../item/list.dog">후원쇼핑몰</a>
			</tr>
		</table>
	</div>
	</div>
	<div style="width: 60%; margin-left: 20%;" >
	 <hr>
	 </div>
<br>
<div style="width: 40%; margin-left: 30%;" >
   <c:if test="${listcount > 0}">
   <c:forEach var="f" items="${boardlist}">
        <table>
        <tr><td rowspan="4"><a href="detail.dog?fund_no=${f.fund_no}"><img src="img/${f.fund_pic}"  style="width:300px; height:240px" align="center" alt=""></a></td>
          <td><a href="detail.dog?fund_no=${f.fund_no}"> <h3><${f.fund_subject}></h3> </a></td></tr>
            <tr><td><i class="fa fa-building fa-fw w3-margin-right w3-large w3-text-teal"></i>${f.sheltername}</td></tr>
            <tr><td><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i>${f.restdate}일 남음</td></tr>
            <td width="60%">
            <input type="button" disabled="" readonly="readonly" style="width: ${f.complete}%; background-color: lightgreen; border-radius: 20px; border: none;" ></td>
            <td width="10%">${f.complete}%</td>
            <hr color = "green" size="10">
        </table>
		</c:forEach>
       <br>
        <table>
            <tr><td colspan="5">
           <c:if test="${pageNum > 1}">
           <a href="list.dog?pageNum=${pageNum-1}">[이전]</a></c:if>
           <c:if test="${pageNum <= 1}">[이전]</c:if>
      <c:forEach var="a" begin="${startpage}" end="${endpage}">
             <c:if test="${a == pageNum}">[${a}]</c:if>
             <c:if test="${a != pageNum}">
             <a href="list.dog?pageNum=${a}">[${a}]</a></c:if>
             </c:forEach>
             <c:if test="${pageNum < maxpage}"> <%--현재 페이지가 maxp 보다 작다->다음 페이지가 있음 --%>
             <a href="list.dog?pageNum=${pageNum + 1})">[다음]</a></c:if>
             <c:if test = "${pageNum >= maxpage}">[다음]</c:if>
             </td></tr></table>
             </c:if>
             <c:if test="${listcount==0}">
             <table>
               <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
               </table>
               </c:if>
                <div align="right">
        <c:if test="${!empty sessionScope.loginsmem}"><h6><a href="fregForm.dog?id=${sessionScope.loginsmem.member_id}">기부모집작성</a></h6>
        </c:if>
        </div>
       </div>
</body>
</html>