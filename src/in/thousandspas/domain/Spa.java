package in.thousandspas.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.jasper.tagplugins.jstl.core.Set;

import java.sql.Date;


@Entity
@Table(name = "spa", uniqueConstraints = @UniqueConstraint(columnNames = {"spaid"}))
@XmlRootElement(name = "spa")
public class Spa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

	 /* Table spa:
	  spaid int NOT NULL AUTO_INCREMENT,
	  spaname varchar(200),
	  primaryemail varchar(80) NOT NULL UNIQUE,
	  secondaryemail varchar(80) NOT NULL,
	  businessphone1 varchar(20),
	  businessphone2 varchar(20),
	  password varchar(80) ,
	  city varchar(80) ,
	  area varchar(100) ,
	  spashostname varchar(200), //Will create URL like www.superspas.in/aromaspa or better yet aromaspa.superspas.in 
	  
	  
	  ownerfirstname varchar(50),
	  ownerlastname varchar(50) ,
	  mobile varchar(20) ,
	
	  joindate date ,
	  isprofilecreated int ,
	  profilecreatedate date ,
	  profilemodifieddate date,
	  isHidden int ,
	  
	  shortdescription text,
	  longdescription text,
	  
	  websiteurl text,
	  fbprofile text,
	  gplusprofile text,
	      
	  addressline1 varchar(200),
	  addressline2 varchar(200),
	  state varchar(100),
	  pincode int,
	  country varchar(100),
	  
	  noofvisittoeditprofilepage int,
	  noofuniqueprofilevisits int,
	  
	  locationlat FLOAT(10,6),
	  locationlong FLOAT(10,6),
	  
	  isprofilecomplete int
	  */
    @Column
    private Integer spaid;

    @Column
    private String spaname;

    @Column
    private String primaryemail;

    @Column
    private String secondaryemail;


    @Column
    private String businessphone1;

    @Column
    private String businessphone2;

    @Column
    private String password;

    @Column
    private String city;

    @Column
    private String area;

    @Column
    private String spashostname;

    @Column
    private String ownerfirstname;

    @Column
    private String ownerlastname;

    @Column
    private String mobile;

    @Column
    private Date joindate;

    @Column
    private Integer isprofilecreated;

    @Column
    private Date profilecreatedate;

    @Column
    private Date profilemodifieddate;

    @Column
    private Integer isHidden;

    @Column
    private String shortdescription;

    @Column
    private String longdescription;

    @Column
    private String websiteurl;

    @Column
    private String fbprofile;

    @Column
    private String gplusprofile;

    @Column
    private String addressline1;

    @Column
    private String addressline2;

    @Column
    private String state;

    @Column
    private Integer pincode;

    @Column
    private String country;

    @Column
    private Integer noofvisittoeditprofilepage;

    @Column
    private Integer noofuniqueprofilevisits;

    @Column
    private float locationlat;

    @Column
    private float locationlong;

    @Column
    private Integer isprofilecomplete;

    @Transient
    private ProfileCompletionStatus profileCompletionStatus;


    @OneToMany(mappedBy="imageid")
    private java.util.Set<Images> images;
    
    
    public Integer getSpaid() {
        return spaid;
    }

    public void setSpaid(Integer spaid) {
        this.spaid = spaid;
    }


    public String getSpaName() {
        return spaname;
    }

    public void setSpaName(String spaname) {
        this.spaname = spaname;
    }

    public String getPrimaryEmail() {
        return primaryemail;
    }

    public void setPrimaryEmail(String spaEmail) {
        this.primaryemail = spaEmail;
    }


    public String getSecondaryEmail() {
        return secondaryemail;
    }

    public void setSecondaryEmail(String spaEmail) {
        this.secondaryemail = spaEmail;
    }


    public String getBusinessPhone1() {
        return businessphone1;
    }

    public void setBusinessPhone1(String phone) {
        this.businessphone1 = phone;
    }

    public String getBusinessPhone2() {
        return businessphone2;
    }

    public void setBusinessPhone2(String phone) {
        this.businessphone2 = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpaHostName() {
        return spashostname;
    }

    public void setSpaHostName(String spashostname) {
        this.spashostname = spashostname;
    }


    public String getOwnerFirstName() {
        return ownerfirstname;
    }

    public void setOwnerFirstName(String ownerfirstname) {
        this.ownerfirstname = ownerfirstname;
    }

    public String getOwnerLastName() {
        return ownerlastname;
    }

    public void setOwnerLastName(String owernlastname) {
        this.ownerlastname = ownerlastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Integer getIsProfileCreated() {
        return isprofilecreated;
    }

    public void setIsProfileCreated(Integer isprofilecreated) {
        this.isprofilecreated = isprofilecreated;
    }

    public Date getProfileCreateDate() {
        return profilecreatedate;
    }

    public void setProfileCreateDate(Date profileCreationDate) {
        this.profilecreatedate = profileCreationDate;
    }

    public Date getProfileModifiedDate() {
        return profilemodifieddate;
    }

    public void setProfileModifiedDate(Date profilemodifieddate) {
        this.profilemodifieddate = profilemodifieddate;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }


    public String getShortDescription() {
        return shortdescription;
    }

    public void setShortDescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getLongDescription() {
        return longdescription;
    }

    public void setLongDescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getWebsiteURL() {
        return websiteurl;
    }

    public void setWebsiteURL(String websiteurl) {
        this.websiteurl = websiteurl;
    }

    public String getFbProfile() {
        return fbprofile;
    }

    public void setFbProfile(String fbprofile) {
        this.fbprofile = fbprofile;
    }

    public String getGplusProfile() {
        return gplusprofile;
    }

    public void setGplusProfile(String gplusprofile) {
        this.gplusprofile = gplusprofile;
    }

    public String getAddressLine1() {
        return addressline1;
    }

    public void setAddressLine1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressLine2() {
        return addressline2;
    }

    public void setAddressLine2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNoOfVisitToEditProfilepage() {
        return noofvisittoeditprofilepage;
    }

    public void setNoOfVisitToEditProfilepage(Integer noofvisittoeditprofilepage) {
        this.noofvisittoeditprofilepage = noofvisittoeditprofilepage;
    }

    public Integer getNoOfUniqueProifleVistis() {
        return noofuniqueprofilevisits;
    }

    public void setNoOfUniqueProifleVistis(Integer noofuniqueprofilevisits) {
        this.noofuniqueprofilevisits = noofuniqueprofilevisits;
    }

    public float getLocationLat() {
        return this.locationlat;
    }

    public void setLocationLat(float locationlat) {
        this.locationlat = locationlat;
    }

    public float getLocationLong() {
        return this.locationlong;
    }

    public void setLocationLong(float locationlong) {
        this.locationlong = locationlong;
    }

    public Integer getIsProfileComplete() {
        return this.isprofilecomplete;
    }

    public void setIsProfileComplete(Integer isprofilecomplete) {
        this.isprofilecomplete = isprofilecomplete;
    }

    public ProfileCompletionStatus getProfileCompletionStatus() {
        return profileCompletionStatus;
    }

    public void setProfileCompletionStatus(
            ProfileCompletionStatus profileCompletionStatus) {
        this.profileCompletionStatus = profileCompletionStatus;
    }

}
