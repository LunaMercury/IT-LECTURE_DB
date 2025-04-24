package DbTests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dao.MemoDaoImpl;
import com.example.app.domain.dto.MemoDto;

@ExtendWith(SpringExtension.class) // 이게 없으면 null 값이 된다
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // root-context를 불러온다
class DataSourceTests {

	@Autowired
	private DataSource dataSource1;

	@Autowired
	private MemoDaoImpl memoDaoImpl;

	@Test
	@Disabled
	void test() throws SQLException {
		System.out.println(dataSource1);
		Connection conn = dataSource1.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_book value(?,?,?,?)");
		pstmt.setString(1, "abcd");
		pstmt.setString(2, "abcd");
		pstmt.setString(3, "abcd");
		pstmt.setString(4, "abcd");

		pstmt.executeUpdate();
	}

	@Test
	void test2() throws Exception {
		memoDaoImpl.insert(new MemoDto(1, "a", "a", LocalDateTime.now(), null));
	}
}