package group9rcraggs.application.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class PasswordResetToken {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String token;
   
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
     
    public PasswordResetToken(User u, String token){
    	this.user=u;
    	this.token=token;
    }
    public PasswordResetToken() {
    }
     
    public String getToken(){
    	return this.token;
    }
    public User getUser() {
    	return this.user;
    }
    public void setUser(User user) {
    	this.user=user;
    }
    public void setToken(String token) {
    	this.token = token;
    }
}
