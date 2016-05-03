package pojo;

import javax.persistence.*;

/**
 * Created by Fadeev on 4/18/2016.
 */
@Entity
@Table(name="LOCATE")
public class LOCATE implements Directory{
    @Id
    @Column(name = "LNAME")
    private String lname;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
