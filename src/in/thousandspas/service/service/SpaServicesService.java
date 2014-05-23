package in.thousandspas.service.service;

import in.thousandspas.common.CommonException;
import in.thousandspas.dao.ServiceDao;
import in.thousandspas.dao.SpaDao;
import in.thousandspas.dao.SpaServiceDao;
import in.thousandspas.domain.Service;
import in.thousandspas.domain.Spa;
import in.thousandspas.domain.SpaService;
import in.thousandspas.util.EnumCollection;
import in.thousandspas.web.AppResponse;
import org.springframework.transaction.annotation.Transactional;

public class SpaServicesService {
    private SpaDao spaDao;
    private ServiceDao serviceDao;
    private SpaServiceDao spaServiceDao;

    public SpaServiceDao getSpaServiceDao() {
        return spaServiceDao;
    }

    public void setSpaServiceDao(SpaServiceDao spaServiceDao) {
        this.spaServiceDao = spaServiceDao;
    }

    @Transactional
    Object getSpa(String spaEmail) throws CommonException {
        Spa spa = null;
        spa = (Spa) spaDao.readByEmail(spaEmail);
        return spa;
    }

    @Transactional
    Object getService(String serviceName, boolean addIfNotExists) throws CommonException {
        Service service = null;
        service = (Service) serviceDao.getServiceByName(serviceName);

        if (service == null && addIfNotExists) {
            service = new Service();
            service.setServiceName(serviceName);
        }

        return service;
    }

    @Transactional
    public AppResponse<Integer> addServiceToSpa(String serviceName, String spaEmail) {
        Spa spa = null;
        Service service = null;
        AppResponse<Integer> response = new AppResponse<Integer>();
        response.setCode(EnumCollection.EventStatus.failure.getValue());

        try {
            spa = (Spa) getSpa(spaEmail);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription("Failed to get spa with email " + serviceName);
            return response;
        }

        try {
            service = (Service) getService(serviceName, false);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription("Failed to add service " + serviceName);
            return response;
        }

        SpaService spaService = new SpaService();
        spaService.setSpaid(spa.getSpaid());
        spaService.setServiceid(service.getServiceId());

        try {
            spaServiceDao.save(spaService);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription("Failed to update SpaService");
        }

        return response;
    }

    @Transactional
    public AppResponse<Integer> deleteServiceFromSpa(String serviceName, String spaEmail) {
        Spa spa;
        Service service;
        AppResponse<Integer> response = new AppResponse<Integer>();
        response.setCode(EnumCollection.EventStatus.failure.getValue());

        try {
            spa = (Spa) getSpa(spaEmail);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription("Failed to get spa with email " + serviceName);
            return response;
        }

        try {
            service = (Service) getService(serviceName, true);
        } catch (CommonException e) {
            e.printStackTrace();
            response.setDescription("Failed to add service " + serviceName);
            return response;
        }

        try {
            SpaService spaService = (SpaService)spaServiceDao.getSpaService(spa.getSpaid(), service.getServiceId());
            spaServiceDao.delete(spaService);
        } catch (CommonException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return response;
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public SpaDao getSpaDao() {
        return spaDao;
    }

    public void setSpaDao(SpaDao spaDao) {
        this.spaDao = spaDao;
    }
}
