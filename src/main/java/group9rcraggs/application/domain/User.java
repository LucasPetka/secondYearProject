package group9rcraggs.application.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


/**
 * TODO Put here a description of what this class does.
 *
 * @author tyler.
 *         Created 30 Jan 2019.
 */
@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;

	@Column(unique = true, nullable = false)
	String login;

	@Column(unique = true, nullable = false)
	String password;
	
	@Transient
	String password2;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	private List<Website> websites = new ArrayList<Website>();

	public List<Website> getWebsites() {
		return websites;
	}
	
	public void addWebsite(Website website) {
		this.websites.add(website);
	}
	
	public void delWebsite(int id) {
		websites.removeIf(w -> w.get_id() == id);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
}

	
	

}
