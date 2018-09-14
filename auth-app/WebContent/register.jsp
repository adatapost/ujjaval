<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="New User" />
<%@ include file="templates/my-header.jsp" %>

<h3>New User</h3>
<form method="post" action="register">
<p>Email</p>
<p><input type="email" name="userEmail" /></p>

<p>Password</p>
<p><input type="password" name="userPass" /></p>

<p>Confirm Password</p>
<p><input type="password" name="userPass1" /></p>

	<button name="cmd" value="Add">Submit</button>
</form>
<p>${ message }</p>
 

<%@ include file="templates/my-footer.jsp" %>
