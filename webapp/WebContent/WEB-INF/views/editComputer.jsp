<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
				href="${pageContext.request.contextPath}/Computers"> Application
				- Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="editTitle"/></h1>

					<form:form action="${pageContext.request.contextPath}/Computer/edit" method="POST" modelAttribute="computer">
						<input type="hidden" value="0" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<spring:bind path="id">
							<div class="form-group">
								<input
									type="hidden" class="form-control" name="id" id="computerId"
									value="${computer.id}">
							</div>
							</spring:bind>
							<spring:bind path="name">
							<div class="form-group">
								<label for="computerName"><spring:message code="name"/></label> <input
									type="text" class="form-control" name="name" id="computerName"
									value="${computer.name}" placeholder="Computer name">
							</div>
							</spring:bind>
							<spring:bind path="introduced">
							<div class="form-group">
								<label for="introduced"><spring:message code="introduced"/></label> <input
									type="date" data-role="date" class="form-control" name="introduced" id="introduced"
									value="${computer.introduced}" placeholder="Introduced date">
							</div>
							</spring:bind>
							<spring:bind path="discontinued">
							<div class="form-group">
								<label for="discontinued"><spring:message code="discontinued"/></label> <input
									type="date" data-role="date" class="form-control" name="discontinued" id="discontinued"
									value="${computer.discontinued}" placeholder="Discontinued date">
							</div>
							</spring:bind>
							<spring:bind path="company">
							<div class="form-group">
								<label for="company"><spring:message code="company"/></label> <select
									class="form-control" id="company" name="company">
									<c:forEach items="${CompanyList}" var="current">
									<c:choose>
									<c:when test="${current.id == computer.company.id }">
										<option  value="${current.id}" selected>${current.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${current.id}" >${current.name}</option>
									</c:otherwise>
									</c:choose>
									</c:forEach>
								</select>
							</div>
							</spring:bind>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="edit"/>" class="btn btn-primary">
							or <a href="${pageContext.request.contextPath}/Computers?navigate=INIT"
								class="btn btn-default"><spring:message code="cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>