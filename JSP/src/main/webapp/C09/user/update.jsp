<%@page import="C04.UserDto"%>
<%@page import="C09.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	String role = request.getParameter("role");
	
	int result = DBUtils.getInstance().updateUser(new UserDto(userid, password, role));
	if(result>0){
		out.println("<script>alert('update 성공'); location.href='./selectAll.jsp'</script>");
	} else {
		out.println("<script>alert('update 실패'); location.href='javascript:history.go(-1)'</script>");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>