package pl.agh.edu.carecenter.server.domain;

import java.util.ArrayList;
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
public class Activity {
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", fetch = FetchType.LAZY, targetEntity = Report.class)
	private List<Report> reportList = new ArrayList<Report>();

	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}


	@ManyToOne(targetEntity = ActivityCategory.class, fetch = FetchType.EAGER)
	private ActivityCategory activityCategory; 
	
	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity", fetch = FetchType.LAZY, targetEntity = ActivityCarePlan.class)
	private List<ActivityCarePlan> activityCarePlanList = new ArrayList<ActivityCarePlan>();
	
	public List<ActivityCarePlan> getActivityCarePlanList() {
		return activityCarePlanList;
	}

	public void setActivityCarePlanList(List<ActivityCarePlan> activityCarePlanList) {
		this.activityCarePlanList = activityCarePlanList;
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
	private String activityName;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	} 
	
	
	@Basic
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
