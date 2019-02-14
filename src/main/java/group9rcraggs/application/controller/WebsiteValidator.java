package group9rcraggs.application.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import group9rcraggs.application.domain.Website;


public class WebsiteValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Website.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Website w = (Website) target;

		if(!w.getUrl().matches("(http://|https://).*")) {
			errors.rejectValue("url", "", "URL must begin with http:// or https://");
		}

	}

}