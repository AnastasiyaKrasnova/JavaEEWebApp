<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Web Faculty</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_courses" var="your_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_courses" var="all_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.first_name" var="first_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.last_name" var="last_name"/>
	<fmt:message bundle="${loc}" key="lang.login.email" var="mail"/>
	<fmt:message bundle="${loc}" key="lang.column.header.status" var="status"/>
	<fmt:message bundle="${loc}" key="lang.column.header.mark" var="mark"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.btn.save" var="btn_save"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.progress" var="status_progr"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.finished" var="status_finish"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.problems" var="status_probl"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"><c:out value="${title}"/></a>
			</div>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list_teacher"
					class="nav-link"><c:out value="${your_courses}"/></a></li>
				<li>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="update_mark" method="post">
				<caption>
					<h2>
            			<c:out value="${title}"/>
					</h2>
				</caption>

					<input type="hidden" name="student_id" value="<c:out value='${info.student_id}' />" />
					<input type="hidden" name="faculty_id" value="<c:out value='${info.faculty_id}' />" />

				<fieldset class="form-group">
					<label><c:out value="${first_name}"/></label> <input type="text"
						value="<c:out value='${info.first_name}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>

				<fieldset class="form-group">
					<label><c:out value="${last_name}"/></label> <input type="text"
						value="<c:out value='${info.last_name}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>
				
				<fieldset class="form-group">
					<label><c:out value="${mail}"/></label> <input type="text"
						value="<c:out value='${info.email}' />" class="form-control"
						name="first_name" readonly>
				</fieldset>
				
				<fieldset class="form-group">
					<label><c:out value="${status}"/></label> <p><select name="status">
    					<option selected value="PROGRESS"><c:out value="${status_progr}"/></option>
    					<option value="FINISHED"><c:out value="${status_finish}"/></option>
    					<option value="PROBLEMS"><c:out value="${status_probl}"/></option>
   						</select></p>
				</fieldset>
				
				<fieldset class="form-group">
					<label><c:out value="${mark}"/></label> <input type="text"
						value="<c:out value='${info.mark}' />" class="form-control"
						name="mark" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success"><c:out value="${btn_save}"/></button>
				</form>
			</div>
		</div>
	</div>
</body>