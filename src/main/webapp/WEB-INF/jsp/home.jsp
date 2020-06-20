<%@ include file="inc/header.jsp"%>
<%@ include file="inc/nav.jsp"%>

 <div class="container">

    <div class="panel panel-primary">
        <div class="panel-heading">
            Welcome ${name}!!
        </div>
        <div class="panel-body">
            <a href="/add-city">Click here</a> to add a new
            city.
        </div>
    </div>

     	<br>
     	<div class="panel panel-primary">
     		<div class="panel-heading">
     			Current weather info
     		</div>
     		<div class="panel-body">
     			<table class="table table-striped">
     				<thead>
     					<tr>
     						<th width="27%">City</th>
     						<th width="27%">Temperature</th>
     						<th width="27%">Humidity</th>
     						<th width="20%">Actions</th>
     					</tr>
     				</thead>
     				<tbody>
                        <c:forEach items="${weatherdata}" var="main">
                            <tr>
                                <td>${main.name}</td>
                                <td>
                                    ${main.main.temp}
                                </td>
                                <td>
                                    ${main.main.humidity}
                                </td>
                                <td>
                                    <a type="button" class="btn btn-warning"
                                    href="/delete-city?id=${city.id}">Delete</a>
                                </td>
                            </tr>
     					</c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><a type="button" class="btn btn-primary btn-md" href="/add-city">Add City</a></td>
                            </tr>
     				</tbody>
     			</table>
     		</div>
     	</div>
    </div>
 </div>

  <%@ include file="inc/footer.jsp"%>

