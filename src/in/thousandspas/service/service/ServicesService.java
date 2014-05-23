package in.thousandspas.service.service;

import in.thousandspas.common.CommonException;
import in.thousandspas.common.MessageCollection;
import in.thousandspas.dao.ServiceDao;
import in.thousandspas.domain.Service;
import in.thousandspas.util.EnumCollection;
import in.thousandspas.web.AppResponse;
import org.springframework.transaction.annotation.Transactional;

public class ServicesService {
    private ServiceDao serviceDao;

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Transactional
    public AppResponse<Integer> addService(String serviceName) {
        AppResponse<Integer> response = new AppResponse<Integer>();
        response.setCode(EnumCollection.EventStatus.failure.getValue());

        //check if the service already exists
        if (doServiceExists(serviceName)) {
            response.setDescription("Service Already present");
            return response;
        }

        Service service = new Service();
        service.setServiceName(serviceName);

        try{
            Integer serviceId = (Integer)serviceDao.save(service);
            response.setData(serviceId);
            response.setCode(EnumCollection.EventStatus.success.getValue());
            response.setDescription(MessageCollection.SUCCESS_MESSAGE);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription(MessageCollection.DATABASE_ERROR);
        }

        return response;
    }

    @Transactional
    private boolean doServiceExists(String serviceName) {
        try {
            Service service;
            service = (Service) serviceDao.doesServiceExists(serviceName);
            if (service != null)
                return true;
        } catch (CommonException e) {
            e.printStackTrace();
        }

        return false;
    }
}
