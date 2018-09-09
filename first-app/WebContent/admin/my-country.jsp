<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty isAdmin }">
  <c:redirect url="/login"></c:redirect>
</c:if>
<c:set var="title" value="Manage - Country" />
<%@include file="my-header.jsp"%>

<h3>Country</h3>
<form method="post" action="country">
	<input type="hidden" name="countryId" value="${model.countryId}" /> <input
		type="text" name="countryName" value="${model.countryName }" />
	<button name="cmd" value="Add">Add</button>
</form>

<table>
	<c:forEach var="c" items="${ countries }">
		<tr>
			<td>${c.countryName }</td>
			<td>
				<form method="post" action="country">
					<input type="hidden" name="countryId" value="${c.countryId}" />
					<button name="cmd" value="Edit">Edit</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<%@include file="my-footer.jsp"%>
