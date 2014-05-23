package in.thousandspas.dao;

import in.thousandspas.common.CommonException;

import org.hibernate.SessionFactory;


public interface BaseDao {
	public Object save(Object obj) throws CommonException;
	public Object readById(int id) throws CommonException;
	public void update(Object obj) throws CommonException;
	public void delete(Object obj) throws CommonException;
	
	public SessionFactory getSessionFactory();
}
