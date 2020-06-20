<%@ include file="inc/header.jsp"%>
<%@ include file="inc/nav.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Add new city</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="city">
						<form:hidden path="id" />
						<fieldset class="form-group">
							<form:label path="name">City name</form:label>
							<form:input path="name" type="text" class="form-control"
								required="required" />
							<form:errors path="name" cssClass="text-warning" />
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>