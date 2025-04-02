<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	:root{}
	*{box-sizing: border-box; padding: 10px; border: 1px solid;}
	body{padding: 0; margin: 0;}
	.wrapper{}
	.wrapper>header{min-height: 100px;}
	.wrapper>header>.top-header{min-height: 25px;}
	.wrapper>header>.nav{min-height: 75px;}
	.wrapper>main{min-height: calc(100vh - 100px - 25px - 75px);}
	.wrapper>section{}
	.wrapper>footer{min-height: 250px;}
	
</style>
</head>
<body>
	<div class="wrapper">
		<header>
			
			<%@ include file="./Layouts/TopHeader.jsp" %>
			<%@ include file="./Layouts/Nav.jsp" %>
			
		</header>
		<main>
			<section>SECTION 영역</section>
		</main>
		
	</div>

</body>
</html>