package util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import logic.Member;

@Component
public class ShelterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member mem = (Member)target;
		String group = "error.required";
		if(mem.getMember_id() == null || mem.getMember_id().length()==0) {
			errors.rejectValue("member_id", group);	//파라미터별 오류 등록
		}
		/*if(mem.getMember_pass() == null || mem.getMember_pass().length()==0) {
			errors.rejectValue("member_pass", group);
		}*/
		if(mem.getMember_tel() == null || mem.getMember_tel().length()==0) {
			errors.rejectValue("member_tel", group);
		}
		if(mem.getMember_email() == null || mem.getMember_email().length()==0) {
			errors.rejectValue("member_email", group);
		}
		if(mem.getShelter_no() == null || mem.getShelter_no().length()==0) {
			errors.rejectValue("shelter_no", group);
		}
		if(errors.hasErrors()) {	//오류 발생
			errors.reject("error.input.member");	//reject : 글로벌오류
		}
	}

}
