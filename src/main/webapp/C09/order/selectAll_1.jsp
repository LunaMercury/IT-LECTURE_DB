<%@page import="C09.OrderDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="C04.UserDto, C09.*,java.util.*"%>

<!-- 
	select category,sum(price*quantity) from tbl_order
	group by category
	having sum(price*quantity)>=50000
	order by sum(price*quantity) desc;
 -->

<!--
select order_date, round(avg(price*quantity),2)
from tbl_order
GROUP BY order_date;
-->

<%
List<OrderDto> list = null;
try {
	list = DBUtils.getInstance().selectAllOrder();
	System.out.println(list);
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
<title>품목별 총액</title>
</head>
<body>
	<h1>품목별 총액(50000 만원 이상)</h1>
	<table>
		<tr>
			<th>카테고리</th>
			<th>총액</th>
		</tr>
		<%
		if (list != null && !list.isEmpty()) {
		%>
		<%
		for (OrderDto orderDto : list) {
		%>
		<tr>
			<td><%=orderDto.getCategory()%></td>
			<td><%=orderDto.getSum()%></td>
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