<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<form action="${ pageContext.request.contextPath }/user/create" method="post">
		USERNAME : <input type="text" name="username" /><br />
		PASSWORD : <input type="text" name="password" /><br />
		<button>회원가입</button>
	</form>
	<div>
		${ username_err }
	</div>
</body>
</html>