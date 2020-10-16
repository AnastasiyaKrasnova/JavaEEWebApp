<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Registration</title>
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
				<a href="https://www.javaguides.net" class="navbar-brand">WEB FACULTY</a>
			</div>
			<c:if test="${user!= null}">
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link">Your Profile</a></li>
				</ul>
			</c:if>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<caption>
					<h2>
					<c:if test="${user!= null}">
						Edit User
					</c:if>
					<c:if test="${user== null}">
						REGISTRATION
					</c:if>
					</h2>
				</caption>
				<c:if test="${user!= null}">
					<form method="post" action="update_user">
				</c:if>
				<c:if test="${user== null}">
					<form method="post" action="insert">
				</c:if>
				<fieldset class="form-group">
					<label>First Name</label> <input type="text"
						value="<c:out value='${user.first_name}' />" class="form-control"
						name="first_name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Last Name</label> <input type="text"
						value="<c:out value='${user.last_name}' />" class="form-control"
						name="last_name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Password</label> <input type="password"
						value="*******" class="form-control"
						name="password" required="required">
				</fieldset>
				<fieldset class="form-group">
					<c:if test="${user== null}">
					<label>Role</label> <p><select name="role">
    					<option selected value="STUDENT">STUDENT</option>
    					<option value="TEACHER">TEACHER</option>
   						</select></p>
   					</c:if>
   					<c:if test="${user!= null}">
   					<label>Role</label> <input type="text"
						value="<c:out value='${user.role}' />" class="form-control"
						name="role" readonly>
   					</c:if>
				</fieldset>
				<c:if test="${user!= null}">
				<button type="submit" class="btn btn-success">Edit</button>
				</c:if>
				<c:if test="${user== null}">
				<button type="submit" class="btn btn-success">REGISTRATE</button>
				</c:if>
				</form>
		
			</div>
		</div>
	</div>
</body>
</html>