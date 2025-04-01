<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력</title>
</head>
<body>
	<h1>구구단 출력</h1>

	<%
	// 폼에서 cnt 값을 받아옴
	String cntStr = request.getParameter("cnt");
	int cnt = 0;

	// cnt 값이 null이 아니면 정수로 변환
	if (cntStr != null && !cntStr.isEmpty()) {
		try {
			cnt = Integer.parseInt(cntStr);
		} catch (NumberFormatException e) {
			out.println("<p style='color:red;'>잘못된 입력입니다. 숫자를 입력해주세요.</p>");
			// 에러 발생 시 기본값 또는 처리 방식 결정
			return;
		}

		out.println("<h2>" + cnt + " 단</h2>");
		out.println("<table>");
		out.println("<tbody>");
		for (int i = 1; i < 10; i++) {
			out.println("<tr>");
			out.println("<td>" + cnt + "</td>");
			out.println("<td> * </td>");
			out.println("<td>" + i + "</td>");
			out.println("<td> = </td>");
			out.println("<td>" + (cnt * i) + "</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");

	} else {
		// cnt 값이 없을 경우 입력 폼을 보여줌
	%>
		<form method="get">
			단수 입력: <input type="number" name="cnt">
			<input type="submit" value="출력">
		</form>
	<%
	}
	%>

</body>
</html>