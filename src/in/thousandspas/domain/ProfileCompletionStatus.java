package in.thousandspas.domain;

public class ProfileCompletionStatus {
    private boolean isBasicInfoSectionFilled = true;
	private boolean isPhotoUploaded = true;
	private boolean isDescriptionSectionFilled = true;
	private boolean isExperienceSectionFilled = true;
	private boolean isContactSectionFilled = true;
	//any one of the above false, meaning this one must be false;
	private boolean isAllMandatorySectionsFilled = true;

	public ProfileCompletionStatus(Spa spa){
		fillCompletionStatus(spa);
	}
	
	public void fillCompletionStatus(Spa spa){
		if(spa.getFirstName() == null || "".equals(spa.getFirstName().trim())
				|| spa.getLastName() == null || "".equals(spa.getLastName().trim())
				|| spa.getEmail() == null || "".equals(spa.getEmail().trim())
				|| spa.getMobile() == null || "".equals(spa.getMobile().trim())
				|| spa.getBusinessPhone() == null || "".equals(spa.getBusinessPhone().trim())
				|| spa.getCity() == null || "".equals(spa.getCity().trim())
				|| spa.getArea() == null || "".equals(spa.getArea().trim())){
			isBasicInfoSectionFilled = false;
			isAllMandatorySectionsFilled = false;
		}
		
		if(spa.getPhotoFileName() == null || "".equals(spa.getPhotoFileName().trim())){
			isPhotoUploaded = false;
			isAllMandatorySectionsFilled = false;			
		}
		
		if(spa.getShortDescription() == null || "".equals(spa.getShortDescription().trim())
				|| spa.getLongDescription() == null || "".equals(spa.getLongDescription().trim())){
			isDescriptionSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
		
		if(spa.getYearOfExp() == null || "".equals(spa.getYearOfExp())
				|| spa.getAreasOfExpertise() == null || "".equals(spa.getAreasOfExpertise().trim())){
			isExperienceSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
		if(spa.getAddressLine1() == null || "".equals(spa.getAddressLine1().trim())
				|| spa.getAddressLine2() == null || "".equals(spa.getAddressLine2().trim())
				|| spa.getCity() == null || "".equals(spa.getCity().trim())
				|| spa.getState() == null || "".equals(spa.getState().trim())
				|| spa.getPincode() == null || "".equals(spa.getPincode())){
			isContactSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
	}
	
	public boolean isBasicInfoSectionFilled() {
		return isBasicInfoSectionFilled;
	}
	public void setBasicInfoSectionFilled(boolean isBasicInfoSectionFilled) {
		this.isBasicInfoSectionFilled = isBasicInfoSectionFilled;
	}
	public boolean isPhotoUploaded() {
		return isPhotoUploaded;
	}
	public void setPhotoUploaded(boolean isPhotoUploaded) {
		this.isPhotoUploaded = isPhotoUploaded;
	}
	public boolean isDescriptionSectionFilled() {
		return isDescriptionSectionFilled;
	}
	public void setDescriptionSectionFilled(boolean isDescriptionSectionFilled) {
		this.isDescriptionSectionFilled = isDescriptionSectionFilled;
	}
	public boolean isExperienceSectionFilled() {
		return isExperienceSectionFilled;
	}
	public void setExperienceSectionFilled(boolean isExperienceSectionFilled) {
		this.isExperienceSectionFilled = isExperienceSectionFilled;
	}
	public boolean isContactSectionFilled() {
		return isContactSectionFilled;
	}
	public void setContactSectionFilled(boolean isContactSectionFilled) {
		this.isContactSectionFilled = isContactSectionFilled;
	}

	public boolean isAllMandatorySectionsFilled() {
		return isAllMandatorySectionsFilled;
	}

	public void setAllMandatorySectionsFilled(boolean isAllMandatorySectionsFilled) {
		this.isAllMandatorySectionsFilled = isAllMandatorySectionsFilled;
	}
}
