package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MysqlDbUtils {

	private String url = "jdbc:mysql://localhost/testDB";
	private String id = "root";
	private String pw = "1234";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// DataSource Resource 연결
	// xml에 설정한 값을 이렇게 받는다.
	private DataSource dataSource;

	// 싱글톤
	private static MysqlDbUtils instance;

	private MysqlDbUtils() throws Exception {
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		conn = DriverManager.getConnection(url, id, pw);
		
		// 나중에 배움
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup("jdbc/MysqlDB");
		conn = dataSource.getConnection();
		System.out.println("Connection : " + conn);
	}

	public static MysqlDbUtils getInstance() throws Exception {
		if (instance == null)
			instance = new MysqlDbUtils();
		return instance;
	}

	public int insert(UserDto userDto) throws SQLException {
		pstmt = conn.prepareStatement("INSERT INTO tbl_user values(?,?,?)");
		pstmt.setString(1, userDto.getUsername());
		pstmt.setString(2, userDto.getPassword());
		pstmt.setString(3, userDto.getRole());
		int result = pstmt.executeUpdate();

		pstmt.close();

		return result;
	}

	public UserDto selectOne(String username) throws Exception {
		pstmt = conn.prepareStatement("select * from tbl_user where username=?");
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		UserDto userDto = null;
		if (rs != null) {
			rs.next();
			userDto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3));

		}
		return userDto;
	}

}
