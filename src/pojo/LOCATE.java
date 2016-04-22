package pojo;

import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * Created by Fadeev on 4/18/2016.
 */
@Entity
@javax.persistence.Table(name = "LOCATE")
public class LOCATE {
    @Id
    @Column(name = "LNO")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_loc")
    @SequenceGenerator(name = "seq_loc", sequenceName = "seq_loc")
    private Integer LNO;
    @Column(name = "LNAME")
    private String LNAME;


    public Integer getLNO() {
        return LNO;
    }

    public void setLNO(Integer LNO) {
        this.LNO = LNO;
    }

    public String getLNAME() {
        return LNAME;
    }

    public void setLNAME(String LNAME) {
        this.LNAME = LNAME;
    }
}
