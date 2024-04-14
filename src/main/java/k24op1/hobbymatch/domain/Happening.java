package k24op1.hobbymatch.domain;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="Happening")

public class Happening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long happeningId;
    private String happeningName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalTime time;

    //Päivämäärä ja aika formia varten
    private String address;
    private String postcode;
    private String city;

    @JsonIgnoreProperties("happenings") // Tässä ignorataan one-to-many linkki JSONISTA
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    @ManyToMany (mappedBy = "happenings")
    private Set <User> participants = new HashSet<>();

    public Happening() {
        
    }
    
    public Happening(String happeningName, Date date, LocalTime time, String address, String postcode, String city, Group group) {
        this.happeningName = happeningName;
        this.date = date;
        this.time = time;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.group = group;
    }
    // Muokataan päivämäärän ja ajan muotoilua
    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
    //private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // Asetetaan muotoillulle päivämäärälle getterit
    // ehtolauseiden avulla muokataan päivämäärä Stringinä, joka saadaan
    //happening.listissä näytettyä oikeassa muodossa.
    
    public String getFormattedTime(){
        if (time != null) {
            return timeFormatter.format(time);
        } else {
            return null;
        }
    }
    public void setFormattedTime(String formattedTime) {
        if (formattedTime != null && !formattedTime.isEmpty()) {
            this.time = LocalTime.parse(formattedTime, timeFormatter);
        } else {
            this.time = null;
        }
    }
    public Long getHappeningId() {
        return happeningId;
    }

    public void setHappeningId(Long happeningId) {
        this.happeningId = happeningId;
    }
    public String getHappeningName(){
        return happeningName;
    }
    public void setHappeningName(String happeningName){
        this.happeningName = happeningName;
    }

    public Date getDate() {
        return date;
    }

    public void Date(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup (Group group){
        this.group = group;
    }
    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }


    @Override
    public String toString() {
        return "Happening [happeningId=" + happeningId + "happeningName=" + happeningName + ", date=" + date + ", time=" + time + ", address=" + address
                + ", postcode=" + postcode + ", city=" + city +  "]";
    }

    


}
