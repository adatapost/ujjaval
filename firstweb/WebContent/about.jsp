<%@page import="firstweb.Emp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About us</title>
</head>
<body>
 <h1>About us</h1>
 <%
  Emp emp = new Emp();
  emp.setId(101);
  emp.setName("Reena");
 %>
 <h3><%=emp.getId() %></h3>
 <h3><%=emp.getName() %></h3>
</body>
</html>