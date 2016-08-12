<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta charset="utf-8">
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<core:url value="/img/icon.png" />" rel="icon"
	type="image/png" />

<title></title>

<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/index.css" rel="stylesheet">

<script src="<core:url value='/js/lib/angular.min.js' />"></script>
<script src="<core:url value='/js/lib/angular-route.min.js' />"></script>

</head>

<body ng-app="scApp" class="ng-cloak">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/"><tags:message
						code="system_name" /></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><core:url var="logoutUrl" value="/logout" /> <form:form
							id="logoutForm" action="${logoutUrl}" method="post">
						</form:form> <a href="#"
						onclick="document.getElementById('logoutForm').submit()"> <span
							class="glyphicon glyphicon-log-out"></span> Sign out
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- <div ng-view></div> -->

	<div class="container-fluid" ng-controller="CommandController as ctrl">
		<div class="row bg-primary">
			<div class="col-xs-12 text-center">
				<tags:message code="index_commands" />
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-12 text-center">Transmission Torrent</div>
			<div class="row">
				<div class="col-xs-4 text-right">
					<button type="button" class="btn btn-primary btn-sm "
						ng-click="ctrl.on(2)">On</button>
				</div>
				<div class="col-xs-2 text-center">
					<button type="button" class="btn btn-danger btn-sm"
						ng-click="ctrl.off(2)">Off</button>
				</div>
				<div class="col-xs-2 text-center"">
					<button type="button" class="btn btn-success btn-sm"
						ng-click="ctrl.status(2)">Status</button>
				</div>
				<div class="col-xs-4 text-center">
					<div id="status2">status</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-12 text-center">Jenkins</div>
			<div class="row">
				<div class="col-xs-4 text-right">
					<button type="button" class="btn btn-primary btn-sm "
						ng-click="ctrl.on(5)">On</button>
				</div>
				<div class="col-xs-2 text-center">
					<button type="button" class="btn btn-danger btn-sm"
						ng-click="ctrl.off(5)">Off</button>
				</div>
				<div class="col-xs-2 text-center"">
					<button type="button" class="btn btn-success btn-sm"
						ng-click="ctrl.status(5)">Status</button>
				</div>
				<div class="col-xs-4 text-center">
					<div id="status5">status</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-12 text-center">Desligar BBB</div>
			<div class="row">
				<div class="col-xs-6 text-right">
					<button type="button" class="btn btn-primary btn-sm "
						ng-click="ctrl.execute(1)">Execute</button>
				</div>
				<div class="col-xs-2 text-center"">
					<button type="button" class="btn btn-success btn-sm"
						ng-click="ctrl.status(1)">Status</button>
				</div>
				<div class="col-xs-4 text-center">
					<div id="status1">status</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-12 text-center">Reiniciar BBB</div>
			<div class="row">
				<div class="col-xs-6 text-right">
					<button type="button" class="btn btn-primary btn-sm "
						ng-click="ctrl.execute(3)">Execute</button>
				</div>
				<div class="col-xs-2 text-center"">
					<button type="button" class="btn btn-success btn-sm"
						ng-click="ctrl.status(3)">Status</button>
				</div>
				<div class="col-xs-4 text-center">
					<div id="status3">status</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col-xs-12 text-center">Desligar Aplicação</div>
			<div class="row">
				<div class="col-xs-6 text-right">
					<button type="button" class="btn btn-primary btn-sm "
						ng-click="ctrl.execute(4)">Execute</button>
				</div>
				<div class="col-xs-2 text-center"">
					<button type="button" class="btn btn-success btn-sm"
						ng-click="ctrl.status(4)">Status</button>
				</div>
				<div class="col-xs-4 text-center">
					<div id="status4">status</div>
				</div>
			</div>
		</div>
	</div>


	<script src="<core:url value='/js/application.js' />"></script>
	<script src="<core:url value='/js/services.js' />"></script>
	<script src="<core:url value='/js/controllers.js' />"></script>

	<%-- <script src="<core:url value='js/route.js' />"></script> --%>
	<script src="<core:url value='/js/lib/jquery.min.js' />"></script>
	<script src="<core:url value='/js/lib/bootstrap.min.js' />"></script>

</body>
</html>
