package pl.agh.edu.carecenter.server.domain;

import java.util.List;

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
	
	

}
