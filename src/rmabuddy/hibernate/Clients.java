package rmabuddy.hibernate;


import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="CLIENTS")
public class Clients  implements java.io.Serializable {

     @Id
     @GeneratedValue(generator = "gen")
     @GenericGenerator(name = "gen", strategy = "increment")
     private int id;
     
     private String fname;
     private String sname;
     private String company;
     private String nip;
     private String addr1;
     private String addr2;
     private String city;
     private String postcode;
     private String email;
     private String phone1;
     private String phone2;
     
     @OneToMany(mappedBy = "klient")
     private List<Repairs> repairs;
     
     @OneToMany(mappedBy = "klient")
     private List<Hardware> hardware;
     
    public Clients() {
    }

	
    public Clients(int id) {
        this.id = id;
    }
    public Clients(int id, String fname, String sname, String company, String nip, String addr1, String addr2, String city, String postcode, String email, String phone1, String phone2) {
       this.id = id;
       this.fname = fname;
       this.sname = sname;
       this.company = company;
       this.nip = nip;
       this.addr1 = addr1;
       this.addr2 = addr2;
       this.city = city;
       this.postcode = postcode;
       this.email = email;
       this.phone1 = phone1;
       this.phone2 = phone2;
    }
    
    // <editor-fold desc="Getters/Setters" defaultstate="collapsed">
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSname() {
        return this.sname;
    }
    
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    public String getNip() {
        return this.nip;
    }
    
    public void setNip(String nip) {
        this.nip = nip;
    }
    public String getAddr1() {
        return this.addr1;
    }
    
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
    public String getAddr2() {
        return this.addr2;
    }
    
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone1() {
        return this.phone1;
    }
    
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }   
    public String getPhone2() {
        return this.phone2;
    }
    
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    
    public List<Repairs> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repairs> repairs) {
        this.repairs = repairs;
    }
    
    public List<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(List<Hardware> hardware) {
        this.hardware = hardware;
    }
    
    //</editor-fold>
    
}


