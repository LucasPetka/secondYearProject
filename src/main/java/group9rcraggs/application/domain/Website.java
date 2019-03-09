package group9rcraggs.application.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity(name="websites")
public class Website {
	@Id
	@Column(updatable=false)
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, orphanRemoval= true)
	public List<Page> pages;

	@ManyToOne(optional=false)
	private User owner;

	private String name;
	
	@Column(updatable=false)
	private String url;
	private boolean tracking;
	private int activePages;
	
	@Column(nullable=true)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "plan_id")
	private Plan plan;

	public Website() {
		pages = new ArrayList<>();
		Page p = new Page();
		p.setName("Home");
		p.setTracking(false);
		p.setOwner(this);
		pages.add(p);
		

}
	
	///* Getters *///
	
	public User getOwner() {
		return owner;
	}
	
	public int getActivePages() {
		return this.activePages;
	}
	
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
	
	public String getEmail() {
		return this.email;
	}
	
	public Plan getPlan() {
		return this.plan;
	}
	
	
	///* Setters *///
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public void setActivePages(int activePages) {
		this.activePages = activePages;
	}
	
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
	
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<Page> getPages() {
		return this.pages;
}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	///* Other methods *///
	

	public void addPage(Page page) {
		this.pages.add(page);
	}
	
	public void deletePage(int id) {
		pages.removeIf(p -> p.getId() == id);
	}
	
}