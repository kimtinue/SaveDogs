package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import exception.CartEmptyException;
import exception.LoginException;
import logic.Cart;
import logic.Member;

@Component	
@Aspect
@Order(2)	
public class CartAspect {
	@Around 
	("execution(* controller.Cart*.check*(..))")
	public Object cartCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpSession session = (HttpSession)joinPoint.getArgs()[0];
		Member loginmem = (Member)session.getAttribute("loginmem");
		Cart cart = (Cart)session.getAttribute("CART");
		if(loginmem == null) {
			throw new LoginException("주문은 회원만 가능합니다. 로그인 하세요","../member/login.dog");
		}
		if(cart == null || cart.getItemSetList().size()==0) {
			throw new CartEmptyException("주문할 상품이 장바구니에 없습니다.", "../item/list.dog");
		}
		return joinPoint.proceed();
	}
	@Around	//보호소관리자 접근권한 확인 aop
	("execution(* controller.*.*chkauth(..)) && args(.., session)")
	public Object ShelterAuthCheck(ProceedingJoinPoint joinPoint, HttpSession session) throws Throwable {
		Member loginsmem = (Member)session.getAttribute("loginsmem");
		if(loginsmem != null && loginsmem.getMember_auth() == 1) {
				throw new LoginException("가입 미승인 상태입니다. 자세한 내용은 Q&A 게시판을 이용해주세요.","../main.dog");
		}
		Object ret = joinPoint.proceed();
		return ret;
	}
}
