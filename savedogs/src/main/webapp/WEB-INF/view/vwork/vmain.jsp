<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구해독 : 봉사 메인 </title>
<!-- calendar 관련 -->
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js"></script>

<link href='${path }/js/packages/core/main.css' rel='stylesheet' />
<link href='${path }/js/packages/daygrid/main.css' rel='stylesheet' />
<script src='${path }/js/packages/core/locales/ko.js'></script>
<script src='${path }/js/packages/core/main.js'></script>
<script src='${path }/js/packages/daygrid/main.js'></script>
<script src='${path }/js/packages/interaction/main.js'></script>

<link rel='stylesheet' href='../css/savedogs_cal.css' />


<script type="text/javascript">
	$(function(){
		$("#op").click(function(){
			$.ajax({
				url : "shelterNames.dog?goo="+$("#goo").val(),
				type : "post",
				success : function(data){
					console.log(data)
		            var shelters = data;
		            var str = '';
		            $.each(shelters , function(i){
		                str += '<option>' + shelters[i].shelter_name +'</option>';
		     
		           });
		           $("#shelterList").append(str); 
		        },
				error : function(e){
					alert("서버오류:"+e.status);
				}
			})			
		})	
	})
</script>
		
<script>
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
   	var calendar = new FullCalendar.Calendar(calendarEl, {
   		plugins : [ 'interaction', 'dayGrid', 'timeGrid' ],
   		locale : 'ko',
   		selectable : true,
		header : {
			left : 'title', 
			center : '',
			right : 'today,prev,next' 
		},dateClick : function(info) { 
		},
		eventClick: function(info) { //이벤트 클릭
			info.jsEvent.preventDefault();
			var date = moment(info.event.start).format('YYYY-MM-DD');
			window.location.href = "vlist.dog?date="+date; //dateformat 필요
		}, 
		select: function(info) { //클릭
			if(${smem != null}){
				info.jsEvent.preventDefault();
	      	 	var op = "width=800, height=700, left=500, top=150";
	      	 	open("vwrite.dog?date="+info.startStr,"",op) <%-- +"&id="+document.f.id.value --%>	
			}		    
	    },
		events: 
			${json}
   })
   calendar.render();
});
</script>
</head>
<body>

<div class="main_div">
	<div class="search_div">
		<form action="vmain.dog" method="post" name="f">
			 <!--<select id="goo">
				<option value="">전체</option>
				<c:forEach items="${list }" var="s">
					<option id="op" value="${s.shelter_address }">${s.shelter_address }</option>
				</c:forEach>
			</select> -->
			
			<select id="shelterList" name="shelter_no" style="width: 400px;">
				<option value="">전체</option>
				<c:forEach items="${list }" var="s">
					<option id="op" value="${s.shelter_no }">${s.shelter_address }&nbsp;${s.shelter_name }</option>
				</c:forEach>
			</select>
			<script type="text/javascript">
				f.shelter_no.value = "${shelter_no}";
			</script>
			<input class="s_btn" type="submit" value="검색" >
			
		</form>
		
	</div>
	<br>
   	<div class="cal_div" >
    	<div id="calendar" style="width: 800px; hegiht: 1000px;"></div>
   	</div>
</div>
</body>
</html>