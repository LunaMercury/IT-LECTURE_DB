<%@page import="C04.UserDto"%>
<%@page import="java.util.List"%>
<%@page import="C09.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	DBUtils dbUtils = DBUtils.getInstance();

	List<UserDto> list = dbUtils.selectAllUser();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
	1. selectAllUser를 이용해서 table의 해당 user 정보 표시
	2. 만들어진 테이블에 컬럼을 추가해서 해당 컬름의 수정 버튼을 만들기
	3. 만들어진 테이블에 컬럼을 추가해서 해당 컬음의 삭제 버튼을 만들기
	4. 수정버튼을 클릭하면 수정페이지로 이동(update.jsp + 해당 사용자 파라미터 전달)
	5. 삭제 버튼을 클릭하면 삭제페이지로 이동(delete.jsp + 해당 사용자 파라미터 전달)
 -->

	총인원 : <%=list.size() %> <br />
	
	<table>
		<tr>
			<th>계정</th>
			<th>패스워드</th>
			<th>역할</th>
		</tr>
		<%
			for(UserDto dto : list) {				
		%>
		<tr>
			<td><%=dto.getUserid() %></td> 
			<td><%=dto.getPassword() %></td>
			<td><%=dto.getRole() %></td>
			<td>
				<a href="./update_form.jsp?userid=<%=dto.getUserid() %>">수정하기</a>
			</td>
			<td>
				<a href="javascript:confirmDelete('<%=dto.getUserid() %>')">삭제하기</a>
			</td>
		</tr>		
		<%
			}
		%>		
		
	</table>
	
	<script>
		function confirmDelete(userid){
				const isDelete = confirm("정말 삭제하시겠습니까?");
				if(isDelete)						
					location.href='./delete.jsp?userid=' + userid;			
		}
	</script>
</body>
</html>