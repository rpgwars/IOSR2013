package pl.agh.edu.carecenter.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity
public class Patient extends Account{
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY, targetEntity = Alarm.class)
	private List<Alarm> alarmList = new ArrayList<Alarm>();
	
	public List<Alarm> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<Alarm> alarmList) {
		this.alarmList = alarmList;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY, targetEntity = PatientCarePlan.class)
	private List<PatientCarePlan> patientCarePlanList = new ArrayList<PatientCarePlan>();
	
	public List<PatientCarePlan> getPatientCarePlanList() {
		return patientCarePlanList;
	}

	public void setPatientCarePlanList(List<PatientCarePlan> patientCarePlanList) {
		this.patientCarePlanList = patientCarePlanList;
	}
	
	public void addPatientCarePlan(PatientCarePlan patientCarePlan){
		this.patientCarePlanList.add(patientCarePlan);
	}

	@Basic
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	@Basic
	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Basic
	private String postalCode;

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	

}
