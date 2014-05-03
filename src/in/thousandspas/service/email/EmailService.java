package in.thousandspas.service.email;

import in.thousandspas.service.BaseService;
import in.thousandspas.util.ApplicationConstants;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


public class EmailService extends BaseService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	
	private static Map<String, String> getVelocityTemplateVariables() {
		 Map<String, String> model = new HashMap<String, String>();
         model.put("discountRate", "5%");
         
         return model;
	}
	
	
	public boolean sendEmail(final String toAddress, final String message) {
		
		final String subject = this.getApplicationProperty(ApplicationConstants.CAMPAIGN_EMAIL_SUBJECT_PROPERTY);
		final String fromAddress = this.getApplicationProperty(ApplicationConstants.CAMPAIGN_FROM_EMAIL_PROPERTY);
		final String fromEmailDisplayName = this.getApplicationProperty(ApplicationConstants.CAMPAIGN_EMAIL_DISPLAY_NAME_PROPERTY);
		final String velocityTemplate = this.getApplicationProperty(ApplicationConstants.CAMPAIGN_VELOCITY_TEMPLATE_PROPERTY);
		
		System.out.println(subject);
		System.out.println(fromAddress);
		System.out.println(fromEmailDisplayName);
		System.out.println(velocityTemplate);
		
		try { 
		
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		            message.setTo(toAddress);
		            message.setFrom(new InternetAddress(fromAddress, fromEmailDisplayName)); //TODO: should be parameterized...
		            message.setSubject(subject);
		           
		            String htmlText = VelocityEngineUtils.mergeTemplateIntoString(
		               velocityEngine, velocityTemplate, getVelocityTemplateVariables());
		            message.setText(htmlText, true);
				}
			};
			
	      this.mailSender.send(preparator);
	      
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	      return true;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}
