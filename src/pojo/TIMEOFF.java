package pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Entity
@Table(name="TIMEOFF")
public class TIMEOFF {
    @Column(name = "EMPNO")
    private Integer empno;
    @Column(name="START__DATE")
    private Date startdate;
    @Column(name="END_DATE")
    private Date enddate;
    @Column(name="TYPENO")
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
}
