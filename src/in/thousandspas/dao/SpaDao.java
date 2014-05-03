package in.thousandspas.dao;

import in.thousandspas.common.CommonException;
import in.thousandspas.domain.Spa;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class SpaDao implements BaseDao{
private SessionFactory sessionFactory;
	
	public Object save(Object obj) throws CommonException{
		Object newObj = sessionFactory.getCurrentSession().save(obj);
		if(newObj != null) {
			return newObj;
		}
		else
			throw new CommonException();

	}
	public void update(Object obj) throws CommonException{
		sessionFactory.getCurrentSession().update(obj);
	}
	public Object delete(Object obj) throws CommonException{
		return null;
	}	
	
	public Object readById(int id) throws CommonException{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Spa.class);
			criteria.add(Restrictions.eq("spaid", id));
		try{
			return criteria.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new CommonException();
		}
	}
	
	public Object readByEmail(String email) throws CommonException{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Spa.class);
			criteria.add(Restrictions.eq("email", email));
		try{
			return criteria.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new CommonException();
		}
	}
	
	public Object validateSpa(String email, String password) throws CommonException{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Spa.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.eq("password", password));
		try{
			return criteria.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new CommonException();
		}
	}
	
	public Object doesTheEmailExist(String email) throws CommonException{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Spa.class);
			criteria.add(Restrictions.eq("email", email));
		try{
			return criteria.uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new CommonException();
		}
	}	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
