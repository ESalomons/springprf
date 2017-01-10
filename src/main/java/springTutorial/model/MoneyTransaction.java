package springTutorial.model;

import java.util.Date;

public class MoneyTransaction {
	private Integer id;
	private Account sourceAccount;
	private Account targetAccount;
	private Float amount;
	private Date creationTime;
	private String description;
	
	public MoneyTransaction() {
		creationTime = new Date();
	}

	public Account getSourceAccount() {
		return sourceAccount;
	}

	public Account getTargetAccount() {
		return targetAccount;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Float getAmount() {
		return amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}


	
	
}
