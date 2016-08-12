<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta charset="utf-8">
<%@	taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<core:url value="/img/icon.png" />" rel="icon"
	type="image/png" />

<title><tags:message code="system_name" /></title>

<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/login.css" rel="stylesheet">

</head>

<body>

	<div class="panel panel-primary form-signin">

		<div class="panel-heading">
			<h3 class="panel-title">
				<tags:message code="login_title_message" />
			</h3>
		</div>

		<div class="panel-body">

			<core:if test="${param.error != null}">
				<div class="alert alert-danger">
					<tags:message code="login_invalid_login" />
				</div>
			</core:if>

			<core:if test="${param.logout != null}">
				<div class="alert alert-danger">
					<tags:message code="login_logout_message" />
				</div>
			</core:if>

			<form:form role="form" method="post">

				<div class="form-group">
					<label for="username"><tags:message code="login_username" /></label>
					<input id="username" name="username" class="form-control"
						placeholder="<tags:message code="login_username" />" required
						autofocus />
				</div>

				<div class="form-group">
					<label for="password"><tags:message code="login_password" /></label>
					<input type="password" id="password" name="password"
						class="form-control"
						placeholder="<tags:message code="login_password" />" required />
					<form:errors cssClass="error" path="password" />
				</div>

				<div class="form-group">
					<div class="checkbox">
						<label> <input name="_spring_security_remember_me"
							type="checkbox"> <tags:message code="login_rememberme" />
						</label>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">
					<tags:message code="login_signin" />
				</button>
			</form:form>
		</div>
	</div>
</body>
</html>
