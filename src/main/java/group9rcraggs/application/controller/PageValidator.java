package group9rcraggs.application.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import group9rcraggs.application.domain.Page;

	public class PageValidator implements Validator {

		public boolean supports(Class<?> clazz) {
			return Page.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			Page p = (Page) target;

			//Rejects any page name or URL that's empty
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Field cannot be empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "", "Field cannot be empty.");

			//Rejects any page URL that doesn't begin with http:// or https://
			if(!p.getUrl().matches("(http://|https://).*")) {
				errors.rejectValue("url", "", "URL must begin with http:// or https://");
			}

		}

	
}