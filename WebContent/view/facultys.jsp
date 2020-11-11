<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>All courses</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.facultys.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_courses" var="your_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_courses" var="all_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.name" var="course_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.hours" var="hours"/>
	<fmt:message bundle="${loc}" key="lang.column.header.start_date" var="start_date"/>
	<fmt:message bundle="${loc}" key="lang.column.header.teacher_name" var="teacher_name"/>
	<fmt:message bundle="${loc}" key="lang.facultys.href.join" var="href_join"/>
	<fmt:message bundle="${loc}" key="lang.facultys.btn.new" var="btn_new"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><c:out value="${title}"/></a>
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
				<li><a href="<%=request.getContextPath()%>/courses"
					class="nav-link"><c:out value="${all_courses}"/></a></li>
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
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center"><c:out value="${all_courses}"/></h3>
			<hr>
			<c:if test="${role.equals('TEACHER')}">
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success"><c:out value="${btn_new}"/></a>
			</div>
			</c:if>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><c:out value="${course_name}"/></th>
						<th><c:out value="${hours}"/></th>
						<th><c:out value="${start_date}"/></th>
						<th><c:out value="${teacher_name}"/></th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="fac" items="${listFac}">

						<tr>
							<td><c:out value="${fac.name}" /></td>
							<td><c:out value="${fac.hours}" /></td>
							<td><c:out value="${fac.start}" /></td>
							<td><c:out value="${fac.teacher_name}" /></td>
							<c:if test="${role.equals('STUDENT')}">
								<td><a href="join_student?id=<c:out value='${fac.id}' />"><c:out value="${href_join}"/></a></td>
							</c:if>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>