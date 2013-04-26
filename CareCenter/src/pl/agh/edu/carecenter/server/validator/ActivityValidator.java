package pl.agh.edu.carecenter.server.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.agh.edu.carecenter.server.domain.Activity;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return (cls.equals(Activity.class));
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "activityName", "", "Specify activity name");
	}

}
