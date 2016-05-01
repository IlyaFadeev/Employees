package pojo;

import javax.persistence.*;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Entity
@Table(name="TIMEOFFTYPES")
public class TIMEOFFTYPES implements Directory{
    @Id
    @Column(name = "TNO") //TODO в бд
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_TYPE")
    @SequenceGenerator(name = "SEQ_TYPE", sequenceName = "SEQ_TYPE")
    private int typeid;
    @Column(name="TNAME")
    private String type;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
