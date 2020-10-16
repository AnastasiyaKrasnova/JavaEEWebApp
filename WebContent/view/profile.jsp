<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Profile</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand">Profile</a>
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
				<li>
    				<a href="<%=request.getContextPath()%>/courses" class="nav-link">Available Courses</a>
				</li>
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
			<h3 class="text-center">Profile</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Role</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
						<tr>
							<td><c:out value="${user.first_name}" /></td>
							<td><c:out value="${user.last_name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.role}" /></td>
							<td><a href="edit_user?id=<c:out value='${user.id}' />">Edit</a>
							</td>
							<td><a href="delete_user?id=<c:out value='${user.id}' />">Delete</a>
							</td>
						</tr>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>