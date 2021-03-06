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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;

	@Column(unique = true, nullable = false)
	String login;

	@Column(unique = true, nullable = false)
	String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Plan plan;

	String tempTier;
	
	int tempLength;
	
	String planValidUntil;
	
	@Column(nullable = false)
	String firstName;
	
	@Column(nullable = false)
	String lastName;
	
	@Transient
	String password2;
	
    @Column
    private boolean enabled;
	

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Website> websites = new ArrayList<Website>();
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Email> emails = new ArrayList<Email>();
	
	//Default constructor just whilst login form hasn't been created
	public User() {
		this.plan = new Plan();
		this.login="";
		this.password="";
		this.firstName="";
		this.lastName="";
		this.enabled=false;
		this.tempTier="Free";
		this.planValidUntil="";
	}

	
	///* Getters *///
	public int getId() {
		return this.id;
	}
	
	public List<Website> getWebsites() {
		return this.websites;
	}
	
	public List<Email> getEmails() {
		return this.emails;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPassword2() {
		return this.password2;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getTempTier() {
		return this.tempTier;
	}
	
	
	public String getPlanValidUntil() {
		return this.planValidUntil;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public Plan getPlan() {
		return this.plan;
	}
	public int getTempLength() {
		return this.tempLength;
	}


	///* Setters *///
	
	public void setEnabled(boolean enabled) {
		this.enabled=enabled;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	
	public void setfirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	
	public void setTempTier(String temptier) {
		this.tempTier=temptier;
	}
	
	
	public void setPlanValidUntil(String tierValidUntil) {
		this.planValidUntil=tierValidUntil;
	}
	
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public void setTempLength(int length) {
		this.tempLength=length;
	}
	
	///* Other methods *///

	
	public void addWebsite(Website website) {
		this.websites.add(website);
	}
	
	public void deleteWebsite(int id) {
		websites.removeIf(w -> w.getId() == id);
	}
	
	public void addEmail(Email email) {
		this.emails.add(email);
	}
	
	public void deleteEmail(Email email) {
		emails.removeIf(w -> w.getId() == id);
	}

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
