<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.userid{${userid_style}}
	.password{${password_style}}
</style>
</head>
<body>

	<h1>회원가입 페이지</h1>
	
	<form action="join.jsp" method="post">
		<div>
			<label for="">아이디 : </label><span>${ userid_msg } </span> <br />
			<input type="text" name="userid" class="userid" />
		</div>
		<div>
			<label for="">패스워드 :</label><span>${ password_msg }</span> <br />
			<input type="text" name="password" class="password" /> <br />
		</div>
		<div>
			<button>회원가입</button>
		</div>	

</body>
</html>