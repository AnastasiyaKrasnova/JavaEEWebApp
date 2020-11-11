<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Students at course</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.students_list.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.nav.your_courses" var="your_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.all_courses" var="all_courses"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="profile"/>
	<fmt:message bundle="${loc}" key="lang.nav.logout" var="logout"/>
	<fmt:message bundle="${loc}" key="lang.column.header.first_name" var="first_name"/>
	<fmt:message bundle="${loc}" key="lang.column.header.last_name" var="last_name"/>
	<fmt:message bundle="${loc}" key="lang.login.email" var="mail"/>
	<fmt:message bundle="${loc}" key="lang.column.header.status" var="status"/>
	<fmt:message bundle="${loc}" key="lang.column.header.mark" var="mark"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.progress" var="status_progr"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.finished" var="status_finish"/>
	<fmt:message bundle="${loc}" key="lang.edit_mark.status.problems" var="status_probl"/>
	<fmt:message bundle="${loc}" key="lang.students_list.href.dismiss" var="href_dismiss"/>
	<fmt:message bundle="${loc}" key="lang.students_list.href.update" var="href_update"/>
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
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center"><c:out value="${title}"/></h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><c:out value="${first_name}"/></th>
						<th><c:out value="${last_name}"/></th>
						<th><c:out value="${mail}"/></th>
						<th><c:out value="${status}"/></th>
						<th><c:out value="${mark}"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stud" items="${listStud}">

						<tr>
							<td><c:out value="${stud.first_name}" /></td>
							<td><c:out value="${stud.last_name}" /></td>
							<td><c:out value="${stud.email}" /></td>
							<c:if test="${stud.status.equals('PROGRESS')}">
								<td><c:out value="${status_progr}" /></td>
							</c:if>
							<c:if test="${stud.status.equals('FINISHED')}">
								<td><c:out value="${status_finish}" /></td>
							</c:if>
							<c:if test="${stud.status.equals('PROBLEMS')}">
								<td><c:out value="${status_probl}" /></td>
							</c:if>
							
							<td><c:out value="${stud.mark}" /></td>
							<td><a href="dismiss?student_id=<c:out value='${stud.student_id}' />&&faculty_id=<c:out value='${stud.faculty_id}' />"><c:out value="${href_dismiss}"/></a>
							</td>
							<td><a href="edit_mark?student_id=<c:out value='${stud.student_id}' />&&faculty_id=<c:out value='${stud.faculty_id}' />"><c:out value="${href_update}"/></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>