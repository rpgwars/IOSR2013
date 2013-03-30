package pl.agh.edu.carecenter.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pl.agh.edu.carecenter.server.domain.Doctor;

@Repository
public class DoctorDAOImpl extends GenericDAOImpl<Doctor> implements DoctorDAO {

	private static final List<String> degreesList = new ArrayList<String>();
	
	static{
		degreesList.add("bachelor");
		degreesList.add("doc.");
	}
	
	@Override
	public List<String> getDegrees() {
		
		return degreesList;
	}

}
