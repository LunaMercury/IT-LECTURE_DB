<!-- 포워딩 처리 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	System.out.println("------------02PAGE--------------");
	System.out.println("username : " + username);
	System.out.println("password : " + password);
	System.out.println("--------------------------------");
	
	request.setAttribute("02Page", "02Page's Value");
	
	/* FORWARDING */
	/* 이로 인해 받아온 데이터를 가지고 03Page로 이동한다 */
	/* url 보면 링크는 Page02이나, 내부적으로는 Page03이다 */
	request.getRequestDispatcher("./03Page.jsp").forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>02PAGE</h1>
	USERNAME : <%=username %> <br />
	PASSWORD : <%=password %> <br />

</body>
</html>