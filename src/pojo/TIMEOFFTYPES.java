package pojo;

import javax.persistence.*;

/**
 * Created by Ilia Komarov on 25.04.2016.
 */
@Entity
@Table(name = "TYPE")
public class TIMEOFFTYPES implements Directory {
    @Id
    @Column(name = "TNAME")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
