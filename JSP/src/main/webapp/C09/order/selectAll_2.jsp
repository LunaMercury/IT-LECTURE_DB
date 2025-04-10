<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="C09.OrderDto2"%>
<%@page import="C09.DBUtils"%>
<%@page import="C09.OrderDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<OrderDto2> list = null;
	try {
		list = DBUtils.getInstance().selectAll_2();
	} catch (Exception e) {
		e.printStackTrace();
		out.println("<script>alert('데이터를 불러오는 중 오류가 발생했습니다.'); history.back();</script>");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
	select u.addr, o.order_date, sum(o.price*o.quantity)
	from tbl_user u
	JOIN tbl_order o
	on u.userid=o.userid
	GROUP BY u.addr, o.order_date
	order by u.addr asc, sum(o.price*o.quantity);
 -->

	<h1>지역+날짜별 구매 총액</h1>
	<table>
		<tr>
			<th>지역</th>
			<th>날짜</th>
			<th>총액</th>
			<th>평균</th>
		</tr>
		<%
		if (list != null && !list.isEmpty()) {
		%>
		<%
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		for (OrderDto2 orderDto2 : list) {
			// yyyy.MM.dd
			
		%>
		<tr>
			<td><%=orderDto2.getAddr() %></td>
			<td><%=orderDto2.getOrder_Date().format(formatter) %></td>
			<td><%=orderDto2.getSum() %></td>
			<td><%=orderDto2.getAverage() %></td>
		</tr>
		<%
		}
		%>
		<%
		} else {
		%>
		<tr>
			<td colspan="2">해당하는 데이터가 없습니다.</td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>