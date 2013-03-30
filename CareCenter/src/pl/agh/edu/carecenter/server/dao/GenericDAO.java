package pl.agh.edu.carecenter.server.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void save(T entity);
	List<T> listAll(Class<T> cls);
	T getById(Integer id, Class<T> cls);

}
