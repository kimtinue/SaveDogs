<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기부상세</title>
</head>
<body>
<div style="width: 80%; margin-left: 10%;" >
 <table class="w3-table" style="width:1000px">
       <!--  <form action="후원하기.dog=?fund_no=${f.fund_no}" method="POST"> -->
        <tr><td rowspan="4"><img src="" style="width:150%;" align="center" alt="후원 배너 사진"></td>
          <td colspan='2'>"${fund_subject}"</td></tr>
            <tr><td>"${sheltername}"</td></tr>
            <tr><td>달력 아이콘 : 1일 남음</td></tr>
            <td>게이지바</td>
        </table>
         <!-- 후원하기 -->
           <!-- 수정하기 -->
<div class="w3-panel">
        <hr>
        <!-- 스마일 + 응원댓글 작성하기 -->
        <!-- 댓글 작성칸 -->
        <!-- 댓글 리스트 -->
        </div>
      </div>
</body>
</html>