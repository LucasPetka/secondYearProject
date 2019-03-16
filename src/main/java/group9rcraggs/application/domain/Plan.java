package group9rcraggs.application.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

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
	
	
	//map containing alert after choices
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="page_id"))
	Map<Long, String> alertAfterList = new HashMap<Long, String>();
	

	public Plan() {
		
	}
	
	
	public Plan(String tier) {
		

		this.tier = tier;
		if(this.tier.equals("Free")) {
			this.numPages = 1;
			this.price = 0;
			this.alertAfterList.put(new Long(483840), "12 Months");
			this.frequency = 2880; //48 hours
		
		}
		if(this.tier.equals("Standard")) {
			this.numPages = 20;
			this.price = 9.99;
			this.frequency = 1440; //24 hours
			this.alertAfterList.put(new Long(120960), "3 Months");
			this.alertAfterList.put(new Long(241920), "6 Months");
			this.alertAfterList.put(new Long(362880), "9 Months");
			this.alertAfterList.put(new Long(483840), "12 Months");
			
			
		}
		else if(this.tier.equals("Pro")) {
			this.numPages = 50;
			this.price = 19.99;
			this.frequency = 360; //6 hours
			this.alertAfterList.put(new Long(40320), "1 Month");
			this.alertAfterList.put(new Long(80640), "2 Months");
			this.alertAfterList.put(new Long(120960), "3 Months");
			this.alertAfterList.put(new Long(161280), "4 Months");
			this.alertAfterList.put(new Long(201600), "5 Months");
			this.alertAfterList.put(new Long(241920), "6 Months");
			this.alertAfterList.put(new Long(282240), "7 Months");
			this.alertAfterList.put(new Long(322560), "8 Months");
			this.alertAfterList.put(new Long(362880), "9 Months");
			this.alertAfterList.put(new Long(403200), "10 Months");
			this.alertAfterList.put(new Long(443520), "11 Months");
			this.alertAfterList.put(new Long(483840), "12 Months");
		}
		else if(this.tier.equals("Enterprise")) {
			this.numPages = 100;
			this.price = 29.99;
			this.frequency = 60; //1 hour
			this.alertAfterList.put(new Long(10080), "1 Week");
			this.alertAfterList.put(new Long(20160), "2 Weeks");
			this.alertAfterList.put(new Long(30240), "3 Weeks");
			this.alertAfterList.put(new Long(40320), "1 Month");
			this.alertAfterList.put(new Long(80640), "2 Months");
			this.alertAfterList.put(new Long(120960), "3 Months");
			this.alertAfterList.put(new Long(161280), "4 Months");
			this.alertAfterList.put(new Long(201600), "5 Months");
			this.alertAfterList.put(new Long(241920), "6 Months");
			this.alertAfterList.put(new Long(282240), "7 Months");
			this.alertAfterList.put(new Long(322560), "8 Months");
			this.alertAfterList.put(new Long(362880), "9 Months");
			this.alertAfterList.put(new Long(403200), "10 Months");
			this.alertAfterList.put(new Long(443520), "11 Months");
			this.alertAfterList.put(new Long(483840), "12 Months");
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
	
	public Map<Long, String> getAlertedAfter() {
		return this.alertAfterList;
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
	
}
