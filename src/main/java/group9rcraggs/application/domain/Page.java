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
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@ManyToOne(optional=false)
	private Website owner;
	
	@Column(nullable=false)
	private String name;
	private String url;
	private String lastUpdated;
	private String frequency;
	private String fileName;
	private boolean tracking;
	@Column(length=1000)
	private String linesIgnored;
	private boolean checked;
	
	private String ownerUrl;
	
	public Page(String name, String url, String lastUpdated, String frequency, String fileName, String linesIgnored) {

		
		this.name=name;
		this.url=url;
		this.lastUpdated=lastUpdated;
		this.frequency=frequency;
		this.fileName=fileName;
		this.linesIgnored=linesIgnored;
    	this.tracking = false;
    	
	}
	
	/*public Page(String ssl, String url, Website website) {
		
		Tracking track = new Tracking();
		track.sourceCodeToFile(ssl + "://" + url, url+"_0");
		try {
			Thread.sleep(70000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		track.sourceCodeToFile(ssl + "://" + url, url+"_1");
		this.name="Test";
		this.url=url;
		this.owner = website;
 	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
 	   LocalDateTime now = LocalDateTime.now();  
		this.lastUpdated=dtf.format(now);
		this.frequency="30";
		this.fileName=url+"_0";
		this.linesIgnored="";
    	this.tracking = false;
    	ArrayList<Integer> linesToBeIgnored = new ArrayList();
		linesToBeIgnored = track.compareFiles("pageDB/"+url+"_0", "pageDB/"+url+"_1");
		this.linesIgnored=linesToBeIgnored.toString();
		this.checked=true;
	}*/
	public Page() {
		
	}
	
	///* Getters *///
	
	
	public Website getOwner() {
		return this.owner;
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
	
	public String getFrequency() {
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
		return this.getOwner().getUrl() + '/' + this.getUrl();
	}
	
	//Used to validate website Url + page Url
	public String getOwnerUrl() {
		return ownerUrl;
	}
	
	
	///* Setters *///
	
	public void setOwner(Website owner) {
		this.owner = owner;
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
	
	public void setFrequency(String frequency) {
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
	
	//Used to validate website Url + page Url 
	//This is set automatically inside createPageTrack.jsp

	public void setOwnerUrl(String ownerUrl) {
		this.ownerUrl = ownerUrl;
	}

}