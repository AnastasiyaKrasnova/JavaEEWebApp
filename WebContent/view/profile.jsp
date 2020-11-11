<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Profile</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_courses" var="your_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_courses" var="all_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.first_name" var="first_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.last_name" var="last_name"/>
	<fmt:message bundle="${loc}" key="lang.login.email" var="mail"/>
	<fmt:message bundle="${loc}" key="lang.register.role" var="user_role"/>
	<fmt:message bundle="${loc}" key="lang.register.role.student" var="stud"/>
	<fmt:message bundle="${loc}" key="lang.register.role.teacher" var="teach"/>
	<fmt:message bundle="${loc}" key="lang.profile.href.edit" var="href_edit"/>
	<fmt:message bundle="${loc}" key="lang.profile.href.delete" var="href_delete"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><c:out value="${profile}"/></a>
			</div>

			<ul class="navbar-nav">
				<c:if test="${role.equals('STUDENT')}">
				<li><a href="<%=request.getContextPath()%>/list_student"
					class="nav-link"><c:out value="${your_courses}"/></a></li>
				</c:if>
				<c:if test="${role.equals('TEACHER')}">
				<li><a href="<%=request.getContextPath()%>/list_teacher"
					class="nav-link"><c:out value="${your_courses}"/></a></li>
				</c:if>
				<li>
    				<a href="<%=request.getContextPath()%>/courses" class="nav-link"><c:out value="${all_courses}"/></a>
				</li>
				<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link"><c:out value="${profile}"/></a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link"><c:out value="${logout}"/></a></li>
			</ul>
			<div>
				<form method="post" action="change_lang">
					<input type="hidden" name="lang" value="ru" />
					<input type="submit" value="RU" /></input>
				</form>
				<form method="post" action="change_lang">
					<input type="hidden" name="lang" value="en" />
					<input type="submit" value="EN" /></input>
				</form>
			</div>
		</nav>
	</header>
	<br>
	<div class="row">

		<div class="container">
			<h3 class="text-center"><c:out value="${profile}"/></h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><c:out value="${first_name}"/></th>
						<th><c:out value="${last_name}"/></th>
						<th><c:out value="${mail}"/></th>
						<th><c:out value="${role}"/></th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
						<tr>
							<td><c:out value="${user.first_name}" /></td>
							<td><c:out value="${user.last_name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<c:if test="${user.role.equals('TEACHER')}">
								<td><c:out value="${teach}" /></td>
							</c:if>
							<c:if test="${user.role.equals('STUDENT')}">
								<td><c:out value="${stud}" /></td>
							</c:if>
							<td><a href="edit_user?id=<c:out value='${user.id}' />"><c:out value="${href_edit}"/></a>
							</td>
							<td><a href="delete_user?id=<c:out value='${user.id}' />"><c:out value="${href_delete}"/></a>
							</td>
						</tr>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>