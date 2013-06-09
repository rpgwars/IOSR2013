<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="mainContent">
    <div class="addInstanceForm">
        <h2 class="actionHeader">Create new activity</h2>
		<form:form method="post" commandName="activity">
			<div>
				<ul>
					<li><form:input path="activityName" placeholder="Name"></form:input></li>
					<li><form:errors path="activityName"></form:errors></li>
				</ul>			
			</div>
			<div>
				<ul>
					<li><form:textarea rows="5" cols="20" placeholder="Description" path="description"></form:textarea></li>
					<li><form:errors path="description"></form:errors></li>
				</ul>			
			</div>
			<div>
				<form:select path="categoryId">
    				<form:options items="${categoryList}" itemValue="id" itemLabel="categoryName" />
				</form:select>
			</div>
			<hr>
			<input type="submit" class="btn btn-large btn-block btn-primary"  value="Add activity"/>
		</form:form>
	</div>
    <div class="contentWrap">
        <div class="instanceTableContainer">
		<h2>Activities:</h2>
		<br>
		<c:choose>
			<c:when test="${not empty activityList}">
			<table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                </tr>
                </thead>
                <tbody>
			<c:forEach items="${activityList}" var="activity">
				<tr>
	 				<td>${activity.activityName}</td>
	 				<td>${activity.description}</td>
	 				<td>${activity.activityCategory.categoryName}</td>
	 			</tr>
			</c:forEach>
                </tbody>
			</table>
			</c:when>
			<c:otherwise>
				<h1>-----------</h1>
			</c:otherwise>
		</c:choose>
	</div>
    </div>
</div>
