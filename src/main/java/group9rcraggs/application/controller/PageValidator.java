package group9rcraggs.application.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import group9rcraggs.application.domain.Page;

	public class PageValidator extends Validation implements Validator {

		public boolean supports(Class<?> clazz) {
			return Page.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			Page p = (Page) target;

			//Rejects any page name or URL that's empty
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Field cannot be empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "", "Field cannot be empty.");

	
			if(httpStatus(p.getOwnerUrl() + p.getUrl()) != 200) {
				errors.rejectValue("url", "", "Invalid URL");
			}

		}

	
}
