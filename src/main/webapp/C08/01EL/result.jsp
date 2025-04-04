<%@page import="C04.UserDto"%>
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
	pageContext.setAttribute("P_ATTR", "P_ATTR_VALUE");
	request.setAttribute("R_ATTR", "R_ATTR_VALUE");
	session.setAttribute("S_ATTR", "S_ATTR_VALUE");
	application.setAttribute("A_ATTR", "A_ATTR_VALUE");
	
	pageContext.setAttribute("DUP", "PAGECONTEXT_VALUE");
	request.setAttribute("DUP", "REQUEST_VALUE");
	session.setAttribute("DUP", "SESSION_VALUE");
	application.setAttribute("DUP", "APPLICATION_VALUE");
	

	
%>

	<!-- 
		EL(Expression Language) : 내장 객체의 Scope 참조
		pageScope : 현재 JSP 페이지 내에서만 사용가능한 속성/기능
			(관련 내장객체 : pageContext)
		requestScope : 하나의 요청 처리동안
			(관련 내장객체 : request)
		sessionScope : 일정기간 동안(지정된 혹은 기본값)
			(관련 내장객체 : session)
		applicationScope : 서버종료 동안
			(관련 내장객체 : application)
	 -->

	<h1>RESULT</h1>
	
	<!-- EL:PARAM -->
	USERNAME : ${ param.username } <br />
	PASSWORD : ${ param.password } <br />
	<hr />
	
	<!-- EL : ATTRIBUTE -->
	PAGECONTEXT'S ATTR : ${ P_ATTR } <br />
	PAGECONTEXT'S ATTR : ${ pageScope.P_ATTR } <br />
	REQUEST'S ATTR : ${ R_ATTR } <br />
	REQUEST'S ATTR : ${ requestScope.R_ATTR } <br />
	SESSION'S ATTR : ${ S_ATTR } <br />
	SESSION'S ATTR : ${ sessionScope.S_ATTR } <br />
	<hr />
	
	<!-- EL : ATTRIBUTE(중복값) -->
	DUPLICATED VALUE : 	${ DUP } <br />
	<hr />
	
	<!-- EL : OBJECT -->
	
	<%
		UserDto userDto = new UserDto("user1", "1234", "ROLE_USER");
		request.setAttribute("userDto", userDto);
	%>
	표현식 : <%=userDto.getUserid() %> <br /> <!-- 이건 위 스크립틀릿 없어도 가능 -->
	EL 표현식 : ${ userDto.userid } <br /> <!-- 위 스크립틀릿처럼 정의되어있어야 가능 -->
	<hr />
	
	<!-- EL 연산자 -->
	연산 : <%= 1 + "1" %> <br />
	EL 표현 : ${ 1+"1" } <br />
	<hr />
	
	<!-- NULL CHECK -->
	NULL : ${ null } <br />
	empty NULL : ${ empty null } <br /> <!-- empty null 은 null이 맞는지 체크하는 기능 -->
	
	empty null : ${ empty TEST } <br />
	empty not null : ${ !empty TEST } <br />
	
	
</body>
</html>