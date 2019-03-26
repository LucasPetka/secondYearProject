package group9rcraggs.application.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToMany;


@Entity(name="plan")
public class Plan {

	
    
	@Id
	@Column(updatable=false)
	private int id;
	//price of monthly subscription
	private double price;
	//max number of pages tracked at one time
	private int numPages;
	//used for verifying purchase
	private String tier;
	//how often to check changes
	private long frequency;
	
	@ManyToMany
	private List<AlertAfter> alerts = new ArrayList<AlertAfter>();
	
	//map containing alert after choices
	

	public Plan() {
		
	}
	

	
	
	///* Getters *///
	public int getId() {
		return this.id;
	}
	
	public String getTier() {
		return this.tier;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getNumPages() {
		return this.numPages;
	}
	
	public List<AlertAfter> getAlertedAfter() {
		return this.alerts;
	}
	
	public long getFrequency() {
		return this.frequency;
	}
	
	///* Setters *///
	
	public void setId(int id) {
		this.id=id;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
	public List<AlertAfter> getAlertAfter() {
		return alerts;
	}
	public void setAlertAfter(ArrayList<AlertAfter> alerts) {
		this.alerts = alerts;
	}

	public void addAlertAfter(AlertAfter alert) {
		this.alerts.add(alert);
	}
}
