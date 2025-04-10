<%@page import="java.nio.channels.Selector"%>
<%@page import="java.awt.Insets"%>
<%@page import="C04.UserDto"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 속성/기능추가 -->
<%!
	// 속성/기능 추가가능
	
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
	private int insert(UserDto userDto) throws Exception{
		pstmt = conn.prepareStatement("INSERT INTO TBL_USER Values(?,?,?)");
		pstmt.setString(1, userDto.getUserid());
		pstmt.setString(2, userDto.getPassword());
		pstmt.setString(3, userDto.getRole());
		int result =pstmt.executeUpdate();
		conn.commit();
		pstmt.close();		
		return result;		
	}
	
	private UserDto selectOne(String userid) throws Exception{
		pstmt=conn.prepareStatement("select * from USER_TBL where userid='"+userid+"'");		
		rs = pstmt.executeQuery();
		UserDto userDto = null;
		if(rs!=null){
			if(rs.next()){
				userDto.setUserid(rs.getString("userid"));
				userDto.setPassword(rs.getString("password"));
				userDto.setRole(rs.getString("role"));
			}
		}
		return userDto;
	}
%>
<!-- Service 함수 -->
<%
	/* 요청 정보 확인 */
	String url = (String)request.getAttribute("url");
	Integer serviceNo = (Integer)request.getAttribute("serviceNo");
	System.out.println("URL : " + url);
	System.out.println("SERVICE NO : " + serviceNo);
	
	
	if(url.contains("/join")){	
		getConnection();
		UserDto userDto = (UserDto)request.getAttribute("userDto");
		
		if(insert(userDto)>0){
			response.sendRedirect("login_form.jsp");
			return;
		}
	}
	if(url.contains("/myinfo")){
		getConnection();
		String userid = (String)request.getAttribute("userid");
		UserDto userDto = selectOne(userid);
		if(userDto != null){
			request.setAttribute("myinfo-result", userDto);
			request.setAttribute("isConfirm", true);
			request.getRequestDispatcher("./myinfo.jsp").forward(request, response);
			return;
		}		
	}	
	
%>