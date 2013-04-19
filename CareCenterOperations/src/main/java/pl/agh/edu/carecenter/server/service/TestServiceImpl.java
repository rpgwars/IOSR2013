package pl.agh.edu.carecenter.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.agh.edu.carecenter.server.dao.TestDao;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	TestDao dao; 
	
	@Override
	public void test() {
		
		dao.test();
		
	}

}
