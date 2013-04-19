package pl.agh.edu.carecenter.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity
public class CarePlan {
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carePlan", fetch = FetchType.LAZY, targetEntity = ActivityCarePlan.class)
	private List<ActivityCarePlan> activityCarePlanList = new ArrayList<ActivityCarePlan>();
	
	public List<ActivityCarePlan> getActivityCarePlanList() {
		return activityCarePlanList;
	}

	public void setActivityCarePlanList(List<ActivityCarePlan> activityCarePlanList) {
		this.activityCarePlanList = activityCarePlanList;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carePlan", fetch = FetchType.LAZY, targetEntity = PatientCarePlan.class)
	private List<PatientCarePlan> patientCarePlanList; 
	
	public List<PatientCarePlan> getPatientCarePlanList() {
		return patientCarePlanList;
	}

	public void setPatientCarePlanList(List<PatientCarePlan> patientCarePlanList) {
		this.patientCarePlanList = patientCarePlanList;
	}

	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
	private Date startDte;

	public Date getStartDte() {
		return startDte;
	}

	public void setStartDte(Date startDte) {
		this.startDte = startDte;
	}
	
	
	@Basic
	private Date endDate;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	

}
