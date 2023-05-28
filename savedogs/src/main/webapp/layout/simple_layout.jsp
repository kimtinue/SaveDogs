<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	var path = "${path}";
	console.log(path);
	
</script>
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

	
	function directpage(dir,view){
		location.href="${path}/"+dir+"/"+view+".dog";
	}
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
        <decorator:body />
    </div>
    <!-- video-section -->
    <!-- footer -->
    <div class="footer" style="background-color: #34b2a4">
        <div class="container">
            <div class="row">
                
            <!-- tiny-footer -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                    <div class="tiny-footer">
                        <p style="color: white;">Copyright © All Rights Reserved 2020, 빅데이터 플랫폼 1조 : 구해독  </p>
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
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${path}/js/menumaker.js" type="text/javascript"></script>
    <script type="text/javascript" src="${path}/js/jquery.sticky.js"></script>
    <script type="text/javascript" src="${path}/js/sticky-header.js"></script>
    <script type="text/javascript" src="${path}/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="${path}/js/multiple-carousel.js"></script>
   
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
    <script type="text/javascript" src="${path}/js/video-play.js">
    </script>



</body>

</html>