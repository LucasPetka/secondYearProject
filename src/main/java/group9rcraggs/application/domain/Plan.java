package group9rcraggs.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="plan")
public class Plan {
	@Id
	@Column(updatable=false)
	private int id;
	
	private String name;
	private double price;
	private int numPages;
	
	///* Getters *///
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getNumPages() {
		return this.numPages;
	}
	
	///* Setters *///
	
	public void setId(int id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
}
