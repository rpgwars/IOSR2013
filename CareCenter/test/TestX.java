import org.junit.Before;
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.agh.edu.carecenter.server.service.AccountService;
import pl.agh.edu.carecenter.server.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestX {
	
	@Autowired
	private DoctorService doctorService; 
	
	@Autowired
	private AccountService accountService; 
	
	
	@Before
	public void bef(){
		System.out.println("before");
	}
	
	@Test
	public void tt(){
		System.out.println("tt");
		System.out.println(doctorService.getDegreeList().size());
		System.out.println(accountService.listDoctors().size());
	}

}
