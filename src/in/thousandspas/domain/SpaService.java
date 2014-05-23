package in.thousandspas.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "spa_service", uniqueConstraints = @UniqueConstraint(columnNames = {"spaserviceid"}))
@XmlRootElement(name = "spa_service")
public class SpaService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column
    private Integer spaserviceid;

    @Column
    private Integer spaid;

    @Column
    private Integer serviceid;


    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getSpaid() {
        return spaid;
    }

    public void setSpaid(Integer spaid) {
        this.spaid = spaid;
    }

    public Integer getSpaserviceid() {
        return spaserviceid;
    }

    public void setSpaserviceid(Integer spaserviceid) {
        this.spaserviceid = spaserviceid;
    }
}
