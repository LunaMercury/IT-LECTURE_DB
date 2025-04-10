<!-- 
	Scriptlet : JSP -> Servlet 파일로 변환될때 Servlet 내의 Service 함수 내에서 처리되는 로직
	Service라는 함수 내에서 동작하기 때문에 지역변수 선언, 반복문/분기문 처리가 가능
	하지만 함수를 벗어나면 지역변수는 소멸되기 때문에 재요청(새로고침 등)되면 상태값이 초기화된다
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String str1 = "HELLO01";
	String str2 = "HELLO02";
	String str3 = str1 + str2;
	int a = 0;
	for (int i = 0; i < 10; i++) {
		a++;
		System.out.println(i);
	}
	%>

	<%=str1%>
	<br />
	<%=str2%>
	<br />
	<%=str3%>
	<br />

</body>
</html>