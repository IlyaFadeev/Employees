package pojo;

import javax.persistence.*;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Entity
@Table(name="DEPARTMENTS")
public class DEPARTMENTS {
    @Id
    @Column(name = "DEPTNO")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_DEPT")
    @SequenceGenerator(name = "SEQ_DEPT", sequenceName = "SEQ_DEPT")
    private Integer deptno;
    @Column(name="DNAME")
    private String dname;
    @Column(name="LOC")
    private String location;
    @Column(name="MGR")
    private Integer manager;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }
}
