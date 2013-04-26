package pl.agh.edu.carecenter.server.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.agh.edu.carecenter.server.domain.ActivityCategory;

public class ActivityCategoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return cls.equals(ActivityCategory.class);
	}

	@Override
	public void validate(Object activityCategory, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "categoryName","", "Specify category name");
		
	}

}
