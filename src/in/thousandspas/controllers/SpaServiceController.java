package in.thousandspas.controllers;


import in.thousandspas.service.service.SpaServicesService;
import in.thousandspas.web.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/spa")
public class SpaServiceController {

    @Autowired
    SpaServicesService spaServicesService;

    @RequestMapping(value = "/addServiceToSpa", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> addServiceToSpa(HttpServletRequest req) {
        String spaEmail = req.getParameter("spa_email");
        String serviceName = req.getParameter("service_name");

        return spaServicesService.addServiceToSpa(serviceName, spaEmail);
    }

    @RequestMapping(value = "/deleteServiceFromSpa", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> deleteServiceFromSpa(HttpServletRequest req) {
        String spaEmail = req.getParameter("spa_email");
        String serviceName = req.getParameter("service_name");

        return spaServicesService.deleteServiceFromSpa(serviceName, spaEmail);
    }
}
