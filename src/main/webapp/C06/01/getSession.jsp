<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>확인하기</h1>
	
	<div>REQUEST 속성 확인</div>
	ID : <%=request.getAttribute("ID1") %> <br />
	PW : <%=request.getAttribute("PW1") %> <br />
	
	<div>SESSION 속성 확인</div>
	ID : <%=request.getAttribute("ID2") %> <br />
	PW : <%=request.getAttribute("PW2") %> <br />

</body>
</html>