<%@page import="C06.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	System.out.println("Join.jsp 실행");

	//userDto 객체 생성
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	
	// 파라미터 유효성 체크
	if(userid.isEmpty()){
		request.setAttribute("userid_msg","아이디를 입력하세요");
		request.setAttribute("userid_style","background-color: yellow; border: 1px solid red;");
	}
	if(password.isEmpty()){
		request.setAttribute("password_msg","비밀번호를 입력하세요");
		request.setAttribute("password_style","background-color: yellow; border: 1px solid red;");
	}
	
	if(userid.isEmpty() || password.isEmpty()){
		request.getRequestDispatcher("./join_form.jsp").forward(request, response);
		return;
	}
	// 유효성 체크 이후 userDto에 정보 담기
	UserDto userDto = new UserDto(userid, password, 123);
	request.setAttribute("userDto", userDto);
	
	// DB에 기존 id가 있는지 확인하기 위해 준비
	request.setAttribute("url", "/join");
	request.setAttribute("serviceNo", 1);	// ServiceNo. C:1,R:2,U:3,D:4
	
	// forwarding
	request.getRequestDispatcher("./validationCheck.jsp").forward(request, response);	
	
%>