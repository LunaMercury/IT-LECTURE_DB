<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String col1 = request.getParameter("col1");
	String col2 = request.getParameter("col2");
	String col3 = request.getParameter("col3");
	String col4 = request.getParameter("col4");
	String style = request.getParameter("style");
	
	String navStyle = (style != null && !style.isEmpty()) ? style : "background-color:#f0f0f0; padding: 10px;";	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
</head>

<style>
	body {
		<%=navStyle%>
	}
</style>
<body>
	<nav>${param.col1}</nav>
	<nav>${param.col2}</nav>
	<nav><%=col3 %></nav>
	<nav><%=col4 %></nav>
</body>
</html>