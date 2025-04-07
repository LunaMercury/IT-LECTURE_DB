package C09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import C04.UserDto;

public class DBUtils {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "system";
	private String pw = "1234";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 싱글톤
	private static DBUtils instance;
	private DBUtils() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
	}

	public static DBUtils getInstance() throws Exception {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

	// 회원가입
	public int insertUser(UserDto userDto) throws Exception {
		pstmt = conn.prepareStatement("INSERT INTO tbl_user VALUES(?,?,?)");
		pstmt.setString(1, userDto.getUserid());
		pstmt.setString(2, userDto.getPassword());
		pstmt.setString(3, userDto.getRole());
		int result = pstmt.executeUpdate();

		conn.commit();
		pstmt.close();
		return result;
	}

	public List<UserDto> selectAllUser() throws Exception {
		List<UserDto> list = new ArrayList<>();
		pstmt = conn.prepareStatement("select * from TBL_USER order by USERID asc");
		rs = pstmt.executeQuery();

		if (rs != null) {
			while (rs.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserid(rs.getString("userid"));
				userDto.setPassword(rs.getString("password"));
				userDto.setRole(rs.getString("role"));
				list.add(userDto);
			}
		}
		rs.close();
		pstmt.close();
		return list;
	}

	public UserDto selectOneUser(String userid) throws Exception {
		pstmt = conn.prepareStatement("select * from TBL_USER where userid=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		UserDto userDto = null;
		if (rs.next()) {
			userDto = new UserDto();
			userDto.setUserid(rs.getString("userid"));
			userDto.setPassword(rs.getString("password"));
			userDto.setRole(rs.getString("role"));
		}
		rs.close();
		pstmt.close();
		return userDto;
	}
	
	public int updateUser(UserDto userDto) throws Exception {
		pstmt = conn.prepareStatement("UPDATE tbl_user set password=?, role=? WHERE userid=?");
		pstmt.setString(1, userDto.getPassword());
		pstmt.setString(2, userDto.getRole());
		pstmt.setString(3, userDto.getUserid());
		int result = pstmt.executeUpdate();

		conn.commit();
		pstmt.close();
		return result;
	}
	
	public int deleteUser(String userId) throws Exception {		
		pstmt = conn.prepareStatement("DELETE tbl_user WHERE userid=?");
		pstmt.setString(1, userId);
		int result = pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();
		return result;
	}
	
	public List<OrderDto> selectAllOrder() throws Exception {
	    String sql = "SELECT category, SUM(price * quantity) AS total_sum FROM tbl_order " +
	                 "GROUP BY category " +
	                 "HAVING SUM(price * quantity) >= 50000 " +
	                 "ORDER BY total_sum DESC";
	    List<OrderDto> list = new ArrayList<>();
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();

	    if (rs != null) {
	        while (rs.next()) {
	            OrderDto orderDto = new OrderDto();
	            orderDto.setCategory(rs.getString("category"));
	            orderDto.setSum(rs.getInt("total_sum"));
	            list.add(orderDto);
	        }
	    }
	    if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	    if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	    return list;
	}
	
	public List<OrderDto2> selectAll_2() throws SQLException{
		String sql = "select u.addr as addr, o.order_date as order_date, sum(o.price*o.quantity) as sum, round(avg(o.price*o.quantity),2) as avg "
				+ "from tbl_user u "
				+ "JOIN tbl_order o "
				+ "on u.userid=o.userid "
				+ "GROUP BY u.addr, o.order_date "
				+ "order by u.addr asc, sum(o.price*o.quantity)";
		List<OrderDto2> list = new ArrayList<>();
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    
	    if (rs != null) {
	    	while(rs.next()) {
	    		OrderDto2 orderDto2 = new OrderDto2();
	    		orderDto2.setAddr(rs.getString("addr"));
	    		LocalDate date = rs.getDate("order_date").toLocalDate(); // java.sql.Date 객체로 받음
	    		orderDto2.setSum(rs.getInt("sum"));
	    		orderDto2.setAverage(rs.getDouble("avg"));
	    		orderDto2.setOrder_Date(date);
	    		list.add(orderDto2);
	    		
	    	}
	    }
	    
	    if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	    if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		return list;
	}

}
