package pojo;

import javax.persistence.*;

/**
 * Created by Fadeev on 4/18/2016.
 */
@Entity
@Table(name = "JOB")
public class JOB implements Directory{
    @Id
    @Column(name = "JNAME")
    private String jname;

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
