package pl.agh.edu.carecenter.server.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="test")
@Entity
public class TestDomainObject {
	
	@Id
	Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	} 
	
	

}
