<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"
	media="screen">
<link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/dashboard"> Application
				- Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${size} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="SEARCH"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="${pageContext.request.contextPath}/AddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
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
						<th>Computer name<a href="${pageContext.request.contextPath}/dashboard?orderBy=NAME"><span
								class="glyphicon glyphicon-chevron-up"></span> </a>
						</th>
						<th>Introduced date<a href="${pageContext.request.contextPath}/dashboard?orderBy=INTRODUCED"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date<a href="${pageContext.request.contextPath}/dashboard?orderBy=DISCONTINUED"><span
								class="glyphicon glyphicon-chevron-up"></span> </a></th>
						<!-- Table header for Company -->
						<th>Company</th>
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
								href="${pageContext.request.contextPath}/EditComputer?id=${current.id}"
								onclick=""><c:out value="${current.name}" /></a></td>
							<td><c:out value="${current.introduced}" /></td>
							<td><c:out value="${current.discontinued}" /></td>
							<td><c:out value="${current.companyId}" /></td>

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
				href="${pageContext.request.contextPath}/dashboard?navigate=PREVIOUS"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a
				href="${pageContext.request.contextPath}/dashboard?navigate=NEXT"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>

	</footer>
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/dashboard.js"/>"></script>

</body>
</html>