package pl.agh.edu.carecenter.server.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class AccountRole {
	
	public AccountRole(){
		this.setRole("ROLE_ANONYMOUS"); 
		
	}
	
	public AccountRole(String role){
		this.setRole(role); 
	}

	
	@Id
	@GeneratedValue
	Integer roleId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Basic
	private String role; 
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
	private Account account; 

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
