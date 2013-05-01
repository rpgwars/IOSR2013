package pl.agh.edu.carecenter.server.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.agh.edu.carecenter.server.domain.CarePlan;

public class CarePlanValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return cls.equals(CarePlan.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "carePlanName", "", "Specify activity plan name");
	}

}
