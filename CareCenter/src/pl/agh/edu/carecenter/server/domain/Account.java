package pl.agh.edu.carecenter.server.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY, targetEntity = AccountRole.class)
	List<AccountRole> accountRole = new ArrayList<AccountRole>();
	
	public List<AccountRole> getAccountRole() {
		return accountRole;
	}
	
	public void addAccountRole(AccountRole accountRole){
		if(!this.accountRole.contains(accountRole))
			accountRole.setAccount(this);
			this.accountRole.add(accountRole); 
	}

	public void setAccountRole(List<AccountRole> accountRole) {
		for(AccountRole ar : accountRole)
			this.addAccountRole(ar);
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
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Basic
	private String surname;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	} 
	
	
	@Basic
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Basic
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	@Basic
	private Date birthDate;
	

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Transient
	private String repeatedPassword;

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	} 
	
	
	
	
}
