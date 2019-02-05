package group9rcraggs.application.domain;

import java.time.Instant;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import group9rcraggs.application.Tracking;

@Entity(name="pages")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@ManyToOne(optional=false, cascade=CascadeType.REMOVE)
	private Website owner;
	
	@Column(nullable=false)
	private String name;
	private String url;
	private Instant lastUpdated;
	private String frequency;
	private String fileName;
	private boolean tracking;
	private String linesIgnored;
	
	public Page(String name, String url, Instant lastUpdated, String frequency, String fileName, String linesIgnored) {

		
		this.name=name;
		this.url=url;
		this.lastUpdated=lastUpdated;
		this.frequency=frequency;
		this.fileName=fileName;
		this.linesIgnored=linesIgnored;
    	this.tracking = false;
	}
	
	public Page(String ssl, String url,Website website) {
		Tracking track = new Tracking();
		track.sourceCodeToFile(ssl + "://" + url, url+"_0");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		track.sourceCodeToFile(ssl + "://" + url, url+"_1");
		this.name="Test";
		this.website=website;
		this.url=url;
		this.lastUpdated=Instant.now();
		this.frequency="30";
		this.fileName=url+"_0";
		this.linesIgnored="";
    	this.tracking = false;
    	ArrayList<Integer> linesToBeIgnored = new ArrayList();
		linesToBeIgnored = track.compareFiles(url+"_0", url+"_1");
		this.linesIgnored=linesToBeIgnored.toString();
	}
	public Page() {
		
	}
	
	///* Getters *///
	
	
	public Website getOwner() {
		return owner;
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
	
	public Instant getLastUpdated() {
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
	
	
	///* Setters *///
	
	public void setOwner(Website owner) {
		this.owner = owner;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setWebsite(Website web) {
		this.website=web;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setLastUpdated(Instant lastUpdated) {
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
	

}
