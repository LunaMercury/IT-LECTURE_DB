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
import org.springframework.jdbc.datasource.lookup.IsolationLevelDataSourceRouter;
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
	private DataSource dataSource2;
	
	@Autowired
	private DataSource dataSource3;

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
	@Disabled
	void test2() throws Exception {
		System.out.println(dataSource2);
		memoDaoImpl.insert(new MemoDto(1, "a", "a", LocalDateTime.now(), null));
	}
	
	@Test
	@Disabled
	void test3() throws Exception{
		System.out.println(dataSource2);
		Connection conn = dataSource2.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_book value(?,?,?,?)");
		pstmt.setString(1, "abbb");
		pstmt.setString(2, "datasource2 test");
		pstmt.setString(3, "gggg");
		pstmt.setString(4, "ssss");

		pstmt.executeUpdate();		
	}
	
	@Test
	void test4() throws Exception{
		System.out.println(dataSource3);
		Connection conn = dataSource3.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_book value(?,?,?,?)");
		pstmt.setString(1, "abcc");
		pstmt.setString(2, "datasource3 hikari test");
		pstmt.setString(3, "gggg");
		pstmt.setString(4, "ssss");

		pstmt.executeUpdate();		
	}
	
}