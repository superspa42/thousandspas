package in.thousandspas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.thousandspas.api.response.EditSpaResponse;
import in.thousandspas.common.MessageCollection;
import in.thousandspas.domain.Spa;
import in.thousandspas.domain.ProfileCompletionStatus;
import in.thousandspas.service.spa.EditSpaService;
import in.thousandspas.util.CollectionOfUtilityMethods;
import in.thousandspas.util.EnumCollection.EventStatus;
import in.thousandspas.web.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spa")
public class SpaController {
    @Autowired
    EditSpaService editSpaService;
    
    @RequestMapping(value="/createSpaWithMinParams", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> addSpaWithMinParams(HttpServletRequest req){
    		String name = req.getParameter("spa_name");
    		String email = req.getParameter("spa_email");
    		String phone = req.getParameter("spa_phone");
    		String password = req.getParameter("spa_password");
    		String isHiddenStr = CollectionOfUtilityMethods.getParamValIfNotNullElseGetDefaultVal("isHidden", "1", req);
    		//By default profile is created in hidden mode.
    		//It is only shown only after all the mandatory sections in the 
    		//profile is filled.
    		Integer isHidden = 1; 
    	
    		try{
    			isHidden = Integer.parseInt(isHiddenStr);
    		}catch(Exception e){}  	

    		String[] firstNameAndLastNameArray = CollectionOfUtilityMethods.getFirstNameAndLastNameArrayFromFullName(name);
    		
    		String firstName = firstNameAndLastNameArray[0];
    		String lastName = firstNameAndLastNameArray[1];
    		return editSpaService.createSpaWithMinimumParams(firstName, lastName, email, phone, isHidden, password);
    }   
    
    @RequestMapping(value="/saveProfile", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<ProfileCompletionStatus> saveSpaProfile(HttpServletRequest req){
       	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<ProfileCompletionStatus>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	int id = CollectionOfUtilityMethods.parseToNumber(req.getParameter("spaid"));
    	String firstName = req.getParameter("firstName");
    	String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String businessPhone = req.getParameter("businessPhone");
        String city = req.getParameter("city");
        String area = req.getParameter("area");
        
        Integer age = CollectionOfUtilityMethods.parseToNumber(req.getParameter("age"));
        
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        
        String websiteURL = req.getParameter("websiteURL");
        String linkedinProfile = req.getParameter("linkedinProfile");
        String fbProfile = req.getParameter("fbProfile");
        String gplusProfile = req.getParameter("gplusProfile");
        
        Integer yearOfExp = CollectionOfUtilityMethods.parseToNumber(req.getParameter("yearOfExp"));
        String areasOfExpertise = req.getParameter("areasOfExpertise");

        String education = req.getParameter("education");
        String certifications = req.getParameter("certifications");
        
        String addressLine1 = req.getParameter("addressLine1");
        String addressLine2 = req.getParameter("addressLine2");
        String state = req.getParameter("state");
        Integer pincode = CollectionOfUtilityMethods.parseToNumber(req.getParameter("pincode"));
        String country = req.getParameter("country");
        if(country == null || "".equals(country)){
        	country = "india";
        }

        return editSpaService.saveSpaProfile(id, email, firstName, lastName, 
        		mobile, businessPhone, city, age, area, 
        		shortDescription, longDescription, websiteURL,
        		linkedinProfile, fbProfile, gplusProfile, 
        		yearOfExp, areasOfExpertise, 
        		education, certifications, 
        		addressLine1, addressLine2, state, pincode, country);
    }
    
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Spa> getSpaForSession(HttpServletRequest req){
    	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<Spa>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	HttpSession session = req.getSession(false);
    	Integer spaId = (Integer)session.getAttribute("spaid");
    	if(spaId == null){
    		return new AppResponse<Spa>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}    	
    	return editSpaService.getSpaById(spaId);
    }
    
    @RequestMapping(value="/profileCompletionStatus", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Spa> getProfileCompletionStatus(HttpServletRequest req){
    	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<Spa>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	HttpSession session = req.getSession(false);
    	Integer spaId = (Integer)session.getAttribute("spaid");
    	if(spaId == null){
    		return new AppResponse<Spa>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}    	
    	return editSpaService.getSpaById(spaId);
    }
    
    @RequestMapping(value="/getPhotoLocation", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<String> getPhotoLocation(HttpServletRequest req){
    	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<String>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	HttpSession session = req.getSession(false);
    	Integer spaId = (Integer)session.getAttribute("spaid");
    	if(spaId == null){
    		return new AppResponse<String>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}    	
    	return editSpaService.getPhotoLocation(spaId);
    }

    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Spa> getSpaById(Model model, @PathVariable("id") int id){
    	return editSpaService.getSpaById(id);
    }
    
    @RequestMapping(value="/testlogin", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> isSpaValid(HttpServletRequest req){
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	return editSpaService.isSpaValid(email, password);
    }
    
    @RequestMapping(value="/setpass", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> setPassword(HttpServletRequest req){
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	return editSpaService.saveSpaPassword(email, password);
    }
}
