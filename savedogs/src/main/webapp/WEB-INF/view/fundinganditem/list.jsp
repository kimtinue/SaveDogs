<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>MyPage</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#minfo").show();
		$("#oinfo").hide();
		$(".saleLine").each(function(){
			$(this).hide();
		})
		$("#tab1").addClass("select");
	})
	function disp_div(id,tab){
		$(".info").each(function(){
			$(this).hide();
		})
		$(".tab").each(function(){
			$(this).removeClass("select");
		})
		$("#"+id).show();
		$("#"+ tab).addClass("select");
	}
	function list_disp(id){
		$("#"+id).toggle();
	}
</script>
<style type="text/css">
	.select{
		padding: 3px;
	}
	.select > a {
		color: #000000;
		text-decoration : none;
		font-weight : bold;	
	}
</style>
</head>
<body>
<div style="margin-left: 20%; margin-right: 20%;">
	<table style="width: 100%;">
		<tr>
			<td id="tab1" class ="tab" style="width: 50%;" align="center">
				<a href="javascript:disp_div('minfo','tab1')" style="color: gray; width: 50%;">펀딩</a>
			</td>
			<c:if test="${param.id != 'admin'}">
				<td id="tab2" class="tab" align="center">
					<a href="javascript:disp_div('oinfo','tab2')" style="color: gray; width: 50%;">후원 쇼핑몰</a>
				</td>
			</c:if>
		</tr>
	</table>
	<hr>
	<%-- oinfo : 쇼핑몰 정보 출력  --%>
	<div id="oinfo" class="info" style="display: none; width: 100%;">
		<div align="center">
			<h6 style="color: #8ac7e9">작은 후원으로 유기견들에게 큰 희망을!</h6>
			<h6 style="color: #8ac7e9">수익금 전액은 유기견 보호소 후원에 사용됩니다.</h6>
		</div>
		<div class="row">
		 	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
		</div>
				<div class="row">
		 	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 ">
            	<div class="post-block">
                	<!-- post block -->
                    <div class="post-img">
                    	<a href="#" class="imghover">
                        	<img src="../images/dogband.jpg">
                        </a>
                    </div>
                </div>
                <div class="post-content">
                	<div class="meta">
                		<a href="#" class="meta-categories text-primary">도그 손목 밴드</a>
                	</div>
                	<h3 class="post-title"><a href="#" class="title">8900원</a></h3>
                </div>
			</div>
		</div>
	</div>
	
	<%-- minfo  : 기부 정보 출력 --%>
	<div id="minfo" class="info">
	
	
	</div>
</div>
</body></html>