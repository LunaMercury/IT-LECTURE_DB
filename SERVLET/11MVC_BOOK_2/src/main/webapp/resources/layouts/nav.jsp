<%@page import="Domain.Dto.PageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="layout navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}">HOME</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/book/list">도서</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Dropdown </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#">Action</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="#">Something else here</a></li>
					</ul></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
			</ul>
			
			<%
				PageDto pageDto_nav = request.getAttribute("pageDto")!=null?(PageDto)request.getAttribute("pageDto"):null;
			%>

			<form action="${pageContext.request.contextPath}/book/list"
				class="d-flex">
				<select name="type">
					<option value="bookCode"
						<%if ("bookCode".equals(session.getAttribute("searchType"))) {%>
						selected="selected" <%}%>>도서코드</option>
					<option value="bookName"
						<%if ("bookName".equals(session.getAttribute("searchType"))) {%>
						selected="selected" <%}%>>도서명</option>
					<option value="publisher"
						<%if ("publisher".equals(session.getAttribute("searchType"))) {%>
						selected="selected" <%}%>>출판사</option>
					<option value="isbn"
						<%if ("isbn".equals(session.getAttribute("searchType"))) {%>
						selected="selected" <%}%>>ISBN</option>
						
				</select>
				<input name="keyword" class="form-control me-2" type="search"
					placeholder="Search" aria-label="Search"
					value="<%= session.getAttribute("searchKeyword") != null ? session.getAttribute("searchKeyword") : "" %>" />
					<input type="hidden" name="pageno" value="<%=pageDto_nav.getCriteria().getPageno() %>" />
				<button class="btn btn-outline-success" type="submit">Search</button>
				<%				
				System.out.println("[nav]"+session.getAttribute("searchType"));
				System.out.println("[nav]"+session.getAttribute("searchKeyword"));				
				%>
			</form>
		</div>
	</div>
</nav>
