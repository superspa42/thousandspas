package in.thousandspas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="trackemail", uniqueConstraints=@UniqueConstraint(columnNames="id"))
@XmlRootElement(name = "trackemail")

public class TrackEmail {
		@Id
		@GenericGenerator(name="generator", strategy="increment")
	    @GeneratedValue(generator="generator")
		@Column
		Integer id;

		@Column(name="ipaddress")
		String ipAddress;
		
		@Column(name="couponcode")
		String couponCode;
		
		@Column 
		Long time;
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}
		
		public String getCouponCode() {
			return couponCode;
		}

		public void setCouponCode(String couponCode) {
			this.couponCode = couponCode;
		}

}
