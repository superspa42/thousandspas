package in.thousandspas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Entity
@Table(name="tracking", uniqueConstraints=@UniqueConstraint(columnNames={"trackid"}))
@XmlRootElement(name = "tracking")
public class Tracking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer trackid;
	
	@Column
	private Integer spaid;
	
	@Column 
	private Integer customerid;
	
	@Column
	private String email;
	
	@Column 
	private String mobile;
	
	@Column
	private String ipaddress;
	
	@Column
	private Date trackdate;
	
	@Column
	private String feature;

	public Integer getTrackid() {
		return trackid;
	}

	public void setTrackid(Integer trackid) {
		this.trackid = trackid;
	}

	public Integer getSpaid() {
		return spaid;
	}

	public void setSpaid(Integer spaid) {
		this.spaid = spaid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Date getTrackdate() {
		return trackdate;
	}

	public void setTrackdate(Date trackdate) {
		this.trackdate = trackdate;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
}
