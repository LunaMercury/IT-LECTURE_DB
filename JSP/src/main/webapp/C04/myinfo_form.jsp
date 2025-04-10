<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	:root{}
	*{box-sizing:border-box;padding:10px; border:1px solid;}
	a{}
	ul{}
	body{padding : 0; margin:0;}
	.wrapper{
		height : 100%;
	}
	.wrapper>header{min-height:100px;}
	.wrapper>header>.top-header{min-height:25px;}
	.wrapper>header>.nav{min-height:75px;}
	.wrapper>main{
		min-height :calc(100vh - 100px - 250px);
	};
	.wrapper>main>section{
		
	}
	.wrapper>footer{min-height:250px;}
</style>
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
			<!-- 
				1. myinfo.jsp(생성)
					- url:"/myinfo"를 request.setAttribute로 저장 후 forwaridng
				2. validationCheck.jsp
					- 유효성 체크
				3. dbUtils.jsp(기존)
					- select 작업 조회이후 forwarding
				4. myinfo.jsp(내용표시)					
			 -->
			
			<h1>MYINFO</h1>
				<form action="myinfo.jsp" method="post">
					<input type="text" name="userid" /><br>					 
					<input type="submit" value="조회" />
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