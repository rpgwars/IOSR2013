package pl.agh.edu.carecenter.server.domain;

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
import javax.persistence.Transient;


@Table
@Entity
public class PatientCarePlan {
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patientCarePlan", fetch = FetchType.LAZY, targetEntity = Report.class)
	private List<Report> reportList; 

	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}

	@ManyToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
	private Patient patient; 
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(targetEntity = CarePlan.class, fetch = FetchType.EAGER)
	private CarePlan carePlan;

	public CarePlan getCarePlan() {
		return carePlan;
	}

	public void setCarePlan(CarePlan carePlan) {
		this.carePlan = carePlan;
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
	private Date startDate;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Basic
	private Date endDate;

	public Date getEndDate() {
		return endDate;
	}
	
	@Basic 
	private String remarks; 

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Transient
	private Integer carePlanId;

	public Integer getCarePlanId() {
		return carePlanId;
	}

	public void setCarePlanId(Integer carePlanId) {
		this.carePlanId = carePlanId;
	}
	
	
	

}
