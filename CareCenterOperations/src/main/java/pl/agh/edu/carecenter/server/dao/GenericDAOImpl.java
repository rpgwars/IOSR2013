package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	@Autowired
	private SessionFactory sessionFactory; 
	
	
	@Override
	@Transactional
	public void save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public T getById(Integer id, Class<T> cls) {
		 
		
		Criteria byIdCriteria = sessionFactory.getCurrentSession().createCriteria(cls);
		byIdCriteria.add(Restrictions.eq("id",id));
		return (T)byIdCriteria.uniqueResult();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<?> list(Class<?> cls){
		Criteria listCriteria = sessionFactory.getCurrentSession().createCriteria(cls);
		return listCriteria.list(); 
	}

}
