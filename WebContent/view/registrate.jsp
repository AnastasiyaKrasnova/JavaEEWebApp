<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Registration</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	
	<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
	<fmt:setBundle basename="resourses.lang" var="loc"/>
	<fmt:message bundle="${loc}" key="lang.register.title.edit" var="title_edit"/>
	<fmt:message bundle="${loc}" key="lang.register.title.reg" var="title_reg"/>
	<fmt:message bundle="${loc}" key="lang.nav.profile" var="nav_prof"/>
	<fmt:message bundle="${loc}" key="lang.register.nav.login" var="nav_login"/>
	<fmt:message bundle="${loc}" key="lang.register.first_name" var="first_name"/>
	<fmt:message bundle="${loc}" key="lang.register.last_name" var="last_name"/>
	<fmt:message bundle="${loc}" key="lang.login.email" var="mail"/>
	<fmt:message bundle="${loc}" key="lang.login.password" var="pass"/>
	<fmt:message bundle="${loc}" key="lang.register.role" var="user_role"/>
	<fmt:message bundle="${loc}" key="lang.register.role.student" var="stud"/>
	<fmt:message bundle="${loc}" key="lang.register.role.teacher" var="teach"/>
	<fmt:message bundle="${loc}" key="lang.register.btn.edit" var="btn_edit"/>
	<fmt:message bundle="${loc}" key="lang.register.btn.reg" var="btn_reg"/>
	<fmt:message bundle="${loc}" key="lang.register.mistake.exists" var="mst_exists"/>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">WEB FACULTY</a>
			</div>
			<c:if test="${user!= null}">
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/profile"
					class="nav-link"><c:out value="${nav_prof}"/></a></li>
				</ul>
			</c:if>
			<c:if test="${user== null}">
				<ul class="navbar-nav">
					<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link"><c:out value="${nav_login}"/></a></li>
				</ul>
			</c:if>
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
					<c:if test="${user!= null}">
						<c:out value="${title_edit}"/>
					</c:if>
					<c:if test="${user== null}">
						<c:out value="${title_reg}"/>
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
					<label><c:out value="${first_name}"/></label> <input type="text"
						value="<c:out value='${user.first_name}' />" class="form-control"
						name="first_name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label><c:out value="${last_name}"/></label> <input type="text"
						value="<c:out value='${user.last_name}' />" class="form-control"
						name="last_name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label><c:out value="${mail}"/></label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label><c:out value="${pass}"/></label> <input type="password"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password" required="required">
				</fieldset>
				<fieldset class="form-group">
					<c:if test="${user== null}">
					<label><c:out value="${user_role}"/></label> <p><select name="role">
    					<option selected value="STUDENT"><c:out value="${stud}"/></option>
    					<option value="TEACHER"><c:out value="${teach}"/></option>
   						</select></p>
   					</c:if>
   					<c:if test="${user!= null}">
   						<c:if test="${user.role.equals('TEACHER')}">
   						<label>Role</label> <input type="text"
							value="<c:out value="${teach}"/>" class="form-control"
							name="role" readonly>
						</c:if>
						<c:if test="${user.role.equals('STUDENT')}">
   						<label>Role</label> <input type="text"
							value="<c:out value="${stud}"/>" class="form-control"
							name="role" readonly>
						</c:if>
					</c:if>
				</fieldset>
				<c:if test="${user!= null}">
				<button type="submit" class="btn btn-success"><c:out value="${btn_edit}"/></button>
				</c:if>
				<c:if test="${user== null}">
				<button type="submit" class="btn btn-success"><c:out value="${btn_reg}"/></button>
				</c:if>
				</form>
		
			</div>
			<c:if test="${mistake_num== 1}">
			<label><c:out value="${mst_exists}"/></label>
			</c:if>
		</div>
	</div>
</body>
</html>