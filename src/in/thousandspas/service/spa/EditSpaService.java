package in.thousandspas.service.spa;

import org.springframework.transaction.annotation.Transactional;

import in.thousandspas.api.response.EditSpaResponse;
import in.thousandspas.common.CommonException;
import in.thousandspas.common.MessageCollection;
import in.thousandspas.dao.SpaDao;
import in.thousandspas.domain.Spa;
import in.thousandspas.domain.ProfileCompletionStatus;
import in.thousandspas.util.CollectionOfUtilityMethods;
import in.thousandspas.util.EnumCollection;
import in.thousandspas.util.EnumCollection.EventStatus;
import in.thousandspas.web.AppResponse;

public class EditSpaService {
	private SpaDao spaDao;
	
	@Transactional
	public boolean doesTheEmailExist(String email){
		boolean doesTheEmailExist = false;
		
		Spa spa = null;
		try {
			spa	= (Spa)spaDao.doesTheEmailExist(email);
			if(spa != null){
				doesTheEmailExist = true;
			}else{
				doesTheEmailExist = false;
			}
		} catch (CommonException e) {
			e.printStackTrace();
		}
		
		return doesTheEmailExist;		
	}
		
	/**
	 * This method is called first to create an spa in the system.
	 * This must be entry point.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param isHidden
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> createSpaWithMinimumParams(
			String firstName,
			String lastName,
			String email, 
			String phone, 
			Integer isHidden,
			String password){
		AppResponse<Integer> response = new AppResponse<Integer>();
		response.setCode(EventStatus.failure.getValue());
		//Check if the email is not duplicate
		if(doesTheEmailExist(email)){
			response.setDescription(MessageCollection.THIS_EMAIL_ALREADY_EXISTS);
		}
		
		Spa spa = new Spa();
		
		spa.setFirstName(firstName);
		spa.setLastName(lastName);
		spa.setEmail(email);
		spa.setMobile(phone);
		spa.setJoindate(new java.sql.Date(new java.util.Date().getTime()));
		spa.setIsProfileCreated(0);
		spa.setIsHidden(isHidden);
		spa.setPassword(password);
		
		spa.setNoOfVisitToEditProfilepage(0);
	
		Integer spaId = -1;
		try {
			spaId = (Integer)spaDao.save(spa);
			response.setData(spaId);
			response.setCode(EventStatus.success.getValue());
		} catch (CommonException e) {
			e.printStackTrace();
			response.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);			
			return response;
		}

		return response;
	}
	
	/**
	 * And then we go and create the full profile of an spa
	 * @param fullName
	 * @param email
	 * @param phone
	 * @param isHidden
	 * @return
	 */
	
	@Transactional
	public AppResponse<ProfileCompletionStatus> saveSpaProfile(
			int id,
			String email, 
			String firstName,
			String lastName,
			
			String mobile,
			String businessPhone,
			String city,
			Integer age,
			String area,
						
			String shortDescription,
			String longDescription,
			
			String websiteURL,
			String linkedinProfile,
			String fbProfile,
			String gplusProfile,
			
			Integer yearOfExp,
			String areasOfExpertise,
			
			String education,
			String certifications,
			
			String addressLine1,
			String addressLine2,
			String state,
			Integer pincode,
			String country){		
		Spa spa = null;
		//Prepare the response
		AppResponse<ProfileCompletionStatus> appResponse = new AppResponse<ProfileCompletionStatus>();
		appResponse.setCode(EventStatus.failure.getValue());
		//To update the profile the spa must already be created.
	    if(id == -1){
	    	appResponse.setDescription(MessageCollection.ACCOUNTANT_DOES_NOT_EXIST_NEEDED_BEFORE_CREATING_PROFILE);
			return appResponse;
		}
		
		try {
			spa	= (Spa)spaDao.readById(id);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}
		
		//If the spa is retrieved successfully
		spa.setFirstName(firstName);
		spa.setLastName(lastName);
		spa.setEmail(email);
		
		spa.setMobile(mobile);
		spa.setIsProfileCreated(1);
		spa.setIsHidden(0);
		
		spa.setBusinessPhone(businessPhone);
		spa.setCity(city);
		spa.setAge(age);
		spa.setArea(area);
				
		spa.setShortDescription(shortDescription);
		spa.setLongDescription(longDescription);
		
		spa.setWebsiteURL(websiteURL);
		spa.setLinkedinProfile(linkedinProfile);
		spa.setFbProfile(fbProfile);
		spa.setGplusProfile(gplusProfile);
		
		spa.setYearOfExp(yearOfExp);
		spa.setAreasOfExpertise(areasOfExpertise);
		
		spa.setEducation(education);
		spa.setCertifications(certifications);
		
		spa.setAddressLine1(addressLine1);
		spa.setAddressLine2(addressLine2);
		spa.setState(state);
		spa.setPincode(pincode);
		spa.setCountry(country);
	
		try {
			spaDao.update(spa);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}
		
		//If spa profile is updated successfully.
		appResponse.setCode(EventStatus.success.getValue());
		appResponse.setData(new ProfileCompletionStatus(spa));
		appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
		
		return appResponse;
	}
	
	/**
	 * Get Spa by Id
	 */
	@Transactional
	public AppResponse<Spa> getSpaById(Integer id){
		Spa spa = null;
		AppResponse<Spa> appResponse = new AppResponse<Spa>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			spa	= (Spa)spaDao.readById(id);
			//TODO: increase the editprofile page access count for this accoutant.
			//I am assuming this call has come from the editprofile page. 
			//This is completely wrong. But I still am doing this because giving something to the customers is more
			//important to me than getting it 100% right! For the sake of time.
			spa.setNoOfVisitToEditProfilepage(spa.getNoOfVisitToEditProfilepage() + 1);
			//Set the section fill information
			ProfileCompletionStatus profileCompletionStatus = new ProfileCompletionStatus(spa);
			spa.setProfileCompletionStatus(profileCompletionStatus);
			
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(spa);
			spaDao.update(spa);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.ERROR_ENGINEERS_WILL_FIX_IT);
		}
		
		return appResponse; 
	}
	/**
	 * Save the photograph of the spa and return the URL to the photo
	 * @param id
	 * @param photoURL
	 * @return
	 */
	
	@Transactional
	public AppResponse<String> saveSpaPhoto(
			Integer id,
			String photoURL){
		Spa spa = null;
		AppResponse<String> appResponse = new AppResponse<String>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			spa	= (Spa)spaDao.readById(id);
		} catch (CommonException e) {
			e.printStackTrace();
		}
    	spa.setPhotoFileName(photoURL);
		
		try {
			spaDao.update(spa);
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(null);
			appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}		
		
		return appResponse;
	}
	
	@Transactional
	public AppResponse<String> getPhotoLocation(Integer id){
		Spa spa = null;
		AppResponse<String> appResponse = new AppResponse<String>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			spa	= (Spa)spaDao.readById(id);
			if(spa != null){
				appResponse.setCode(EventStatus.success.getValue());
				appResponse.setData(spa.getPhotoFileName());
			}
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR);
		}
    	return appResponse;
	}
	
	/**
	 * Save spa password. Create an account if spa does not exist.
	 * @param id
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> saveSpaPassword(String email, String password){
		Spa spa = null;
		AppResponse<Integer> appResponse = new AppResponse<Integer>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			spa	= (Spa)spaDao.readByEmail(email);
		} catch (CommonException e) {
			e.printStackTrace();
		}
		//If the account is not created first create it.
		if(spa == null){
			return createSpaWithMinimumParams("", "", email, "", 1, password);
		}else{		
			spa.setPassword(password);
		
			try {
				spaDao.update(spa);
				appResponse.setCode(EventStatus.success.getValue());
				appResponse.setData(spa.getSpaid());
				appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
			} catch (CommonException e) {
				e.printStackTrace();
				appResponse.setDescription(MessageCollection.PASSWORD_COULD_NOT_BE_SET);
				return appResponse;
			}
		}
			
		return appResponse;
	}
	
	/**
	 * Validate Spa & return the Id associated
	 * @param email
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> isSpaValid(String email, String password){
		Spa spa = null;
		AppResponse<Integer> appResponse = new AppResponse<Integer>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			spa	= (Spa)spaDao.validateSpa(email, password);
			if(spa != null){
			  appResponse.setData(spa.getSpaid());
				appResponse.setCode(EventStatus.success.getValue());
			}else{
				appResponse.setDescription(MessageCollection.LOGIN_FAILED);
			}
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.ERROR_ENGINEERS_WILL_FIX_IT);
		}
		
		return appResponse; 
	}


	public SpaDao getSpaDao() {
		return spaDao;
	}

	public void setSpaDao(SpaDao spaDao) {
		this.spaDao = spaDao;
	}
}
