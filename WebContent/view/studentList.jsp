<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Students at course</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand">Students of the course</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list_teacher"
					class="nav-link">Your courses</a></li>
				<li>
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
			<h3 class="text-center">Students of the course</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Hours</th>
						<th>Email</th>
						<th>Status</th>
						<th>Mark</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stud" items="${listStud}">

						<tr>
							<td><c:out value="${stud.first_name}" /></td>
							<td><c:out value="${stud.last_name}" /></td>
							<td><c:out value="${stud.email}" /></td>
							<td><c:out value="${stud.status}" /></td>
							<td><c:out value="${stud.mark}" /></td>
							<td><a href="dismiss?student_id=<c:out value='${stud.student_id}' />&&faculty_id=<c:out value='${stud.faculty_id}' />">Dismiss student</a>
							</td>
							<td><a href="update_mark?student_id=<c:out value='${stud.student_id}' />&&faculty_id=<c:out value='${stud.faculty_id}' />">Update status</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>