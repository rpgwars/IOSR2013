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
public class CareGroup {
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.LAZY, targetEntity = AccountGroup.class)
	private List<AccountGroup> accountGroupList = new ArrayList<AccountGroup>();

	public List<AccountGroup> getAccountGroupList() {
		return accountGroupList;
	}

	public void setAccountGroupList(List<AccountGroup> accountGroupList) {
		this.accountGroupList = accountGroupList;
	}

	public void addAccountGroup(AccountGroup accountGroup){
		accountGroupList.add(accountGroup);
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
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
	

}
