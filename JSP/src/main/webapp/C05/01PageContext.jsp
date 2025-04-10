<!-- 
	세션이란?
	사용자가 설정한 일정 시간동안 데이터를 유지하는 기능.
	홈페이지에 로그인 후 일정 시간이 지나면 자동로그아웃 되는 프로그램.
 -->

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
		System.out.println("pageContext : " + pageContext);
		System.out.println("pageContext's get request : " + pageContext.getRequest());
		System.out.println("pageContext's get response : " + pageContext.getResponse());
		System.out.println("pageContext's get session : " + pageContext.getSession());
		System.out.println("pageContext's get application : " + pageContext.getServletContext());
		
		/* 프로젝트 경로 확인 */
		System.out.println("project path : " + pageContext.getServletContext().getContextPath());
		

	%>
		<!-- 표현식 -->
		PROJECTPATH : <%=pageContext.getServletContext().getContextPath() %>
		<hr />
		<!-- EL, 이걸 더 많이 씀 -->
		PROJECTPATH(EL) : ${ pageContext.request.contextPath }
</body>
</html>