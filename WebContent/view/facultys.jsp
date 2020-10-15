<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>All courses</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">Courses</a>
			</div>

			<ul class="navbar-nav">
				<c:if test="${role.equals('STUDENT')}">
				<li><a href="<%=request.getContextPath()%>/list_student"
					class="nav-link">Your courses</a></li>
				</c:if>
				<c:if test="${role.equals('TEACHER')}">
				<li><a href="<%=request.getContextPath()%>/list_teacher"
					class="nav-link">Your courses</a></li>
				</c:if>
				<li><a href="<%=request.getContextPath()%>/courses"
					class="nav-link">Available courses</a></li>
				<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link">Your Profile</a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Available Courses</h3>
			<hr>
			<c:if test="${role.equals('TEACHER')}">
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Course</a>
			</div>
			</c:if>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Hours</th>
						<th>Start Date</th>
						<th>Teacher Name</th>
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
								<td><a href="join_student?id=<c:out value='${fac.id}' />">Join</a></td>
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