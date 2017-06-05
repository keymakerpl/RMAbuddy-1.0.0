package rmabuddy.hibernate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Table(name="REPAIRS")
@Entity
public class Repairs  implements java.io.Serializable {

     @Id
     @GeneratedValue(generator = "gen")
     @GenericGenerator(name = "gen", strategy = "increment")
     private int id;
     
     private String number;
     private Integer startdate;
     private Integer enddate;
     private Integer status;
     private String defect;
     private String fix;
     private String cost;
     private Integer clientid;
     private Integer hwid;
         
    
    @OneToOne
    @JoinColumn(name = "KLIENT_ID", nullable = false)    
     private Clients klient;

    public Repairs() {
    }

	
    public Repairs(int id) {
        this.id = id;
    }
    public Repairs(int id, String number, Integer startdate, Integer enddate, Integer status, String defect, String fix, String cost, Integer clientid, Integer hwid) {
       this.id = id;
       this.number = number;
       this.startdate = startdate;
       this.enddate = enddate;
       this.status = status;
       this.defect = defect;
       this.fix = fix;
       this.cost = cost;
       this.clientid = clientid;
       this.hwid = hwid;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getStartdate() {
        return this.startdate;
    }
    
    public void setStartdate(Integer startdate) {
        this.startdate = startdate;
    }
    public Integer getEnddate() {
        return this.enddate;
    }
    
    public void setEnddate(Integer enddate) {
        this.enddate = enddate;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDefect() {
        return this.defect;
    }
    
    public void setDefect(String defect) {
        this.defect = defect;
    }
    public String getFix() {
        return this.fix;
    }
    
    public void setFix(String fix) {
        this.fix = fix;
    }
    public String getCost() {
        return this.cost;
    }
    
    public void setCost(String cost) {
        this.cost = cost;
    }
    public Integer getClientid() {
        return this.clientid;
    }
    
    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }
    public Integer getHwid() {
        return this.hwid;
    }
    
    public void setHwid(Integer hwid) {
        this.hwid = hwid;
    }
    
    public Clients getKlient() {
        return klient;
    }

    public void setKlient(Clients klient) {
        this.klient = klient;
    }
    

}


