package pl.agh.edu.carecenter.android.transferobject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class AndroidCarePlan {

	//to id nie moze sluzyc jako id na androidzie bo mozna miec wiecej tych samych planow
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:MM", timezone="CET")
	private Date startDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:MM", timezone="CET")
	private Date endDate;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private List<AndroidActivity> androindActivityList;

	public List<AndroidActivity> getAndroindActivityList() {
		return androindActivityList;
	}

	public void setAndroindActivityList(List<AndroidActivity> androindActivityList) {
		this.androindActivityList = androindActivityList;
	}
	
	public void addAndroidActivity(AndroidActivity androidActivity){
		androindActivityList.add(androidActivity);
	}

}


