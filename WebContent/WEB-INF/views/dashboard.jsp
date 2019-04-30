<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.excilys.model.Computer"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	media="screen">
</head>
<body>
    <script>
    const urlParams = new URLSearchParams(window.location.search);
    const lang = urlParams.get('lang');
    </script>  
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/Computers"> Application
				- Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<spring:message code="homeTitle" arguments="${size}" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="SEARCH"
							class="form-control"
							placeholder="<spring:message code="search"/>" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message code="filter"/>" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="${pageContext.request.contextPath}/Computer/add"><spring:message
							code="add" /> </a> <a class="btn btn-default" id="editComputer"
						href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="name" /><a
							href="${pageContext.request.contextPath}/Computers?orderBy=NAME"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<th><spring:message code="introduced" /><a
							href="${pageContext.request.contextPath}/Computers?orderBy=INTRODUCED"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="discontinued" /><a
							href="${pageContext.request.contextPath}/Computers?orderBy=DISCONTINUED"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Company -->
						<th><spring:message code="company" /></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${ComputerList}" var="current">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${current.id}"></td>
							<td style="display: none;"><c:out value="${current.id}" /></td>
							<td><a
								href="${pageContext.request.contextPath}/Computer/edit?id=${current.id}"
								onclick=""><c:out value="${current.name}" /></a></td>
							<td><c:out value="${current.introduced}" /></td>
							<td><c:out value="${current.discontinued}" /></td>
							<td><c:out value="${current.company.name}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<!-- <div class="container text-center"> -->
		<ul class="pagination">
			<li><a
				href="${pageContext.request.contextPath}/Computers?navigate=PREVIOUS"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/Computers?navigate=NEXT"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
		<div class="pagination" style="float: right;">
			Language : <a
				href="${pageContext.request.contextPath}/Computers?lang=en" id="englishSwitch" onclick="$.fn.englishSwitch();" ><spring:message code="english" /></a>
			| <a href="${pageContext.request.contextPath}/Computers?lang=fr" id="frenchSwitch" onclick="$.fn.frenchSwitch();"><spring:message code="french" /></a>
		</div>
	</footer>
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/dashboard.js"/>"></script>

</body>
</html>