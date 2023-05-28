<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="type" value="${param.type }" />
<%-- 슬라이드없는 심플 레이아웃 버전 --%>
<!DOCTYPE html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	var path = "${path}";
	$(document).ready(function() {
		var type = ${type}
		if(type==1) {
			$(".mytab").removeClass("select");
			$("#mytab1").addClass("select");
		} else if(type==2) {
			$(".mytab").removeClass("select");
			$("#mytab2").addClass("select");
		} else if(type==3) {
			$(".mytab").removeClass("select");
			$("#mytab3").addClass("select");
		} else if(type==4) {
			$(".mytab").removeClass("select");
			$("#mytab4").addClass("select");
		} else {
			$(".mytab").removeClass("select");
			$("#mytab5").addClass("select");
		}
		
	})
</script>
<style type="text/css">
.tab_a{
	font-size: 25px;
	
}
.select {
	padding: 3px;
}

.select>a {
	color: #34b2a4;
	text-decoration: none; /* 하이퍼링크 밑줄 제거 */
	font-weight: bold; /* 글씨체 굵게 */
}
.mytab{
	padding: 30px;
	text-align: center;
}
</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title> <decorator:title /> </title>
    <!-- Bootstrap -->
    <link href="${path }/css/bootstrap.min.css" rel="stylesheet">
    <!-- Style CSS -->
    <link href="${path }/css/style.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i%7CPT+Serif:400,400i,700,700i" rel="stylesheet">
    <!-- Owl Carousel CSS -->
    <link href="${path }/css/owl.carousel.css" rel="stylesheet">
    <link href="${path }/css/owl.theme.default.css" rel="stylesheet">
    <!-- FontAwesome CSS -->
    <link rel="stylesheet" type="text/css" href="${path }/css/fontello.css">
    <link href="${path }/css/font-awesome.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script type="text/javascript">
 	var path = '${path}';

</script>
<decorator:head />
<body>
    <!-- top-header-section-->
    <div class="top-header" style="background-color: #34b2a4">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-8 col-md-7 col-sm-6 col-xs-12" style="text-align: left;">
                    <p class="welcome-text" style="color: white;">구해독 페이지에 오신걸 환영합니다.</p>
                </div>
                <div class="col-lg-4 col-md-5 col-sm-6 col-xs-12">
                    <ul style="color: white;">
                    <c:if test="${empty sessionScope.loginmem and empty sessionScope.loginsmem and empty sessionScope.loginadmin}">
                    	<a style="color: white;" href="${path}/member/login.dog">로그인</a>&nbsp;|&nbsp; <a href="${path}/member/selectSignup.dog" style="color: white;">회원가입</a>
                    </c:if>
                    <c:if test="${!empty sessionScope.loginmem}">
                    	${sessionScope.loginmem.member_name}님 반갑습니다.&nbsp;|&nbsp; <a style="color: white;" href="${path}/member/logout.dog">로그아웃</a>&nbsp;|&nbsp;<a href="${path}/member/memberMypage.dog?type=1&id=${sessionScope.loginmem.member_id}" style="color: white;">마이페이지</a>
                    </c:if>
                    <c:if test="${!empty sessionScope.loginsmem}">
                    	${sessionScope.smemName}관리자님 반갑습니다.&nbsp;|&nbsp; <a style="color: white;" href="${path}/member/logout.dog">로그아웃</a>&nbsp;|&nbsp;<a href="${path}/member/shelterMypage.dog?type=1&id=${sessionScope.loginsmem.member_id}" style="color: white;">마이페이지</a>
                    </c:if>
                    <c:if test="${!empty sessionScope.loginadmin}">
                    	관리자님 반갑습니다.&nbsp;|&nbsp; <a style="color: white;" href="${path}/member/logout.dog">로그아웃</a>&nbsp;|&nbsp;<a href="${path}/admin/adminMypage.dog?type=1&id=${sessionScope.loginadmin.member_id}" style="color: white;">마이페이지</a>
                    </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="menu-toggel">
        <a href="#" id="dots" class="dots-icon"><i class="fa fa-ellipsis-v visible-xs"></i></a>
    </div>
    <!-- /.top-header-section-->
    <!-- header-section-->
    <div class="header-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12" style="margin-left: 10%; width: 15%; float: left;">
                    <div class="logo">
                        <a href="${path}/main.dog"><img src="${path}/images/logo.png" style="width: 50%; height: 50%;"> </a>
                    </div>
                </div>
                <div class="col-lg-10 col-md-8 col-sm-12 col-xs-12" style="width: 75%; float: right;" >
                    <!-- navigations-->
                    <div class="navigation">
                        <div id="navigation" style="margin-right: 10%;">
                           <ul>
                                <li class="active"><a href="${path}/adopt/amain.dog">입양</a></li>
                                  <li class="has-sub"><a href="#">후원</a>
                                    <ul>
                                        <li><a href="${path}/funding/list.dog">기부</a></li>
                                        <li><a href="${path}/item/list.dog">쇼핑</a></li>
                                    </ul>
                                </li>
                                <li class="active"><a href="${path}/vwork/vmain.dog">봉사</a></li>
                              	<li class="active"><a href="${path}/info.dog">정보</a></li>
                                 <li class="has-sub"><a href="#">커뮤니티</a>
                                    <ul>
                                        <li><a href="${path }/board/qnaList.dog?type=2">Q&A</a></li>
                                        <li><a href="${path }/board/noticeList.dog?type=1">공지사항</a></li>
                                        <li><a href="${path }/board/reviewList.dog?type=0">입양 후기 </a></li>
                                    </ul>
                                </li>
                               
                            </ul>
                        </div>
                    </div>
                    <!-- /.navigations-->
                </div>
            </div>
        </div>
    </div>
    <!-- /. header-section-->

    <!-- coachservice-section -->
    <div class="space-medium">
    <!-- <div style="margin-left: 30%; width: 40%;"> -->
		<h3>My Page</h3>
		<hr>
		<c:if test="${!empty sessionScope.loginmem or param.update == 1}">
		<div align="center" style="width: 100%;">
			<table>
				<tr>
					<td id="mytab1" align="center" class="mytab"><a
						href="memberMypage.dog?type=1&id=${sessionScope.loginmem.member_id }" class="tab_a">내정보</a></td>
					<td id="mytab2" align="center" class="mytab"><a
						href="vworkMypage.dog?type=2&id=${sessionScope.loginmem.member_id }" class="tab_a">봉사</a>
					</td>
					<td id="mytab3" align="center" class="mytab"><a
						href="fundMypage.dog?type=3&id=${sessionScope.loginmem.member_id }" class="tab_a">기부</a>
					</td>
					<td id="mytab4" align="center" class="mytab"><a
						href="adoptMypage.dog?type=4&id=${sessionScope.loginmem.member_id }" class="tab_a">입양</a>
					</td>
					<td id="mytab5" align="center" class="mytab" ><a
						href="shopMypage.dog?type=5&id=${sessionScope.loginmem.member_id }" class="tab_a">쇼핑</a>
					</td>
				</tr>
			</table>
		</div>
		</c:if>
		<c:if test="${!empty sessionScope.loginsmem or param.update == 2}">
		<div align="center" style="width: 100%; ">
			<table>
				<tr>
					<td id="mytab1" align="center" class="mytab"><a
						href="shelterMypage.dog?type=1&id=${sessionScope.loginsmem.member_id }" class="tab_a">내정보</a></td>
					<td id="mytab2" align="center" class="mytab"><a
						href="sheltervworkMypage.dog?type=2&id=${sessionScope.loginsmem.member_id }" class="tab_a">봉사</a>
					</td>
					<td id="mytab3" align="center" class="mytab"><a
						href="shelterfundMypage.dog?type=3&id=${sessionScope.loginsmem.member_id }" class="tab_a">기부</a>
					</td>
					<td id="mytab4" align="center" class="mytab"><a
						href="shelteradoptMypage.dog?type=4&id=${sessionScope.loginsmem.member_id }" class="tab_a">입양</a>
					</td>
				</tr>
			</table>
		</div>
		</c:if>
		<c:if test="${!empty sessionScope.loginadmin and param.update == null}">
		<div align="center" style="width: 100%; ">
			<table>
				<tr>
					<td id="mytab1" align="center" class="mytab"><a
						href="adminMypage.dog?type=1&id=${sessionScope.loginadmin.member_id }" class="tab_a">내정보</a></td>
					<td id="mytab2" align="center" class="mytab"><a
						href="adminlistMypage.dog?type=2&id=${sessionScope.loginadmin.member_id }" class="tab_a">회원</a>
					</td>
					<td id="mytab3" align="center" class="mytab"><a
						href="adminshopMypage.dog?type=3&id=${sessionScope.loginadmin.member_id }" class="tab_a">후원</a>
					</td>
				</tr>
			</table>
		</div>
		</c:if>
		<decorator:body />
	</div>
    <!-- </div>  -->
    <!-- video-section -->
    <!-- footer -->
    <div class="footer" style="background-color: #34b2a4">
        <div class="container">
            <div class="row">
                
            <!-- tiny-footer -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                    <div class="tiny-footer">
                        <p>Copyright © All Rights Reserved 2020, 빅데이터 플랫폼 1조 : 구해독  </p>
                    </div>
                </div>
                <!-- /. tiny-footer -->
            </div>
        </div>
    </div>
    <!-- /.footer -->


<!-- ICON NEEDS FONT AWESOME FOR CHEVRON UP ICON -->

  
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${path }/js/jquery.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${path }/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${path }/js/menumaker.js" type="text/javascript"></script>
    <script type="text/javascript" src="${path }/js/jquery.sticky.js"></script>
    <script type="text/javascript" src="${path }/js/sticky-header.js"></script>
    <script type="text/javascript" src="${path }/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="${path }/js/multiple-carousel.js"></script>
   
    <script type="text/javascript">
    $("#dots").click(function() {
        $(".top-header").toggle("slow", function() {
            // Animation complete.
        });
    });
    </script>
    <script type="text/javascript">
    $(document).ready(function() {
        $('.btn-vertical-slider').on('click', function() {
            if ($(this).attr('data-slide') == 'next') {
                $('#myCarousel').carousel('next');
            }
            if ($(this).attr('data-slide') == 'prev') {
                $('#myCarousel').carousel('prev')
            }
        });
    });
    </script>
    <script type="text/javascript" src="${path }/js/video-play.js">
    </script>



</body>

</html>