package rmabuddy.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "HARDWARE")

public class Hardware implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "increment")
    private int id;

    private String name;
    private Integer type;
    private Integer instore;
    private String sn;
    private String other;
    private Integer repairid;

    @OneToOne(mappedBy = "sprzet")
    private Repairs naprawa;

    

    public Hardware() {
    }

    public Hardware(int id) {
        this.id = id;
    }

    public Hardware(int id, String name, Integer type, Integer instore, String sn, String other, Integer clientid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.instore = instore;
        this.sn = sn;
        this.other = other;
        //this.clientid = clientid;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInstore() {
        return this.instore;
    }

    public void setInstore(Integer instore) {
        this.instore = instore;
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOther() {
        return this.other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getRepairid() {
        return repairid;
    }

    public void setRepairid(Integer repairid) {
        this.repairid = repairid;
    }

    public Repairs getNaprawa() {
        return naprawa;
    }

    public void setNaprawa(Repairs naprawa) {
        this.naprawa = naprawa;
    }
    
    

}
