package pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Entity
@Table(name = "TIMEOFF")
public class TIMEOFF {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "EMPNO")
    private Integer empno;
    @Column(name = "START_DATE")
    private Date startdate;
    @Column(name = "END_DATE")
    private Date enddate;
    @Column(name = "TYPENO")
    private Integer type;


    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
