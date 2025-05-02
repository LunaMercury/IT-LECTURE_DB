package TxTests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

@ExtendWith(SpringExtension.class) // 이게 없으면 null 값이 된다
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // root-context를 불러온다
class Txtests {
	
	@Autowired
	private MemoServiceImpl memoService;

	@Test
	void test() {
		memoService.addMemoTx(new MemoDto(9090, "a", "a", LocalDateTime.now()));
	}
}