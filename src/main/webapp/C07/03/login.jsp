<%@page import="java.lang.reflect.AccessFlag.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String idSave = request.getParameter("idSave");
	String pwSave = request.getParameter("pwSave");
	System.out.println("idsave : "+idSave);
	System.out.println("pwsave : "+pwSave);

	// 파라미터 유효성 체크
	if(username.isEmpty()) {
		// out.println("<script>alert('username을 입력하세요');location.href='./login_form.jsp'</script>");
		request.setAttribute("username_msg","username을 입력하세요");
		
	} else if(password.isEmpty()) {
		// out.println("<script>alert('password를 입력하세요');location.href='./login_form.jsp'</script>");
		request.setAttribute("password_msg","password를 입력하세요");
	}
	
	if(username.isEmpty() || password.isEmpty()){
		request.getRequestDispatcher("./login_form.jsp").forward(request, response);
		return;
	}
	
	// 01 ID 확인(DB조회 - 여기서는 생략)
	if(!username.equals("admin")){
		request.setAttribute("username_msg", "해당 아이디는 존재하지 않습니다.");
		request.getRequestDispatcher("./login_form.jsp").forward(request, response);
	}	
	// 02 PW 확인(일치여부 확인)
	if(!password.equals("1234")){
		request.setAttribute("password_msg", "패스워드가 일치하지 않습니다.");
		request.getRequestDispatcher("./login_form.jsp").forward(request, response);
	}
	
	// 03 사용자 상태정보(인증됨)를 Session에 저장
	session.setAttribute("isAuth", true);
	session.setAttribute("role", "ROLE_ADMIN");
	session.setMaxInactiveInterval(30); // 30초. 이 코드가 없으면 기본 30분
	
	// 쿠키 설정
	// 아이디
	Cookie idRemeberCookie = new Cookie("username", username);
	idRemeberCookie.setPath("/01JSP/C07/03/login_form.jsp");
	Cookie idchk;
	if(idSave!=null){
		idRemeberCookie.setMaxAge(60*5);
		idchk = new Cookie("idchk","checked");	
		idchk.setMaxAge(60*5);
	} else {
		idRemeberCookie.setMaxAge(0);		
		idchk = new Cookie("idchk",null);
		idchk.setMaxAge(0);
	}
	idchk.setPath("/01JSP/C07/03/login_form.jsp");
	response.addCookie(idRemeberCookie);
	response.addCookie(idchk);
	
	// 비밀번호
	Cookie pwRememberCookie = new Cookie("password", password);
	pwRememberCookie.setPath("/01JSP/C07/03/login_form.jsp");
	Cookie pwchk;
	if(pwSave!=null){
		pwRememberCookie.setMaxAge(60*5);
		pwchk = new Cookie("pwchk","checked");
		pwchk.setMaxAge(60*5);
	} else{
		pwRememberCookie.setMaxAge(0);
		pwchk = new Cookie("pwchk", null);
		pwchk.setMaxAge(0);		
	}
	pwchk.setPath("/01JSP/C07/03/login_form.jsp");
	response.addCookie(pwRememberCookie);
	response.addCookie(pwchk);
	
	// 04 로그인 처리 후 MAIN PAGE에 REDIRECT
	out.println("<script> alert('로그인 성공! Mainpage로 이동'); location.href='main.jsp'</script>");
%>