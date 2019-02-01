package group9rcraggs.application.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="websites")
public class Website {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	@OneToMany(mappedBy="website", cascade=CascadeType.ALL, orphanRemoval= true)
	public List<Page> pages;

	@ManyToOne(optional=false, cascade=CascadeType.PERSIST)
	private User owner;
	
	
	@Column
	private String name;
	private String url;
	
	private boolean tracking;
	
	

	public Website() {
		pages = new ArrayList<>();

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
		
		public void set_boolean(boolean tracking) {
			this.tracking = tracking;
		}
	
	
	
	public List<Page> get_Pages() {
		return this.pages;
}
	
	
	public void add_Page(Page page) {
		this.pages.add(page);
	}
	
	public void del_Page(int id) {
		pages.removeIf(p -> p.get_id() == id);
	}
	
}