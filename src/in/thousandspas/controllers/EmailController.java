package in.thousandspas.controllers;

import in.thousandspas.api.response.GenericSuccessFailureResponse;
import in.thousandspas.service.email.EmailService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class EmailController {
	
	@Autowired
	EmailService emailService = null;
	
	@RequestMapping(value="/email", method = RequestMethod.GET)
	@ResponseBody
	public GenericSuccessFailureResponse sendEmail(HttpServletRequest req) { 
		String to = req.getParameter("emailAddress");
		String message = req.getParameter("message");
		
		GenericSuccessFailureResponse response = new GenericSuccessFailureResponse();
		response.setSuccess(emailService.sendEmail(to, message));
		
		return response; 
   }  

}
