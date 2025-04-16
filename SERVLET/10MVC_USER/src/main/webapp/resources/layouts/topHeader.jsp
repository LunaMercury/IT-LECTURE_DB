<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="top-header layout  bg-success text-light">

	<ul class="user-block">

		<%
		Boolean isAuth = (Boolean) session.getAttribute("isAuth");
		if (isAuth == null) {
		%>
		<li><a href="${ pageContext.request.contextPath }/user/login">
				<span class="material-symbols-outlined">login</span>
		</a></li>
		<%
		} else {
		%>
		<!-- login -->
		<li>${username }</li>
		<li><a href="javascript:void(0)"> <span
				class="material-symbols-outlined">person</span>
		</a></li>

		<!-- logout -->
		<li><a href="${ pageContext.request.contextPath }/user/logout">
				<span class="material-symbols-outlined">logout</span>
		</a></li>
		<%
		}
		%>


	</ul>


</div>