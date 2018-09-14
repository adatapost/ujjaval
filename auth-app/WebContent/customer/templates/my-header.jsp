<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ title }</title>
<base href="/auth-app/customer/">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<div class="container">
		<header>
			<h1>Customer Control Panel</h1>
		</header>
		<nav>
			<a href="index.jsp">Home</a>
			<a href="../login">Logout</a>
		</nav>
		<section>
			<script src="../js/juery-3.3.1.min.js"></script>
			<script src="../js/bootstrap.bundle.min.js"></script>