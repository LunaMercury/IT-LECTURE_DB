<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String msg1 = "HELLO 1";
	String msg2 = "HELLO 2";
	String msg3 = "HELLO 3";
	String msg4 = "HELLO 4";
	String msg5 = "HELLO 5";
	request.setAttribute("message", "TEST");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<script type="text/javascript">
	
<%-- 		const message1 = '<%=msg1%>'; 	/* 문자열로 변환하기 위해 '' 작성하기 */
		const message2 = '<%=msg2%>';		
		const message3 = '<%=msg3%>';		
		const message4 = '<%=msg4%>'; --%>
		const message1 = '<%=msg1%>';	
		const message2 = '${message2}';		
		const message3 = `${message3}`; // 보간법(JSP의 message값을 리터럴 형태로 변환)		
		const message4 = '<%=msg4%>';
		console.log(message1);
		console.log(message2);
		console.log(message3);
		console.log(message4);
	</script>

</body>
</html>