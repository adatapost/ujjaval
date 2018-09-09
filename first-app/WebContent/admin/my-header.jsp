<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ title }</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="container">
		<header>
			<h1>Sample Java EE Web Application</h1>
		</header>
		<nav>
			<a class="btn btn-primary" href="country">Country</a> <a
				class="btn btn-primary" href="state">State</a> <a
				class="btn btn-primary" href="city">City</a>
			   	<c:choose>
			   	  <c:when test="${ isLogged }">
			   	     <a class="btn btn-link" href="login">Logout</a>
			   	  </c:when>
			   	  <c:otherwise>
			   	      <a class="btn btn-link" href="login">Login</a>
			   	  </c:otherwise>
			   	</c:choose>
			 
		</nav>
		<section>
			<script src="js/juery-3.3.1.min.js"></script>
			<script src="js/bootstrap.bundle.min.js"></script>