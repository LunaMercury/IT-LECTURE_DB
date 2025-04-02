<%@page import="C04.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./errors.jsp" %>

<%
	System.out.println("validationCheck.jsp 실행");	
	// request로부터 전달받은 파라미터 꺼내서 System.out 확인
	// 받은 username,password 값이 null인지여부를 체크(String API trim()  + isEmpty() 이용)
	// 에러 발생 시 처리되는 페이지로 전달(error.jsp)
	
	// url 선별 -> serviceNo 선별 -> 유효성 체크
	
	String url = (String)request.getAttribute("url");
	Integer serviceNo = (Integer)request.getAttribute("serviceNo");
	
	if(url.contains("/join")){
		// /join
		UserDto userDto = (UserDto)request.getAttribute("userDto");
		isValid(userDto);
	} else if(url.contains("/myinfo")){	
		// /myinfo
		String userid = (String)request.getAttribute("userid");
		isValid(userid);
	}
	request.getRequestDispatcher("./dbUtils.jsp").forward(request, response);
%>

<%!
	void isValid(UserDto userDto) throws Exception{	
		System.out.println("[VALIDATION CHECK] userDto : "+userDto);
		if(userDto==null)
			throw new Exception("유저 정보가 없습니다");;
		if(userDto.getUserid().trim().isEmpty())
			throw new Exception("아이디를 입력하세요");
		if(userDto.getPassword().trim().isEmpty())
			throw new Exception("비밀번호를 입력하세요");
		if(userDto.getRole().trim().isEmpty())
			throw new Exception("기본 역할(ROLE_USER)이 지정되지 않았습니다");		
	}

	void isValid(String userid) throws Exception{
		if(userid.trim().isEmpty())
			throw new Exception("userid를 입력하세요");
	}
%>