<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="Login" />
<%@include file="my-header.jsp"%>

<h3>Login</h3>
<h4><a href="register">New user?</a></h4>

<form method="post" action="login">
<p>Email</p>
<p><input type="email" name="userEmail" /></p>

<p>Password</p>
<p><input type="password" name="userPass" /></p>
	<button name="cmd" value="Login">Login</button>
</form>
<p>${ message }</p>
 

<%@include file="my-footer.jsp"%>
