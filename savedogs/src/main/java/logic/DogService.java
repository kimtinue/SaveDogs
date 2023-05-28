package logic;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import dao.AdminDao;
import dao.BoardDao;
import dao.AdoptDao;
import dao.BuyitemDao;
import dao.BuylistDao;
import dao.FundingDao;
import dao.FundinglistDao;
import dao.ItemDao;
import dao.MemberDao;
import dao.ShelterDao;
import dao.VworkDao;
import dao.VworklistDao;

@Service
public class DogService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private FundingDao fundingDao;
	@Autowired
	private VworkDao vworkDao;
	@Autowired
	private ShelterDao shelterDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private BuylistDao buylistDao;
	@Autowired
	private BuyitemDao buyitemDao;
	@Autowired
	private VworklistDao vworklistDao;
	@Autowired
	private FundinglistDao fundlistDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private AdoptDao adoptDao;

//-------------------회원관련 시작-------------------------------------------------
	public void memberInsert(Member mem) {
		memberDao.memberInsert(mem);
	}

	public void smemberInsert(Member mem, HttpServletRequest request) {
		if(mem.getF1() != null && !mem.getF1().isEmpty()) {
			uploadFileCreate(mem.getF1(),request,"member/img/");
			mem.setFile1(mem.getF1().getOriginalFilename());
		}
		if(mem.getF2() != null && !mem.getF2().isEmpty()) {
			uploadFileCreate(mem.getF2(),request,"member/img/");
			mem.setFile2(mem.getF2().getOriginalFilename());
		}
		memberDao.smemberInsert(mem);
	}
	
	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request, String path) {
		String orgFile = picture.getOriginalFilename();

/*		String orgFile ="";
		try {
			orgFile = new String(picture.getOriginalFilename().getBytes("8859_1"), StandardCharsets.UTF_8);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
*/	
		String uploadPath = request.getServletContext().getRealPath("/") + path;
		System.out.println(uploadPath);
		File fpath = new File(uploadPath);
		if(!fpath.exists()) fpath.mkdirs();
		try {
			picture.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member getMember(String member_id) {
		return memberDao.getMember(member_id);
	}

	public void insertSlist(Map<String, Map<String, String>> data) {
		for(Map.Entry<String, Map<String,String>> me : data.entrySet()) {
			Shelter shelter = new Shelter();
			shelter.setShelter_no(me.getKey().toString());
			shelter.setShelter_name(me.getValue().toString().split("=")[0].substring(1));
			shelter.setShelter_address(me.getValue().toString().split("=")[1].substring(0, me.getValue().toString().split("=")[1].indexOf("}")));
			shelter.setShelter_tel(" ");
			//System.out.println("보호소 코드 : " + me.getKey() + " 보호소명 : " + me.getValue().toString().split("=")[0].substring(1) + " 지역구 : " + me.getValue().toString().split("=")[1].substring(0, me.getValue().toString().split("=")[1].indexOf("}")) + "<br>");
			try {
			    adminDao.insert(shelter);
			} catch (Exception e) {
//				adminDao.update(shelter);
			}
		}
	}

	public void deleteAllList() {
		adminDao.deleteAllList();
	}

	public void insertInit() {
		adminDao.insertInit();
	}


	public List<Shelter> getShelterAddress() {
		return adminDao.getAddressList();
	}

	public List<Shelter> getShelterName(String goo) {
		return adminDao.getShelterName(goo);
	}
	
	public void memUpdate(Member mem, HttpServletRequest request) {
		if(mem.getF1() != null && !mem.getF1().isEmpty()) {
			uploadFileCreate(mem.getF1(),request,"member/img/");
			mem.setFile1(mem.getF1().getOriginalFilename());
		}
		if(mem.getF2() != null && !mem.getF2().isEmpty()) {
			uploadFileCreate(mem.getF2(),request,"member/img/");
			mem.setFile2(mem.getF2().getOriginalFilename());
		}
		memberDao.memUpdate(mem);
	}
	
	public void memPassUpdate(String newpass, String id) {
		memberDao.memPassUpdate(newpass,id);
	}
	
	public List<Vwork> getMyvworkList(String id) {
		return vworklistDao.getMyvworkList(id);
	}
	
	public List<Buylist> getbuylist(String id) {
		return buylistDao.list(id);
	}
	
	public List<BuyItem> getbuyitemlist(int buy_no) {
		return buyitemDao.list(buy_no);
	}
	
	public List<Fundinglist> getMyfundlist(String id) {
		return fundlistDao.list(id);
	}
	
	public List<Fundinglist> getMyendfundlist(String id) {
		return fundlistDao.endlist(id);
	}
	
	public List<Vwork> getwritelist(String id) {
		return vworkDao.getwritelist(id);
	}
	
	public List<Vworklist> getOnevworklist(String vwork_no) {
		return vworklistDao.getOnevworklist(vwork_no);
	}
	
	public List<Funding> getwritelist2(String id) {
		return fundingDao.getwritelist2(id);
	}
	
	public List<Fundinglist> getOnefundlist(String fund_no) {
		return fundlistDao.getOnefundlist(fund_no);
	}
	
	public List<Member> memberList(String[] idchks) {
		return memberDao.memberList(idchks);
	}
	
	//관리자
	public List<Member> getMemberList() {
		return adminDao.getMemberList();
	}
	
	public List<Member> getSmemberList() {
		return adminDao.getSmemberList();
	}
	
	public String getMemberPass(String member_id) {
		return memberDao.getMemberPass(member_id);
	}
	
	public List<Vworklist> getYearlist(String id) {
		return vworklistDao.getYearlist(id);
	}
	
	public List<Fundinglist> getfundYearlist(String id) {
		return fundlistDao.getfundYearlist(id);
	}
	
	public Map<String, Object> vworkgraph(String year, String member_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for(Map<String, Object> m : vworklistDao.vworkgraph(year, member_id)) {
			map.put((String)m.get("m"), m.get("cnt"));
		}
		return map;
	}
	
	public Map<String, Object> fundgraph(String year, String member_id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for(Map<String, Object> m : fundlistDao.fundgraph(year, member_id)) {
			map.put((String)m.get("m"), m.get("cnt"));
		}
		return map;
	}
	
	public Map<String, Object> shopgraph(String name) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for(Map<String, Object> m : itemDao.shopgraph(name)) {
			map.put((String)m.get("m"), m.get("cnt"));
		}
		return map;
	}
	
	public Map<String, Object> shopallgraph(String year) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for(Map<String, Object> m : itemDao.shopallgraph(year)) {
			map.put((String)m.get("m"), m.get("cnt"));
		}
		return map;
	}

	public List<Item> getShopall() {
		return itemDao.getShopall();
	}
	
	public void updateAuth(String member_id, String member_auth) {
		memberDao.updateAuth(member_id, member_auth);
	}
	
	public List<AdoptSign> getMyadoptlist(String id) {
		return adoptDao.getMyadoptlist(id);
	}
	
	public List<AdoptSign> getShelteradoptlist(String id) {
		return adoptDao.getShelteradoptlist(id);
	}
	
	public void updateEtc(String dog_no, String state) {
		adoptDao.updateEtc(dog_no, state);
	}
	
	public Idpw getFindID(String tel, String email) {
		return memberDao.getFindId(tel, email);
	}
	
	public Idpw getFindPW(String id, String tel, String email) {
		return memberDao.getFindPW(id,tel, email);
	}
	
	public void deleteMember(String member_id) {
		memberDao.deleteMember(member_id);
	}
	
	public void deleteVworklist(String member_id) {
		vworklistDao.deleteVworklist(member_id);
	}
	
	public void deleteFundlist(String member_id) {
		fundlistDao.deleteFundlist(member_id);
	}

//-------------------회원관련 끝-------------------------------------------------
	
//-------------------봉사관련 시작------------------------------------------------- 
	public Shelter getShelter(String shelter_no) {
		return shelterDao.getShelter(shelter_no);
	}
	
	public List<Shelter> getShelters(String goo) {
		return shelterDao.getShelters(goo);
	}

	public List<Shelter> getShelterlist() {
		return  shelterDao.getShelterlist();
	}
	public void vWrite(Vwork vwork, HttpServletRequest request) {
		int max = vworkDao.maxno();
		vwork.setVwork_no(++max);
		
		vworkDao.insertVwork(vwork);
	}
	
	public HashSet<String> allvwork() {
		return vworkDao.allvwork();
	}
	
	public HashSet<String> sheltervwork(String shelter_no) {
		return vworkDao.sheltervwork(shelter_no);
	}

	public int getNowmem(int Vno) {
		return vworkDao.getNowmem(Vno);
	}
	
	public int getListcnt(String date) {
		return vworkDao.getListcnt(date);
	}
	
	public Vwork getVwork(String vwork_no) {		
		return vworkDao.getVwork(vwork_no);
	}

	public List<Vwork> getVlist(String date) {
		return vworkDao.getVlist(date);
	}

	public void vJoin(Vworklist vworklist, HttpServletRequest request) {
		vworkDao.vJoin(vworklist);
	}
	
	public void vUpdate(Vwork vwork, HttpServletRequest request) {
		vworkDao.updateVwork(vwork);
	}
	
	public void vdelete(String vwork_no) {
		vworkDao.deleteVwork(vwork_no);
	}


	

//-------------------봉사관련 끝-------------------------------------------------

//-------------------펀딩관련 시작-------------------------------------------------
		   
	

		   public void fundCreate(Funding funding, HttpServletRequest request) { 
		      if(funding.getPicture() != null && !funding.getPicture().isEmpty()) {
		         uploadFileCreate(funding.getPicture(),request,"funding/img/");
		         funding.setFund_pic(funding.getPicture().getOriginalFilename());
		      }
		      int fund_no =fundingDao.maxfundno() ;
		      funding.setFund_no(++fund_no);
		      fundingDao.fundinsert(funding);
		   }

		public void fundUpdate(Funding funding, HttpServletRequest request) {
		  if(funding.getPicture() != null && !funding.getPicture().isEmpty()) {
		   uploadFileCreate(funding.getPicture(),request,"funding/img/");
		   funding.setFund_pic(funding.getPicture().getOriginalFilename());	
		}
		  fundingDao.fundupdate(funding); 
		}
		public void fundDelete(String fund_no) {
			fundingDao.fundDelete(fund_no);		
		}
		
		public Funding getfundingdetail(String fund_no) {
	           return fundingDao.selectOne(fund_no);
			}
		public void fundingapply(Fundinglist fundinglist, HttpServletRequest request) {
			fundlistDao.applyinsert(fundinglist);
		}

		public List<Funding> boardList(Integer pageNum, int limit) {
			return fundingDao.list(pageNum, limit);
		}

		public int boardcount() {
			return fundingDao.listcount();
		}
	
		//펀딩 댓글
		public List<Fundreply> freplyList(String fund_no) {
			return fundingDao.freplyList(fund_no);
		}
		
		public int getFRmax() {
			return fundingDao.getFRmax();
		}
		
		public void insertReply(Fundreply reply) {
			fundingDao.insertReply(reply);
		}

		public void deleteFreply(String rno) {
			fundingDao.deleteFreply(rno);			
		}
//-------------------펀딩관련 끝-------------------------------------------------
//-------------------쇼핑관련 시작-------------------------------------------------
		public List<Item> getItemList() {
			return itemDao.list();
		}
		

		public void itemCreate(@Valid Item item, HttpServletRequest request) {
			if(item.getPicture() != null && !item.getPicture().isEmpty()) {
				uploadFileCreate(item.getPicture(),request,"item/img/");
				item.setItem_picture(item.getPicture().getOriginalFilename());
			}
			System.out.println(item);
			itemDao.insert(item);
		}


		public Item itemselect(Integer item_no) {
			return itemDao.selectOne(item_no);
		}

		public Buylist checkend(Member loginmem, Cart cart) {
			Buylist buylist = new Buylist();
			int buy_no = buylistDao.getMaxSaleid();
			buylist.setBuy_no(++buy_no);
			buylist.setMember_id(loginmem.getMember_id());
			buylist.setBuy_address(loginmem.getMember_address());
			buylist.setBuy_daddress(loginmem.getMember_daddress());
			buylist.setBuy_postcode(loginmem.getMember_postcode());
			buylist.setBuy_tel(loginmem.getMember_tel());
			buylist.setMember(loginmem);
			buylistDao.insert(buylist);
			List<ItemSet> itemList = cart.getItemSetList(); //cart 상품 정보
			int i = 0;
			for(ItemSet is : itemList) {
				int seq = ++i;
				BuyItem saleItem = new BuyItem(buylist.getBuy_no(),seq,is);
				buylist.getItemList().add(saleItem);
				buyitemDao.insert(saleItem);
			}
			return buylist;
		}

		public Buylist checkend2(@Valid Buylist buylist, Cart cart) {
			int buy_no = buylistDao.getMaxSaleid();
			buylist.setBuy_no(++buy_no);
			buylistDao.insert(buylist);
			List<ItemSet> itemList = cart.getItemSetList(); //cart 상품 정보
			int i = 0;
			for(ItemSet is : itemList) {
				int seq = ++i;
				BuyItem saleItem = new BuyItem(buylist.getBuy_no(),seq,is);
				buylist.getItemList().add(saleItem);
				buyitemDao.insert(saleItem);
			}
			return buylist;
		}

		public void updateState(String item_no, String item_state) {
			itemDao.updateState(item_no, item_state);
		}
		
		public void ItemUpdate(@Valid Item item) {
			itemDao.updateitem(item);
		}
		
		public void ItemUpdatepicture(@Valid Item item,HttpServletRequest request) {
			if(item.getPicture() != null && !item.getPicture().isEmpty()) {
				uploadFileCreate(item.getPicture(),request,"item/img/");
				item.setItem_picture(item.getPicture().getOriginalFilename());
			}
			System.out.println(item);
			itemDao.updateitem(item);
		}
		public void itemdelete(int item_no) {
			itemDao.deleteitem(item_no);
		}


//-------------------쇼핑관련 끝--------------------------------------------------

//-------------------커뮤니티 관련 시작-------------------------------------------------

		public void boardWrite(Board board, HttpServletRequest request) {
			int max = boardDao.maxno();
			board.setBoard_no(++max);
			board.setGrp(max);
			board.setGrplevel(0);
			board.setGrpstep(0);
			
			if(board.getType().equals("0")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/review/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			} else if(board.getType().equals("1")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/notice/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			} else if(board.getType().equals("2")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/qna/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			}		
			boardDao.insertBoard(board);
		}
		
		public Board boardDetail(String board_no) {
			boardDao.cntup(board_no);
			return boardDao.getBoard(board_no);
		}
		
		public int noticecnt(String type) {
			return boardDao.getTypecnt(type);
		}
		
		public List<Board> boardlist(Integer pageNum, int limit, String type) {
			return boardDao.boardlist(pageNum, limit, type);
		}
		
		public void boardUpdate(Board board, HttpServletRequest request) {
			if(board.getType().equals("0")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/review/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			} else if(board.getType().equals("1")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/notice/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			} else if(board.getType().equals("2")) {
				if(board.getFile1() != null && !board.getFile1().isEmpty()) {
					uploadFileCreate(board.getFile1(), request, "board/qna/");
					board.setFileurl(board.getFile1().getOriginalFilename());
				}
			}		
			boardDao.updateBoard(board);
		}
		
		public void boardDelete(String board_no) {
			boardDao.deleteBoard(board_no);
		}

		public String getBoardType(String board_no) {
			return boardDao.getBoardType(board_no);
		}
		
		public List<Board> qnalist(Integer pageNum, int limit, String searchtype, String searchcontent, String type) {
			return boardDao.qnalist(pageNum, limit,searchtype,searchcontent,type);
		}
		
		public List<Board> postqnalist(Integer pageNum, int limit, String searchtype, String searchcontent, String type) {
			
			return boardDao.postqnalist(pageNum, limit,searchtype,searchcontent,type);
		}
		
		public int qnacnt(String searchtype, String searchcontent, String type) {
			return boardDao.qnacnt(searchtype,searchcontent,type);
		}
		
		public int reviewcnt(String searchtype, String searchcontent, String type) {
			return boardDao.reviewcnt(searchtype,searchcontent,type);
		}

		public List<Board> reviewlist(Integer pageNum, int limit, String searchtype, String searchcontent, String type) {
			return boardDao.reviewlist(pageNum, limit,searchtype,searchcontent,type);
		}
		
		public Board getBoard(String board_no) {
			return boardDao.getBoard(board_no);
		}
		
		public void boardReply(Board board) {
			boardDao.updateGrpStep(board); //가존 답글의 grpstep 증가
			int max = boardDao.maxno();
			//답글 정보 수정
			board.setBoard_no(++max);
			board.setGrplevel(board.getGrplevel()+1);
			board.setGrpstep(board.getGrpstep()+1);
			boardDao.insertBoard(board);
			
		}
//-------------------댓글------------------------------------------------	
		public List<Reply> replyList(String board_no) {
			return boardDao.getReplyList(board_no);
		}

		public void insertReply(Reply reply) {
			boardDao.insertReply(reply);
		}

		public int getRmax() {
			return boardDao.getRmax();
		}		
		
		public void deleteReply(String rno) {
			boardDao.deleteReply(rno);
		}
		
//-------------------입양 관련 시작------------------------------------------------
		public void adoptInsert(AdoptSign a, HttpServletRequest request) {
			if(a.getF() != null && !a.getF().isEmpty()) {
				uploadFileCreate(a.getF(),request,"adopt/img/");
				a.setAdopt_file(a.getF().getOriginalFilename());
			}
			adoptDao.adoptInsert(a);
		}
		
		public List<Shelter> getHaplist() {
			return shelterDao.getHaplist();
		}
		
		public List<AdoptSign> getAdoptlist() {
			return adoptDao.getAdoptlist();
		}
//-------------------입양 관련 끝------------------------------------------------

//-------------------메인관련 시작-------------------------------------------------
		public List<Board> mainnotice() {
			return boardDao.mainnotice();
		}
		public List<Item> bestItem() {
			return itemDao.bestItem();
		}

		public List<Funding> duefunding() {
			return fundingDao.duefunding();
		}

			

		

		

		

		

		

		


//-------------------메인관련 끝-------------------------------------------------

}