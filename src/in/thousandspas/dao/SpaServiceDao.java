package in.thousandspas.dao;

import in.thousandspas.common.CommonException;
import in.thousandspas.domain.SpaService;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class SpaServiceDao implements BaseDao{
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Object obj) throws CommonException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Object obj) throws CommonException {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public SessionFactory getSessionFactory() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object getSpaService(Integer spaid, Integer serviceId) throws CommonException {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(SpaService.class);
        criteria.add(Restrictions.eq("spaid", spaid));
        criteria.add(Restrictions.eq("serviceid", serviceId));
        try {
            return criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new CommonException();
        }
    }
}
