package rmabuddy.hibernate;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "REPAIRS")
@SecondaryTables({
    @SecondaryTable(name="HARDWARE"),
    @SecondaryTable(name="CLIENTS")
    })

public class Repairs implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "increment")
    private int id;

    private String number;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startdate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enddate;
    private Integer status;
    private String defect;
    private String fix;
    private String cost;
    @Column(name = "type", table = "HARDWARE") 
    private Integer type;
    @Column(name = "name", table = "HARDWARE") 
    private String name;
    @Column(name = "fname", table = "CLIENTS")
    private String fname;
    @Column(name = "sname", table = "CLIENTS")
    private String sname;

    @OneToOne
    @JoinColumn(name = "CLIENTID", nullable = false)
    private Clients klient;

    @OneToOne
    @JoinColumn(name = "HWID")
    private Hardware sprzet;

    public Repairs() {
    }

    public Repairs(int id) {
        this.id = id;
    }

    public Repairs(int id, String number, Date startdate, Date enddate, Integer status, String defect, String fix, String cost, Integer clientid, Integer hwid) {
        this.id = id;
        this.number = number;
        this.startdate = startdate;
        this.enddate = enddate;
        this.status = status;
        this.defect = defect;
        this.fix = fix;
        this.cost = cost;
       //this.clientid = clientid;
        //this.hwid = hwid;
    }

    //<editor-fold desc="Setters/Getters" defaultstate="collapsed">
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

    public Date getStartdate() {
        return this.startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return this.enddate;
    }

    public void setEnddate(Date enddate) {
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

    public Clients getKlient() {
        return klient;
    }

    public void setKlient(Clients klient) {
        this.klient = klient;
    }

    public Hardware getSprzet() {
        return sprzet;
    }

    public void setSprzet(Hardware sprzet) {
        this.sprzet = sprzet;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    } 
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    //</editor-fold>

    
}
