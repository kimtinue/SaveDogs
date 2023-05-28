package logic;

import java.util.Date;

public class Adopt {

	// 유기동물 조회 오퍼레이션 요청, 응답 항목 전부

//	private String bgnde; // 유기 날짜(검색 시작일)
//	private String endde; // 유기 날짜(검색 종료일)
//	private String upKind; // 축종 코드
//	private String kind; // 품종 코드
//	private String upr_cd; // 시도 코드
//	private String org_cd; // 시군구 코드
//	private String care_reg_no; // 보호소 번호
//	private String state; // 상태
//	private String neuter_yn; // 중성화 여부
//	private int pageNo; // 페이지 번호
//	private int numOfRows; // 페이지당 보여줄 개수
//
//	private String resultCode; // 결과 코드
//	private String resultMsg; // 결과 메세지
	private String desertionNo; // 유기 번호
	private String filename; // Thumbnail image
	private Date happenDt; // 접수일
	private String happenPlace; // 발견 장소
	private String kindCd; // 품종
	private String colorCd; // 색상
	private String age; // 나이
	private String weight; // 체중
	private String noticeNo; // 공고 번호
	private Date noticeSdt; // 공고 시작일
	private Date noticeEdt; // 공고 종료일
	private String popfile; // image
	private String processState; // 상태
	private String sexCd; // 성별
	private char neuterYn; // 중성화 여부
	private String specialMark; // 특징
	private String careNm; // 보호소 이름
	private String careTel; // 보호소 전화번호
	private String careAddr; // 보호 장소
	private String orgNm; // 관할 기관
	private String chargeNm; // 담당자
	private String officetel; // 담당자 연락처
	private String noticeComment; // 특이 사항
//	private int totalCount; // 전체 결과 수

	// getter
//	public String getBgnde() {
//		return bgnde;
//	}
//
//	public String getEndde() {
//		return endde;
//	}
//
//	public String getUpKind() {
//		return upKind;
//	}
//
//	public String getKind() {
//		return kind;
//	}
//
//	public String getUpr_cd() {
//		return upr_cd;
//	}
//
//	public String getOrg_cd() {
//		return org_cd;
//	}
//
//	public String getCare_reg_no() {
//		return care_reg_no;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public String getNeuter_yn() {
//		return neuter_yn;
//	}
//
//	public int getPageNo() {
//		return pageNo;
//	}
//
//	public int getNumOfRows() {
//		return numOfRows;
//	}
//
//	public String getResultCode() {
//		return resultCode;
//	}
//
//	public String getResultMsg() {
//		return resultMsg;
//	}

	public String getDesertionNo() {
		return desertionNo;
	}

	public String getFilename() {
		return filename;
	}

	public Date getHappenDt() {
		return happenDt;
	}

	public String getHappenPlace() {
		return happenPlace;
	}

	public String getKindCd() {
		return kindCd;
	}

	public String getColorCd() {
		return colorCd;
	}

	public String getAge() {
		return age;
	}

	public String getWeight() {
		return weight;
	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public Date getNoticeSdt() {
		return noticeSdt;
	}

	public Date getNoticeEdt() {
		return noticeEdt;
	}

	public String getPopfile() {
		return popfile;
	}

	public String getProcessState() {
		return processState;
	}

	public String getSexCd() {
		return sexCd;
	}

	public char getNeuterYn() {
		return neuterYn;
	}

	public String getSpecialMark() {
		return specialMark;
	}

	public String getCareNm() {
		return careNm;
	}

	public String getCareTel() {
		return careTel;
	}

	public String getCareAddr() {
		return careAddr;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public String getChargeNm() {
		return chargeNm;
	}

	public String getOfficetel() {
		return officetel;
	}

	public String getNoticeComment() {
		return noticeComment;
	}

//	public int getTotalCount() {
//		return totalCount;
//	}
//
//	// setter
//	public void setBgnde(String bgnde) {
//		this.bgnde = bgnde;
//	}
//
//	public void setEndde(String endde) {
//		this.endde = endde;
//	}
//
//	public void setUpKind(String upKind) {
//		this.upKind = upKind;
//	}
//
//	public void setKind(String kind) {
//		this.kind = kind;
//	}
//
//	public void setUpr_cd(String upr_cd) {
//		this.upr_cd = upr_cd;
//	}
//
//	public void setOrg_cd(String org_cd) {
//		this.org_cd = org_cd;
//	}
//
//	public void setCare_reg_no(String care_reg_no) {
//		this.care_reg_no = care_reg_no;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public void setNeuter_yn(String neuter_yn) {
//		this.neuter_yn = neuter_yn;
//	}
//
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//
//	public void setNumOfRows(int numOfRows) {
//		this.numOfRows = numOfRows;
//	}
//
//	public void setResultCode(String resultCode) {
//		this.resultCode = resultCode;
//	}
//
//	public void setResultMsg(String resultMsg) {
//		this.resultMsg = resultMsg;
//	}

	public void setDesertionNo(String desertionNo) {
		this.desertionNo = desertionNo;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setHappenDt(Date happenDt) {
		this.happenDt = happenDt;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}

	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}

	public void setColorCd(String colorCd) {
		this.colorCd = colorCd;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public void setNoticeSdt(Date noticeSdt) {
		this.noticeSdt = noticeSdt;
	}

	public void setNoticeEdt(Date noticeEdt) {
		this.noticeEdt = noticeEdt;
	}

	public void setPopfile(String popfile) {
		this.popfile = popfile;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public void setNeuterYn(char neuterYn) {
		this.neuterYn = neuterYn;
	}

	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	public void setCareNm(String careNm) {
		this.careNm = careNm;
	}

	public void setCareTel(String careTel) {
		this.careTel = careTel;
	}

	public void setCareAddr(String careAddr) {
		this.careAddr = careAddr;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public void setChargeNm(String chargeNm) {
		this.chargeNm = chargeNm;
	}

	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}

	public void setNoticeComment(String noticeComment) {
		this.noticeComment = noticeComment;
	}

//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}

	@Override
	public String toString() {
		return "Adopt [desertionNo=" + desertionNo + ", filename=" + filename + ", happenDt=" + happenDt
				+ ", happenPlace=" + happenPlace + ", kindCd=" + kindCd + ", colorCd=" + colorCd + ", age=" + age
				+ ", weight=" + weight + ", noticeNo=" + noticeNo + ", noticeSdt=" + noticeSdt + ", noticeEdt="
				+ noticeEdt + ", popfile=" + popfile + ", processState=" + processState + ", sexCd=" + sexCd
				+ ", neuterYn=" + neuterYn + ", specialMark=" + specialMark + ", careNm=" + careNm + ", careTel="
				+ careTel + ", careAddr=" + careAddr + ", orgNm=" + orgNm + ", chargeNm=" + chargeNm + ", officetel="
				+ officetel + ", noticeComment=" + noticeComment + "]";
	}

}
