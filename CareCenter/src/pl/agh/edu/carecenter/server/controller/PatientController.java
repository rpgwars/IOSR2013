package pl.agh.edu.carecenter.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.agh.edu.carecenter.android.transferobject.AndroidAccount;
import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.android.transferobject.AndroidPanicAlert;
import pl.agh.edu.carecenter.android.transferobject.AndroidReport;
import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.service.AccountService;
import pl.agh.edu.carecenter.server.service.PatientService;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;
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

	@RequestMapping(value = "/patient/saveAlarm", method = RequestMethod.POST)
    @ResponseBody
	public HttpServletResponse saveAlarm(@RequestBody AndroidPanicAlert panicAlert){
        patientService.saveAlarm(panicAlert);
		return null; 
	}

    @RequestMapping(value = "/patient/saveReport", method = RequestMethod.POST)
    @ResponseBody
    public HttpServletResponse saveReport(@RequestBody AndroidReport androidReport){
        patientService.saveReport(androidReport);
        return null;
    }
	
	@RequestMapping("/patient/saveReport")
	public void saveReport(){
		
		String patient1 = "p1";
		String patient2 = "p2";
		String patient3 = "p3";
		AndroidReport androidReport1 = new AndroidReport(); 
		AndroidReport androidReport2 = new AndroidReport();
		AndroidReport androidReport3 = new AndroidReport(); 
		AndroidReport androidReport4 = new AndroidReport();
		AndroidReport androidReport5 = new AndroidReport(); 
		AndroidReport androidReport6 = new AndroidReport();
		
		androidReport1.setCarePlanId(1);
		androidReport1.setActivityId(2);
		androidReport1.setDateOfReport(new Date());
		androidReport1.setDone(true);
		androidReport1.setRemarks("bylo ciezko");
		
		androidReport2.setCarePlanId(1);
		androidReport2.setActivityId(1);
		androidReport2.setDateOfReport(new Date());
		androidReport2.setDone(false);
		androidReport2.setRemarks("nie dalem rady");
		
		androidReport3.setCarePlanId(2);
		androidReport3.setActivityId(4);
		androidReport3.setDateOfReport(new Date());
		androidReport3.setDone(true);
		androidReport3.setRemarks("bez uwag");
		
		androidReport4.setCarePlanId(3);
		androidReport4.setActivityId(3);
		androidReport4.setDateOfReport(new Date());
		androidReport4.setDone(true);
		androidReport4.setRemarks("ok");
		
		androidReport5.setCarePlanId(4);
		androidReport5.setActivityId(1);
		androidReport5.setDateOfReport(new Date());
		androidReport5.setDone(true);
		androidReport5.setRemarks("11111111");
		
		androidReport6.setCarePlanId(5);
		androidReport6.setActivityId(2);
		androidReport6.setDateOfReport(new Date());
		androidReport6.setDone(true);
		androidReport6.setRemarks("222222222222");
		
		
		//patientCareplan -> patient id
		//1 -> 8
		//2 -> 6
		//3 -> 7
		
		//care plan -> activity
		//1 -> 2 1 4 
		//2 -> 4 4 
		//3 -> 2 3 
		
		patientService.saveReport(androidReport1);
		patientService.saveReport(androidReport2);
		patientService.saveReport(androidReport3);
		patientService.saveReport(androidReport4);
		patientService.saveReport(androidReport5);
		patientService.saveReport(androidReport6);
		
		
	}
	
	

}
