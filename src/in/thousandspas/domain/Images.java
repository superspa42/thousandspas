package in.thousandspas.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;


@Entity
@Table(name = "images", uniqueConstraints = @UniqueConstraint(columnNames = {"imageid"}))
@XmlRootElement(name = "images")
public class Images {
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column
	    private Integer imageid;
	  
	    @Column
	    private Integer spaid;
	    
	    @Column
	    private String imageurl;

}