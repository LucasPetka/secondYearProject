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
	
	@ManyToOne(optional=false,cascade=CascadeType.REMOVE)
	private Website website;
	
	@Column(nullable=false)
	private String name;
	private String url;
	private String last_Updated;
	private String frequency;
	
	private boolean tracking;

	
	public Page(String name,String url,String last_Updated,String frequency) {

		
		this.name=name;
		this.url=url;
		this.last_Updated=last_Updated;
		this.frequency=frequency;
    	tracking = false;
	}
	
	public Page() {
		
	}
	
	//Getters
	
	public int get_id() {
		return this.id;
	}
	
	public String get_Name() {
		return this.name;
	}
	
	public String get_url() {
		return this.url;
	}
	
	public String get_last_Updated() {
		return this.last_Updated;
	}
	
	public String get_frequency() {
		return this.frequency;
	}
	
	public boolean get_tracking() {
		return this.tracking;
	}
	
	
	//Setters
	
	public void set_id(int id) {
		this.id = id;
	}
	
	public void set_name(String name) {
		this.name = name;
	}
	
	public void set_url(String url) {
		this.url = url;
	}
	
	public void set_last_Updated(String last_Updated) {
		this.last_Updated = last_Updated;
	}
	
	public void set_frequency(String frequency) {
		this.frequency = frequency;
	}
	
	public void set_boolean(boolean tracking) {
		this.tracking = tracking;
	}
	
	
	
	
	

}
