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
	<fmt:message bundle="${loc}" key="lang.facultys.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.new_faculty.caption_new" var="cap_new"/>
	<fmt:message bundle="${loc}" key="lang.new_faculty.caption_edit" var="cap_edit"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_courses" var="your_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_courses" var="all_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.name" var="course_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.hours" var="hours"/>
	<fmt:message bundle="${loc}" key="lang.column.header.start_date" var="start_date"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.btn.save" var="btn_save"/>
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
				<c:if test="${fac != null}">
					<form action="update_faculty" method="post">
				</c:if>
				<c:if test="${fac == null}">
					<form action="insert_faculty" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${fac != null}">
            			<c:out value="${cap_new}"/>
            		</c:if>
						<c:if test="${fac == null}">
            			<c:out value="${cap_edit}"/>
            		</c:if>
					</h2>
				</caption>

				<c:if test="${fac != null}">
					<input type="hidden" name="id" value="<c:out value='${fac.id}' />" />
					<input type="hidden" name="teacher_name" value="<c:out value='${fac.teacher_name}' />" />
				</c:if>

				<fieldset class="form-group">
					<label><c:out value="${course_name}"/></label> <input type="text"
						value="<c:out value='${fac.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label><c:out value="${hours}"/></label> <input type="text"
						value="<c:out value='${fac.hours}' />" class="form-control"
						name="hours">
				</fieldset>
				
				<fieldset class="form-group">
					<label><c:out value="${start_date}"/></label> <input type="text"
						value="<c:out value='${fac.start}' />" class="form-control"
						name="start">
				</fieldset>

				<button type="submit" class="btn btn-success"><c:out value="${btn_save}"/></button>
				</form>
			</div>
		</div>
	</div>
</body>
