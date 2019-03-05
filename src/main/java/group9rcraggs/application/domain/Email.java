package group9rcraggs.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="emails")
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;
	
	@Column(nullable = false)
	String address;
	
	@ManyToOne(optional=false)
	private User owner;
	

	public Email(String address, User owner) {
		this.address = address;
		this.owner = owner;
	}
	
	
	
	///* Setters *///
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	///* Getters *///
	public int getId() {
		return this.id;
	}
	public String getAddress() {
		return this.address;
	}
	
	public User getOwner() {
		return this.owner;
	}

	
}
