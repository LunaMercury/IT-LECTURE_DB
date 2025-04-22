package ditest;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.config.PersonConponent;
import com.example.app.config.PersonDtoBeanConfig;
import com.example.app.domain.dto.PersonDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class DiTests {
	
	// @Autowired 는 ~~~.getInstance() 와 유사
	@Autowired
	private PersonDto personDto1;
	@Autowired
	private PersonDto personDto2;
//	@Autowired
//	private PersonDto personDto3;
	@Autowired
	private PersonDtoBeanConfig personBean;
	@Autowired
	private PersonConponent personConponent;

	@Test
	public void test() {
//		fail("Not yet implemented");		
		System.out.println(personDto1);
		System.out.println(personDto2);
//		System.out.println(personDto3);
		System.out.println(personBean);
		System.out.println(personConponent);		
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void test2() {
		assertNotNull(applicationContext);
		System.out.println(applicationContext.getBean("personDto1"));
	}
}
