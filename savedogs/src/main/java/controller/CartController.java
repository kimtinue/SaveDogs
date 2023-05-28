package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import exception.CartEmptyException;
import logic.Buylist;
import logic.Cart;
import logic.DogService;
import logic.Item;
import logic.ItemSet;
import logic.Member;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private DogService service;
	
	@RequestMapping("cartAdd")
	public ModelAndView add(Integer item_no, Integer item_each, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Item item = service.itemselect(item_no);
		Cart cart = (Cart)session.getAttribute("CART");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		cart.push(new ItemSet(item, item_each));
		mav.addObject("message",item.getItem_name() + ":" + item_each + "개 장바구니 추가");
		mav.addObject("cart",cart);
		return mav;
	}
	@GetMapping("cartDelete")
	public ModelAndView delete(int index, HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/cart");
		Cart cart = (Cart)session.getAttribute("CART");
		ItemSet itemSet = null;
		 try {
			itemSet = cart.getItemSetList().remove(index);
			mav.addObject("message",itemSet.getItem().getItem_name()+"상품을 삭제하였습니다.");
		} catch (Exception e) {
			mav.addObject("message","장바구니에서 상품을 삭제하지 못했습니다.");
		}
		mav.addObject("cart",cart);
		return mav;
	}
	@RequestMapping("cartView")
	public ModelAndView cartView(HttpSession session){
		ModelAndView mav = new ModelAndView("cart/cart");
		Cart cart = (Cart)session.getAttribute("CART");
		if(cart == null || cart.getItemSetList().size()==0) {
			throw new CartEmptyException("장바구니에 상품이 없습니다.","../item/list.dog");
		}
		mav.addObject("cart",cart);
		return mav;
	}
	
	@RequestMapping("checkout")	// AOP 클래스에서 첫번째 매개변수를 사용하므로 첫번때 매개변수는 httpsession 이어야함
	public String checkout(HttpSession session,Model model) {
		Buylist buylist = new Buylist();
		model.addAttribute(buylist);
		return null;
	}
	
	@GetMapping("end")	
	public ModelAndView checkend(HttpSession session) { //CartAspect 구동
		ModelAndView mav = new ModelAndView();
		Cart cart = (Cart)session.getAttribute("CART");
		Member loginmem = (Member)session.getAttribute("loginmem");
		Buylist buylist = service.checkend(loginmem,cart);
		long total = cart.getTotal();
		session.removeAttribute("CART");
		mav.addObject("buylist",buylist);
		mav.addObject("total",total);
		mav.setViewName("redirect:../member/shopMypage.dog?type=5&id="+loginmem.getMember_id());
		return mav;
	}

	@PostMapping("checkout")
	public ModelAndView newend(@Valid Buylist buylist, BindingResult bresult, HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/checkout");
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		Cart cart = (Cart)session.getAttribute("CART");
		Member loginmem = (Member)session.getAttribute("loginmem");
		buylist.setMember(loginmem);
		buylist.setMember_id(loginmem.getMember_id());
		try {
			Buylist buylist2 =service.checkend2(buylist,cart);
			mav.setViewName("redirect:../member/login.dog");
		} catch(Exception e) {
			e.printStackTrace();
		}
		long total = cart.getTotal();
		session.removeAttribute("CART");
		mav.addObject("buylist",buylist);
		mav.addObject("total",total);
		mav.setViewName("redirect:../item/list.dog");
		return mav;
	}
//		Member loginmem = (Member)session.getAttribute("loginmem");
//		Cart cart = (Cart)session.getAttribute("CART");
//		Buylist buylist1 = service.checkend2(loginmem,buylist,cart);
//		return mav;
//	}
}
