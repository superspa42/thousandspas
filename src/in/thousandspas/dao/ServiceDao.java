package in.thousandspas.dao;

import in.thousandspas.common.CommonException;
import in.thousandspas.domain.Service;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class ServiceDao implements BaseDao {
    private SessionFactory sessionFactory;

    @Override
    public Object save(Object obj) throws CommonException {
        Object newObj = sessionFactory.getCurrentSession().save(obj);
        if (newObj != null) {
            return newObj;
        } else
            throw new CommonException();
    }

    @Override
    public Object readById(int id) throws CommonException {
        return null;
    }

    @Override
    public void update(Object obj) throws CommonException {
    }

    @Override
    public void delete(Object obj) throws CommonException {
    }

    @Override
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object getServiceByName(String serviceName) throws CommonException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Service.class);
        criteria.add(Restrictions.eq("serviceName", serviceName));
        try {
            return criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new CommonException();
        }
    }

    public Object doesServiceExists(String serviceName) throws CommonException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Service.class);
        criteria.add(Restrictions.eq("serviceName", serviceName));
        try {
            return criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new CommonException();
        }
    }
}
