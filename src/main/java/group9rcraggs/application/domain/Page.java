package group9rcraggs.application.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="pages")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@ManyToOne(optional=false, cascade=CascadeType.REMOVE)
	private Website website;
	
	@Column(nullable=false)
	private String name;
	private String url;
	private String lastUpdated;
	private String frequency;
	
	private boolean tracking;

	
	public Page(String name, String url, String lastUpdated, String frequency) {

		
		this.name=name;
		this.url=url;
		this.lastUpdated=lastUpdated;
		this.frequency=frequency;
    	tracking = false;
	}
	
	public Page() {
		
	}
	
	//Getters
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getLastUpdated() {
		return this.lastUpdated;
	}
	
	public String getFrequency() {
		return this.frequency;
	}
	
	public boolean getTracking() {
		return this.tracking;
	}
	
	
	//Setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public void setTracking(boolean tracking) {
		this.tracking = tracking;
	}
	
	
	
	
	

}
