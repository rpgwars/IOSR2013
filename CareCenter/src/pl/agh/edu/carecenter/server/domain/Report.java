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
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "report", fetch = FetchType.LAZY, targetEntity = ActivityReport.class)
	private List<ActivityReport> activityReportList = new ArrayList<ActivityReport>();

	public List<ActivityReport> getActivityReportList() {
		return activityReportList;
	}

	public void setActivityReportList(List<ActivityReport> activityReportList) {
		this.activityReportList = activityReportList;
	}


	@ManyToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
	private Patient patient; 

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
	
	
	
	
	

}
