package pl.agh.edu.carecenter.server.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class AccountGroup {

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CareGroup.class)
	private CareGroup group;

	public CareGroup getGroup() {
		return group;
	}

	public void setGroup(CareGroup group) {
		this.group = group;
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
