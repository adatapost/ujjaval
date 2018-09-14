<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<c:set var="title" value="Manage - Country" />
<%@ include file="templates/my-header.jsp" %>

<h3>State</h3>
<form method="post" action="state" id="form1">
	<select name="countryId" onchange="form1.submit()">
		<option value="">Choose</option>
		<c:forEach var="c" items="${ countries }">
			<c:set var="sel"
				value='${ c.countryId eq model.countryId ? "selected" : "" }' />
			<option ${sel} value="${ c.countryId }">${ c.countryName }</option>
		</c:forEach>
	</select> <input type="hidden" name="stateId" value="${model.stateId}" /> <input
		type="text" name="stateName" value="${model.stateName }" />
	<button name="cmd" value="Add">Add</button>
</form>

<table>
	<c:forEach var="c" items="${ states }">
		<tr>
			<td>${c.stateName }</td>
			<td>
				<form method="post" action="state">
					<input type="hidden" name="stateId" value="${c.stateId}" /> <input
						type="hidden" name="countryId" value="${c.countryId}" />
					<button name="cmd" value="Edit">Edit</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="templates/my-footer.jsp" %>
