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
    private int empno;
    @Column(name="START__DATE")
    private Date startdate;
    @Column(name="END_DATE")
    private Date enddate;
    @Column(name="TYPENO")
    private int type;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
