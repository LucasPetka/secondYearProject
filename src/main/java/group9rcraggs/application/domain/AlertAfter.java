package group9rcraggs.application.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="alertafter")
public class AlertAfter {
	
@Id
@Column(updatable=false)
@GeneratedValue(strategy = GenerationType.TABLE)
private int id;

private String name;

private long alertAfter;



public AlertAfter(){
	
}
public AlertAfter(long alertAfter, String name){
	this.alertAfter=alertAfter;
	this.name=name;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


public String getName() {
	return this.name;
}
public void setName(String name) {
	this.name=name;
}


public long getAlertAfter() {
	return alertAfter;
}
public void setAlertAfter(long alertAfter) {
	this.alertAfter = alertAfter;
}



}
