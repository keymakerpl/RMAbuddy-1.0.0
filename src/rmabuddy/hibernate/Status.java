package rmabuddy.hibernate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="STATUS")
public class Status  implements java.io.Serializable {

     @Id
     private int id;
     
     private String description;
     private String color;

    public Status() {
    }

	
    public Status(int id) {
        this.id = id;
    }
    public Status(int id, String description, String color) {
       this.id = id;
       this.description = description;
       this.color = color;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }




}


