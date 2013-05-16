package pl.agh.edu.carecenter.server.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.agh.edu.carecenter.server.domain.PatientCarePlan;

public class PatientCarePlanValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return cls.equals(PatientCarePlan.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		PatientCarePlan patientCarePlan = (PatientCarePlan) object;  
		
		ValidationUtils.rejectIfEmpty(errors, "startDate", "", "Specify start date");
		ValidationUtils.rejectIfEmpty(errors, "endDate", "", "Specify end date");
		
		if(!errors.hasErrors() && patientCarePlan.getStartDate().after(patientCarePlan.getEndDate())){
			errors.rejectValue("startDate", "", "date is incorrect");
			errors.rejectValue("endDate", "", "date is incorrect");
		}
		Date yesterday = new Date();
		yesterday.setTime(yesterday.getTime() - 24 * 60 * 60 * 1000);
		if(!errors.hasErrors() && patientCarePlan.getStartDate().before(yesterday)){
			errors.rejectValue("startDate","","start date is incorrect");
		}
	}

}
