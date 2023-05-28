<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키워드 검색</title>
<link rel='stylesheet' href='../css/savedogs_main.css' />
<style>
hr {
	width: 100%;
}

.nb {
	text-align: left;
	font-size: 25px;
	padding-left: 700px;
}

.tag {
	border-radius: 20px 20px 20px 20px;
	background-color: #AAAAAA;
	color: white;
	font-size: 20px;
	padding: 5px 7px;
	border: 0;
	outline: 0;
	width: 100px;
	height: 80%;
	margin: 5px;
}

select {
	text-align: left;
	padding-left: 100px;
	font-size: 15px;
	padding: 5px;
	width: 250px;
	margin: 5px;
}
</style>
</head>
<body>
	<div>
		<h2>
			<img src="adopt_img.png" style="width: 3%; height: 3%;">&nbsp;키워드
			검색
		</h2>
		<br>
		<div class="nb">
			&nbsp;상태&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;품종
		</div>
		<form action="amain.dog" method="POST" name="keyword">
			<input type="hidden" name="pageNo" value="1"> <select
				name="state" onchange="this.form.submit()">
				<option value="">전체</option>
				<option value="notice">보호중</option>
				<option value="protected">공고중</option>
			</select>
			<script type="text/javascript">
				keyword.state.value = "${param.state}";
			</script>
			<select name="kind" onchange="this.form.submit()">
				<option value="">전체</option>
				<option value="000054">골든 리트리버</option>
				<option value="000056">그레이 하운드</option>
				<option value="000055">그레이트 덴</option>
				<option value="000118">그레이트 피레니즈</option>
				<option value="000115">기타</option>
				<option value="000037">꼬똥 드 뚤레아</option>
				<option value="000081">네오폴리탄 마스티프</option>
				<option value="000204">노르포크 테리어</option>
				<option value="000083">노리치 테리어</option>
				<option value="000082">뉴펀들랜드</option>
				<option value="000038">닥스훈트</option>
				<option value="000039">달마시안</option>
				<option value="000040">댄디 딘몬트 테리어</option>
				<option value="000043">도고 까니리오</option>
				<option value="000042">도고 아르젠티노</option>
				<option value="000153">도고 아르젠티노</option>
				<option value="000041">도베르만</option>
				<option value="000120">도사</option>
				<option value="000155">동경견</option>
				<option value="000069">라브라도 리트리버</option>
				<option value="000071">라사 압소</option>
				<option value="000142">라이카</option>
				<option value="000093">래빗 닥스훈드</option>
				<option value="000167">랫 테리어</option>
				<option value="000070">레이크랜드 테리어</option>
				<option value="000166">로디지안 리즈백</option>
				<option value="000094">로트바일러</option>
				<option value="000121">롯트와일러</option>
				<option value="000152">마리노이즈</option>
				<option value="000073">마스티프</option>
				<option value="000146">말라뮤트</option>
				<option value="000072">말티즈</option>
				<option value="000159">맨체스터테리어</option>
				<option value="000076">미니어쳐 닥스훈트</option>
				<option value="000075">미니어쳐 불 테리어</option>
				<option value="000079">미니어쳐 슈나우저</option>
				<option value="000078">미니어쳐 푸들</option>
				<option value="000077">미니어쳐 핀셔</option>
				<option value="000074">미디엄 푸들</option>
				<option value="000080">미텔 스피츠</option>
				<option value="000114">믹스견</option>
				<option value="000133">바센지</option>
				<option value="000012">바셋 하운드</option>
				<option value="000017">버니즈 마운틴 독</option>
				<option value="000015">베들링턴 테리어</option>
				<option value="000164">벨기에 그로넨달</option>
				<option value="000157">벨기에 쉽독</option>
				<option value="000148">벨기에 테뷰런</option>
				<option value="000016">벨지안 셰퍼드 독</option>
				<option value="000020">보더 콜리</option>
				<option value="000021">보르조이</option>
				<option value="000022">보스턴 테리어</option>
				<option value="000024">복서</option>
				<option value="000023">부비에 데 플랑드르</option>
				<option value="000026">불 테리어</option>
				<option value="000027">불독</option>
				<option value="000169">브뤼셀그리펀</option>
				<option value="000025">브리타니 스파니엘</option>
				<option value="000019">블랙 테리어</option>
				<option value="000013">비글</option>
				<option value="000018">비숑 프리제</option>
				<option value="000014">비어디드 콜리</option>
				<option value="000162">비즐라</option>
				<option value="000085">빠삐용</option>
				<option value="000096">사모예드</option>
				<option value="000095">살루키</option>
				<option value="000001">삽살개</option>
				<option value="000034">샤페이</option>
				<option value="000104">세인트 버나드</option>
				<option value="000031">센트럴 아시안 오브차카</option>
				<option value="000099">셔틀랜드 쉽독</option>
				<option value="000122">셰퍼드</option>
				<option value="000123">슈나우져</option>
				<option value="000097">스코티쉬 테리어</option>
				<option value="000132">스코티시 디어하운드</option>
				<option value="000105">스탠다드 푸들</option>
				<option value="000154">스테포드셔불테리어</option>
				<option value="000124">스피츠</option>
				<option value="000100">시바</option>
				<option value="000103">시베리안 허스키</option>
				<option value="000151">시베리안라이카</option>
				<option value="000139">시잉프랑세즈</option>
				<option value="000101">시츄</option>
				<option value="000102">시코쿠</option>
				<option value="000098">실리햄 테리어</option>
				<option value="000136">실키테리어</option>
				<option value="000202">아나톨리안 셰퍼드</option>
				<option value="000160">아메리칸 불독</option>
				<option value="000203">아메리칸 스태퍼드셔 테리어</option>
				<option value="000008">아메리칸 아키다</option>
				<option value="000131">아메리칸 에스키모</option>
				<option value="000009">아메리칸 코카 스파니엘</option>
				<option value="000119">아메리칸 핏불 테리어</option>
				<option value="000150">아메리칸불리</option>
				<option value="000057">아이리쉬 세터</option>
				<option value="000058">아이리쉬 울프 하운드</option>
				<option value="000059">아이리쉬소프트코튼휘튼테리어</option>
				<option value="000006">아키다</option>
				<option value="000004">아프간 하운드</option>
				<option value="000007">알라스칸 말라뮤트</option>
				<option value="000005">에어델 테리어</option>
				<option value="000143">오브차카</option>
				<option value="000011">오스트랄리안 셰퍼드 독</option>
				<option value="000010">오스트랄리안 캐틀 독</option>
				<option value="000137">올드 잉글리쉬 불독</option>
				<option value="000084">올드 잉글리쉬 쉽독</option>
				<option value="000163">와이마라너</option>
				<option value="000112">와이어 폭스 테리어</option>
				<option value="000113">요크셔 테리어</option>
				<option value="000149">울프독</option>
				<option value="000110">웨스트하이랜드화이트테리어</option>
				<option value="000205">웰시 코기 카디건</option>
				<option value="000108">웰시 코기 펨브로크</option>
				<option value="000109">웰시 테리어</option>
				<option value="000060">이탈리안 그레이 하운드</option>
				<option value="000046">잉글리쉬 세터</option>
				<option value="000047">잉글리쉬 스프링거 스파니엘</option>
				<option value="000044">잉글리쉬 코카 스파니엘</option>
				<option value="000045">잉글리쉬 포인터</option>
				<option value="000053">자이언트 슈나우져</option>
				<option value="000062">재패니즈 스피츠</option>
				<option value="000061">잭 러셀 테리어</option>
				<option value="000052">저먼 셰퍼드 독</option>
				<option value="000165">저먼 와이어헤어드 포인터</option>
				<option value="000051">저먼 포인터</option>
				<option value="000156">제주개</option>
				<option value="000129">제페니즈칭</option>
				<option value="000067">진도견</option>
				<option value="000035">차우차우</option>
				<option value="000033">차이니즈 크레스티드 독</option>
				<option value="000032">치와와</option>
				<option value="000158">카레리안 베어독</option>
				<option value="000144">카이훗</option>
				<option value="000030">캐벌리어 킹 찰스 스파니엘</option>
				<option value="000029">케니스펜더</option>
				<option value="000064">케리 블루 테리어</option>
				<option value="000028">케인 코르소</option>
				<option value="000002">코리아 트라이 하운드</option>
				<option value="000068">코리안 마스티프</option>
				<option value="000125">코카 스파니엘</option>
				<option value="000141">코카 푸</option>
				<option value="000145">코카시안오브차카</option>
				<option value="000036">콜리</option>
				<option value="000066">클라인스피츠</option>
				<option value="000065">키슈</option>
				<option value="000063">키스 훈드</option>
				<option value="000140">토이 맨체스터 테리어</option>
				<option value="000107">토이 푸들</option>
				<option value="000106">티베탄 마스티프</option>
				<option value="000086">파슨 러셀 테리어</option>
				<option value="000088">팔렌</option>
				<option value="000090">퍼그</option>
				<option value="000087">페키니즈</option>
				<option value="000138">페터데일테리어</option>
				<option value="000089">포메라니안</option>
				<option value="000126">포인터</option>
				<option value="000127">폭스테리어</option>
				<option value="000128">푸들</option>
				<option value="000091">풀리</option>
				<option value="000003">풍산견</option>
				<option value="000161">프레사까나리오</option>
				<option value="000050">프렌치 불독</option>
				<option value="000168">프렌치 브리타니</option>
				<option value="000049">플랫 코티드 리트리버</option>
				<option value="000147">플롯하운드</option>
				<option value="000092">피레니안 마운틴 독</option>
				<option value="000048">필라 브라질레오</option>
				<option value="000135">핏불테리어</option>
				<option value="000130">화이트리트리버</option>
				<option value="000134">화이트테리어</option>
				<option value="000111">휘펫</option>
			</select>
			<script type="text/javascript">
				keyword.kind.value = "${param.kind}";
			</script>
			<hr>
			<div>
				<div>
					<c:forEach var="item" items="${go}">
						<div
							style="width: 350px; height: 350px; margin: 20px; display: inline-block;">
							<a href="adetail.dog?noticeNo=${item.noticeNo}"
								class="imghover"><img src="${item.popfile}"
								style="width: 350px; height: 350px;"></a>
							<div style="height: 5px"></div>
							<span class="tag">${item.processState}</span> &nbsp; <span
								class="tag">${item.kindCd}</span> <br>
						</div>
					</c:forEach>
				</div>
			</div>
			<br>
			<c:if test="${empty message}">
				<div class="st-pagination">
					<ul class="pagination">
						<li><c:if test="${pageNo > 1}">
								<a href="javascript:listpage('${pageNo - 1}')"
									aria-label="previous"><span aria-hidden="true"><i
										class="fa fa-angle-left"></i></span></a>
							</c:if> <c:if test="${pageNo <= 1}">
								<span aria-hidden="true"><i class="fa fa-angle-left"></i></span>
							</c:if></li>
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == pageNo}">
								<li><a>${a}</a></li>
							</c:if>
							<c:if test="${a != pageNo}">
								<li><a href="javascript:listpage('${a}')">${a}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageNo < maxpage}">
							<li><a href="javascript:listpage('${pageNo + 1}')"
								aria-label="Next"><span aria-hidden="true"><i
										class="fa fa-angle-right"></i></span></a></li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</form>
		<div>
			<h3>${message}</h3>
		</div>
	</div>
	<script type="text/javascript">
	$(".pagination > li").removeClass;
	if(${param.pageNo % 10} == 0) { // 0, 10
		if(${param.pageNo / 10} == 1) // pageNo(10) : 0(이전) 
			$(".pagination > li").eq(10).addClass("active"); // 10
		else if(${param.pageNo / 10} == 2)
			$(".pagination > li").eq(20).addClass("active");
		else // pageNo(0) : 0
			$(".pagination > li").eq(1).addClass("active"); // 1
	}
	else 
		$(".pagination > li").eq(${param.pageNo % 10}).addClass("active");

	function listpage(page) {
		document.keyword.pageNo.value = page;
		document.keyword.submit();
	}
</script>
</body>
</html>