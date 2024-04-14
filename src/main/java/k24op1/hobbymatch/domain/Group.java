package k24op1.hobbymatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Luodaan luokka Group-tauluksi 
@Table(name = "AppGroup")

public class Group {
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "group")
    @JsonIgnoreProperties ("group") //Estetään infinit loop
    private List <Happening> happenings;
    @Id //Merkitään groupId taulun id-sarakkeeksi 
    @GeneratedValue(strategy = GenerationType.AUTO) //Luo jokaiselle uudelle objektille uniikin pääavaimen
    private Long groupId;
    @NotBlank(message = "Nimi ei voi olla tyhjä")
    private String name;
    private String description;

    @ManyToMany (mappedBy = "groups")
    private Set <User> members = new HashSet<>();



public Group() {
  
}

public Group(String name, String description) {
    this.name = name;
    this.description = description;
}

public Long getGroupId() {
    return groupId;
}

public void setGroupId(Long groupId) {
    this.groupId = groupId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}
public List <Happening> getHappenings(){
    return happenings;
}
public void setHappenings(List<Happening> happenings){
    this.happenings = happenings;
}
public Set<User> getMembers() {
    return members;
}

public void setMembers(Set<User> members) {
    this.members = members;
}

@Override
public String toString() {
    return "Group [groupId=" + groupId + ", name=" + name + ", description=" + description + "]";
}




}
