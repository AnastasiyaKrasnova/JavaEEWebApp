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
					<form action="update_mark" method="post">
				<caption>
					<h2>
            			Edit Mark
					</h2>
				</caption>

					<input type="hidden" name="student_id" value="<c:out value='${info.student_id}' />" />
					<input type="hidden" name="faculty_id" value="<c:out value='${info.faculty_id}' />" />

				<fieldset class="form-group">
					<label>First Name</label> <input type="text"
						value="<c:out value='${info.first_name}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>

				<fieldset class="form-group">
					<label>Last Name</label> <input type="text"
						value="<c:out value='${info.last_name}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${info.email}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Status</label> <p><select name="status">
    					<option selected value="PROGRESS">PROGRESS</option>
    					<option value="FINISHED">FINISHED</option>
    					<option value="PROBLEMS">PROBLEMS</option>
   						</select></p>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Mark</label> <input type="text"
						value="<c:out value='${info.mark}' />" class="form-control"
						name="mark" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>