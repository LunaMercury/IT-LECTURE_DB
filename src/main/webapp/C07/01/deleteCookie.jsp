<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String cookieName = request.getParameter("cookieName");
	if(cookieName != null){
		Cookie cookie = new Cookie(cookieName,""); // 빈 Value의 쿠키 생성
		cookie.setMaxAge(0);		// 쿠키유지시간 0초로 설정
		//cookie.setPath("/");		// 쿠키적용 URL 설정 (/:모든영역)
		response.addCookie(cookie);	// 만료처리 전달
		System.out.println(cookie.getName() + " 삭제 완료");
	}
	
	// 만료쿠키 전달 후 다음위치
	out.print("<script>alert('" + cookieName + "'+' 쿠키삭제완료');location.href='getCookie.jsp';</script>");	
%>