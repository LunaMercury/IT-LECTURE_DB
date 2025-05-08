package Dbtest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.club_matching.app.domain.dto.UserDto;
import com.club_matching.app.domain.mapper.UserMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class MybatisTests_memo {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private UserMapper userMapper;

	@Test
	@Disabled
	void test() {
		assertNotNull(sqlSessionFactory);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		assertNotNull(sqlSession);
	}

	@Test
//	@Disabled
	void t1() {
		UserDto dto = new UserDto("john", "JJ", "1234", "john@name.com", "ROLE_USER");
		userMapper.insertXml(dto);
		System.out.println("RESULT : " + dto);
	}

}