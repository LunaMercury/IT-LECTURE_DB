<!-- 구구단 출력 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
java.util.Scanner sc = new java.util.Scanner(System.in);
System.out.print("단수 입력 : ");
int cnt = sc.nextInt();
System.out.printf("%d 단 출력", cnt);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tbody>
			<%
			for (int i = 0; i < 10; i++) {
			%>
			<tr>
				<%=System.out.printf("%d * %d = %d", cnt, i, cnt * i)%>
			</tr>
			<%
			}
			%>
		</tbody>



	</table>

</body>
</html>