<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<header>
			<!-- top-header insert 지시자 이용해서 가져오기 -->
			<%@ include file="./Layouts/TopHeader.jsp" %>
			<!-- nav Insert 지시자 이용해서 가져오기 -->
			<%@ include file="./Layouts/Nav.jsp" %>
		</header>
		<main>
			<section>
				<form action="Login_process.jsp" method="post">
					<input type="text" name="userid" /><br> 
					<input type="text"name="password" /><br> 
					<input type="submit" value="로그인" />
				</form>
			</section>

		</main>

		<footer>
			<!-- footer insert 지시자 이용해서 가져오기 -->
			<%@ include file="./Layouts/Footer.jsp" %>
		</footer>
	</div>
</body>
</html>