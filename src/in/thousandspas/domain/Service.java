package in.thousandspas.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "service", uniqueConstraints = @UniqueConstraint(columnNames = {"serviceid"}))
@XmlRootElement(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column
    private Integer serviceId;

    @Column
    private String serviceName;

    @Column
    private String serviceDescription;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
