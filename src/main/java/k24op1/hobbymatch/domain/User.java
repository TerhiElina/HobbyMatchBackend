package k24op1.hobbymatch.domain;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="AppUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;
    
    //Luodaan rajoitteet attribuuteille
    @Column(name="username", nullable = false, unique = true)
    private String username;
    
    @Column(name="password", nullable = false)
    private String passwordHash;

    private String email;
    private String status;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "Participation",
        joinColumns = {@JoinColumn(name = "userId")
    },
        inverseJoinColumns = {@JoinColumn(name= "happeningId")}
    )
    Set<Happening> happenings = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "Membership",
        joinColumns = {@JoinColumn(name = "userId")
    },
        inverseJoinColumns = {@JoinColumn(name= "groupId")}
    )
    Set<Group> groups = new HashSet<>();

    public User() {
    }

    public User(String username, String passwordHash, String email, String status) {
    
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public void setPassword(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Set<Happening> getHappenings() {
        return happenings;
    }

    public void setHappenings(Set<Happening> happenings) {
        this.happenings = happenings;
    }
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", email=" + email
                + ", status=" + status + "]";
    }

    

    

    

}
