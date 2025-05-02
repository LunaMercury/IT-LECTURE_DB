package DbTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class MybatisTests_memo {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// 이 코드로 MemoMapper를 불러온다.
	@Autowired
	private MemoMapper memoMapper;
	
	// 단순 DB 연결 테스트를 위한 코드이다.
	@Test
	@Disabled
	void test() {
		assertNotNull(sqlSessionFactory);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		assertNotNull(sqlSession);		
	}
	@Test
	@Disabled
	void t1() {
//		memoMapper.insert(new MemoDto(1010, "a", "a@naver.com", LocalDateTime.now(), null));
//		memoMapper.insert(new MemoDto(1011, "b", "b@naver.com", LocalDateTime.now(), null));
		
		MemoDto dto = new MemoDto(null, "b", "b@naver.com", LocalDateTime.now());
		memoMapper.insert(dto);
		System.out.println("RESULT : " + dto);
	}
	
	@Test
	@Disabled
	void t2() {
		memoMapper.update(new MemoDto(1010, "b", "b@naver.com", LocalDateTime.now()));
	}
	
	@Test
	@Disabled
	void t3() {
		memoMapper.delete(1010);
	}
	
	@Test
	@Disabled
	void t4() {
		System.out.println("t4 : "+ memoMapper.selectAt(1010));
	}
	
	@Test
	@Disabled
	void t5() {
		List<MemoDto> list = memoMapper.selectAll();
		for (MemoDto memoDto : list) {
			System.out.println(memoDto);
		}
	}
	
	@Test
	@Disabled
	void t6() {
		List<Map<String, Object>> list = memoMapper.selectAllResultMap();
		list.forEach(System.out::println);
	}
	
	// XML 테스트
	@Test
	@Disabled
	void x1() {
		memoMapper.insertXml(new MemoDto(2010, "x1", "x1@naver.com", LocalDateTime.now()));		
	}
	
	@Test
	@Disabled
	void x2() {
		List<Map<String, Object>> list = memoMapper.selectAllResultMapXml();
		list.forEach(System.out::println);
	}
	
	@Test
	@Disabled
	void t7() {
		Map<String, Object> param = new HashMap<>();
		param.put("type", "text");		
		param.put("keyword", "b");
		List<Map<String, Object>> response = memoMapper.Select_if_xml(param);
		response.forEach(System.out::println);
	}
	
	@Test
	void t8() {
		Map<String, Object> param = new HashMap<>();
		param.put("type", "id");		
		param.put("keyword", "11");
		List<Map<String, Object>> response = memoMapper.Select_when_xml(param);
		response.forEach(System.out::println);		
	}
}