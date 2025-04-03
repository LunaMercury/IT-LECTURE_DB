<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("validationCheck.jsp 실행");
	
	String url = (String)request.getAttribute("url");
	Integer serviceNo = (Integer)request.getAttribute("serviceNo");
	
	UserDto userDto = (UserDto)request.getAttribute("userDto"); // request에서 UserDto 객체 가져오기
	
	
	if(url.contains("/join")){
		 
	}

%>

<%!
	void isValid(){
	System.out.println("[VALIDATION CHECK]");
	
	if(userid==null){
		
	}
	}
%>