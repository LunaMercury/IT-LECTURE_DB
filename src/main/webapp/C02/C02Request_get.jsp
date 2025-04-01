<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 메서드 방식을 post 방식으로 변경-->
	<form action="./C02Request_process.jsp" method="post">
		<input type="text" name="username"/><br />
		<input type="text" name="password"/><br />
		<input type="text" name="bgcolor"/><br />
		<button>전송</button>
	</form>

</body>
</html>