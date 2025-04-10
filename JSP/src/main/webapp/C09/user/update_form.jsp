<%@page import="C04.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="C09.DBUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userid = request.getParameter("userid");

	DBUtils dbUtils = DBUtils.getInstance();
	UserDto userDto = dbUtils.selectOneUser(userid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
	1. dbutils 에서 UserDto selectOneUser(String usrid) 를 받아와서 단건 조회가 가능하도록 코드 생성
	2. selectOneUser 함수를 이용해서 해당 사용자 정보 받아와서 form>input에 각각 표시\
	3. dbutils에서 int updateUser(UserDto userDto)를 생성 -> Update 처리 코드
	4. update_form.jsp 에서 수정요청 버튼 클릭하면 ./update.jsp에서 업데이트 처리
	5. 처리완료 이후 selectAll.jsp로 이동
 -->
	<h1>사용자 정보 수정</h1>
	
	<form action="./update.jsp" method="post">
		<input type="text" name="userid" value="<%=userDto.getUserid() %>"> <br />
		패스워드: <input type="text" name="password" value="<%=userDto.getPassword() %>"> <br />
		역할: <input type="text" name="role" value="<%=userDto.getRole() %>"> <br />
		<button>수정 요청</button>
		<a href="javascript:history.go(-1)">이전으로</a>
	</form>


</body>
</html>