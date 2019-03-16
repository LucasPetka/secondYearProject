package group9rcraggs.application.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity(name="pages")
public class Page {
	
	@Id
	@Column(updatable=false)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@ManyToOne(optional=false)
	private Website owner;
	
	@Column(updatable=false)
	private String url;
	
	@Column(nullable=false)
	private String name;
	private String lastUpdated;
	private long frequency;
	private String fileName;
	private long alertAfter;
	private boolean warning;
	private boolean tracking;
	@Column(length=1000)
	private String linesIgnored;
	private boolean checked;
	
	@Column(nullable=true)
	private String email;
	
	private String ownerUrl;
	

	public Page(String name, String url, String lastUpdated, long frequency, String fileName, String linesIgnored, long alertAfter,
			boolean warning) {

		
		this.name=name;
		this.url=url;
		this.lastUpdated=lastUpdated;
		this.frequency=frequency;
		this.fileName=fileName;
		this.linesIgnored=linesIgnored;
    	this.tracking = false;
    	this.alertAfter = alertAfter;
    	this.warning = warning;

    	
	}
	
	public Page() {
		
	}
	
	///* Getters *///
	
	
	public Website getOwner() {
		return this.owner;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getChecked() {
		return this.checked;
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
	
	public String getLastUpdated() {
		return this.lastUpdated;
	}
	
	public long getFrequency() {
		return this.frequency;
	}
	
	public boolean getTracking() {
		return this.tracking;
	}
	
	public String getLinesIgnored() {
		return this.linesIgnored;
	}
	public String getFileName() {
		return this.fileName;
	}
	
	public String getUrlWithParent() {
		return this.getOwner().getUrl() + this.getUrl();
	}
	
	//Used to validate website Url + page Url
	public String getOwnerUrl() {
		return this.ownerUrl;
	}
	
	public long getAlertAfter() {
		return this.alertAfter;
	}
	
	public boolean getWarning() {
		return this.warning;
	}
	
	
	///* Setters *///
	
	public void setOwner(Website owner) {
		this.owner = owner;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
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
	
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
	
	public void setTracking(boolean tracking) {
		this.tracking = tracking;
	}
	public void setLinesIgnored(String linesIgnored) {
		this.linesIgnored=linesIgnored;
	}
	public void setFileName(String fileName) {
		this.fileName=fileName;
	}
	
	public void setAlertAfter(long alertAfter) {
		this.alertAfter = alertAfter;
	}
	
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	
	//Used to validate website Url + page Url 
	//This is set automatically inside createPageTrack.jsp

	public void setOwnerUrl(String ownerUrl) {
		this.ownerUrl = ownerUrl;
	}

}