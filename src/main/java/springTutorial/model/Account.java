package springTutorial.model;

import java.util.Date;

public class Account {
	private Integer id;
	private String name;
	private Date creationTime;
	private Float balance;
	
	public Account() {
		setCreationTime(new Date());
		balance = 0f;
	}

	public void mutateBalance(Float change) {
		balance += change;
		
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public Float getBalance() {
		return balance;
	}

	@Override
	public boolean equals(Object other){
		if (other instanceof Account){
			return ((Account) other).id == id;
		}
		return false;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
}
