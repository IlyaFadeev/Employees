package pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Fadeev on 4/17/2016.
 */
@Entity
@Table(name = "EMPLOYEES")
public class EMPLOYEES implements Serializable {
    @Id
    @Column(name = "EMPNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPLOYEE")
    @SequenceGenerator(name = "SEQ_EMPLOYEE", sequenceName = "SEQ_EMPLOYEE")
    private Integer empNo;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String secondName;
    @Column(name = "JOB")
    private String job;
    @Column(name = "MGR")
    private Integer mgr;
    @Column(name = "HIREDATE")
    private Date hireDate;
    @Column(name = "SAL")
    private Integer sal;
    @Column(name = "DEPTNO")
    private Integer deptNo;


    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

}
