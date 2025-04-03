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
		String protocol = request.getProtocol();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String remoteAddr = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		String method = request.getMethod();
		StringBuffer requestURL = request.getRequestURL();
		String Browser = request.getHeader("User-Agent");
		String fileType = request.getHeader("Accept");
	%>
	
	프로토콜 : <%=protocol %> <br />
	서버이름 : <%=serverName %> <br />
	서버포트 : <%=serverPort %> <br />
	고객주소 : <%=remoteAddr %> <br />
	고객이름 : <%=remoteHost %> <br />
	요청함수 : <%=method %> <br />
	요청경로 : <%=requestURL %> <br />
	브라우저 : <%=Browser %> <br /> <!-- 브라우저 스펙 등 -->
	파일타입 : <%=fileType %> <br />
	

</body>
</html>