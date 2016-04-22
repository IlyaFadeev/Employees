package pojo;

import javax.persistence.*;

/**
 * Created by Fadeev on 4/18/2016.
 */
@Entity
@Table(name = "JOB")
public class JOB {
    @Id
    @Column(name = "JNO")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seq_job")
    @SequenceGenerator(name = "seq_job", sequenceName = "seq_job")
    private Integer jno;
    @Column(name = "JNAME")
    private String jname;

    public Integer getJno() {
        return jno;
    }

    public void setJno(Integer jno) {
        this.jno = jno;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
