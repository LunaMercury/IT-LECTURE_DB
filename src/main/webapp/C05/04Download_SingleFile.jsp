<!-- 실행하면 파일을 다운로드 하거나 읽어낼 수 있게 만들기 -->

<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%    	
	// 파일 경로
   	String dirPath = pageContext.getServletContext().getRealPath("/")+"C05\\files\\";
   	System.out.println("dirPath : " + dirPath);
   	
   	try{
   		// INPUTSTREAM
   		FileInputStream in = new FileInputStream(dirPath+"TEST.txt");
   		
   		// OUTPUTSTREAM
   		out.clear();	//response outputstream을 닫고 버퍼를 비움
   		out = pageContext.pushBody(); 	// 현재페이지의 body에 연결
   		
   		ServletOutputStream bout = response.getOutputStream();
   		
   		// Response Header 지정
   		response.setHeader("Content-Tyte", "application/octet-stream;charset-utf-8");
   		response.setHeader("Content-Disposition", "attachment; filename=TEST.txt"); // 이 코드로 인해 읽은 파일을 다운로드 한다.
   		// response.setHeader("Content-Disposition", "inline; filename=TEST.txt"); // 이 코드는 화면에 나타내게 함
   		
   		byte[] buff = new byte[4096];
   		while(true){
   			int data = in.read(buff);
   			if(data==-1)
   				break;
   			bout.write(buff, 0, data);
   			bout.flush();
   		}
   		
   		}
   	catch(Exception e){
   		e.printStackTrace();
   	}
    	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>