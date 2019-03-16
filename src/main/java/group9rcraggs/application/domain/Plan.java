package group9rcraggs.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="plan")
public class Plan {

	
    
	@Id
	@Column(updatable=false)
	private int id;
	private double price;
	private int numPages;
	private String [] alertedEvery;
	private String tier;
	

	public Plan() {
		
	}
	
	
	public Plan(String tier) {
		

		this.tier = tier;
		if(this.tier.equals("Free")) {
			this.numPages = 1;
			this.price = 0;
			this.alertedEvery = new String[] {"12 Months"};
		}
		if(this.tier.equals("Standard")) {
			this.numPages = 20;
			this.price = 9.99;
			this.alertedEvery = new String[] {"3 Months", "6 Months", "9 Months", "12 Months"};
		}
		else if(this.tier.equals("Pro")) {
			this.numPages = 50;
			this.price = 19.99;
			this.alertedEvery = new String[] {"1 Month", "2 Months",  "3 Months",  "4 Months",  "5 Months", 
					 "6 Months",  "7 Months",  "8 Months",  "9 Months",  "10 Months",  "11 Months",  "12 Months", };
			}
		else if(this.tier.equals("Enterprise")) {
			this.numPages = 100;
			this.price = 29.99;
			this.alertedEvery = new String[] {"1 Week", "2 Weeks", "3 Weeks", "1 Month", "2 Months",  "3 Months",  "4 Months", 
					"5 Months", "6 Months",  "7 Months",  "8 Months",  "9 Months",  "10 Months",  "11 Months",  "12 Months", };
			}
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
	
	public String [] getAlertedEvery() {
		return this.alertedEvery;
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
	
}
