<%@page import="C03.C03SimpleDto"%>
<%@page import="java.util.Scanner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Scanner sc = new Scanner(System.in);

C03SimpleDto dto = new C03SimpleDto("홍길동", 55, "대구");

sc.close();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
name : <%=dto.getName() %> <br />
age : <%=dto.getAge() %> <br />
addr : <%=dto.getAddr() %> <br />

</body>
</html>