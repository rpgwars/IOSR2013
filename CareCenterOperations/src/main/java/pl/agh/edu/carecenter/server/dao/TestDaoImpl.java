package pl.agh.edu.carecenter.server.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class TestDaoImpl implements TestDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	@Override
	public void test() {

		sessionFactory.getCurrentSession().createQuery("from TestDomainObject").uniqueResult();
		
	}

}
