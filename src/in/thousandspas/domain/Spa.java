package in.thousandspas.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="spa", uniqueConstraints=@UniqueConstraint(columnNames={"spaid"}))
@XmlRootElement(name = "spa")
public class Spa {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer spaid;
	
	@Column
	private String email;
	
	@Column
	private String firstName;
	
	@Column 
	private String lastName;
	
	@Column
	private String password;
		
	@Column
	private Date joindate;
	
	@Column
	private Integer isProfileCreated;
	
	@Column
	private Date profileCreateDate;
	
	@Column
	private Integer isHidden;
	
	@Column
	private String mobile;
	
	@Column
	private String businessPhone;
	
	@Column
	private String city;
		
	@Column
	private Integer age;
	
	@Column
	private String area;
	
	@Column
	private String photoFileName;
	
	@Column
	private String shortDescription;
	
	@Column 
	private String longDescription;
	
	@Column
	private String websiteURL;
	
	@Column
	private String linkedinProfile;
	
	@Column
	private String fbProfile;
	
	@Column 
	private String gplusProfile;
	
	@Column
	private Integer yearOfExp;
	
	@Column
	private String areasOfExpertise;
	
	@Column
	private String education;
	
	@Column 
	private String certifications;
	
	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@Column
	private String state;
	
	@Column 
	private Integer pincode;
	
	@Column
	private String country;	
	
	@Transient
	private ProfileCompletionStatus profileCompletionStatus;
	
	@Column
	private Integer noOfVisitToEditProfilepage;
	
	public Integer getSpaid() {
		return spaid;
	}

	public void setSpaid(Integer spaid) {
		this.spaid = spaid;
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

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Integer getIsProfileCreated() {
		return isProfileCreated;
	}

	public void setIsProfileCreated(Integer profileCreated) {
		this.isProfileCreated = profileCreated;
	}

	public Date getProfileCreateDate() {
		return profileCreateDate;
	}

	public void setProfileCreateDate(Date profileCreationDate) {
		this.profileCreateDate = profileCreationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String spaEmail) {
		this.email = spaEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getLinkedinProfile() {
		return linkedinProfile;
	}

	public void setLinkedinProfile(String linkedinProfile) {
		this.linkedinProfile = linkedinProfile;
	}

	public String getFbProfile() {
		return fbProfile;
	}

	public void setFbProfile(String fbProfile) {
		this.fbProfile = fbProfile;
	}

	public String getGplusProfile() {
		return gplusProfile;
	}

	public void setGplusProfile(String gplusProfile) {
		this.gplusProfile = gplusProfile;
	}

	public Integer getYearOfExp() {
		return yearOfExp;
	}

	public void setYearOfExp(Integer yearOfExp) {
		this.yearOfExp = yearOfExp;
	}

	public String getAreasOfExpertise() {
		return areasOfExpertise;
	}

	public void setAreasOfExpertise(String areasOfExpertise) {
		this.areasOfExpertise = areasOfExpertise;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCertifications() {
		return certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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

	public ProfileCompletionStatus getProfileCompletionStatus() {
		return profileCompletionStatus;
	}

	public void setProfileCompletionStatus(
			ProfileCompletionStatus profileCompletionStatus) {
		this.profileCompletionStatus = profileCompletionStatus;
	}

	public Integer getNoOfVisitToEditProfilepage() {
		return noOfVisitToEditProfilepage;
	}

	public void setNoOfVisitToEditProfilepage(Integer noOfVisitToProfilepage) {
		this.noOfVisitToEditProfilepage = noOfVisitToProfilepage;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}	
	
}
