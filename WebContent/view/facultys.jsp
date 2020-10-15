<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Main Page</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand">Student Main Page</a>
			</div>

			<ul class="navbar-nav">
				<c:if test="${role.equals('STUDENT')}">
				<li><a href="<%=request.getContextPath()%>/student/all_courses"
					class="nav-link">Available courses</a></li>
				<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link">Your Profile</a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
				</c:if>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Available Courses</h3>
			<hr>
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
								<td><a href="join?id=<c:out value='${fac.id}' />">Join</a></td>
							</c:if>
							<c:if test="${role.equals('TEACHER')}">
							<td><a href="edit?id=<c:out value='${fac.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${fac.id}' />">Delete</a></td>
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