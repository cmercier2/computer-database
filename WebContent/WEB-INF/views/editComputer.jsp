<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.excilys.model.Computer"%>
<%@ page import="com.excilys.model.Company"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: 0</div>
					<h1>Edit Computer</h1>

					<form:form action="${pageContext.request.contextPath}/EditComputer" method="POST" modelAttribute="computerdto">
						<input type="hidden" value="0" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<spring:bind path="id">
							<div class="form-group">
								<input
									type="hidden" class="form-control" name="id" id="computerId"
									value="${computerdto.id}">
							</div>
							</spring:bind>
							<spring:bind path="name">
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="name" id="computerName"
									value="${computerdto.name}" placeholder="Computer name">
							</div>
							</spring:bind>
							<spring:bind path="introduced">
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" data-role="date" class="form-control" name="introduced" id="introduced"
									value="${computerdto.introduced}" placeholder="Introduced date">
							</div>
							</spring:bind>
							<spring:bind path="discontinued">
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" data-role="date" class="form-control" name="discontinued" id="discontinued"
									value="${computerdto.discontinued}" placeholder="Discontinued date">
							</div>
							</spring:bind>
							<spring:bind path="companyId">
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach items="${CompanyList}" var="current">
									<c:choose>
									<c:when test="${current.id == computerdto.id }">
										<option  value="${current.id}"selected>${current.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${current.id}">${current.name}</option>
									</c:otherwise>
									</c:choose>
									</c:forEach>
								</select>
							</div>
							</spring:bind>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="${pageContext.request.contextPath}/dashboard?navigate=INIT"
								class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>