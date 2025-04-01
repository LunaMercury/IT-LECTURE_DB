<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
/* request 내장객체 - 요청정보값 저장(HTTP Request Protocol의 구조형태) */

	/* 한글 문자가 깨지는 것을 방지하기 위한 문자셋 설정 */
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String bgColor = request.getParameter("bgcolor");
	System.out.printf("%s, %s, %s \n", username, password, bgColor);
%>

<%--<!DOCTYPE html>
 <html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- 표현식으로 처리 -->
<body style="background-color:<%=bgColor.equals("")? "gray":bgColor %> ">

username : <%=username%>
<hr/>
password : <%=password%>
<hr />
bgcolor : <%=bgColor%>

</body>
</html>  --%>


<!-- EL 처리 -->
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body style="background-color:${param.bgcolor}">

	EL_USERNAME : ${param.username} <br />
	EL_PASSWORD : ${param.password} <br />
	
</body>
</html>