package pl.agh.edu.carecenter.android.transferobject;

public class AndroidActivity {

	//to id nie moze sluzyc jako id na androidzie bo rozne plany moga sie skladac z tej samej aktywnosci 
	private Integer id; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	private Integer dayOfWeek;

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	} 
	
	private Integer hourOfDay;

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	} 
	
	
	

}
