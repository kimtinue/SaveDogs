package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import exception.LoginException;
import logic.Member;

@Component
@Aspect
@Order(1)
public class AdoptAspect {
	@Around("execution(* controller.Adopt*.chkm*(..)) && args(..,session)")
	public Object AdoptMemberLoginCheck(ProceedingJoinPoint joinPoint, HttpSession session) throws Throwable {
		Member loginmem = (Member) session.getAttribute("loginmem");
		Member loginsmem = (Member) session.getAttribute("loginsmem");
		Member loginadmin = (Member) session.getAttribute("loginadmin");
		if (loginmem == null) {
			if (loginsmem != null || loginadmin != null) {
				throw new LoginException("일반 회원만 이용 가능합니다", "../adopt/amain.dog");
			}
			else
				throw new LoginException("로그인 후 이용 가능합니다", "../member/login.dog");
		}
		Object ret = joinPoint.proceed();
		return ret;
	}
}
