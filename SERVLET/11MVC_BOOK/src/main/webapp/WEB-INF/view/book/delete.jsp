<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- link -->
	<%@include file="/resources/layouts/link.jsp" %>
	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="wrapper">
		<header>
			<!-- topHeader -->
			<%@include file="/resources/layouts/topHeader.jsp" %>
			<!-- nav -->
			<%@include file="/resources/layouts/nav.jsp" %>
		</header>
		<main  class="layout">
			<h1>도서 삭제 페이지</h1>
			<form action="${pageContext.request.contextPath }/book/delete" method="post">
				BOOKCODE? : <input name="bookcode" /><br/>
				<button>도서삭제</button>
			</form>
	
		</main>
		
		
		<!-- footer -->
		<%@include file="/resources/layouts/footer.jsp" %>
	</div>

	
</body>
</html>







