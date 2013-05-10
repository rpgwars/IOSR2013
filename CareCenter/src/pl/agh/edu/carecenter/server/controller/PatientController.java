package pl.agh.edu.carecenter.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.agh.edu.carecenter.android.transferobject.AndroidAccount;
import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.service.AccountService;
import pl.agh.edu.carecenter.server.service.PatientService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private AccountService accountService;

	@Autowired
	private PatientService patientService;

    @RequestMapping(value = "/patient/login", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AndroidAccount login(@RequestHeader("Authorization") String authInfo, HttpServletResponse response) throws AccountNotFound {
        if(authInfo.length()>0) {
            String[] authData = authInfo.split(":");
            Account accountFromDatabase;
            AndroidAccount patientAccount = new AndroidAccount();
            try {
                accountFromDatabase = (Account) accountService.getAccountByLogin(authData[0]);
                if(accountFromDatabase!=null) {
                    Boolean isAPatient = false;
                    for(AccountRole role : accountFromDatabase.getAccountRole()) {
                        if(role.getRole().equals("ROLE_PATIENT")) isAPatient = true;
                    }
                    if(isAPatient) {
                        if(accountFromDatabase.getPassword().equals(authData[1])) {
                            patientAccount.setId(accountFromDatabase.getId());
                            patientAccount.setName(accountFromDatabase.getName());
                            patientAccount.setSurname(accountFromDatabase.getSurname());
                            patientAccount.setEmail(accountFromDatabase.getEmail());
                            patientAccount.setGroupId(accountFromDatabase.getGroupId());
                            patientAccount.setPassword(accountFromDatabase.getPassword());
                        } else {
                            patientAccount.setId(-1);
                            patientAccount.setName("Wrong login or password.");
                        }
                    } else {
                        patientAccount.setId(-1);
                        patientAccount.setName("You can login only as a patient.");
                    }
                } else {
                    patientAccount.setId(-1);
                    patientAccount.setName("No user in system.");
                }
            } catch (AccountNotFound e) {
                patientAccount.setId(-1);
                patientAccount.setName("No user in system.");
            }
            return patientAccount;

        } else {
            return null;
        }
    }
	
	@RequestMapping(value = "/patient/getCarePlans", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AndroidCarePlan> getCarePlans(@RequestHeader("Authorization") String authInfo, HttpServletResponse response) throws AccountNotFound  {

		List<AndroidCarePlan> list = patientService.getPatientsCarePlans(authInfo);
		return list;
		
		
	}

}
