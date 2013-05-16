package pl.agh.edu.carecenter.server.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void save(T entity);
	T getById(Integer id, Class<T> cls);
	List<?> list(Class<?> cls);
	void remove(T entity);

}
