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
<link href="/css/angular-block-ui.min.css" rel="stylesheet">

<script src="<core:url value='/js/lib/angular.min.js' />"></script>
<script src="<core:url value='/js/lib/angular-block-ui.min.js' />"></script>

</head>

<body ng-app="commandRunnerApp" class="ng-cloak">
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
		<div class="container-fluid" ng-repeat="group in ctrl.groups">
			<div class="row bg-primary">
				<div class="col-xs-12 text-center">
					<span ng-bind="group.name">
				</div>
			</div>
			<div class="row" ng-repeat="commandGroup in group.commandGroup">
				<div class="col-xs-12 text-center">
					<span ng-bind="commandGroup.name">
				</div>
				<div class="row">
					<div
						class="{{ctrl.divClassRow(commandGroup.commands.length)}} text-center"
						ng-repeat="command in commandGroup.commands">
						<button type="button" class="btn {{command.color}} btn-sm "
							ng-click="ctrl.execute(commandGroup.id, command.id)">
							<span ng-bind="command.name">
						</button>
					</div>
					<div
						class="{{ctrl.divClassRow(commandGroup.commands.length)}} text-center">
						<div id="status{{commandGroup.id}}">status</div>
					</div>
				</div>
				<br />
			</div>
		</div>
	</div>

	<script src="<core:url value='/js/application.js' />"></script>
	<script src="<core:url value='/js/services.js' />"></script>
	<script src="<core:url value='/js/controllers.js' />"></script>

	<script src="<core:url value='/js/lib/jquery.min.js' />"></script>
	<script src="<core:url value='/js/lib/bootstrap.min.js' />"></script>

</body>
</html>
