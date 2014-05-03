package in.thousandspas.service.tracking;

import org.springframework.transaction.annotation.Transactional;

import in.thousandspas.api.response.GenericSuccessFailureResponse;
import in.thousandspas.dao.TrackingDao;
import in.thousandspas.domain.Tracking;

public class TrackingService {
	private TrackingDao trackingDao;
	
	@Transactional
	public GenericSuccessFailureResponse addTracking(Integer spaid, Integer customerid, 
			String email, String mobile, String ipAddress, String feature){
		GenericSuccessFailureResponse response = new GenericSuccessFailureResponse();
		response.setSuccess(false);
		
		Tracking tracking = new Tracking();
		tracking.setSpaid(spaid);
		tracking.setCustomerid(customerid);
		tracking.setEmail(email);
		tracking.setMobile(mobile);
		tracking.setTrackdate(new java.sql.Date(new java.util.Date().getTime()));
		tracking.setIpaddress(ipAddress);
		tracking.setFeature(feature);
		
		try{
			trackingDao.save(tracking);
		}catch(Exception e){
		   e.printStackTrace();
		   response.setSuccess(false);
		}
		
		response.setSuccess(true);
		return response;
	}

	public TrackingDao getTrackingDao() {
		return trackingDao;
	}

	public void setTrackingDao(TrackingDao trackingDao) {
		this.trackingDao = trackingDao;
	}
}
