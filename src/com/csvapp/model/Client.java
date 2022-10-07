package com.csvapp.model;
import java.math.BigDecimal;

public class Client implements Comparable<Client> {
	long id;
	String name;
	BigDecimal amountDue;

	public Client(long id, String name, BigDecimal amountDue) {
		super();
		this.id = id;
		this.name = name;
		this.amountDue = amountDue;
	}

	@Override
	public String toString() {
		return "Client M [id=" + id + ", name=" + name + ", amountDue=" + amountDue + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	@Override
	public int compareTo(Client client) {
		if (client.getAmountDue().compareTo(amountDue) ==0) 
			return 0;
		else if(client.getAmountDue().compareTo(amountDue) <0)
			return  -1;
		 else 
			return 1;
						
		}
	
	}
    


