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
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUrl() {
		return this.url;
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
		
		public void setTracking(boolean tracking) {
			this.tracking = tracking;
		}
	
	
	
	public List<Page> getPages() {
		return this.pages;
}
	
	
	public void addPage(Page page) {
		this.pages.add(page);
	}
	
	public void deletePage(int id) {
		pages.removeIf(p -> p.getId() == id);
	}
	
}