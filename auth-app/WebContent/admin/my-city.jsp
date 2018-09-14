<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="Manage - City" />
<%@ include file="templates/my-header.jsp" %>

<div class="row">
	<div class="col">
		<div class="card">
			<div class="card-header">Manage State</div>
			<div class="card-body">
				<form method="post" action="city" id="form1">
					<select name="countryId" onchange="form1.submit()">
						<option value="">Choose</option>
						<c:forEach var="c" items="${ countries }">
							<c:set var="sel"
								value='${ c.countryId eq model.countryId ? "selected" : "" }' />
							<option ${sel} value="${ c.countryId }">${ c.countryName }</option>
						</c:forEach>
					</select> <select name="stateId" onchange="form1.submit()">
						<option value="">Choose</option>
						<c:forEach var="c" items="${ states }">
							<c:set var="sel"
								value='${ c.stateId eq model.stateId ? "selected" : "" }' />
							<option ${sel} value="${ c.stateId }">${ c.stateName }</option>
						</c:forEach>
					</select> <input type="hidden" name="cityId" value="${model.cityId}" /> <input
						type="text" name="cityName" value="${model.cityName }" />
					<button name="cmd" value="Add">Add</button>
				</form>

			</div>
		</div>

	</div>
	<div class="col">
		<div class="card">
			<div class="card-header">Cities</div>
			<div class="card-body">
				<table class="table">
					<c:forEach var="c" items="${ cities }">
						<tr>
							<td>${c.cityName }/${ c.stateName }/${ c.countryName }</td>
							<td>
								<form method="post" action="city">
									<input type="hidden" name="cityId" value="${c.cityId}" /> <input
										type="hidden" name="stateId" value="${c.stateId}" /> <input
										type="hidden" name="countryId" value="${c.countryId}" />
									<button name="cmd" value="Edit">Edit</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
</div>



<%@ include file="templates/my-footer.jsp" %>
