<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	/* 02Request.jsp 에 정보 보내기 */
	// response.sendRedirect("./02Request.jsp");
	
	/* TIMEOUT 페이지 */
	// response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT);
	
	/* 404 ERROR */
	// response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 페이지를 찾을 수 없습니다.");
	
	/* 403 ERROR */
	// response.sendError(HttpServletResponse.SC_FORBIDDEN,"접근금지됨");
	
	/* 5xx ERROR */
	// response.sendError(HttpServletResponse.SC_BAD_GATEWAY,"서버장애발생");
	
	/* 자동 새로고침 기능 */
	// response.setIntHeader("Refresh", 3); // 3초마다 새로고침
	
	/* OutStream 추출 */
/* 	ServletOutputStream bout = response.getOutputStream();
	bout.write('a');
	bout.write(98);
	bout.flush();
	bout.close(); */
	
	PrintWriter o = response.getWriter();
	o.write("<h1>HELLO WORLD</h1>");
	
%>
	<%@page import="java.util.*" %>
	<%=new Date() %>

</body>
</html>