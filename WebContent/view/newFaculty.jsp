<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web Faculty</title>
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
				<li><a href="<%=request.getContextPath()%>/list_teacher"
					class="nav-link">Your courses</a></li>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${fac != null}">
					<form action="update_faculty" method="post">
				</c:if>
				<c:if test="${fac == null}">
					<form action="insert_faculty" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${fac != null}">
            			Edit Faculty
            		</c:if>
						<c:if test="${fac == null}">
            			Add New Faculty
            		</c:if>
					</h2>
				</caption>

				<c:if test="${fac != null}">
					<input type="hidden" name="id" value="<c:out value='${fac.id}' />" />
					<input type="hidden" name="teacher_name" value="<c:out value='${fac.teacher_name}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${fac.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Hours</label> <input type="text"
						value="<c:out value='${fac.hours}' />" class="form-control"
						name="hours">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Start</label> <input type="text"
						value="<c:out value='${fac.start}' />" class="form-control"
						name="start">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
