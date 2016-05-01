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
    private int deptno;
    @Column(name="DNAME")
    private String dname;
    @Column(name="LOC")
    private int location;
    @Column(name="MGR")
    private int manager;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
}
