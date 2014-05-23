package in.thousandspas.controllers;

import in.thousandspas.web.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/spa")
public class ServiceController {
    @Autowired
    in.thousandspas.service.service.ServicesService servicesService;

    @RequestMapping(value="/bootstrap", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> bootstrap(HttpServletRequest req){
        return null;
    }

    @RequestMapping(value="/addService", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> addService(HttpServletRequest req){
        String serviceName = req.getParameter("service_name");

        return servicesService.addService(serviceName);
    }
}
