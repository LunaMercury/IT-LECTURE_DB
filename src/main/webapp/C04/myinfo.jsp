<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!
	public static boolean isConfirm; // false가 기본값
%>

<%
	System.out.println("myinfo.jsp 실행");

	boolean isConfirm = (request.getAttribute("isConfirm")!=null)?(boolean)request.getAttribute("isConfirm"):null;
	
	String userid = request.getParameter("userid");
	if(isConfirm!=null && isConfirm==true){
		; // 현재 위치에서 내용 표시
		request.getAttribute("");/////////////
	} else{
		request.setAttribute("userid", userid);
		request.setAttribute("url", "/myinfo");	// DB 요청 처리
		request.setAttribute("serviceNo", 2);	// ServiceNo. C:1,R:2,U:3,D:4
		request.getRequestDispatcher("./validationCheck.jsp").forward(request, response);	
	}
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<h1>MYINFO 확인(dbUtils's 로부터 Forwarding 처리된 화면)</h1>
</body>
</html>