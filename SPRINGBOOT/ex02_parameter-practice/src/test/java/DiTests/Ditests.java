package DiTests;


import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.config.PersonComponent;
import com.example.app.domain.dto.PersonDto;

@ExtendWith(SpringExtension.class) // 이게 없으면 null 값이 된다
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 불러올 위치
public class Ditests {
	
	@Autowired
	private PersonDto personDto1;
	
	@Autowired
	private PersonDto personDto2;
	
	@Autowired
	private PersonDto person03;
	
	@Autowired
	private PersonDto personBean;
	
	@Autowired
	private PersonDto person05;
	
	@Autowired
	private PersonComponent personComponent;

	@Test
	@Disabled
	public void test() {
		System.out.println(personDto1);
		System.out.println(personDto2);
		System.out.println(person03);
		System.out.println(personBean);
		System.out.println(person05);
		System.out.println(personComponent);
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void test2() {
		
		assertNotNull(applicationContext);		
		System.out.println(applicationContext.getBean("personDto1"));
	}

}
