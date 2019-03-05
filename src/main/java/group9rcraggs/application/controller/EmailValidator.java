package group9rcraggs.application.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import group9rcraggs.application.domain.Email;

public class EmailValidator extends Validation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Email.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Email e = (Email) target;

		//Rejects any page name or URL that's empty
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "Field cannot be empty.");

		
		
//		if(httpStatus(e.getAddress())) {
//			errors.rejectValue("address", "", "Invalid Email");
//		}

	}


}
