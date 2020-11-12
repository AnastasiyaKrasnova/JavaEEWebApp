<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.login.title" var="title"/>
	<fmt:message bundle="${loc}" key="lang.login.email" var="mail"/>
	<fmt:message bundle="${loc}" key="lang.login.password" var="pass"/>
	<fmt:message bundle="${loc}" key="lang.login.btn.sign_in" var="btn_text"/>
	<fmt:message bundle="${loc}" key="lang.login.href.reg" var="href_text"/>
	<fmt:message bundle="${loc}" key="lang.login.mistake.incorrect" var="mst_incorrect"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">WEB FACULTY</a>
			</div>
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

				<caption>
					<h2>
						<c:out value="${title}"/>
					</h2>
				</caption>
				<form method="post" action="route">
				<fieldset class="form-group">
					<label><c:out value="${mail}"/></label><input type="text"
						value="email" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label><c:out value="${pass}"/></label> <input type="password"
						value="password" class="form-control"
						name="password" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success"><c:out value="${btn_text}"/></button>
				</form>
				
				<a href="<%=request.getContextPath()%>/register"><c:out value="${href_text}"/></a>
				
			</div>
			<c:if test="${mistake_num== 1}">
			<label><c:out value="${mst_incorrect}"/></label>
			</c:if>
		</div>
	</div>
</body>
</html>