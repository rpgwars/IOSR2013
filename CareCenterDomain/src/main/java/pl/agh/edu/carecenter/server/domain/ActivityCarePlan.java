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
public class ActivityCarePlan {
	
	
	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	
	@ManyToOne(targetEntity = CarePlan.class, fetch = FetchType.EAGER)
	private CarePlan carePlan; 
	
	public CarePlan getCarePlan() {
		return carePlan;
	}

	public void setCarePlan(CarePlan carePlan) {
		this.carePlan = carePlan;
	}
	
	@ManyToOne(targetEntity = Activity.class, fetch = FetchType.EAGER)
	private Activity activity; 

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	@Basic
	private Integer dayOfWeek;

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	} 
	
	@Basic
	private Integer hourOfDay;

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	} 
	
	

}
