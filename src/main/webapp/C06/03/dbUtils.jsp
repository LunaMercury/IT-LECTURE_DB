<%@page import="C06.UserDto"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String id="system";
	private String pw="1234";	
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private void getConnection() throws Exception{
		if(conn == null)
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			conn = DriverManager.getConnection(url, id, pw);		
	}
	
	private int insert(UserDto userDto){
		pstmt = conn.prepareStatement("INSERT INTO TBL_USER Values(?,?,?)");
		pstmt.setString(1, userDto.getUserid());
		pstmt.setString(2, userDto.getPassword());
		int result = pstmt.executeUpdate();
		conn.commit();
		pstmt.close();
		return result;
	}
%>