package in.thousandspas.controllers.tracking;

import javax.servlet.http.HttpServletRequest;

import in.thousandspas.api.response.GenericSuccessFailureResponse;
import in.thousandspas.service.tracking.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
   
	@Autowired TrackingService trackingService;
	
	@RequestMapping(value="/addTracking", method = RequestMethod.POST)
    @ResponseBody
    public GenericSuccessFailureResponse addTracking(HttpServletRequest req){
		//Capture IP address
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = req.getRemoteAddr();
		}
		
		String spaidStr = req.getParameter("spa_id");
		String customeridStr = req.getParameter("customer_id");
		Integer spaid = null;
		Integer customerid = null;
		try{
			spaid = Integer.parseInt(spaidStr);
		}catch(NumberFormatException e){}
		try{
			customerid = Integer.parseInt(customeridStr);
		}catch(NumberFormatException e){}
		
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String feature = req.getParameter("feature");
		
		return trackingService.addTracking(spaid, customerid, email, mobile, ipAddress, feature);
	}

	public TrackingService getTrackingService() {
		return trackingService;
	}

	public void setTrackingService(TrackingService trackingService) {
		this.trackingService = trackingService;
	}
}
