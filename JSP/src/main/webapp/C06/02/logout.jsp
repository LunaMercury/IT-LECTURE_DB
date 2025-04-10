<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	session.invalidate(); // 세션 초기화
	out.println("<script> alert('로그아웃 성공! login page로 이동합니다.'); location.href='login_form.jsp?message=logout_Successful'</script>");
%>