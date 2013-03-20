package pl.agh.edu.carecenter.server.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table
@Entity
public class ActivityReport {
	
	
	@ManyToOne(targetEntity = Activity.class, fetch = FetchType.EAGER)
	private Activity activity;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	@ManyToOne(targetEntity = Report.class, fetch = FetchType.EAGER) 
	private Report report;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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
	private Integer done;

	public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
	}
	
	
	

}
