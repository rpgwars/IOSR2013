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
public class Report {
	
	
	@ManyToOne(targetEntity = Activity.class, fetch = FetchType.EAGER)
	private Activity activity; 

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	@ManyToOne(targetEntity = PatientCarePlan.class, fetch = FetchType.EAGER)
	private PatientCarePlan patientCarePlan; 

	public PatientCarePlan getPatientCarePlan() {
		return patientCarePlan;
	}

	public void setPatientCarePlan(PatientCarePlan patientCarePlan) {
		this.patientCarePlan = patientCarePlan;
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
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Basic
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Basic
	private Integer done;

	public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
	}
	
	
	
	
	
	
	

}
