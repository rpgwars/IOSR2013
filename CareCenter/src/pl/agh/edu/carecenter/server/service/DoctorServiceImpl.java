package pl.agh.edu.carecenter.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.agh.edu.carecenter.server.dao.DoctorDAO;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDAO doctorDao; 
	
	@Override
	public List<String> getDegreeList() {
		return doctorDao.getDegrees();
	}

}
